package com.cnnic.whois.util;

/**
 * 
 * static properties for Whois
 *
 */
public class WhoisProperties {
	
	/*
	 * User Expire Time for anonymous
	 */
	private static String anonymousExpireTime;
	
	/*
	 * User Expire Time for authenticated
	 */
	private static String authenticatedExpireTime;
	
	/*
	 * User Expire Time for ROOT
	 */
	private static String rootExpireTime;
	
	/*
	 * Cache Expire Time
	 */
	private static String expireTime;
	
	/*
	 * Domain Solr Url
	 */
	private static String solrUrl;
	
	
	/*
	 * Max Length for fuzzy query
	 */
	private static String maxFuzzyQuery;
	
	/*
	 * prefix url of ".well-known/rdap/" 
	 */
	private static String rdapUrl;
	
	/*
	 * server url
	 */
	private static String serverurl;
	
	/**
	 * setAnonymousExpireTime
	 * @param time
	 */
	public void setAnonymousExpireTime(String time) {
	    anonymousExpireTime = time;
	}
	 
	/**
	 * getAnonymousExpireTime
	 * @return string
	 */
	public static String getAnonymousExpireTime() {
	    return anonymousExpireTime;
	}
	
	/**
	 * setAuthenticatedExpireTime
	 * @param time
	 */
	public void setAuthenticatedExpireTime(String time) {
	    authenticatedExpireTime = time;
	}
	 
	/**
	 * getAuthenticatedExpireTime
	 * @return string
	 */
	public static String getAuthenticatedExpireTime() {
	    return authenticatedExpireTime;
	}
	
	/**
	 * setRootExpireTime
	 * @param time
	 */
	public void setRootExpireTime(String time) {
	    rootExpireTime = time;
	}
	 
	/**
	 * getRootExpireTime
	 * @return string
	 */
	public static String getRootExpireTime() {
	    return rootExpireTime;
	}
	/**
	 * setExpireTime
	 * @param time
	 */
	public void setExpireTime(String time) {
	    expireTime = time;
	}
	 
	/**
	 * getExpireTime
	 * @return expireTime
	 */
	public static String getExpireTime() {
	    return expireTime;
	}
	/**
	 * setSolrUrl
	 * @param url
	 */
	public void setSolrUrl(String url) {
		solrUrl = url;
	}
	 
	/**
	 * getDomainSolrUrl
	 * @return String
	 */
	public static String getDomainSolrUrl() {
	    return solrUrl + "/domain";
	}
	/**
	 * getEntitySolrUrl
	 * @return String
	 */
	public static String getEntitySolrUrl() {
	    return solrUrl + "/entity";
	}
	 
	/**
	 * getNameServerSolrUrl
	 * @return string
	 */
	public static String getNameServerSolrUrl() {
	    return solrUrl + "/nameserver";
	}
	/**
	 * setMaxFuzzyQuery
	 * @param num
	 */
	public void setMaxFuzzyQuery(String num) {
	    maxFuzzyQuery = num;
	}
	 
	/**
	 * getMaxFuzzyQuery
	 * @return String
	 */
	public static String getMaxFuzzyQuery() {
	    return maxFuzzyQuery;
	}
	/**
	 * setRdapUrl
	 * @param url
	 */
	public void setRdapUrl(String url) {
		rdapUrl = url;
	}
	 
	/**
	 * getRdapUrl
	 * @return String
	 */
	public static String getRdapUrl() {
	    return rdapUrl;
	}

	/**
	 * getServerurl
	 * @return String
	 */
	public static String getServerurl() {
		return serverurl;
	}

	/**
	 * setServerurl
	 * @param serverurl
	 */
	public void setServerurl(String serverurl) {
		WhoisProperties.serverurl = serverurl;
	}
}
