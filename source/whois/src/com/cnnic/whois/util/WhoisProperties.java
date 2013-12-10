package com.cnnic.whois.util;

import java.util.Properties;

public class WhoisProperties {

	private static Properties resource;
	
	/*
	 * WHOIS Properties Location
	 */
	private static final String CLASSESURL = "com/cnnic/whois/properties/whois.properties";
	
	/*
	 * User Expire Time for anonymous
	 */
	private static final String ANONYMOUS_EXPIRE_TIME = "anonymous_expire_time";
	
	/*
	 * User Expire Time for authenticated
	 */
	private static final String AUTHENTICATED_EXPIRE_TIME = "authenticated_expire_time";
	
	/*
	 * User Expire Time for ROOT
	 */
	private static final String ROOT_EXPIRE_TIME = "root_expire_time";
	
	/*
	 * Cache Expire Time
	 */
	private static final String EXPIRE_TIME = "expire_time";
	
	/*
	 * Max Length for fuzzy query
	 */
	private static final String MAX_SIZE_FUZZY_QUERY = "maxsize.fuzzyquery";
	
	/*
	 * prefix url of ".well-known/rdap/"
	 */
	private static final String WELL_KNOWN_RDAP_URL = "well-known/rdap";
	
	/**
	 * Load the resource file
	 */
	static {
		resource = new Properties();
		try {
			resource.load(WhoisUtil.class.getClassLoader().getResourceAsStream(
					CLASSESURL));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get AnonymousExpireTime
	 * 
	 * @return long
	 */
	public static long getAnonymousExpireTime() {
		return Long.parseLong(resource.getProperty(ANONYMOUS_EXPIRE_TIME));
	}

	/**
	 * Get AuthenticatedExpireTime
	 * 
	 * @return long
	 */
	public static long getAuthenticatedExpireTime() {
		return Long.parseLong(resource.getProperty(AUTHENTICATED_EXPIRE_TIME));
	}

	/**
	 * Get RootExpireTime
	 * 
	 * @return long
	 */
	public static long getRootExpireTime() {
		return Long.parseLong(resource.getProperty(ROOT_EXPIRE_TIME));
	}
	

	/**
	 * Get RootExpireTime
	 * 
	 * @return long
	 */
	public static long getExpireTime() {
		return Long.parseLong(resource.getProperty(EXPIRE_TIME));
	}
	
	public static String getDomainSolrUrl() {
		return resource.getProperty("solr.url.domain");
	}
	
	public static String getNameServerSolrUrl() {
		return resource.getProperty("solr.url.nameserver");
	}
	
	public static String getEntitySolrUrl() {
		return resource.getProperty("solr.url.entity");
	}
	
	public static int getMaxSizeFuzzyQuery() {
		return Integer.parseInt(resource.getProperty(MAX_SIZE_FUZZY_QUERY));
	}
	
	public static String getCacheIp() {
		return resource.getProperty("cache.ip");
	}
	
	public static String getCachePort() {
		return resource.getProperty("cache.port");
	}
	
	public static String getRdapUrl() {
		String field = resource.getProperty("rdap.url");
		return (null == field) ? WELL_KNOWN_RDAP_URL : field;
	}
}
