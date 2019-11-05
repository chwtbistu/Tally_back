package Tally.Dao;

import java.util.ArrayList;

import Tally.Model.User;

public class UserDao {
	/**
	 * 通过用户名以及密码检索数据库
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public ArrayList<User> getUserFromUserNameAndPassword(String username, String password) {
		ArrayList<User> users = new ArrayList<>();
		// TODO 通过select检索数据库
		return users;
	}

	public boolean addUser(String username, String password) {
		// TODO 通过insert into插入数据库
		return true;
	}

}
