package com.cnnic.whois.dao.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;

public abstract class AbstractDomainQueryDAO extends AbstractDbQueryDAO {
	private static final String QUERY_KEY = "$mul$domains";

	protected Map<String, Object> doQquery(List<String> keyFlieds, String sql,
			String role, String format) {
		PreparedStatement stmt = null;
		ResultSet results = null;
		Connection connection = null;
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(sql);
			results = stmt.executeQuery();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			while (results.next()) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				for (int i = 0; i < keyFlieds.size(); i++) {
					String field = keyFlieds.get(i);
					handleField(role, format, results, map, field);
				}
				list.add(map);
			}
			if (list.size() == 0) {
				return null;
			}
			Map<String, Object> mapInfo = new LinkedHashMap<String, Object>();
			if (list.size() > 1) {
				mapInfo.put(QUERY_KEY, list.toArray());
			} else {
				mapInfo = list.get(0);
			}
			return mapInfo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (results != null) {
				try {
					results.close();
				} catch (SQLException se) {
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException se) {
				}
			}
		}
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.DOMAIN.equals(queryType);
	}

	@Override
	public String getJoinFieldIdColumnName() {
		throw new UnsupportedOperationException();// TODO: superClass
	}

	@Override
	public Map<String, Object> queryJoins(String handle, String role,
			String format) {
		throw new UnsupportedOperationException();
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.DOMAIN;
	}

	@Override
	public boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return false;
	}
}
