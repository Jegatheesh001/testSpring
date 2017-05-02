package springDemo.chat.persistence;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.ActionContext;

import springDemo.chat.vo.ChatMessages;
import springDemo.chat.vo.ChatSetup;
import springDemo.chat.vo.Messages;
import springDemo.chat.vo.UserSetup;

@Repository
public class ChatHibernateDao implements ChatDao {

	@Autowired
	SessionFactory sessionFactory;

	private Map getSession() {
		return ActionContext.getContext().getSession();
	}

	private UserSetup getUserDetails() {
		return (UserSetup) getSession().get("chatUser");
	}

	@Override
	public Integer checkUserAvailability(String userName) {
		Session session = sessionFactory.openSession();
		try {
			Query q = session.createQuery("select count(*) from UserSetup where loginName like :loginName")
					.setParameter("loginName", "%" + userName + "%");
			Long count = (long) q.uniqueResult();
			return count.intValue();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void saveChatUser(UserSetup user) {
		Session session = sessionFactory.openSession();
		session.save(user);
		session.close();
	}

	@Override
	public Integer loginCheck(UserSetup user) {
		Session session = sessionFactory.openSession();
		Integer userId = null;
		try {
			Query q = session
					.createQuery(
							"select userId from UserSetup where loginName=:loginName and userPassword=:userPassword and activeStatus='Y'")
					.setParameter("loginName", user.getLoginName())
					.setParameter("userPassword", user.getUserPassword());
			userId = (Integer) q.uniqueResult();
			if (userId == null)
				userId = 0;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userId;
	}

	@Override
	public void saveForumChatMessage(ChatMessages chatMessage) {
		Session session = sessionFactory.openSession();
		session.save(chatMessage);
		session.close();
	}

	@Override
	public ChatSetup getChatDetailsById(Integer chatId) {
		ChatSetup chat = null;
		Session session = sessionFactory.openSession();
		try {
			Query q = session.createQuery("from ChatSetup where chatId=:chatId").setParameter("chatId", chatId);
			chat = (ChatSetup) q.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return chat;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChatMessages> getAllForumChat(Integer startValue, Integer direction, Integer limit, Integer chatId) {
		List<ChatMessages> chatMessageList = null;
		Session session = sessionFactory.openSession();
		StringBuilder query = new StringBuilder("from ChatMessages where chatId.chatId=:chatId and deleteStatus='N' ");
		if (direction != -1) {
			query.append("and chatMessageId>:chatMessageId ");
		} else {
			query.append("and chatMessageId<:chatMessageId ");
		}
		query.append("order by chatMessageId desc");
		try {
			Query q = session.createQuery(query.toString());
			q.setParameter("chatId", chatId);
			q.setParameter("chatMessageId", startValue);
			if (direction != 1)
				q.setMaxResults(limit);
			chatMessageList = (List<ChatMessages>) q.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return chatMessageList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserSetup> getAllInboxUsers(Integer startValue, Integer direction, Integer limit) {
		List<UserSetup> chatUserList = null;
		Session session = sessionFactory.openSession();
		UserSetup user = getUserDetails();
		String query = "";
		StringBuilder queryA = new StringBuilder(
				"SELECT a.message_Id AS messageId, a.from_User AS userId, c.user_Name AS userName, c.login_Name AS loginName FROM gc_messages a JOIN user_setup c ON a.from_User = c.user_Id WHERE a.to_User=:userId ");
		StringBuilder queryB = new StringBuilder(
				"SELECT b.message_Id AS messageId, b.to_User AS userId, d.user_Name AS userName, d.login_Name AS loginName FROM gc_messages b JOIN test.user_setup d ON b.to_User = d.user_Id WHERE b.from_User=:userId ");
		if (direction != -1) {
			queryA.append("and a.message_Id>:messageId ");
			queryB.append("and b.message_Id>:messageId ");
		} else {
			queryA.append("and a.message_Id<:messageId ");
			queryB.append("and b.message_Id<:messageId ");
		}
		query = "select messageId, userId, userName, loginName from (SELECT *, MAX(messageId) max FROM(("
				+ queryA.toString() + ") union (" + queryB.toString() + ")) as m "
				+ "group by m.userId order by max desc) as r";
		try {
			SQLQuery q = session.createSQLQuery(query.toString());
			q.setParameter("messageId", startValue);
			q.setParameter("userId", user.getUserId());
			if (direction != 1)
				q.setMaxResults(limit);
			chatUserList = (List<UserSetup>) q.setResultTransformer(new AliasToBeanResultTransformer(UserSetup.class))
					.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return chatUserList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Messages> getAllInboxChat(Integer startValue, Integer direction, Integer limit, Integer otherUserId) {
		List<Messages> inboxMessageList = null;
		Session session = sessionFactory.openSession();
		UserSetup user = getUserDetails();
		StringBuilder query = new StringBuilder(
				"from Messages m where ((m.fromUser.userId=:userId and m.toUser.userId=:otherUserId) or (m.fromUser.userId=:otherUserId and m.toUser.userId=:userId)) ");
		if (direction != -1) {
			query.append("and messageId>:messageId ");
		} else {
			query.append("and messageId<:messageId ");
		}
		query.append("order by messageId desc");
		try {
			Query q = session.createQuery(query.toString());
			q.setParameter("messageId", startValue);
			q.setParameter("userId", user.getUserId());
			q.setParameter("otherUserId", otherUserId);
			if (direction != 1)
				q.setMaxResults(limit);
			inboxMessageList = (List<Messages>) q.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return inboxMessageList;
	}

	@Override
	public Integer getLatestInboxChatUserId() {
		Integer userId = null;
		UserSetup user = getUserDetails();
		Session session = sessionFactory.openSession();
		try {
			SQLQuery q = session.createSQLQuery(
					"SELECT userId FROM ((SELECT a.from_User AS userId, a.message_Id AS messageId FROM gc_messages a WHERE a.to_User = :userId) "
							+ "union (SELECT b.to_User AS userId, b.message_Id AS messageId FROM gc_messages b WHERE b.from_user = :userId)) as m "
							+ "order by m.messageId desc limit 1");
			q.setParameter("userId", user.getUserId());
			userId = (Integer) q.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return userId;
	}

	@Override
	public void saveInboxMessage(Messages message) {
		Session session = sessionFactory.openSession();
		session.save(message);
		session.close();
	}

	@Override
	public UserSetup getUserDetails(Integer userId) {
		UserSetup user = null;
		Session session = sessionFactory.openSession();
		user = (UserSetup) session.get(UserSetup.class, userId);
		session.close();
		return user;
	}

	@Override
	public List<UserSetup> getUserNameList(String userName) {
		List<UserSetup> userList = null;
		UserSetup user = getUserDetails();
		Session session = sessionFactory.openSession();
		Query q = session.createQuery(
				"select new UserSetup(userId, userName, loginName) from UserSetup where activeStatus='Y' and userId<>:userId and userName like :userName");
		q.setParameter("userId", user.getUserId());
		q.setParameter("userName", "%" + userName + "%");
		userList = (List<UserSetup>) q.list();
		session.close();
		return userList;
	}

}
