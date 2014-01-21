package com.cnnic.whois.bean;

public class RedirectionQueryParam extends QueryParam {
	private String tableName;

	public RedirectionQueryParam(String tableName, String q) {
		super(q);
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
}