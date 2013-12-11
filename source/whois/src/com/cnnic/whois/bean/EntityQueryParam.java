package com.cnnic.whois.bean;

import com.cnnic.whois.view.FormatType;

public class EntityQueryParam extends QueryParam {
	private String fuzzyQueryParamName;

	public EntityQueryParam(String q, String fuzzyQueryParamName) {
		super(q);
		this.fuzzyQueryParamName = fuzzyQueryParamName;
	}

	public EntityQueryParam(FormatType formatType, PageBean page) {
		super.setFormat(formatType);
		super.setPage(page);
	}

	public String getFuzzyQueryParamName() {
		return fuzzyQueryParamName;
	}

	public void setFuzzyQueryParamName(String fuzzyQueryParamName) {
		this.fuzzyQueryParamName = fuzzyQueryParamName;
	}
}