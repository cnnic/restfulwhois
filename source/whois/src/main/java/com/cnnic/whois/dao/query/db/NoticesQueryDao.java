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
public class NoticesQueryDao extends AbstractDbQueryDao {
	public static final String GET_ALL_NOTICES = "select * from notices ";

	@Override
	public Map<String, Object> query(QueryParam param) throws QueryException {
		Map<String, Object> map = null;
		try {
			String selectSql = WhoisUtil.SELECT_LIST_NOTICES + "'"
					+ param.getQ() + "'";
			map = query(selectSql, columnCache
					.getNoticesKeyFileds(), "$mul$notices");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return map;
	}

	@Override
	public Map<String, Object> getAll() throws QueryException {
		Map<String, Object> map = null;
		try {
			map = query(GET_ALL_NOTICES, columnCache.getNoticesKeyFileds(), "$mul$notices");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return map;
	}

	@Override
	protected String getJoinFieldName(String keyName) {
		String fliedName = "";
		if (keyName.equals(WhoisUtil.MULTIPRXNOTICES)) {
			fliedName = keyName.substring(WhoisUtil.MULTIPRX.length()) + "Id";
		} else if (keyName.equals(WhoisUtil.JOINNANOTICES)) {
			fliedName = keyName.substring(WhoisUtil.JOINFILEDPRX.length())
					+ "Id";
		} else {
			fliedName = WhoisUtil.HANDLE;
		}
		return fliedName;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.NOTICES;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.NOTICES.equals(queryType);
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return QueryJoinType.NOTICES.equals(queryJoinType);
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle) throws SQLException {
		return querySpecificJoinTable(key, handle,
				WhoisUtil.SELECT_JOIN_LIST_NOTICES, columnCache.getNoticesKeyFileds());
	}

	@Override
	public List<String> getKeyFields(String role) {
		return permissionCache.getNoticesKeyFileds(role);
	}
}
