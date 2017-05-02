package springDemo.chat.vo;

public class UserSetup {

	public UserSetup() {

	}

	private Integer userId;
	private String userName;
	private String loginName;
	private String userPassword;
	private String activeStatus;

	private Integer messageId;// For Inbox Users

	// ------------------- Constructors -------------------------//

	public UserSetup(Integer userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}
	
	public UserSetup(Integer userId, String userName, String loginName) {
		this.userId = userId;
		this.userName = userName;
		this.loginName = loginName;
	}

	// ------------------- Getters & Setters-------------------------//

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

}
