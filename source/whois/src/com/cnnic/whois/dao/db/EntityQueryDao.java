package com.cnnic.whois.dao.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.Index;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.index.SearchResult;
import com.cnnic.whois.util.ColumnCache;
import com.cnnic.whois.util.WhoisUtil;
@Repository
public class EntityQueryDao extends AbstractSearchQueryDao {
	private static final String MAP_KEY = "$mul$entity";
	public static final String GET_ALL_DNRENTITY = "select * from DNREntity ";
	public static final String GET_ALL_RIRENTITY = "select * from RIREntity ";

	@Override
	public Map<String, Object> query(QueryParam param)
			throws QueryException {
		SearchResult<? extends Index> result = searchQueryExecutor
				.query(QueryType.SEARCHENTITY, param);
		Connection connection = null;
		Map<String, Object> map = null;
		try {
			connection = ds.getConnection();
			map = fuzzyQuery(connection, result, MAP_KEY);
			map = rdapConformance(map);
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

	@SuppressWarnings("unused")
	private Map<String, Object> queryDNREntity(String queryInfo, String role,
			String format) throws QueryException {
		Connection connection = null;
		Map<String, Object> map = null;

		try {
			connection = ds.getConnection();
			String selectSql = WhoisUtil.SELECT_LIST_DNRENTITY + "'"
					+ queryInfo + "'";
			Map<String, Object> entityMap = query(connection, selectSql,
					ColumnCache.getColumnCache().getDNREntityKeyFileds(),
					MAP_KEY);
			if (entityMap != null) {
				map = rdapConformance(map);
				map.putAll(entityMap);
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
		return map;
	}

	@SuppressWarnings("unused")
	private Map<String, Object> queryRIREntity(String queryInfo, String role,
			String format) throws QueryException {
		Connection connection = null;
		Map<String, Object> map = null;

		try {
			connection = ds.getConnection();
			String selectSql = WhoisUtil.SELECT_LIST_RIRENTITY + "'"
					+ queryInfo + "'";
			Map<String, Object> entityMap = query(connection, selectSql,
					ColumnCache.getColumnCache().getRIREntityKeyFileds(),
					MAP_KEY);
			if (entityMap != null) {
				map = rdapConformance(map);
				map.putAll(entityMap);
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
		return map;
	}

	@Override
	protected Map<String, Object> formatValue(Map<String, Object> map){
		if (map.containsKey(WhoisUtil.QUERY_JOIN_TYPE) && map.containsKey("events")) {
			String entityHandle = (String) map.get(WhoisUtil.HANDLE);
			Map<String, Object> map_Events = new LinkedHashMap<String, Object>();
			map_Events = (Map<String, Object>) map.get("events");
			if (map_Events.containsKey("Event_Actor")) {
				String eventactor = (String) map_Events.get("Event_Actor");
				if (entityHandle.equals(eventactor)) {
					map_Events.remove("Event_Actor");
					List<Map<String, Object>> listEvents = new ArrayList<Map<String, Object>>();
					listEvents.add(map_Events);
					map.put("asEventActor", listEvents.toArray());
					map.remove("events");
				}
			}
		}
		map = WhoisUtil.toVCard(map);
		return map;
	}

	protected Map<String, Object> getAllEntity(String sql, List<String> keyFields)
			throws QueryException {
		Connection connection = null;
		Map<String, Object> map = null;
		try {
			connection = ds.getConnection();
			Map<String, Object> entityMap = query(connection, sql, keyFields,
					MAP_KEY);
			if (entityMap != null) {
				map = rdapConformance(map);
				map.putAll(entityMap);
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
		return map;
	}

	private void getListFromMap(Map<String, Object> allEntity,
			List<Map<String, Object>> mapList) {
		if (null == allEntity) {
			return;
		}
		if (null != allEntity.get("Handle")) {// only one result
			mapList.add(allEntity);
		} else {
			Object[] entities = (Object[]) allEntity.get(MAP_KEY);
			for (Object entity : entities) {
				mapList.add((Map<String, Object>) entity);
			}
		}
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.ENTITY;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.ENTITY.equals(queryType);
	}
}
