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

public class KeyDataQueryDao extends AbstractDbQueryDao {
	public KeyDataQueryDao(List<AbstractDbQueryDao> dbQueryDaos) {
		super(dbQueryDaos);
	}

	@Override
	public Map<String, Object> query(QueryParam param,
			PageBean... page) throws QueryException {
		Connection connection = null;
		Map<String, Object> map = null;

		try {
			connection = ds.getConnection();
			String selectSql = WhoisUtil.SELECT_LIST_KEYDATA + "'"
					+ param.getQ() + "'";
			map = query(connection, selectSql,
					permissionCache.getKeyDataMapKeyFileds(param.getRole()),
					"$mul$keyData", param);
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
		if (keyName.equals(WhoisUtil.JOINKEYDATA)
				|| keyName.equals("$mul$keyData")) {
			fliedName = "KeyDataID";
		} else {
			fliedName = WhoisUtil.HANDLE;
		}
		return fliedName;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.KEYDATA;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.KEYDATA.equals(queryType);
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return QueryJoinType.KEYDATA.equals(queryJoinType);
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle,
			QueryParam param, Connection connection) throws SQLException {
		return querySpecificJoinTable(key, handle,
				WhoisUtil.SELECT_JOIN_LIST_KEYDATA, param, connection,
				permissionCache.getKeyDataMapKeyFileds(param.getRole()));
	}
}