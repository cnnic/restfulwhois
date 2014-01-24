package com.cnnic.whois.bean;

public class Authentication {
	private String role;

	public Authentication(String role) {
		super();
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
