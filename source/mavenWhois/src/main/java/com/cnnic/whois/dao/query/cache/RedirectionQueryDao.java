package com.cnnic.whois.dao.query.cache;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
@Repository("cacheRedirectionQueryDao")
public class RedirectionQueryDao extends AbstractCacheQueryDao {

	@Override
	public Map<String, Object> query(QueryParam param)
			throws QueryException, RedirectExecption {
		return dbQueryExecutor.query(QueryType.REDIRECTION, param);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.REDIRECTION;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.REDIRECTION.equals(queryType);
	}
}