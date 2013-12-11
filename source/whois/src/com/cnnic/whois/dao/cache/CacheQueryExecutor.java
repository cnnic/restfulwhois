package com.cnnic.whois.dao.cache;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.dao.QueryExecutor;
import com.cnnic.whois.dao.db.QueryDao;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;

@Service("cacheQueryExecutor")
public class CacheQueryExecutor implements QueryExecutor {
	private static CacheQueryExecutor executor = new CacheQueryExecutor();

	public static CacheQueryExecutor getExecutor() {
		return executor;
	}

	@Resource(name = "cacheQueryDaos")
	private List<QueryDao> cacheQueryDaos;

	@Override
	public Map<String, Object> query(QueryType queryType, QueryParam param,
			PageBean... pageParam) throws QueryException, RedirectExecption {
		for (QueryDao queryDao : cacheQueryDaos) {
			if (queryDao.supportType(queryType)) {
				return queryDao.query(param);
			}
		}
		return null;
	}

	public Map<String, Object> initCache() {
		for (QueryDao queryDao : cacheQueryDaos) {
			if (queryDao instanceof AbstractCacheQueryDao) {
				AbstractCacheQueryDao cacheQueryDao = (AbstractCacheQueryDao) queryDao;
				if (cacheQueryDao.needInitCache()) {
					cacheQueryDao.initCache();
				}
			}
		}
		return null;
	}
}