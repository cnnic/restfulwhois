package com.cnnic.whois.dao.oauth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.oauth.UserApp;
import com.cnnic.whois.bean.oauth.UserAppRowMapper;
import com.cnnic.whois.dao.base.BaseJdbcDao;

@Repository
public class UserAppDao extends BaseJdbcDao {

	public void save(UserApp userApp) {
//		TODO : this part need modification, default invalid_time setting 7 days
		userApp.setApp_key("key" +System.currentTimeMillis());
		userApp.setApp_secret("secret" +System.currentTimeMillis());
		userApp.setInvalid_time(7);

		String sql = "insert into oauth_users_app (app_key, app_secret, app_description, user_id, invalid_time) values(?, ?, ?, ?, ?)";
		Object[] param = new Object[]{userApp.getApp_key(), userApp.getApp_secret(), userApp.getApp_description(), userApp.getUser_id(), userApp.getInvalid_time() };
		this.getJdbcTemplate().update(sql, param);
	}
	
	public void update(int id, UserApp userApp) {
		String sql = "update oauth_users set app_description=? where id = ?";
		Object[] param = new Object[]{userApp.getApp_description(), id };
		this.getJdbcTemplate().update(sql, param);
	}
	
	public void delete(int id) {
		String sql = "delete from oauth_users_app where id =?";
		Object[] param = new Object[]{id };
		this.getJdbcTemplate().update(sql, param);
	}

	public UserApp getUserAppById(int user_id) {
		String sql = "select id, app_key, app_secret, app_description, user_id from oauth_users_app where id = ? ";
		Object[] param = new Object[] {user_id };
		return this.getJdbcTemplate().queryForObject(sql, param, new UserAppRowMapper());
	}
	
	public UserApp getUserAppByUserId(int user_id) {
		String sql = "select id, app_key, app_secret, app_description, user_id from oauth_users_app where user_id = ? ";
		Object[] param = new Object[] {user_id };
		return this.getJdbcTemplate().queryForObject(sql, param, new UserAppRowMapper());
	}

	public List<UserApp> getUserApps(int user_id) {
		String sql = "select id, app_key, app_secret, app_description, user_id from oauth_users_app where user_id = ? ";
		Object[] param = new Object[] {user_id };
		return this.getJdbcTemplate().query(sql, param, new UserAppRowMapper());
	}

	public Map<String, String> getUserApps() {
		String sql = "select id, app_key, app_secret, app_description, user_id from oauth_users_app ";
		List<UserApp> userApps = this.getJdbcTemplate().query(sql, new UserAppRowMapper());
		Map<String, String> map = new HashMap<String, String>();
		for(UserApp uApp : userApps){
			uApp.getApp_description();
			map.put(uApp.getApp_key(), uApp.getApp_secret());
			map.put(uApp.getApp_key()+".description", uApp.getApp_description());
		}
		return map;
	}

}
