package com.cnnic.whois.dao.oauth;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cnnic.whois.bean.oauth.UserApp;
import com.cnnic.whois.dao.base.BaseDao;
import com.cnnic.whois.util.JdbcUtils;

@Service("userAppDao")
public class UserAppDaoImpl extends BaseDao implements UserAppDao {

	public void save(UserApp userApp) {
		Connection conn = JdbcUtils.getConnection();
		
//		TODO : this part need modification, default invalid_time setting 7 days
		userApp.setApp_key("key" +System.currentTimeMillis());
		userApp.setApp_secret("secret" +System.currentTimeMillis());
		userApp.setInvalid_time(7);
		
		this.update(JdbcUtils.getConnection(), "insert into users_app (app_key, app_secret, app_description, user_id, invalid_time) values(?, ?, ?, ?, ?)", 
				new Object[]{userApp.getApp_key(), userApp.getApp_secret(), userApp.getApp_description(), userApp.getUser_id(), userApp.getInvalid_time() }, "Save user app information failed !");
		JdbcUtils.free(null, null, conn);
	}
	
	public void update(int id, UserApp userApp) {
		Connection conn = JdbcUtils.getConnection();
		this.update(JdbcUtils.getConnection(), "update users set app_description=? where id = ?", 
				new Object[]{userApp.getApp_description(), id }, "Update user app information failed !");
		JdbcUtils.free(null, null, conn);
	}
	
	public void delete(int id) {
		Connection conn = JdbcUtils.getConnection();
		this.update(conn, "delete from users_app where id =?", 
				new Object[]{ id}, "Delete user app information failed !");
		JdbcUtils.free(null, null, conn);
	}

	public UserApp getUserAppById(int user_id) {
		Connection conn = JdbcUtils.getConnection();
		UserApp userApp = this.getObject(JdbcUtils.getConnection(), "select id, app_key, app_secret, app_description, user_id from users_app where id = ?", 
				new Object[]{user_id}, "Query user app information failed !", UserApp.class);
		JdbcUtils.free(null, null, conn);
		return userApp;
	}
	
	public UserApp getUserAppByUserId(int user_id) {
		Connection conn = JdbcUtils.getConnection();
		UserApp userApp = this.getObject(JdbcUtils.getConnection(), "select id, app_key, app_secret, app_description, user_id from users_app where user_id = ?", 
				new Object[]{user_id}, "Query user app information failed !", UserApp.class);
		JdbcUtils.free(null, null, conn);
		return userApp;
	}

	public List<UserApp> getUserApps(int user_id) {
		Connection conn = JdbcUtils.getConnection();
		List<UserApp> userApps = this.getAllObject(JdbcUtils.getConnection(), "select id, app_key, app_secret, app_description, user_id from users_app where user_id = ?", 
				new Object[]{user_id}, "Query user app information list failed !", UserApp.class);
		JdbcUtils.free(null, null, conn);
		return userApps;
	}

	@Override
	public Map<String, String> getUserApps() {
		Connection conn = JdbcUtils.getConnection();
		List<UserApp> userApps = this.getAllObject(JdbcUtils.getConnection(), "select id, app_key, app_secret, app_description, user_id from users_app ", 
				null, "Query user app information list failed !", UserApp.class);
		Map<String, String> map = new HashMap<String, String>();
		for(UserApp uApp : userApps){
			uApp.getApp_description();
			map.put(uApp.getApp_key(), uApp.getApp_secret());
			map.put(uApp.getApp_key()+".description", uApp.getApp_description());
		}
		JdbcUtils.free(null, null, conn);
		return map;
	}

}
