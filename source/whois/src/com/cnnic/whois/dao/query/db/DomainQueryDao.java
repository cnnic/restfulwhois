package com.cnnic.whois.dao.query.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.Index;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.service.index.SearchResult;
import com.cnnic.whois.util.ColumnCache;
import com.cnnic.whois.util.WhoisUtil;
//@Repository
public class DomainQueryDao extends AbstractSearchQueryDao {
	public static final String GET_ALL_DNRDOMAIN = "select * from DNRDomain";
	public static final String GET_ALL_RIRDOMAIN = "select * from RIRDomain";
	public static final String QUERY_KEY = "$mul$domains";

	@Override
	public Map<String, Object> query(QueryParam param) throws QueryException, RedirectExecption {
		if (param.isFuzzyQ()) {
			return doFuzzyQuery(param);
		} else {
			return doQuery(param);
		}
	}

	private Map<String, Object> doFuzzyQuery(QueryParam param) throws QueryException {
		SearchResult<? extends Index> result = searchQueryExecutor.query(
				QueryType.SEARCHDOMAIN, param);
		return queryWithIndexs(result);
	}

	private Map<String, Object> queryWithIndexs(
			SearchResult<? extends Index> result) throws QueryException {
		Map<String, Object> map = null;
		if (result.getResultList().size() == 0) {
			return map;
		}
		try {
			Map<String, Object> domainMap = super.fuzzyQuery(result, "$mul$domains");
			if (domainMap != null) {
				map = rdapConformance(map);
				map.putAll(domainMap);
				addTruncatedParamToMap(map, result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return map;
	}

	private Map<String, Object> doQuery(QueryParam param, 
			PageBean... page) throws QueryException {
		Map<String, Object> map = null;
		List<String> dnrKeyFields = columnCache.getDNRDomainKeyFileds();
		Map<String, Object> dnrMap = query(param, dnrKeyFields, page);
		map.putAll(dnrMap);
		List<String> rirKeyFields = columnCache.getRIRDomainKeyFileds();
		Map<String, Object> rirMap = query(param, rirKeyFields, page);
		map.putAll(rirMap);
		return map;
	}

	private Map<String, Object> query(QueryParam param,
			List<String> keyFields, PageBean... page) throws QueryException {
		Map<String, Object> map = null;
		try {
			Map<String, Object> domainMap = query(
					WhoisUtil.SELECT_LIST_DNRDOMAIN, keyFields, param.getQ());
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

//	protected Map<String, Object> querys(String listSql, List<String> keyFields,
//			String q) throws QueryException {
//		String sql = listSql + "'" + q + "'";
//		return this.queryBySql(sql, keyFields);
//	}

	protected Map<String, Object> queryBySql(String sql,
			List<String> keyFields) throws QueryException {
		Map<String, Object> map = null;

		try {
			Map<String, Object> domainMap = query(sql, keyFields,
					QUERY_KEY);
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
	public Map<String, Object> getAll() throws QueryException {
		List<String> dnrKeyFields = columnCache.getDNRDomainKeyFileds();
		Map<String, Object> dnrDomains = queryBySql(GET_ALL_DNRDOMAIN,
				dnrKeyFields);
		List<String> rirKeyFields = columnCache.getRIRDomainKeyFileds();
		Map<String, Object> rirDomains = queryBySql(GET_ALL_RIRDOMAIN,
				rirKeyFields);
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
	public Object querySpecificJoinTable(String key, String handle) throws SQLException {
		throw new UnsupportedOperationException();
	}
}
