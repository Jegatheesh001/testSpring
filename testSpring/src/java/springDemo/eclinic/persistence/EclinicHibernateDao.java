package springDemo.eclinic.persistence;

import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.orm.hibernate3.HibernateTemplate;

import springDemo.actionDispatcherFilter.HibernateUtil;
import springDemo.admin.vo.UserBean;
import springDemo.admin.vo.Users;
import springDemo.eclinic.vo.LabtestInsurarNet;
import springDemo.eclinic.vo.ProcedureInsurerNetwork;
import springDemo.eclinic.vo.ProcedureSetup;
import springDemo.eclinic.vo.TestSetup;

public class EclinicHibernateDao implements EclinicDao {

	@Override
	public List<String> getClumnListFromTable(String tableName) {
		List<Map<String, Object>> rs = null;
		String dbName = "eclinic_rami_new";

		Session session = HibernateUtil.getSession();
		SQLQuery q = null;

		// q = session.createSQLQuery("SHOW COLUMNS FROM :tableName FROM
		// :dbName");
		// q = session.createSQLQuery("SHOW COLUMNS FROM procedure_setup FROM
		// eclinic_rami_new");

		String queryStr = "SELECT column_name FROM information_schema.COLUMNS WHERE TABLE_SCHEMA=DATABASE() AND TABLE_NAME= "
				+ tableName;
		rs = session.createSQLQuery(queryStr).list();

		// q.setParameter("tableName", tableName);
		// q.setParameter("dbName", dbName);
		// rs = q.list();
		return null;
	}

	@Override
	public List<ProcedureSetup> getProcedureListByLimit(Integer limit) {
		List<ProcedureSetup> psList = null;
		List<Object[]> rs = null;
		Session session = HibernateUtil.getSession();
		Query q = null;
		q = session.createQuery("from ProcedureSetup as ps");
		if (limit != null)
			q.setMaxResults(limit);
		psList = q.list();
		return psList;
	}

	@Override
	public List<TestSetup> getLabTestListByLimit(Integer limit) {
		List<TestSetup> tsList = null;
		List<Object[]> rs = null;
		Session session = HibernateUtil.getSession();
		Query q = null;
		q = session.createQuery("from TestSetup as ts");
		if (limit != null)
			q.setMaxResults(limit);
		tsList = q.list();
		return tsList;
	}

	@Override
	public TestSetup getLabTestByIcd(String icdCode) {
		TestSetup ts = null;
		Session session = HibernateUtil.getSession();
		Query q = null;
		q = session.createQuery("from TestSetup as ts where ts.icdCode=:icdCode");
		q.setParameter("icdCode", icdCode);
		q.setMaxResults(1);
		ts = (TestSetup) q.uniqueResult();
		session.close();
		return ts;
	}

	@Override
	public ProcedureSetup getProcedureByIcd(String icdCode) {
		ProcedureSetup ps = null;
		Session session = HibernateUtil.getSession();
		Query q = null;
		q = session.createQuery("from ProcedureSetup as ts where ts.icdCode=:icdCode");
		q.setParameter("icdCode", icdCode);
		q.setMaxResults(1);
		ps = (ProcedureSetup) q.uniqueResult();
		session.close();
		return ps;
	}
	
	@Override
	public Integer getLabTestCountByIcd(String icdCode) {
		Long count = 0l;
		Session session = HibernateUtil.getSession();
		Query q = null;
		q = session.createQuery("select count(*) from TestSetup as ts where ts.icdCode=:icdCode");
		q.setParameter("icdCode", icdCode);
		q.setMaxResults(1);
		count = (Long) q.uniqueResult();
		session.close();
		return count.intValue();
	}

	@Override
	public Integer getProcedureCountByIcd(String icdCode) {
		Long count = 0l;
		Session session = HibernateUtil.getSession();
		Query q = null;
		q = session.createQuery("select count(*) from ProcedureSetup as ps where ps.icdCode=:icdCode");
		q.setParameter("icdCode", icdCode);
		q.setMaxResults(1);
		count = (Long) q.uniqueResult();
		session.close();
		return count.intValue();
	}
	
	@Override
	public Integer getTestIdByIcd(String icdCode) {
		Integer procedureId;
		Session session = HibernateUtil.getSession();
		Query q = null;
		q = session.createQuery("select testId from TestSetup as ts where ts.icdCode=:icdCode");
		q.setParameter("icdCode", icdCode);
		q.setMaxResults(1);
		procedureId = (Integer) q.uniqueResult();
		session.close();
		return procedureId;
	}
	
	@Override
	public Integer getProcedureIdByIcd(String icdCode) {
		Integer procedureId;
		Session session = HibernateUtil.getSession();
		Query q = null;
		q = session.createQuery("select procedureId from ProcedureSetup as ps where ps.icdCode=:icdCode");
		q.setParameter("icdCode", icdCode);
		q.setMaxResults(1);
		procedureId = (Integer) q.uniqueResult();
		session.close();
		return procedureId;
	}
	
	@Override
	public void insertTestsetup(TestSetup ts) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.save(ts);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void insertProcedureSetup(ProcedureSetup ps) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.save(ps);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public LabtestInsurarNet getLabTestNetPriceByTestId(Integer testId) {
		LabtestInsurarNet labInsNet = null;
		Session session = HibernateUtil.getSession();
		Query q = null;
		q = session.createQuery("from LabtestInsurarNet as pin where pin.testId=:testId order by labtestInsurarNetId desc");
		q.setParameter("testId", testId);
		q.setMaxResults(1);
		labInsNet = (LabtestInsurarNet) q.uniqueResult();
		session.close();
		return labInsNet;
	}

	@Override
	public ProcedureInsurerNetwork getProcedureNetPriceByTestId(Integer procedureId) {
		ProcedureInsurerNetwork proInsNet = null;
		Session session = HibernateUtil.getSession();
		Query q = null;
		q = session.createQuery("from ProcedureInsurerNetwork as pin where pin.procedureId=:procedureId order by procedureInsurerNetworkid desc");
		q.setParameter("procedureId", procedureId);
		q.setMaxResults(1);
		proInsNet = (ProcedureInsurerNetwork) q.uniqueResult();
		session.close();
		return proInsNet;
	}

	@Override
	public void insertLabtestInsurarNet(LabtestInsurarNet labInsNet) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.saveOrUpdate(labInsNet);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void insertProcedureInsurerNetwork(ProcedureInsurerNetwork proInsNet) {
		Session session = null;
		try {
			session = HibernateUtil.getSession();
			session.saveOrUpdate(proInsNet);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

}
