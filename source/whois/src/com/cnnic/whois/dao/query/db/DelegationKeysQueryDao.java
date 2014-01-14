package com.cnnic.whois.dao.query.db;

import java.sql.SQLException;
import java.util.Map;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.util.ColumnCache;
import com.cnnic.whois.util.WhoisUtil;

@Deprecated
public class DelegationKeysQueryDao extends AbstractDbQueryDao {

	@Override
	public Map<String, Object> query(QueryParam param) throws QueryException {
		Map<String, Object> map = null;
		try {
			String selectSql = WhoisUtil.SELECT_LIST_DELEGATIONKEYS + "'" + param.getQ() + "'";
			map = query(selectSql, ColumnCache.getColumnCache()
					.getDelegationKeyFileds(), "$mul$delegationKeys");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return map;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.DELETATIONKEY;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.DELETATIONKEY.equals(queryType);
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return QueryJoinType.DELEGATIONKEYS.equals(queryJoinType);
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle) throws SQLException {
		return querySpecificJoinTable(key, handle,
				WhoisUtil.SELECT_JOIN_LIST_DELEGATIONKEYS,
				ColumnCache.getColumnCache().getDelegationKeyFileds());
	}
}
