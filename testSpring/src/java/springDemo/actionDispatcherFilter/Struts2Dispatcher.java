package springDemo.actionDispatcherFilter;

import javax.servlet.*;
import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;
import org.hibernate.HibernateException;

public class Struts2Dispatcher extends StrutsPrepareAndExecuteFilter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
		try {
			HibernateUtil.createSessionFactory();
		} catch (HibernateException e) {
			throw new ServletException(e);
		}

	}

}
