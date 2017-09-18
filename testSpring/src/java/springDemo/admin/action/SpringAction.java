package springDemo.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;

import springDemo.actionDispatcherFilter.HibernateUtil;
import springDemo.admin.controller.SpringController;
import springDemo.admin.service.AdminService;
import springDemo.admin.vo.UserBean;
import springDemo.admin.vo.Users;

import com.opensymphony.xwork2.ActionSupport;

@Controller
public class SpringAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = 1L;

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

	private UserBean userBean;

	@Autowired
	AdminService controllerService;

	Users user;
	List<Users> userList;

	@SuppressWarnings("resource")
	public String execute() {
		ApplicationContext beans = new ClassPathXmlApplicationContext(
				HibernateUtil.BEAN_PATH + "applicationContext.xml");
		// BeanFactory beans = new XmlBeanFactory(new
		// FileSystemResource(HibernateUtil.BEAN_PATH + "Spring-Beans.xml"));
		userBean = (UserBean) beans.getBean("helloWorld");
		System.out.println(userBean.getUserName());
		return "success";
	}

	public String insertUser() throws Exception {
		user = new Users();
		// user.setUserId(2);
		user.setUserName("Jegatheesh");
		user.setUserCode("Jegatheesh");
		user.setPassword("pass");
		controllerService.insertUser(user);
		user = controllerService.getUser(user.getUserId());
		user.setUserId(null);
		controllerService.checkTransaction(user);
		userList = controllerService.getAllUsers();
		return "success";
	}

	// --------------------- Getters & Setters ----------------------//

	/**
	 * @return the user
	 */
	public Users getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(Users user) {
		this.user = user;
	}

	/**
	 * @return the userList
	 */
	public List<Users> getUserList() {
		return userList;
	}

	/**
	 * @param userList
	 *            the userList to set
	 */
	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

}
