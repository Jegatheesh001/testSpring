package springDemo.chat.vo;

import java.util.Date;

public class ChatSetup {

	private Integer chatId;
	private String chatName;
	private String type;
	private String chatType;
	private UserSetup createdBy;
	private Date createdOn;

	public Integer getChatId() {
		return chatId;
	}

	public void setChatId(Integer chatId) {
		this.chatId = chatId;
	}

	public String getChatName() {
		return chatName;
	}

	public void setChatName(String chatName) {
		this.chatName = chatName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChatType() {
		return chatType;
	}

	public void setChatType(String chatType) {
		this.chatType = chatType;
	}

	public UserSetup getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserSetup createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
