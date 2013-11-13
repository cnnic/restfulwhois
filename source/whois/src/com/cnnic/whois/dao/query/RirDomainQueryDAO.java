package com.cnnic.whois.dao.query;

import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.util.WhoisUtil;

public class RirDomainQueryDAO extends AbstractDomainQueryDAO {

	public Map<String, Object> query(String q, String role, String format,
			PageBean... page) {
		String rirSql = WhoisUtil.SELECT_LIST_RIRDOMAIN + "'" + q + "'";
		List<String> rirKeyFlieds = permissionCache.getDNRDomainKeyFileds(role);
		// cache.queryDoamin("", role, format);
		return doQquery(rirKeyFlieds, rirSql, role, format);
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.RIRDOMAIN.equals(queryType);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.RIRDOMAIN;
	}

	@Override
	public boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return false;
	}
}