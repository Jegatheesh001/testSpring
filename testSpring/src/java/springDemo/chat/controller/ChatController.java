package springDemo.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import springDemo.chat.persistence.ChatDao;
import springDemo.chat.vo.ChatMessages;
import springDemo.chat.vo.ChatSetup;
import springDemo.chat.vo.Messages;
import springDemo.chat.vo.UserSetup;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ChatController implements ChatService {

	@Autowired
	ChatDao chatDao;

	public void setChatDao(ChatDao chatDao) {
		this.chatDao = chatDao;
	}

	@Override
	public void saveChatUser(UserSetup user) {
		chatDao.saveChatUser(user);
	}

	@Override
	public Integer checkUserAvailability(String userName) {
		return chatDao.checkUserAvailability(userName);
	}

	@Override
	public Integer loginCheck(UserSetup user) {
		return chatDao.loginCheck(user);
	}

	@Override
	public void saveForumChatMessage(ChatMessages chatMessage) {
		chatDao.saveForumChatMessage(chatMessage);
	}

	@Override
	public ChatSetup getChatDetailsById(Integer chatId) {
		return chatDao.getChatDetailsById(chatId);
	}

	@Override
	public List<ChatMessages> getAllForumChat(Integer startValue, Integer direction, Integer limit, Integer chatId) {
		return chatDao.getAllForumChat(startValue, direction, limit, chatId);
	}

	@Override
	public List<UserSetup> getAllInboxUsers(Integer startValue, Integer direction, Integer limit) {
		return chatDao.getAllInboxUsers(startValue, direction, limit);
	}

	@Override
	public List<Messages> getAllInboxChat(Integer startValue, Integer direction, Integer limit, Integer otherUserId) {
		return chatDao.getAllInboxChat(startValue, direction, limit, otherUserId);
	}

	@Override
	public Integer getLatestInboxChatUserId() {
		return chatDao.getLatestInboxChatUserId();
	}

	@Override
	public void saveInboxMessage(Messages message) {
		chatDao.saveInboxMessage(message);
	}

	@Override
	public UserSetup getUserDetails(Integer userId) {
		return chatDao.getUserDetails(userId);
	}

	@Override
	public List<UserSetup> getUserNameList(String userName) {
		return chatDao.getUserNameList(userName);
	}

}
