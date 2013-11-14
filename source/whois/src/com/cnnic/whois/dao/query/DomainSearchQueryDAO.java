package com.cnnic.whois.dao.query;

import java.util.Map;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.DomainIndex;
import com.cnnic.whois.bean.index.Index;
import com.cnnic.whois.bean.index.SearchCondition;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.QueryService;
import com.cnnic.whois.service.index.SearchResult;
import com.cnnic.whois.util.WhoisUtil;

public class DomainSearchQueryDAO extends DbQueryDAO {
	private static final String QUERY_KEY = "$mul$domains";

	public Map<String, Object> query(String q, String role, String format,
			PageBean... pageParams) throws QueryException {
		Map<String, Object> map = null;
		String domainPuny = q;
		String domain = q;
		PageBean page = pageParams[0];
		SearchCondition searchCondition = new SearchCondition("ldhName:"
				+ domainPuny + " OR unicodeName:" + domain);
		int startPage = page.getCurrentPage() - 1;
		startPage = startPage >= 0 ? startPage : 0;
		int start = startPage * page.getMaxRecords();
		searchCondition.setStart(start);
		searchCondition.setRow(page.getMaxRecords());
		SearchResult<DomainIndex> result = domainIndexService
				.queryDomains(searchCondition);
		page.setRecordsCount(Long.valueOf(result.getTotalResults()).intValue());
		if (result.getResultList().size() == 0) {
			return map;
		}
		Map<String, Object> domainMap = fuzzyQuery(result, QUERY_KEY, role,
				format);
		if (domainMap != null) {
			map = rdapConformance(map);
			map.putAll(domainMap);
			addTruncatedParamToMap(map, result);
		}
		return map;
	}

	private void addTruncatedParamToMap(Map<String, Object> map,
			SearchResult<? extends Index> result) {
		if (result.getTotalResults() > QueryService.MAX_SIZE_FUZZY_QUERY) {
			map.put(WhoisUtil.SEARCH_RESULTS_TRUNCATED_EKEY, true);
		}
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.DOMAIN.equals(queryType);
	}

	@Override
	public String getJoinFieldIdColumnName() {
		throw new UnsupportedOperationException();// TODO: superClass
	}

	@Override
	public Map<String, Object> queryJoins(String handle, String role,
			String format) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.DOMAIN;
	}

	@Override
	public boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return false;
	}

	@Override
	protected Map<String, Object> postHandleFuzzyField(Map<String, Object> map,
			String format) {
		// do nothing
		return map;
	}
}
