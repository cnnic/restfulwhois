package com.cnnic.whois.bean.oauth;

public class UserApp {
	
	private int id;
	
	private String app_key;
	
	private String app_secret;
	
	private String app_description;
	
	private int user_id;
	
	private int invalid_time;
	
	public UserApp() { }
	
	public UserApp(String app_description, int user_id) {
		this.app_description = app_description;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getApp_description() {
		return app_description;
	}

	public void setApp_description(String app_description) {
		this.app_description = app_description;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public int getInvalid_time() {
		return invalid_time;
	}

	public void setInvalid_time(int invalid_time) {
		this.invalid_time = invalid_time;
	}
	
}
