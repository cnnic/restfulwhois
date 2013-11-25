package com.cnnic.whois.dao.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;

public abstract class AbstractDomainQueryDao extends AbstractDbQueryDao {
	public static final String GET_ALL_DNRDOMAIN = "select * from DNRDomain";
	public static final String GET_ALL_RIRDOMAIN = "select * from RIRDomain";
	public static final String QUERY_KEY = "$mul$domains";

	public AbstractDomainQueryDao(List<AbstractDbQueryDao> dbQueryDaos) {
		super(dbQueryDaos);
	}

	@Override
	public Map<String, Object> query(QueryParam param,
			PageBean... page) throws QueryException, RedirectExecption {
		throw new UnsupportedOperationException();
	}

	public Map<String, Object> query(String listSql, List<String> keyFields,
			String q, QueryParam param) throws QueryException {
		String sql = listSql + "'" + q + "'";
		return this.queryBySql(sql, keyFields, param);
	}

	protected Map<String, Object> queryBySql(String sql,
			List<String> keyFields, QueryParam param) throws QueryException {
		Connection connection = null;
		Map<String, Object> map = null;

		try {
			connection = ds.getConnection();
			Map<String, Object> domainMap = query(connection, sql, keyFields,
					QUERY_KEY, param);
			if (domainMap != null) {
				map = rdapConformance(map);
				map.putAll(domainMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new QueryException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException se) {
				}
			}
		}
		return map;
	}

	@Override
	public Map<String, Object> getAll(QueryParam param) throws QueryException {
		List<String> dnrKeyFields = permissionCache.getDNRDomainKeyFileds(param.getRole());
		Map<String, Object> dnrDomains = queryBySql(GET_ALL_DNRDOMAIN,
				dnrKeyFields, param);
		List<String> rirKeyFields = permissionCache.getRIRDomainKeyFileds(param.getRole());
		Map<String, Object> rirDomains = queryBySql(GET_ALL_RIRDOMAIN,
				rirKeyFields, param);
		Map<String, Object> result = new HashMap<String, Object>();
		// TODO:handle size 1
		result.putAll(dnrDomains);
		result.putAll(rirDomains);
		return result;
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
	public Object querySpecificJoinTable(String key, String handle,
			QueryParam param, Connection connection) throws SQLException {
		throw new UnsupportedOperationException();
	}
}