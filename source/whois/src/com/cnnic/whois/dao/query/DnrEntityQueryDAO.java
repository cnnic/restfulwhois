package com.cnnic.whois.dao.query;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.util.WhoisUtil;

public class DnrEntityQueryDAO extends EntityQueryDAO {

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.DNRENTITY.equals(queryType);
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