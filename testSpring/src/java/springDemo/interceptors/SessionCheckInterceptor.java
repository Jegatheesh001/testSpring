package springDemo.interceptors;

import java.util.Map;
import springDemo.admin.vo.UserBean;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionCheckInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String USER_KEY = "userbean";

	public String intercept(ActionInvocation invocation) throws Exception {
		Map session = invocation.getInvocationContext().getSession();
		UserBean userBean = (UserBean) session.get(USER_KEY);
		if (userBean == null) {
			addActionError(invocation, "Your session has expired.");
			return "invalidsession";
		}
		return invocation.invoke();
	}

	private void addActionError(ActionInvocation invocation, String message) {
		Object action = invocation.getAction();
		if (action instanceof ValidationAware) {
			((ValidationAware) action).addActionError(message);
		}
	}

	public static String getUserKey() {
		return USER_KEY;
	}

}
