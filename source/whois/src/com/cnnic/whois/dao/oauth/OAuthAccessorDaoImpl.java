package com.cnnic.whois.dao.oauth;

import java.sql.Connection;
import java.util.List;

import com.cnnic.whois.bean.OAuthAccessorBean;
import com.cnnic.whois.dao.base.BaseDao;
import com.cnnic.whois.util.JdbcUtils;

public class OAuthAccessorDaoImpl extends BaseDao implements OAuthAccessorDao {

	public void save(OAuthAccessorBean oauuthAccessorBean) {
		Connection conn = JdbcUtils.getConnection();
		
		this.update(JdbcUtils.getConnection(), "insert into oauth_accessor (request_token, token_secret, access_token) values(?, ?, ?)", 
				new Object[]{oauuthAccessorBean.getRequestToken(), oauuthAccessorBean.getTokenSecret(), oauuthAccessorBean.getAccessToken() }, "Save user app information failed !");
		JdbcUtils.free(null, null, conn);
	}
	
	public void update(int id, OAuthAccessorBean oauuthAccessorBean) {
//		Connection conn = JdbcUtils.getConnection();
//		this.update(JdbcUtils.getConnection(), "update users set app_description=? where id = ?", 
//				new Object[]{userApp.getApp_description(), id }, "Update user app information failed !");
//		JdbcUtils.free(null, null, conn);
		throw new UnsupportedOperationException();
	}
	
	public void delete(int id) {
//		Connection conn = JdbcUtils.getConnection();
//		this.update(conn, "delete from users_app where id =?", 
//				new Object[]{ id}, "Delete user app information failed !");
//		JdbcUtils.free(null, null, conn);
		throw new UnsupportedOperationException();
	}

	public OAuthAccessorBean getOAuthAccessorBeanById(int id) {
//		Connection conn = JdbcUtils.getConnection();
//		UserApp userApp = this.getObject(JdbcUtils.getConnection(), "select id, app_key, app_secret, app_description, user_id from users_app where user_id = ?", 
//				new Object[]{user_id}, "Query user app information failed !", UserApp.class);
//		JdbcUtils.free(null, null, conn);
//		return userApp;
		throw new UnsupportedOperationException();
	}

	public List<OAuthAccessorBean> getOAuthAccessorBeans() {
//		Connection conn = JdbcUtils.getConnection();
//		List<UserApp> userApps = this.getAllObject(JdbcUtils.getConnection(), "select id, app_key, app_secret, app_description, user_id from users_app where user_id = ?", 
//				new Object[]{user_id}, "Query user app information list failed !", UserApp.class);
//		JdbcUtils.free(null, null, conn);
//		return userApps;
		throw new UnsupportedOperationException();
	}

}
