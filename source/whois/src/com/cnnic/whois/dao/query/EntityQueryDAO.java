package com.cnnic.whois.dao.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.EntityIndex;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.EntityIndexService;
import com.cnnic.whois.service.index.SearchResult;
import com.cnnic.whois.util.WhoisUtil;

public abstract class EntityQueryDAO extends AbstractDbQueryDAO {
	private AbstractSearchQueryDAO searchQueryDAO;
	protected EntityIndexService entityIndexService = EntityIndexService
			.getIndexService();

	@Override
	public Map<String, Object> query(String q, String role, String format,
			PageBean... page) throws QueryException {
		SearchResult<EntityIndex> result = entityIndexService
				.preciseQueryEntitiesByHandleOrName(q);
		Map<String, Object> map = null;
		map = searchQueryDAO.fuzzyQuery(result, "$mul$entity", role, format);
		map = rdapConformance(map);
		return map;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.ENTITY.equals(queryType);
	}

	protected String getJoinSql(String handle) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Map<String, Object> queryJoins(String handle, String role,
			String format) {
		String sql = getJoinSql(handle);
		PreparedStatement stmt = null;
		ResultSet results = null;
		Connection connection = null;
		try {
			connection = ds.getConnection();
			List<String> keyFlieds = permissionCache
					.getDNRDomainKeyFileds(role);
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
				mapInfo.put(WhoisUtil.JOINFILEDPRX + QueryJoinType.ENTITIES,
						list.toArray());
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
	public String getJoinFieldIdColumnName() {
		return WhoisUtil.HANDLE;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.ENTITY;
	}

	@Override
	public boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return false;
	}
}
