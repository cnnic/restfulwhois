package com.cnnic.whois.dao.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.util.WhoisUtil;

public class RemarksQueryDao extends AbstractDbQueryDao {
	public RemarksQueryDao(List<AbstractDbQueryDao> dbQueryDaos) {
		super(dbQueryDaos);
	}

	@Override
	public Map<String, Object> query(QueryParam param,
			PageBean... page) throws QueryException {
		Connection connection = null;
		Map<String, Object> map = null;

		try {
			connection = ds.getConnection();
			String selectSql = WhoisUtil.SELECT_LIST_REMARKS + "'"
					+ param.getQ() + "'";
			map = query(connection, selectSql,
					permissionCache.getRemarksKeyFileds(param.getRole()), "$mul$remarks",
					param);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new QueryException(e);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException se) {
				}
			}
		}
		return map;
	}

	@Override
	protected String getJoinFieldName(String keyName) {
		String fliedName = "";
		if (keyName.equals(WhoisUtil.MULTIPRXREMARKS)) {
			fliedName = keyName.substring(WhoisUtil.MULTIPRX.length()) + "Id";
		} else if (keyName.equals(WhoisUtil.JOINREMARKS)) {
			fliedName = keyName.substring(WhoisUtil.JOINFILEDPRX.length())
					+ "Id";
		} else {
			fliedName = WhoisUtil.HANDLE;
		}
		return fliedName;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.REMARKS;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.REMARKS.equals(queryType);
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return QueryJoinType.REMARKS.equals(queryJoinType);
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle,
			QueryParam param, Connection connection) throws SQLException {
		return querySpecificJoinTable(key, handle,
				WhoisUtil.SELECT_JOIN_LIST_REMARKS, param, connection,
				permissionCache.getRemarksKeyFileds(param.getRole()));
	}
}