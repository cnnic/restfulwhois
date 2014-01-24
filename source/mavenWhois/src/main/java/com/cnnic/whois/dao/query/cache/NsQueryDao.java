package com.cnnic.whois.dao.query.cache;

import java.net.IDN;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
@Repository("cacheNsQueryDao")
public class NsQueryDao extends AbstractCacheQueryDao {
	@Override
	protected List<String> getCacheKeySplits(QueryParam param) {
		return super.getHandleCacheKeySplits(param);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.NAMESERVER;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.NAMESERVER.equals(queryType);
	}

	@Override
	protected boolean needInitCache() {
		return true;
	}

	@Override
	protected void initCache() {
		super.initCacheWithOneKey("$mul$nameServer", "Ldh_Name");
	}
	@Override
	protected String convertCacheKeyValue(String value) {
		try {
			value = IDN.toASCII(value);// long lable exception
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}