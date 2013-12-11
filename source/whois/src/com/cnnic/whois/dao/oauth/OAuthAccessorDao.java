package com.cnnic.whois.dao.oauth;

import java.util.List;

import com.cnnic.whois.bean.OAuthAccessorBean;

public interface OAuthAccessorDao {

	public void save(OAuthAccessorBean oauthAccessorBean);

	public void update(int id, OAuthAccessorBean oauthAccessorBean);

	public void delete(int id);

	public OAuthAccessorBean getOAuthAccessorBeanById(int id);

	public List<OAuthAccessorBean> getOAuthAccessorBeans();
	
}
