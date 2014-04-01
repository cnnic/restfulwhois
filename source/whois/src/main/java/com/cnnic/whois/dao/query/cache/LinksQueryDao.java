package com.cnnic.whois.dao.query.cache;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
@Repository("cacheLinksQueryDao")
public class LinksQueryDao extends AbstractCacheQueryDao {
	@Override
	protected List<String> getCacheKeySplits(QueryParam param) {
		return super.getHandleCacheKeySplits(param);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.LINKS;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.LINKS.equals(queryType);
	}

	@Override
	protected boolean needInitCache() {
		return true;
	}

	@Override
	protected void initCache() {
		super.initCacheWithOneKey("$mul$link", "linkId");
	}
}