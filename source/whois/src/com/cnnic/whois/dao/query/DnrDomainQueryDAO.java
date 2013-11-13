package com.cnnic.whois.dao.query;

import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.util.WhoisUtil;

public class DnrDomainQueryDAO extends AbstractDomainQueryDAO {

	public Map<String, Object> query(String q, String role, String format,
			PageBean... page) {
		String dnrSql = WhoisUtil.SELECT_LIST_DNRDOMAIN + "'" + q + "'";
		List<String> dnrKeyFlieds = permissionCache.getDNRDomainKeyFileds(role);
		return doQquery(dnrKeyFlieds, dnrSql, role, format);
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.DNRDOMAIN.equals(queryType);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.DNRDOMAIN;
	}

	@Override
	public boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return false;
	}
}