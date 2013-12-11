package com.cnnic.whois.dao.cache;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
@Repository("cache.searchDomainQueryDao")
public class SearchDomainQueryDao extends AbstractCacheQueryDao {

	@Override
	public Map<String, Object> query(QueryParam param)
			throws QueryException, RedirectExecption {
		return dbQueryExecutor.query(QueryType.SEARCHDOMAIN, param);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.SEARCHDOMAIN;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.SEARCHDOMAIN.equals(queryType);
	}
}