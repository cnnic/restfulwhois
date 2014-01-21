package com.cnnic.whois.bean.index;


public class SearchCondition {

	private int start = 0;
	private int row = 25;
	private String searchword;

	public SearchCondition(
			String searchword) {
		this.searchword = searchword;
	}

	public String getSearchword() {
		return searchword;
	}

	public int getStart() {
		return start;
	}

	public void setStart(
			int start) {
		this.start = start;
	}

	public int getRow() {
		return row;
	}

	public void setRow(
			int row) {
		this.row = row;
	}
}
