package com.cnnic.whois.bean;

import com.cnnic.whois.view.FormatType;

public class DomainQueryParam extends QueryParam {
	private String domainPuny;

	public DomainQueryParam(String q, String domainPuny) {
		super(q);
		this.domainPuny = domainPuny;
	}

	public DomainQueryParam(FormatType formatType, PageBean page) {
		super.setFormat(formatType);
		super.setPage(page);
	}

	public String getDomainPuny() {
		return domainPuny;
	}

	public void setDomainPuny(String domainPuny) {
		this.domainPuny = domainPuny;
	}
}