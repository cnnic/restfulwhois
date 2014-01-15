package com.cnnic.whois.dao.query.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.util.ColumnCache;
import com.cnnic.whois.util.PermissionCache;
import com.cnnic.whois.util.WhoisUtil;
@Repository
public class SecureDnsQueryDao extends AbstractDbQueryDao {
	public static final String GET_ALL_SECUREDNS = "select * from secureDNS ";

	@Override
	public Map<String, Object> query(QueryParam param)
			throws QueryException {
		Map<String, Object> map = null;
		try {
			String selectSql = WhoisUtil.SELECT_LIST_SECUREDNS + "'"
					+ param.getQ() + "'";
			map = query(selectSql, ColumnCache.getColumnCache()
					.getSecureDNSKeyFileds(), "$mul$secureDNS");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return map;
	}

	@Override
	protected String getJoinFieldName(String keyName) {
		String fliedName = "";
		if (keyName.equals(WhoisUtil.JOINSECUREDNS)
				|| keyName.equals("$mul$secureDNS")) {
			fliedName = "SecureDNSID";
		} else {
			fliedName = WhoisUtil.HANDLE;
		}
		return fliedName;
	}

	@Override
	public Map<String, Object> getAll() throws QueryException {
		Map<String, Object> map = null;
		try {
			map = query(GET_ALL_SECUREDNS, ColumnCache
					.getColumnCache().getSecureDNSKeyFileds(), "$mul$secureDNS");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return map;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.SECUREDNS;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.SECUREDNS.equals(queryType);
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return QueryJoinType.SECUREDNS.equals(queryJoinType);
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle) throws SQLException {
		return querySpecificJoinTable(key, handle,
				WhoisUtil.SELECT_JOIN_LIST_SECUREDNS, ColumnCache
						.getColumnCache().getSecureDNSKeyFileds());
	}

	@Override
	public List<String> getKeyFields(String role) {
		return PermissionCache.getPermissionCache().getSecureDNSMapKeyFileds(
				role);
	}
}
