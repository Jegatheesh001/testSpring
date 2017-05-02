package springDemo.chat.vo;

import java.util.Date;

public class ChatMessages {

	private Integer chatMessageId;
	private ChatSetup chatId;
	private UserSetup fromUser;
	private String chatMessage;
	private Date messageDate;
	private String deleteStatus;

	public Integer getChatMessageId() {
		return chatMessageId;
	}

	public void setChatMessageId(Integer chatMessageId) {
		this.chatMessageId = chatMessageId;
	}

	public String getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(String chatMessage) {
		this.chatMessage = chatMessage;
	}

	public Date getMessageDate() {
		return messageDate;
	}

	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public ChatSetup getChatId() {
		return chatId;
	}

	public void setChatId(ChatSetup chatId) {
		this.chatId = chatId;
	}

	public UserSetup getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserSetup fromUser) {
		this.fromUser = fromUser;
	}

}
