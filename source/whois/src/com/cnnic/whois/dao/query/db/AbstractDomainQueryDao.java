package com.cnnic.whois.dao.query.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;

public abstract class AbstractDomainQueryDao extends AbstractDbQueryDao {
	public static final String GET_ALL_DNRDOMAIN = "select * from DNRDomain";
	public static final String GET_ALL_RIRDOMAIN = "select * from RIRDomain";
	public static final String QUERY_KEY = "$mul$domains";

	@Override
	public Map<String, Object> query(QueryParam param)
			throws QueryException, RedirectExecption {
		throw new UnsupportedOperationException();
	}

//	public Map<String, Object> query(String listSql, List<String> keyFields,
//			String q) throws QueryException {
//		String sql = listSql + "'" + q + "'";
//		return this.queryBySql(sql, keyFields);
//	}

	protected Map<String, Object> queryBySql(String sql,
			List<String> keyFields) throws QueryException {
		Map<String, Object> map = null;
		try {
			Map<String, Object> domainMap = query(sql, keyFields, QUERY_KEY);
			if (domainMap != null) {
				map = rdapConformance(map);
				map.putAll(domainMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return map;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.DOMAIN;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.DOMAIN.equals(queryType);
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return false;
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle) throws SQLException {
		throw new UnsupportedOperationException();
	}
}
