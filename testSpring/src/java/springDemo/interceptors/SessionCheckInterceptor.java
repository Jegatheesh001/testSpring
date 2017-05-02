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
		ActionProxy proxy = invocation.getProxy();
		Map results = proxy.getConfig().getResults();
		Map session = invocation.getInvocationContext().getSession();
		UserBean userBean = null;
		userBean = (UserBean) session.get("userbean");
		// if(session.get(USER_KEY) == null)
		if (session.get("userbean") == null) {
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

}
