package com.cnnic.whois.dao.oauth;

import java.sql.Connection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cnnic.whois.bean.oauth.OAuthAccessorBean;
import com.cnnic.whois.dao.base.BaseDao;
import com.cnnic.whois.util.JdbcUtils;

@Service("oauthAccessorDao")
public class OAuthAccessorDaoImpl extends BaseDao implements OAuthAccessorDao {

	public void save(OAuthAccessorBean oauuthAccessorBean) {
		Connection conn = JdbcUtils.getConnection();
		this.update(JdbcUtils.getConnection(), "insert into oauth_accessor (request_token, token_secret, access_token, app_key, app_secret) values(?, ?, ?, ?, ?)", 
				new Object[]{oauuthAccessorBean.getRequest_token(), oauuthAccessorBean.getToken_secret(), 
					oauuthAccessorBean.getAccess_token(), oauuthAccessorBean.getApp_key(), oauuthAccessorBean.getApp_secret() }, "Save OAuthAccessor information failed !");
		JdbcUtils.free(null, null, conn);
	}

	public List<OAuthAccessorBean> getOAuthAccessorBeans() {
		Connection conn = JdbcUtils.getConnection();
		List<OAuthAccessorBean> oauthAccessorBean = this.getAllObject(JdbcUtils.getConnection(), "select id, request_token, token_secret, access_token, app_key, app_secret from oauth_accessor ", 
				new Object[]{}, "Query OAuthAccessor information list failed !", OAuthAccessorBean.class);
		JdbcUtils.free(null, null, conn);
		return oauthAccessorBean;
	}

	@Override
	public OAuthAccessorBean getOAuthAccessorBeanByTokenAndSecret(String requestToken, String tokenSecret) {
		Connection conn = JdbcUtils.getConnection();
		OAuthAccessorBean oauthAccessorBean = this.getObject(JdbcUtils.getConnection(), "select id, request_token, token_secret from oauth_accessor where request_token = ? and token_secret = ? ", 
				new Object[]{requestToken, tokenSecret }, "Query OAuthAccessor information failed !", OAuthAccessorBean.class);
		JdbcUtils.free(null, null, conn);
		return oauthAccessorBean;
	}

	@Override
	public void updateByTokenAndSecret(String requestToken, String tokenSecret, String accessToken) {
		Connection conn = JdbcUtils.getConnection();
		this.update(JdbcUtils.getConnection(), "update oauth_accessor set access_token = ? where request_token = ? and token_secret = ? ", 
				new Object[]{accessToken, requestToken, tokenSecret }, "Update OAuthAccessor information failed !");
		JdbcUtils.free(null, null, conn);
	}

	@Override
	public void deleteInvalidDate() {
		Connection conn = JdbcUtils.getConnection();
		List<OAuthAccessorBean> oauthAccessorBeanList = this.getAllObject(JdbcUtils.getConnection(), 
				"select oa.id from oauth_accessor oa, users_app ua where oa.app_key = ua.app_key and date_add(oa.create_time, interval ua.invalid_time day) < now() ", 
				new Object[]{}, "Query OAuthAccessor information list failed !", OAuthAccessorBean.class);
		
		if(oauthAccessorBeanList.size() > 0 ){
			String result = "";
			for(OAuthAccessorBean oauth : oauthAccessorBeanList){
				result += oauth.getId() + ",";
			}
			System.out.println("id lists =: " +result.substring(0, result.length() - 1));
			
			this.update(conn, "delete from oauth_accessor where id in ("+ result.substring(0, result.length() - 1) +") ", 
					new Object[]{ }, "Delete OAuthAccessor information failed !");
		}
		JdbcUtils.free(null, null, conn);
	}

}
