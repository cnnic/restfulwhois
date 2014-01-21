package com.cnnic.whois.bean.oauth;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	public User mapRow(ResultSet rs, int row) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUser_name(rs.getString("user_name"));
		user.setPwd(rs.getString("pwd"));
		user.setUser_role(rs.getString("user_role"));
		return user;
	}
}
