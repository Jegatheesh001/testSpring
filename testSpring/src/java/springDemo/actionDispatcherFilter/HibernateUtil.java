package springDemo.actionDispatcherFilter;

import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HibernateUtil {

	public static final String BEAN_PATH = "";

	private static SessionFactory sessionFactory;

	@SuppressWarnings("resource")
	public static void createSessionFactory() {
		// create the SessionFactory from hibernate.cfg.xml
		// sessionFactory = new
		// Configuration().configure().buildSessionFactory();

		// Spring
		ApplicationContext beans = new ClassPathXmlApplicationContext(BEAN_PATH + "applicationContext.xml");
		MyTransactionManager dao = (MyTransactionManager) beans.getBean("dao");

		// create the SessionFactory from Spring
		sessionFactory = dao.sessionFactory;

	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

}