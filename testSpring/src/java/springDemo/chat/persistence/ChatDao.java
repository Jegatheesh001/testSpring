package springDemo.chat.persistence;

import java.util.List;

import springDemo.chat.vo.ChatMessages;
import springDemo.chat.vo.ChatSetup;
import springDemo.chat.vo.Messages;
import springDemo.chat.vo.UserSetup;

public interface ChatDao {

	void saveChatUser(UserSetup user);

	Integer checkUserAvailability(String userName);

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
