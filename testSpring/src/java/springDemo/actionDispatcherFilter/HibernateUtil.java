package springDemo.actionDispatcherFilter;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Session;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class HibernateUtil {

	public static final String BEAN_PATH = "webapps/testSpring/WEB-INF/";

	private static SessionFactory sessionFactory;

	public static void createSessionFactory() {
		// create the SessionFactory from hibernate.cfg.xml
		// sessionFactory = new
		// Configuration().configure().buildSessionFactory();

		// Spring
		@SuppressWarnings("deprecation")
		BeanFactory beans = new XmlBeanFactory(new FileSystemResource(BEAN_PATH + "applicationContext.xml"));
		MyTransactionManager dao = (MyTransactionManager) beans.getBean("dao");

		// create the SessionFactory from Spring
		sessionFactory = dao.sessionFactory;

	}

	public static Session getSession() {
		return sessionFactory.openSession();
	}

}