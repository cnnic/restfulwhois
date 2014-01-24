package com.cnnic.whois.bean.oauth;

public class User {
	
	private int id;
	
	private String user_name;
	
	private String pwd;
	
	private String user_role;

	public User(){}

	public User(String user_name, String pwd) {
		this.user_name = user_name;
		this.pwd = pwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}
}
