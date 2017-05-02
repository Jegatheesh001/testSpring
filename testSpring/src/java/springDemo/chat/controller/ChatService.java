package springDemo.chat.controller;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import springDemo.chat.vo.ChatMessages;
import springDemo.chat.vo.ChatSetup;
import springDemo.chat.vo.Messages;
import springDemo.chat.vo.UserSetup;

@Transactional
public interface ChatService {

	Integer checkUserAvailability(String userName);

	void saveChatUser(UserSetup user);

	Integer loginCheck(UserSetup user);

	void saveForumChatMessage(ChatMessages chatMessage);

	ChatSetup getChatDetailsById(Integer chatId);

	List<ChatMessages> getAllForumChat(Integer startValue, Integer direction, Integer limit, Integer chatId);

	List<UserSetup> getAllInboxUsers(Integer startValue, Integer direction, Integer limit);

	List<Messages> getAllInboxChat(Integer startValue, Integer direction, Integer limit, Integer otherUserId);

	Integer getLatestInboxChatUserId();

	void saveInboxMessage(Messages message);

	UserSetup getUserDetails(Integer userId);

	List<UserSetup> getUserNameList(String userName);

}
