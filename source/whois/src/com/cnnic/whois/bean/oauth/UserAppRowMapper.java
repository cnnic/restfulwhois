package com.cnnic.whois.bean.oauth;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserAppRowMapper implements RowMapper<UserApp> {

	public UserApp mapRow(ResultSet rs, int row) throws SQLException {
		UserApp userApp = new UserApp();
		userApp.setId(rs.getInt("id"));
		userApp.setApp_key(rs.getString("app_key"));
		userApp.setApp_secret(rs.getString("app_secret"));
		userApp.setApp_description(rs.getString("app_description"));
		userApp.setUser_id(rs.getInt("user_id"));
		return userApp;
	}

}
