package springDemo.chat.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import springDemo.admin.vo.UserBean;
import springDemo.chat.controller.ChatService;
import springDemo.chat.vo.ChatMessages;
import springDemo.chat.vo.ChatSetup;
import springDemo.chat.vo.Messages;
import springDemo.chat.vo.UserSetup;
import springDemo.interceptors.AESAlgorithm;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class GlobalChatAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;
	Map session;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	private UserBean userBean;

	@Autowired
	ChatService chatController;

	UserSetup user;

	ChatSetup chat;
	ChatMessages chatMessage;
	Messages message;
	List<ChatMessages> chatMessageList;
	List<Messages> messageList;
	List<UserSetup> userList;
	Integer startValue;
	Integer direction;
	Integer limit;

	Integer inboxUserId;

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	private void getSession() {
		session = ActionContext.getContext().getSession();
	}

	private void getUserDetails() {
		getSession();
		user = (UserSetup) session.get("chatUser");
	}

	public String chatBase() {
		getUserDetails();
		return SUCCESS;
	}

	public String login() {
		return SUCCESS;
	}

	public String register() {
		return SUCCESS;
	}

	public void checkUserAvailability() throws IOException {
		JsonObject result = new JsonObject();
		Integer present = chatController.checkUserAvailability(user.getLoginName());
		result.addProperty("response", present);
		response.setContentType("application/json");
		response.getWriter().print(result);
	}

	public String registerUser() {
		getSession();
		user.setActiveStatus("Y");
		user.setUserName(user.getLoginName());
		user.setUserPassword(AESAlgorithm.encrypt(user.getUserPassword()));
		chatController.saveChatUser(user);
		session.put("chatUser", user);
		return SUCCESS;
	}

	public void loginCheck() throws IOException {
		getSession();
		JsonObject result = new JsonObject();
		user.setUserPassword(AESAlgorithm.encrypt(user.getUserPassword()));
		Integer userId = chatController.loginCheck(user);
		result.addProperty("response", userId);
		if (userId > 0) {
			user = chatController.getUserDetails(userId);
			session.put("chatUser", user);
			setClientTimeZone();
		}
		response.setContentType("application/json");
		response.getWriter().print(result);
	}

	public void setClientTimeZone() {
		String timeZone = request.getParameter("timeZone");
		session.put("timeZone", timeZone);
	}

	public String loginUser() {
		return SUCCESS;
	}

	public String chatLogout() {
		getSession();
		session.remove("chatUser");
		return SUCCESS;
	}

	public String openInbox() {
		return SUCCESS;
	}

	public String globalChat() {
		return SUCCESS;
	}

	public String privateChat() {
		getUserDetails();
		// Integer access = checkChatAccess(user.getUserId());
		return SUCCESS;
	}

	public String openChat() {
		chat = chatController.getChatDetailsById(chat.getChatId());
		return SUCCESS;
	}

	public void saveForumChat() throws IOException {
		getUserDetails();
		chatMessage.setChatId(new ChatSetup());
		chatMessage.getChatId().setChatId(chat.getChatId());
		chatMessage.setFromUser(new UserSetup());
		chatMessage.getFromUser().setUserId(user.getUserId());
		chatMessage.setMessageDate(new Date());
		chatMessage.setDeleteStatus("N");
		chatController.saveForumChatMessage(chatMessage);
		JsonObject result = new JsonObject();
		result.addProperty("response", SUCCESS);
		response.setContentType("application/json");
		response.getWriter().print(result);
	}

	public void getAllForumChat() throws IOException {
		getUserDetails();
		if (startValue == null) {
			startValue = 0;
		}
		if (direction == null) {
			direction = 0;
		}
		limit = 10;
		chatMessageList = chatController.getAllForumChat(startValue, direction, limit, chat.getChatId());
		processForumChat();
	}

	public void processForumChat() throws IOException {
		StringBuilder output = new StringBuilder("");
		String className = "me";
		int i = 1;
		for (ChatMessages chatMessages : chatMessageList) {
			if (chatMessages.getFromUser().getUserId() == user.getUserId())
				className = "me";
			else
				className = "other";
			output.append("<div class='" + className + "'>");
			output.append(chatMessages.getFromUser().getUserName() + " : " + chatMessages.getChatMessage() + "<br/>");
			output.append("<div class='date'>");
			output.append(convertToDateString("dd-MM-yyyy hh:mm", chatMessages.getMessageDate()));
			output.append("</div>");
			output.append("</div>");
			if ((i == 1) && (direction == 0 || direction == 1)) {
				output.append("<input type='hidden' id='maxId' value='" + chatMessages.getChatMessageId() + "' />");
			}
			if ((i == chatMessageList.size()) && (direction == 0 || direction == -1)) {
				output.append("<input type='hidden' id='minId' value='" + chatMessages.getChatMessageId() + "' />");
			}
			i++;
		}
		response.setContentType("text/html");
		response.getWriter().print(output.toString());
	}

	public void deleteForumChat() {
	}

	public void getAllInboxUsers() throws IOException {
		if (startValue == null) {
			startValue = 0;
		}
		if (direction == null) {
			direction = 0;
		}
		limit = 10;
		userList = chatController.getAllInboxUsers(startValue, direction, limit);
		processInboxUsers();
	}

	public void processInboxUsers() throws IOException {
		StringBuilder output = new StringBuilder("");
		int i = 0;
		String className = "";
		for (UserSetup user : userList) {
			if (i % 2 == 0)
				className = "even";
			else
				className = "odd";
			output.append("<div title='" + user.getLoginName() + "' class='" + className + "' onclick='openInboxChat("
					+ user.getUserId() + ");'>");
			output.append(user.getUserName() + "");
			output.append("</div>");
			if ((i == 1) && (direction == 0 || direction == 1)) {
				output.append("<input type='hidden' id='maxUserId' value='" + user.getMessageId() + "' />");
			}
			if ((i == userList.size()) && (direction == 0 || direction == -1)) {
				output.append("<input type='hidden' id='minUserId' value='" + user.getMessageId() + "' />");
			}
			i++;
		}
		response.setContentType("text/html");
		response.getWriter().print(output.toString());
	}

	public void saveInboxMessage() throws IOException {
		getUserDetails();
		message.setFromUser(new UserSetup());
		message.getFromUser().setUserId(user.getUserId());
		message.setToUser(new UserSetup());
		message.getToUser().setUserId(inboxUserId);
		message.setMessageDate(new Date());
		message.setDeleteStatus("N");
		message.setViewStatus("u");// Unseen
		chatController.saveInboxMessage(message);
		JsonObject result = new JsonObject();
		result.addProperty("response", SUCCESS);
		response.setContentType("application/json");
		response.getWriter().print(result);
	}

	public String newInboxChat() {
		return SUCCESS;
	}

	public void getInboxChat() throws IOException {
		getUserDetails();
		if (startValue == null) {
			startValue = 0;
		}
		if (direction == null) {
			direction = 0;
		}
		limit = 10;
		if (inboxUserId == null) {
			inboxUserId = chatController.getLatestInboxChatUserId();
		}
		messageList = chatController.getAllInboxChat(startValue, direction, limit, inboxUserId);
		processInboxChat();
	}

	public void processInboxChat() throws IOException {
		StringBuilder output = new StringBuilder("");
		String className = "me";

		int size = messageList.size();
		// For New Users
		if (inboxUserId != null && direction == 0)
			output.append("<input type='hidden' id='inboxUserId' value='" + inboxUserId + "' />");
		Messages message = null;
		if (size > 0) {
			for (int i = (size - 1); i >= 0; i--) {
				message = messageList.get(i);
				if (message.getFromUser().getUserId() == user.getUserId())
					className = "me";
				else
					className = "other";
				output.append("<div class='" + className + "'>");
				output.append(message.getFromUser().getUserName() + " : " + message.getMessage() + "<br/>");
				output.append("<div class='date'>");
				output.append(convertToDateString("dd-MM-yyyy hh:mm", message.getMessageDate()));
				output.append("</div>");
				output.append("</div>");

				if ((i == (size - 1)) && (direction == 0 || direction == -1)) {
					output.append("<input type='hidden' id='minId' value='" + message.getMessageId() + "' />");
				}
				if ((i == 0) && (direction == 0 || direction == 1)) {
					output.append("<input type='hidden' id='maxId' value='" + message.getMessageId() + "' />");
				}
			}
		} else {
			if (direction == 1) {
				output.append("<input type='hidden' id='maxId' value='" + startValue + "' />");
			}
		}
		response.setContentType("text/html");
		response.getWriter().print(output.toString());
	}

	public void getUserName() throws IOException {
		UserSetup userDetails = chatController.getUserDetails(inboxUserId);
		response.setContentType("text/html");
		response.getWriter().print(userDetails.getUserName());
	}

	public void getUserNameList() throws IOException {
		String userName = request.getParameter("userName");
		if (userName == null)
			userName = "";
		userList = chatController.getUserNameList(userName);
		response.setContentType("application/json");
		response.getWriter().print(new Gson().toJson(userList));
	}

	// --------------------- Common Functions ----------------------//

	Date convertToDate(String format, String dateString) {
		Date output = null;
		sdf = new SimpleDateFormat(format);
		try {
			output = sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return output;
	}

	String convertToDateString(String format, Date date) {
		String output = null;
		sdf = new SimpleDateFormat(format);
		try {
			output = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	// --------------------- Getters & Setters ----------------------//

	public UserSetup getUser() {
		return user;
	}

	public void setUser(UserSetup user) {
		this.user = user;
	}

	public ChatSetup getChat() {
		return chat;
	}

	public void setChat(ChatSetup chat) {
		this.chat = chat;
	}

	public ChatMessages getChatMessage() {
		return chatMessage;
	}

	public void setChatMessage(ChatMessages chatMessage) {
		this.chatMessage = chatMessage;
	}

	public Integer getStartValue() {
		return startValue;
	}

	public void setStartValue(Integer startValue) {
		this.startValue = startValue;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Messages getMessage() {
		return message;
	}

	public void setMessage(Messages message) {
		this.message = message;
	}

	public Integer getInboxUserId() {
		return inboxUserId;
	}

	public void setInboxUserId(Integer inboxUserId) {
		this.inboxUserId = inboxUserId;
	}

}
