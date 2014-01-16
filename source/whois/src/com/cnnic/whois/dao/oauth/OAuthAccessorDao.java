package com.cnnic.whois.dao.oauth;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.oauth.OAuthAccessorBean;
import com.cnnic.whois.bean.oauth.OAuthAccessorBeanIdRowMapper;
import com.cnnic.whois.bean.oauth.OAuthAccessorBeanRowMapper;
import com.cnnic.whois.dao.base.BaseJdbcDao;

@Repository
public class OAuthAccessorDao extends BaseJdbcDao {

	public void save(OAuthAccessorBean oauuthAccessorBean) {
		String sql = "insert into oauth_accessor (request_token, token_secret, access_token, app_key, app_secret) values(?, ?, ?, ?, ?) ";
		Object[] param = new Object[]{oauuthAccessorBean.getRequest_token(), oauuthAccessorBean.getToken_secret(), 
				oauuthAccessorBean.getAccess_token(), oauuthAccessorBean.getApp_key(), oauuthAccessorBean.getApp_secret() };
		this.getJdbcTemplate().update(sql, param);
	}

	public List<OAuthAccessorBean> getOAuthAccessorBeans() {
		String sql = "select id, request_token, token_secret, access_token, app_key, app_secret from oauth_accessor ";
		return this.getJdbcTemplate().query(sql, new OAuthAccessorBeanRowMapper());
	}

	public OAuthAccessorBean getOAuthAccessorBeanByTokenAndSecret(String requestToken, String tokenSecret) {
		String sql = "select id, request_token, token_secret, access_token, app_key, app_secret from oauth_accessor where request_token = ? and token_secret = ? ";
		Object[] param = new Object[] {requestToken, tokenSecret };
		return this.getJdbcTemplate().queryForObject(sql, param, new OAuthAccessorBeanRowMapper());
	}

	public void updateUserRoleByTokenAndSecret(String requestToken, String tokenSecret,
			String userRole) {
		String sql = "update oauth_accessor set oauth_user_role = ? where request_token = ? and token_secret = ? ";
		Object[] param = new Object[]{userRole, requestToken, tokenSecret };
		this.getJdbcTemplate().update(sql, param);
	}

	public void updateAccessTokenByTokenAndSecret(String requestToken, String tokenSecret, String accessToken) {
		String sql = "update oauth_accessor set access_token = ? where request_token = ? and token_secret = ? ";
		Object[] param = new Object[]{accessToken, requestToken, tokenSecret };
		this.getJdbcTemplate().update(sql, param);
	}

	public void deleteInvalidDate() {
		String sql1 = "select oa.id, oa.oauth_user_role from oauth_accessor oa, oauth_users_app ua where oa.app_key = ua.app_key and date_add(oa.create_time, interval ua.invalid_time day) < now() ";
		List<OAuthAccessorBean> oauthAccessorBeanList = this.getJdbcTemplate().query(sql1, new OAuthAccessorBeanIdRowMapper());
		if(oauthAccessorBeanList.size() > 0 ){
			String result = "";
			for(OAuthAccessorBean oauth : oauthAccessorBeanList){
				result += oauth.getId() + ",";
			}
			System.out.println("id lists =: " +result.substring(0, result.length() - 1));
			
			String sql2 = "delete from oauth_accessor where id in ("+ result.substring(0, result.length() - 1) +") ";
			this.getJdbcTemplate().update(sql2);
		}
	}

	public OAuthAccessorBean getOAuthAccessorBeanByAccessToken(String accessToken) {
		String sql = "select oa.id, oa.oauth_user_role from oauth_accessor oa where oa.access_token = ? ";
		Object[] param = new Object[] {accessToken };
		return this.getJdbcTemplate().queryForObject(sql, param, new OAuthAccessorBeanIdRowMapper());
	}

}
