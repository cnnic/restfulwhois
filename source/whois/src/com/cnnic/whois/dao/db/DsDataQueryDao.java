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

public class DsDataQueryDao extends AbstractDbQueryDao {
	public static final String GET_ALL_DSDATA = "select * from dsData";
	private static final String QUERY_KEY = "$mul$dsData";

	public DsDataQueryDao(List<AbstractDbQueryDao> dbQueryDaos) {
		super(dbQueryDaos);
	}

	@Override
	public Map<String, Object> query(QueryParam param,
			PageBean... page) throws QueryException {
		Connection connection = null;
		Map<String, Object> map = null;

		try {
			connection = ds.getConnection();
			String selectSql = WhoisUtil.SELECT_LIST_DSDATA + "'"
					+ param.getQ() + "'";
			map = query(connection, selectSql,
					permissionCache.getDsDataMapKeyFileds(param.getRole()), "$mul$dsData",
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
		if (keyName.equals(WhoisUtil.JOINDSDATA)
				|| keyName.equals("$mul$dsData")) {
			fliedName = "DsDataID";
		} else {
			fliedName = WhoisUtil.HANDLE;
		}
		return fliedName;
	}

	@Override
	public Map<String, Object> getAll(QueryParam param) throws QueryException {
		Connection connection = null;
		Map<String, Object> map = null;
		try {
			connection = ds.getConnection();
			map = query(connection, GET_ALL_DSDATA,
					permissionCache.getDsDataMapKeyFileds(param.getRole()), QUERY_KEY,
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
	public QueryType getQueryType() {
		return QueryType.DSDATA;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.DSDATA.equals(queryType);
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return QueryJoinType.DSDATA.equals(queryJoinType);
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle,
			QueryParam param, Connection connection) throws SQLException {
		return querySpecificJoinTable(key, handle,
				WhoisUtil.SELECT_JOIN_LIST_DSDATA, param, connection,
				permissionCache.getDsDataMapKeyFileds(param.getRole()));
	}
}