package com.cnnic.whois.dao.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.util.WhoisUtil;

public class RirEntityQueryDao extends EntityQueryDao {

	public RirEntityQueryDao(List<AbstractDbQueryDao> dbQueryDaos) {
		super(dbQueryDaos);
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.NONE;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return false;
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		if (!QueryJoinType.ENTITIES.equals(queryJoinType)) {
			return false;
		}
		return joinRirEntity(queryType);
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle,
			QueryParam param, Connection connection) throws SQLException {
		return querySpecificJoinTable(key, handle,
				WhoisUtil.SELECT_JOIN_LIST_JOINRIRENTITY, param, connection,
				permissionCache.getRIREntityKeyFileds(param.getRole()));
	}

	public static boolean joinRirEntity(QueryType queryType) {
		return QueryType.IP.equals(queryType)
				|| QueryType.AUTNUM.equals(queryType)
				|| QueryType.RIRDOMAIN.equals(queryType);
	}
}