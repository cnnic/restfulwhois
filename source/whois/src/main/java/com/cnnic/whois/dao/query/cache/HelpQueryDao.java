package com.cnnic.whois.dao.query.cache;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
@Repository("cacheHelpQueryDao")
public class HelpQueryDao extends AbstractCacheQueryDao {
	@Override
	public Map<String, Object> query(QueryParam param) throws QueryException, RedirectExecption {
		return dbQueryExecutor.query(QueryType.HELP, param);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.HELP;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.HELP.equals(queryType);
	}

	@Override
	protected boolean needInitCache() {
		return false;
	}
	@Deprecated
	@Override
	protected void initCache() {
		super.initCacheWithOneKey("$mul$notices", "noticesId");
	}
}