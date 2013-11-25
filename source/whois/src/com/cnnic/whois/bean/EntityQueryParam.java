package com.cnnic.whois.bean;

public class EntityQueryParam extends QueryParam {
	private String fuzzyQueryParamName;

	public EntityQueryParam(String q, String role, String fuzzyQueryParamName) {
		super(q, role);
		this.fuzzyQueryParamName = fuzzyQueryParamName;
	}

	public String getFuzzyQueryParamName() {
		return fuzzyQueryParamName;
	}

	public void setFuzzyQueryParamName(String fuzzyQueryParamName) {
		this.fuzzyQueryParamName = fuzzyQueryParamName;
	}
}