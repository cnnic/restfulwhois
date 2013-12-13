package com.cnnic.whois.dao.oauth;

import java.util.List;

import com.cnnic.whois.bean.oauth.OAuthAccessorBean;

public interface OAuthAccessorDao {

	public void save(OAuthAccessorBean oauthAccessorBean);

	public List<OAuthAccessorBean> getOAuthAccessorBeans();
	
	public OAuthAccessorBean getOAuthAccessorBeanByTokenAndSecret(String requestToken, String tokenSecret);
	
	public void updateByTokenAndSecret(String requestToken, String tokenSecret, String accessToken);
	
	public void deleteInvalidDate();
	
}
