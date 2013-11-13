package com.cnnic.whois.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.dao.query.DnrDomainQueryDAO;
import com.cnnic.whois.dao.query.QueryDao;
import com.cnnic.whois.execption.QueryException;

public class DbQueryExecutor {

	List<QueryDao> queryDaos = new ArrayList<QueryDao>();

	private void init() {
		queryDaos.add(new DnrDomainQueryDAO());
	}

	public DbQueryExecutor() {
		super();
		init();
	}

	public Map<String, Object> query(QueryType queryType, String role,
			String format) throws QueryException {
		for (QueryDao queryDao : queryDaos) {
			if (queryDao.supportType(queryType)) {
				queryDao.query("q", role, format);
				break;
			}
		}
		return null;
	}
}
