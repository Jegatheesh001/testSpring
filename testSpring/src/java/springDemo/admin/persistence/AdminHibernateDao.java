package springDemo.admin.persistence;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import springDemo.actionDispatcherFilter.HibernateUtil;
import springDemo.admin.vo.UserBean;
import springDemo.admin.vo.Users;

@Repository
public class AdminHibernateDao implements AdminDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public UserBean authenticateUser(String username, String password) {
		Session session = HibernateUtil.getSession();
		try {
			UserBean tmpcategory = null;
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(
					"select alloted.allot_Id as allotid,seat.hierarchy_Id as seatId,user.user_Id as userId,"
							+ "seat.hierarchy_Name as seatName,seat.hierarchy_Code as seatCode,user.user_Name as empName,user.user_Code as empCode "
							+ "from aimsmaster.seat_user_alloted as alloted "
							+ "join aimsmaster.hierarchy_setup as seat on seat.hierarchy_Id=alloted.seat_Id "
							+ "join aimsmaster.user_setup as user on alloted.user_Id=user.user_Id "
							+ "where user.active_Status='Y' and seat.active_Status='Y' and alloted.active_Status='Y' and alloted.passwd=:password and seat.hierarchy_Code=:username");
			/*
			 * query.addScalar("allotid", Hibernate.STRING) .addScalar("seatId",
			 * Hibernate.STRING) .addScalar("userId", Hibernate.STRING)
			 * .addScalar("seatName", Hibernate.STRING) .addScalar("seatCode",
			 * Hibernate.STRING) .addScalar("empName", Hibernate.STRING)
			 * .addScalar("empCode", Hibernate.STRING);
			 */
			query.setParameter("password", password);
			query.setParameter("username", username);
			query.setResultTransformer(Transformers.aliasToBean(UserBean.class));
			tmpcategory = (UserBean) query.uniqueResult();
			return tmpcategory;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public void insertUser(Users user) {
		// Session session = HibernateUtil.getSession();
		Session session = sessionFactory.openSession();
		session.save(user);
	}

	public Users getUser(int userId) {
		Users user = null;
		Session session = HibernateUtil.getSession();
		user = (Users) session.get(Users.class, userId);
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<Users> getAllUsers() {
		List<Users> users = null;
		Session session = HibernateUtil.getSession();
		users = (List<Users>) session.createQuery("from Users").setMaxResults(10).list();
		return users;
	}

	public String removeUser(Users user) throws Exception {
		Session session = HibernateUtil.getSession();
		session.createQuery("delete from Users where userId=:userId").setParameter("userId", user.getUserId())
				.executeUpdate();
		return "Success";
	}

}
