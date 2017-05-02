package springDemo.actionDispatcherFilter;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class MyTransactionManager {

	public HibernateTemplate template;
	public SessionFactory sessionFactory;

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
