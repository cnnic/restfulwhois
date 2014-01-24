package com.cnnic.whois.bean.oauth;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OAuthAccessorBeanIdRowMapper implements RowMapper<OAuthAccessorBean> {

	public OAuthAccessorBean mapRow(ResultSet rs, int row) throws SQLException {
		OAuthAccessorBean oauthAccessorBean = new OAuthAccessorBean();
		oauthAccessorBean.setId(rs.getInt("id"));
		oauthAccessorBean.setOauth_user_role(rs.getString("oauth_user_role"));
		return oauthAccessorBean;
	}

}
