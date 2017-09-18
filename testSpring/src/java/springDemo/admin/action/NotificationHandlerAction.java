package springDemo.admin.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import springDemo.admin.vo.Notification;
import springDemo.admin.vo.UserBean;
import springDemo.interceptors.SessionCheckInterceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class NotificationHandlerAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	HttpServletResponse response;
	UserBean userbean;
	private static HashMap<Integer, HashMap<String, Notification>> notifications;
	HashMap<String, Notification> userNotifications;

	public String pushNotification(String alertType, String alert) {
		Map session = ActionContext.getContext().getSession();
		userbean = (UserBean) session.get(SessionCheckInterceptor.getUserKey());
		if (notifications == null) {
			notifications = new LinkedHashMap<Integer, HashMap<String, Notification>>();
		}
		userNotifications = notifications.get(userbean.getUserid());
		if (userNotifications == null) {
			userNotifications = new LinkedHashMap<String, Notification>();
		}
		Notification notification = new Notification(generateNotificationId(), alertType, alert);
		userNotifications.put(notification.getNotificationId(), notification);
		notifications.put(userbean.getUserid(), userNotifications);
		return "success";
	}

	private String generateNotificationId() {
		return "11";
	}

	public String removeNotification(String notificationId) {
		Map session = ActionContext.getContext().getSession();
		userbean = (UserBean) session.get("userbean");
		if (notifications != null) {
			notifications.get(userbean.getUserid()).remove(notificationId);
		}
		return "";
	}

	public HashMap<Integer, HashMap<String, Notification>> getNotifications() {
		return notifications;
	}

	public HashMap<String, Notification> getUserNotifications() {
		Map session = ActionContext.getContext().getSession();
		userbean = (UserBean) session.get("userbean");
		if (notifications == null) {
			notifications = new LinkedHashMap<Integer, HashMap<String, Notification>>();
		}
		return notifications.get(userbean.getUserid());
	}

}
