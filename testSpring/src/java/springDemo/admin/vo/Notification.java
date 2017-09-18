package springDemo.admin.vo;

import java.io.Serializable;

public class Notification implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String notificationId;
	private String alertType;
	private String alert;
	private String viewStatus = "N";

	public Notification() {

	}

	public Notification(String notificationId, String alertType, String alert) {
		this.notificationId = notificationId;
		this.alertType = alertType;
		this.alert = alert;
	}

	public String getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}

	public String getAlertType() {
		return alertType;
	}

	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}

}
