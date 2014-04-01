package com.cnnic.whois.bean;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

public class PageBean implements java.io.Serializable {
	private static final long serialVersionUID = 693435998917420886L;
	public static final int FIRST_PAGE_NUM = 1;
	private Map<String, Object> parameterMap;
	private int currentPage = FIRST_PAGE_NUM;
	private int recordsCount = -1;
	private Integer maxRecords = 5;
	private int pageCount;

	/**
	 * Creates a new PageBean object.
	 */
	public PageBean() {
	}

	/**
	 * Creates a new PageBean object.
	 * 
	 * @param currentPage
	 *            DOCUMENT ME!
	 * @param maxRecords
	 *            DOCUMENT ME!
	 */
	public PageBean(int currentPage, int maxRecords) {
		this.currentPage = currentPage;
		this.maxRecords = maxRecords;
	}

	/**
	 * Creates a new PageBean object.
	 * 
	 * @param maxRecords
	 *            DOCUMENT ME!
	 */
	public PageBean(int maxRecords) {
		this.maxRecords = maxRecords;
	}

	/**
	 * Creates a new PageBean object.
	 * 
	 * @param currentPage
	 *            DOCUMENT ME!
	 * @param recordsCount
	 *            DOCUMENT ME!
	 * @param params
	 *            DOCUMENT ME!
	 */
	public PageBean(int currentPage, int recordsCount,
			Map<String, Object> params) {
		this.currentPage = currentPage;
		this.recordsCount = recordsCount;
		this.parameterMap = params;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @param key
	 *            DOCUMENT ME!
	 * @param value
	 *            DOCUMENT ME!
	 */
	public void putParameter(String key, Object value) {
		if (parameterMap == null) {
			parameterMap = new HashMap<String, Object>();
		}

		parameterMap.put(key, value);
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public int getRecordsCount() {
		return recordsCount;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	public Integer getMaxRecords() {
		return maxRecords;
	}

	/**
	 * 
	 * @param currentPage
	 *            DOCUMENT ME!
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 
	 * @param recordsCount
	 *            DOCUMENT ME!
	 */
	public void setRecordsCount(int recordsCount) {
		this.recordsCount = recordsCount;
	}

	/**
	 * 
	 * @param parameterMap
	 *            DOCUMENT ME!
	 */
	public void setParameterMap(Map<String, Object> parameterMap) {
		this.parameterMap = parameterMap;
	}

	/**
	 * 
	 * @param maxRecords
	 */
	public void setMaxRecords(Integer maxRecords) {
		this.maxRecords = maxRecords;
	}

	/**
	 * DOCUMENT ME!
	 */
	public void incrementCurrentPage() {
		this.currentPage++;
	}

	/**
	 * DOCUMENT ME!
	 * 
	 * @return DOCUMENT ME!
	 */
	@Override
	public String toString() {
		return new ToStringBuilder(this).append("currentPage", currentPage)
				.append("maxRecords", maxRecords).append("recordsCount",
						recordsCount).append("parameterMap", parameterMap)
				.toString();
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageCount() {
		return pageCount;
	}

}
