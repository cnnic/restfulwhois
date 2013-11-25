package com.cnnic.whois.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.util.WhoisUtil;

public class RefirectionQueryDao extends AbstractDbQueryDao {
	public RefirectionQueryDao(List<AbstractDbQueryDao> dbQueryDaos) {
		super(dbQueryDaos);
	}

	@Override
	public Map<String, Object> query(QueryParam param,
			PageBean... page) throws QueryException {
		// TODO Auto-generated method stub
		return null;
	}

	public void queryRedirection(String tableName, String queryInfo)
			throws QueryException, RedirectExecption {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet results = null;
		String selectSql = "";

		if (tableName.equals(WhoisUtil.AUTNUM)) {
			int queryPara = Integer.parseInt(queryInfo);
			selectSql = WhoisUtil.SELECT_URL_AUTNUM_EDIRECTION1 + queryPara
					+ WhoisUtil.SELECT_URL_AUTNUM_EDIRECTION2 + queryPara;
		} else {
			queryInfo = queryInfo.substring(queryInfo.lastIndexOf(".") + 1);
			selectSql = WhoisUtil.SELECT_URL_DOAMIN_REDIRECTION + "'"
					+ queryInfo + "'";
		}

		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(selectSql);
			results = stmt.executeQuery();
			if (results.next()) {
				throw new RedirectExecption(results.getString("redirectURL"));
			}
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
		return false;
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle,
			QueryParam param, Connection connection) throws SQLException {
		throw new UnsupportedOperationException();
	}
}