package com.cnnic.whois.dao.oauth;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cnnic.whois.bean.oauth.User;
import com.cnnic.whois.dao.base.BaseDao;
import com.cnnic.whois.util.JdbcUtils;

@Service("userDao")
public class UserDaoImpl extends BaseDao implements UserDao {

	public void save(User user) {
		Connection conn = JdbcUtils.getConnection();
		this.update(JdbcUtils.getConnection(), "insert into users (user_name, pwd) values(?, ?)", 
				new Object[]{user.getUser_name(), user.getPwd() }, "Save user information failed !");
		JdbcUtils.free(null, null, conn);
	}
	
	public void update(int id, User user) {
		Connection conn = JdbcUtils.getConnection();
		this.update(JdbcUtils.getConnection(), "update users set user_name=? where id = ?", 
				new Object[]{user.getUser_name(), id }, "Update user information failed !");
		JdbcUtils.free(null, null, conn);
	}
	
	public void delete(int id) {
		Connection conn = JdbcUtils.getConnection();
		this.update(conn, "delete from users where id =?", 
				new Object[]{ id}, "Delete user information failed !");
		JdbcUtils.free(null, null, conn);
	}

	public User getUserById(int id) {
		Connection conn = JdbcUtils.getConnection();
		User user = this.getObject(JdbcUtils.getConnection(), "select id, user_name, pwd, user_role from users where id = ?", 
				new Object[]{id}, "Query user information failed !", User.class);
		JdbcUtils.free(null, null, conn);
		return user;
	}

	public List<User> getUsers() {
		Connection conn = JdbcUtils.getConnection();
		List<User> users = this.getAllObject(JdbcUtils.getConnection(), "select id, user_name, pwd, user_role from users", null, 
				"Query user information list failed !", User.class);
		JdbcUtils.free(null, null, conn);
		return users;
	}
	
	public User findByUserIdAndPassword(String userId, String password) {
		Connection conn = JdbcUtils.getConnection();
		User user = this.getObject(conn, "select id, user_name, pwd, user_role from users where user_name = ? and pwd = ? ", 
				new Object[]{userId, password }, "Query user information failed !", User.class);
		JdbcUtils.free(null, null, conn);
		return user;
	}
	

}
