package com.cnnic.whois.dao.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.dao.QueryExecutor;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
@Service("dbQueryExecutor")
public class DbQueryExecutor implements QueryExecutor {
	private static DbQueryExecutor executor = new DbQueryExecutor();

	public static DbQueryExecutor getExecutor() {
		return executor;
	}

	@Autowired
	private List<AbstractDbQueryDao> dbQueryDaos ;

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
		for (AbstractDbQueryDao queryDao : dbQueryDaos) {
			if (queryDao.supportType(queryType)) {
				return queryDao.getAll();
			}
		}
		return null;
	}

	public List<AbstractDbQueryDao> getDbQueryDaos() {
		return dbQueryDaos;
	}

	public void setDbQueryDaos(List<AbstractDbQueryDao> dbQueryDaos) {
		this.dbQueryDaos = dbQueryDaos;
	}

	public List<String> getKeyFields(QueryType queryType, String role) {
		for (AbstractDbQueryDao queryDao : dbQueryDaos) {
			if (queryDao.supportType(queryType)) {
				return queryDao.getKeyFields(role);
			}
		}
		return new ArrayList<String>();
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