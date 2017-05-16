package springDemo.admin.persistence;

import java.util.List;

import springDemo.admin.vo.UserBean;
import springDemo.admin.vo.Users;

public interface AdminDao {

	UserBean authenticateUser(String username, String password);

	void insertUser(Users user);

	Users getUser(int userId);

	List<Users> getAllUsers();

	String removeUser(Users user) throws Exception;

}
