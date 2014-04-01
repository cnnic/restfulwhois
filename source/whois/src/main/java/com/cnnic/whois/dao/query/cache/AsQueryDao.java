package com.cnnic.whois.dao.query.cache;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
@Repository("cacheAsQueryDao")
public class AsQueryDao extends AbstractCacheQueryDao {

	@Override
	public Map<String, Object> query(QueryParam param) throws QueryException, RedirectExecption {
		return dbQueryExecutor.query(QueryType.AUTNUM, param);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.AUTNUM;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.AUTNUM.equals(queryType);
	}
}