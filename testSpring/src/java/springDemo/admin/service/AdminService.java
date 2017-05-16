package springDemo.admin.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import springDemo.admin.vo.UserBean;
import springDemo.admin.vo.Users;

public interface AdminService {

	UserBean authenticateUser(String username, String password);

	void insertUser(Users user);

	Users getUser(Integer userId) throws Exception;

	List<Users> getAllUsers();

	void checkTransaction(Users user) throws Exception;

}
