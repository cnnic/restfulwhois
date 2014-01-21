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
	private static String domainSolrUrl;
	
	/*
	 * Entity Solr Url
	 */
	private static String entitySolrUrl;
	
	/*
	 * Name Server Solr Url
	 */
	private static String nameServerSolrUrl;
	
	/*
	 * Max Length for fuzzy query
	 */
	private static String maxFuzzyQuery;
	
	/*
	 * cache IP
	 */
	private static String cacheIP;
	
	/*
	 * cache port
	 */
	private static String cachePort;
	
	/*
	 * prefix url of ".well-known/rdap/" 
	 */
	private static String rdapUrl;
	
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
	
	public void setDomainSolrUrl(String url) {
		domainSolrUrl = url;
	}
	 
	public static String getDomainSolrUrl() {
	    return domainSolrUrl;
	}
	
	public void setEntitySolrUrl(String url) {
		entitySolrUrl = url;
	}
	 
	public static String getEntitySolrUrl() {
	    return entitySolrUrl;
	}
	public void setNameServerSolrUrl(String url) {
		nameServerSolrUrl = url;
	}
	 
	public static String getNameServerSolrUrl() {
	    return nameServerSolrUrl;
	}
	
	public void setMaxFuzzyQuery(String num) {
	    maxFuzzyQuery = num;
	}
	 
	public static String getMaxFuzzyQuery() {
	    return maxFuzzyQuery;
	}
	
	public void setCacheIP(String ip) {
	    cacheIP = ip;
	}
	
	public static String getCacheIP() {
	    return cacheIP;
	}
	
	public void setCachePort(String port) {
	    cachePort = port;
	}
	
	public static String getCachePort() {
	    return cachePort;
	}	
	
	public void setRdapUrl(String url) {
		rdapUrl = url;
	}
	 
	public static String getRdapUrl() {
	    return rdapUrl;
	}
}
