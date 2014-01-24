package com.cnnic.whois.dao.oauth;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.oauth.User;
import com.cnnic.whois.bean.oauth.UserRowMapper;
import com.cnnic.whois.dao.base.BaseJdbcDao;

@Repository
public class UserDao extends BaseJdbcDao {

	public void save(User user) {
		String sql = "insert into oauth_users (user_name, pwd) values(?, ?)";
		Object[] param = new Object[]{user.getUser_name(), user.getPwd() };
		this.getJdbcTemplate().update(sql, param);
	}
	
	public void update(int id, User user) {
		String sql = "update oauth_users set user_name=? where id = ?";
		Object[] param = new Object[]{user.getUser_name(), id };
		this.getJdbcTemplate().update(sql, param);
	}
	
	public void delete(int id) {
		String sql = "delete from oauth_users where id = ? ";
		Object[] param = new Object[]{id };
		this.getJdbcTemplate().update(sql, param);
	}

	public User getUserById(int id) {
		String sql = "select id, user_name, pwd, user_role from oauth_users where id = ? ";
		Object[] param = new Object[] {id };
		return this.getJdbcTemplate().queryForObject(sql, param, new UserRowMapper());
		
	}

	public List<User> getUsers() {
		String sql = "select id, user_name, pwd, user_role from oauth_users ";
		return this.getJdbcTemplate().query(sql, new UserRowMapper());
	}
	
	public User findByUserIdAndPassword(String userId, String password) {
		String sql = "select id, user_name, pwd, user_role from oauth_users where user_name = ? and pwd = ? ";
		Object[] param = new Object[] {userId, password };
		try {
			return this.getJdbcTemplate().queryForObject(sql, param, new UserRowMapper());
		} catch (DataAccessException e) {
			return null;
		}
	}

}
