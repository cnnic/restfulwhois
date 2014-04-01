package com.cnnic.whois.util;

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
	
	private static String serverurl;
	
	public void setAnonymousExpireTime(String time) {
	    anonymousExpireTime = time;
	}
	 
	public static String getAnonymousExpireTime() {
	    return anonymousExpireTime;
	}
	
	public void setAuthenticatedExpireTime(String time) {
	    authenticatedExpireTime = time;
	}
	 
	public static String getAuthenticatedExpireTime() {
	    return authenticatedExpireTime;
	}
	
	public void setRootExpireTime(String time) {
	    rootExpireTime = time;
	}
	 
	public static String getRootExpireTime() {
	    return rootExpireTime;
	}
	
	public void setExpireTime(String time) {
	    expireTime = time;
	}
	 
	public static String getExpireTime() {
	    return expireTime;
	}
	
	public void setSolrUrl(String url) {
		solrUrl = url;
	}
	 
	public static String getDomainSolrUrl() {
	    return solrUrl + "/domain";
	}
	
	public static String getEntitySolrUrl() {
	    return solrUrl + "/entity";
	}
	 
	public static String getNameServerSolrUrl() {
	    return solrUrl + "/nameserver";
	}
	
	public void setMaxFuzzyQuery(String num) {
	    maxFuzzyQuery = num;
	}
	 
	public static String getMaxFuzzyQuery() {
	    return maxFuzzyQuery;
	}
	
	public void setRdapUrl(String url) {
		rdapUrl = url;
	}
	 
	public static String getRdapUrl() {
	    return rdapUrl;
	}

	public static String getServerurl() {
		return serverurl;
	}

	public void setServerurl(String serverurl) {
		WhoisProperties.serverurl = serverurl;
	}
}
