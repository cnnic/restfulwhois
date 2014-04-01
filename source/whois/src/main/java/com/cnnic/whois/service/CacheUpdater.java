package com.cnnic.whois.service;

import com.cnnic.whois.dao.query.QueryExecutor;
import com.cnnic.whois.dao.query.cache.CacheQueryExecutor;
import com.cnnic.whois.dao.query.db.DbQueryExecutor;

public class CacheUpdater {
	private static CacheUpdater cacheUpdater = new CacheUpdater();
	private static QueryExecutor dbQueryExecutor = DbQueryExecutor.getExecutor();
	
	public static CacheUpdater getCacheUpdater() {
		return cacheUpdater;
	}

	public void init() {
		CacheQueryExecutor cacheQueryExecutor = CacheQueryExecutor.getExecutor();
		cacheQueryExecutor.initCache();
	}
}