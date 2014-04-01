package com.cnnic.whois.dao.query.cache;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
@Repository("cacheIpQueryDao")
public class IpQueryDao extends AbstractCacheQueryDao {

	@Override
	public Map<String, Object> query(QueryParam param) throws QueryException, RedirectExecption {
		return dbQueryExecutor.query(QueryType.IP, param);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.IP;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.IP.equals(queryType);
	}
}