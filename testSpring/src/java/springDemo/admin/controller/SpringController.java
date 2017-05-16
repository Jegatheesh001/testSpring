package springDemo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import springDemo.admin.persistence.AdminDao;
import springDemo.admin.service.AdminService;
import springDemo.admin.vo.UserBean;
import springDemo.admin.vo.Users;

@Service
@Transactional
public class SpringController implements AdminService {

	@Autowired
	AdminDao adminDao;

	public UserBean authenticateUser(String username, String password) {
		return null;
	}

	public Users getUser(Integer userId) throws Exception {
		Users user = null;
		System.out.println("Transaction active :: " + TransactionSynchronizationManager.isActualTransactionActive());
		user = adminDao.getUser(userId);
		return user;
	}

	public void insertUser(Users user) {
		adminDao.insertUser(user);
	}

	public List<Users> getAllUsers() {
		return adminDao.getAllUsers();
	}

	public void checkTransaction(Users user) throws Exception {
		adminDao.insertUser(user);
		System.out.println("Transaction active :: " + TransactionSynchronizationManager.isActualTransactionActive());
		throw (new Exception("Testing Transaction"));
	}

	// ----------------- Getters & Setters ---------------------//

	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

}
