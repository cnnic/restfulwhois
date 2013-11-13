package com.cnnic.whois.dao.query;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.util.WhoisUtil;

public class EntityFuzzyQueryDAO extends EntityQueryDAO {
	@Override
	public QueryType getQueryType() {
		return QueryType.ENTITYSEARCH;
	}
	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.ENTITYSEARCH.equals(queryType);
	}

	@Override
	protected String getJoinSql(String handle) {
		return WhoisUtil.SELECT_JOIN_LIST_JOINDNRENTITY + "'" + handle + "'";
	}

	@Override
	public boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		if (!QueryJoinType.ENTITIES.equals(queryJoinType)) {
			return false;
		}
		if (RirEntityQueryDAO.joinRirEntity(queryType)) {
			return false;
		}
		return true;
	}
}