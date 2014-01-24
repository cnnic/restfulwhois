package com.cnnic.whois.bean;

import com.cnnic.whois.view.FormatType;

public class QueryParam {
	private QueryType queryType;
	private FormatType format;
	private String q;
	private boolean isFuzzyQ = false;
	private PageBean page;

	public QueryParam(String q) {
		this.q = q;
	}
	
	public QueryParam() {
	}

	public QueryParam(FormatType format, String q, boolean isFuzzyQ,
			PageBean page) {
		super();
		this.format = format;
		this.q = q;
		this.isFuzzyQ = isFuzzyQ;
		this.page = page;
	}

	public QueryParam(FormatType format, PageBean page) {
		super();
		this.format = format;
		this.page = page;
	}

	public QueryType getQueryType() {
		return queryType;
	}

	public void setQueryType(QueryType queryType) {
		this.queryType = queryType;
	}

	public PageBean getPage() {
		return page;
	}

	public void setPage(PageBean page) {
		this.page = page;
	}

	public FormatType getFormat() {
		return format;
	}

	public void setFormat(FormatType format) {
		this.format = format;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public boolean isFuzzyQ() {
		return isFuzzyQ;
	}

	public void setFuzzyQ(boolean isFuzzyQ) {
		this.isFuzzyQ = isFuzzyQ;
	}

}