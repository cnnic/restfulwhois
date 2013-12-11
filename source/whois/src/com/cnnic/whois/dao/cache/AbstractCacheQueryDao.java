package com.cnnic.whois.dao.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import redis.clients.jedis.Jedis;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.dao.db.AbstractDbQueryDao;
import com.cnnic.whois.dao.db.DbQueryExecutor;
import com.cnnic.whois.dao.db.QueryDao;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.util.DataFormat;
import com.cnnic.whois.util.WhoisProperties;

public abstract class AbstractCacheQueryDao implements QueryDao {
	@Autowired
	@Qualifier("dbQueryExecutor")
	protected DbQueryExecutor dbQueryExecutor;
	private static Jedis cache = new Jedis(WhoisProperties.getCacheIp(),
			Integer.valueOf(WhoisProperties.getCachePort()));

	public AbstractCacheQueryDao() {
		super();
		init();
	}

	private void init() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> query(QueryParam param)
			throws QueryException, RedirectExecption {
		String cacheKey = getCacheKey(param);
		return getMapAndConvertToJsonObject(cacheKey);
	}

	protected String getCacheKey(QueryParam param) {
		List<String> keySplits = getCacheKeySplits(param);
		return StringUtils.join(keySplits, ":");
	}

	protected List<String> getCacheKeySplits(QueryParam param) {
		throw new UnsupportedOperationException();
	}

	protected List<String> getHandleCacheKeySplits(QueryParam param) {
		List<String> keySplits = new ArrayList<String>();
		keySplits.add(this.getQueryType().toString());
		keySplits.add("handle");
		keySplits.add(param.getQ());
		return keySplits;
	}

	private JSONObject getMapAndConvertToJsonObject(String key) {
		String cacheObj = cache.get(key);
		if (StringUtils.isBlank(cacheObj)) {
			return null;
		}
		return DataFormat.fromObject(cacheObj);
	}

	protected boolean needInitCache() {
		return false;
	}

	protected void initCache() {
		throw new UnsupportedOperationException();
	}

	protected void initCacheWithOneKey(String queryResultKey, String key) {
		initCacheWithOneKey(queryResultKey, key, getQueryType());
	}

	protected void initCacheWithOneKey(String queryResultKey, String key,
			QueryType queryType) {
		try {
			Map<String, Object> valuesMap = dbQueryExecutor.getAll(queryType);
			if (null == valuesMap) {
				return;
			}
			if (null == valuesMap.get(queryResultKey)) {
				setCache(valuesMap, key);
				return;
			}
			Object[] values = (Object[]) valuesMap.get(queryResultKey);
			for (Object entity : values) {
				Map<String, Object> entityMap = (Map<String, Object>) entity;
				Map<String, Object> map = null;
				map = AbstractDbQueryDao.rdapConformance(map);
				map.putAll(entityMap);
				setCache(map, key);
			}
			System.err.println("init cache,add " + getQueryType() + " size:"
					+ values.length);
		} catch (QueryException e) {
			e.printStackTrace();
		}
	}

	private void setCache(Map<String, Object> entityMap, String key) {
		String cacheKey = getCacheKey(new QueryParam(entityMap.get(key)
				.toString()));
		System.err.println("init cache,add " + getQueryType() + ",key:"
				+ cacheKey);
		setCache(cacheKey, entityMap);
	}

	@Override
	public Map<String, Object> getAll() throws QueryException {
		throw new UnsupportedOperationException();
	}

	protected void setCache(String key, Map<String, Object> entityMap) {
		String jsonStr = DataFormat.getJsonObject(entityMap).toString();
		System.err.println(jsonStr);
		cache.set(key, jsonStr);
	}
}