package springDemo.interceptors;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.ValidationAware;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class FindRequestTypeInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String GET = "GET";
	static final String POST = "POST";
	private String customType;
	private String requestType;

	HttpServletRequest request;

	public String intercept(ActionInvocation invocation) throws Exception {

		ActionContext context = invocation.getInvocationContext();
		request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		requestType = request.getMethod();
		if (!customType.equals(requestType)) {
			addActionError(invocation, "Sorry. Wrong Request.");
			return "error";
		}
		return invocation.invoke();
	}

	private void addActionError(ActionInvocation invocation, String message) {
		Object action = invocation.getAction();
		if (action instanceof ValidationAware) {
			((ValidationAware) action).addActionError(message);
		}
	}

	/**
	 * @return the customType
	 */
	public String getCustomType() {
		return customType;
	}

	/**
	 * @param customType
	 *            the customType to set
	 */
	public void setCustomType(String customType) {
		this.customType = customType;
	}

}
