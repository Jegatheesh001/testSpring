package springDemo.interceptors;

import java.util.Locale;
import java.util.Map;

import org.apache.struts2.dispatcher.Parameter;
import org.apache.struts2.interceptor.I18nInterceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

public class CustomI18nInterceptor extends I18nInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	private static final String localeKey = "lang";
	private static final String defaultLang = "ar";

	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Parameter> params = ActionContext.getContext().getParameters();
		String locale = params.get(localeKey).getValue();
		if (locale == null) {
			locale = (String) invocation.getInvocationContext().getSession().get(localeKey);
		}
		if (locale != null) {
			invocation.getInvocationContext().setLocale(new Locale(locale));
			invocation.getInvocationContext().getSession().put(localeKey, locale);
		} else {
			invocation.getInvocationContext().setLocale(new Locale(defaultLang));
			invocation.getInvocationContext().getSession().put(localeKey, defaultLang);
		}
		return invocation.invoke();
	}

	public String getLocaleKey() {
		return localeKey;
	}
}
