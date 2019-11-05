package Tally.Controller;

import Tally.Dao.UserDao;

public class UserController {

	private UserDao userDao = new UserDao();

	/**
	 * 检测用户名密码是否可以登入
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean verify(String username, String password) {
		if (userDao.getUserFromUserNameAndPassword(username, password) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 用户登入
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username, String password) {
		if (verify(username, password)) {
			System.out.print("登入成功！");
			return true;
		} else {
			System.out.print("登入失败！");
			return false;
		}
	}

	/**
	 * 注册
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean register(String username, String password) {
		//TODO 判断用户名和密码是否合法，不合法直接false
		if (userDao.addUser(username, password)) {
			return true;
		} else {
			System.out.print("注册失败！");
			return false;
		}
	}
}
