package com.cnnic.whois.bean;

public class QueryParam {
	private String q;
	private String role;

	public QueryParam(String q) {
		this.q = q;
	}
	
	public QueryParam(String q, String role) {
		this.q = q;
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}
}