package com.cnnic.whois.bean.oauth;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OAuthAccessorBeanRowMapper implements RowMapper<OAuthAccessorBean> {

	public OAuthAccessorBean mapRow(ResultSet rs, int row) throws SQLException {
		OAuthAccessorBean oauthAccessorBean = new OAuthAccessorBean();
		oauthAccessorBean.setId(rs.getInt("id"));
		oauthAccessorBean.setRequest_token(rs.getString("request_token"));
		oauthAccessorBean.setToken_secret(rs.getString("token_secret"));
		oauthAccessorBean.setAccess_token(rs.getString("access_token"));
		oauthAccessorBean.setApp_key(rs.getString("app_key"));
		oauthAccessorBean.setApp_secret(rs.getString("app_secret"));
		return oauthAccessorBean;
	}

}
