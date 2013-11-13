package com.cnnic.whois.dao.query;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.util.WhoisUtil;

public class RirEntityQueryDAO extends EntityQueryDAO {
	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.RIRENTITY.equals(queryType);
	}

	@Override
	protected String getJoinSql(String handle) {
		return WhoisUtil.SELECT_JOIN_LIST_JOINRIRENTITY + "'" + handle + "'";
	}

	@Override
	public boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		if (!QueryJoinType.ENTITIES.equals(queryJoinType)) {
			return false;
		}
		return joinRirEntity(queryType);
	}

	public static boolean joinRirEntity(QueryType queryType) {
		return QueryType.IP.equals(queryType)
				|| QueryType.AUTNUM.equals(queryType)
				|| QueryType.RIRDOMAIN.equals(queryType);
	}
}