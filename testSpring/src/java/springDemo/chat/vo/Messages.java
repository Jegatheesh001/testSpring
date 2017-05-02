package springDemo.chat.vo;

import java.util.Date;

public class Messages {

	private Integer messageId;
	private UserSetup fromUser;
	private UserSetup toUser;
	private String message;
	private Date messageDate;
	private String deleteStatus;
	private String viewStatus;

	// ------------------- Getters & Setters-------------------------//

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public UserSetup getFromUser() {
		return fromUser;
	}

	public void setFromUser(UserSetup fromUser) {
		this.fromUser = fromUser;
	}

	public UserSetup getToUser() {
		return toUser;
	}

	public void setToUser(UserSetup toUser) {
		this.toUser = toUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public String getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}

}
