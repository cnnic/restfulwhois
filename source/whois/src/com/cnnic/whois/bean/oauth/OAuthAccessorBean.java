package com.cnnic.whois.bean.oauth;

import java.util.Date;

public class OAuthAccessorBean {

	private int id;

	private String request_token;

	private String token_secret;

	private String access_token;
	
	private String app_key;
	
	private String app_secret;
	
	private Date create_time;
	
	private String oauth_user_role;


	public OAuthAccessorBean() { }
	
	public OAuthAccessorBean(String request_token, String token_secret,
			String access_token) {
		this.request_token = request_token;
		this.token_secret = token_secret;
		this.access_token = access_token;
	}

	public OAuthAccessorBean(String request_token, String token_secret,
			String access_token, String app_key, String app_secret) {
		this.request_token = request_token;
		this.token_secret = token_secret;
		this.access_token = access_token;
		this.app_key = app_key;
		this.app_secret = app_secret;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getRequest_token() {
		return request_token;
	}

	public void setRequest_token(String request_token) {
		this.request_token = request_token;
	}

	public String getToken_secret() {
		return token_secret;
	}

	public void setToken_secret(String token_secret) {
		this.token_secret = token_secret;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public String getApp_secret() {
		return app_secret;
	}

	public void setApp_secret(String app_secret) {
		this.app_secret = app_secret;
	}
	
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public String getOauth_user_role() {
		return oauth_user_role;
	}

	public void setOauth_user_role(String oauth_user_role) {
		this.oauth_user_role = oauth_user_role;
	}

}
