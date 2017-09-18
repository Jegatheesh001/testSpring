package springDemo.admin.action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import springDemo.actionDispatcherFilter.HibernateUtil;
import springDemo.admin.persistence.AdminDao;
import springDemo.admin.persistence.AdminHibernateDao;
import springDemo.admin.vo.UserBean;
import springDemo.interceptors.AESAlgorithm;
import springDemo.interceptors.CustomI18nInterceptor;
import springDemo.interceptors.SessionCheckInterceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.ws.transport.http.HttpAdapterList;

public class LoginAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

	AdminDao adminDao = new AdminHibernateDao();
	HttpServletRequest request;
	HttpServletResponse response;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	UserBean userbean;

	public String showLoginPage() {
		return "success";
	}

	public String loginAction() {
		Map session = ActionContext.getContext().getSession();
		userbean = authenticateUser("admin", "pass");
		setCookies();
		userbean = new UserBean();
		userbean.setUserid(1);
		userbean.setUserName("Admin");
		session.put(SessionCheckInterceptor.getUserKey(), userbean);
		new NotificationHandlerAction().pushNotification("User Entered", userbean.getUserName()+" Entered");
		return "success";
	}

	void setCookies() {
		// For 24 Hrs
		Integer expiry = 60 * 60 * 24;

		// Cookies
		Cookie userName = new Cookie("userName-" + request.getParameter("userName"), request.getParameter("userName"));
		// Cookie password = new Cookie("password",
		// AESAlgorithm.encrypt(request.getParameter("password")));

		// Set expiry date for both the cookies.
		userName.setMaxAge(expiry);
		// password.setMaxAge(expiry);

		// Set Cookies to Response
		response.addCookie(userName);
		// response.addCookie(password);
	}

	public UserBean authenticateUser(String username, String password) {
		userbean = adminDao.authenticateUser(username, password);
		return userbean;
	}

	public String logout() {
		Map session = ActionContext.getContext().getSession();
		session.remove(SessionCheckInterceptor.getUserKey());
		session.remove(CustomI18nInterceptor.getLocaleKey());
		// session.clear();
		return "success";
	}
}
