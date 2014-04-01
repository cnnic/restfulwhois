package com.cnnic.whois.dao.query.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.dao.query.QueryExecutor;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;

@Service("dbQueryExecutor")
public class DbQueryExecutor implements QueryExecutor {
	private static DbQueryExecutor executor = new DbQueryExecutor();

	public static DbQueryExecutor getExecutor() {
		return executor;
	}

	@Resource(name = "dbQueryDaos")
	private List<AbstractDbQueryDao> dbQueryDaos;

	@Override
	public Map<String, Object> query(QueryType queryType, QueryParam param,
			PageBean... pageParam) throws QueryException, RedirectExecption {
		for (AbstractDbQueryDao queryDao : dbQueryDaos) {
			if (queryDao.supportType(queryType)) {
				return queryDao.query(param);
			}
		}
		return null;
	}

	public Map<String, Object> getAll(QueryType queryType)
			throws QueryException {
		for (QueryDao queryDao : dbQueryDaos) {
			if (queryDao.supportType(queryType)) {
				return queryDao.getAll();
			}
		}
		return null;
	}

	public List<String> getKeyFields(QueryType queryType, String role) {
		for (AbstractDbQueryDao queryDao : dbQueryDaos) {
			if (queryDao.supportType(queryType)) {
				return queryDao.getKeyFields(role);
			}
		}
		return new ArrayList<String>();
	}
	
	public String getMapKey(QueryType queryType) {
		for (AbstractDbQueryDao queryDao : dbQueryDaos) {
			if (queryDao.supportType(queryType)) {
				return queryDao.getMapKey();
			}
		}
		return "";
	}

	public Map<String, Object> formatValue(QueryType queryType,
			Map<String, Object> map) {
		for (AbstractDbQueryDao queryDao : dbQueryDaos) {
			if (queryDao.supportType(queryType)) {
				return queryDao.formatValue(map);
			}
		}
		return map;
	}
}