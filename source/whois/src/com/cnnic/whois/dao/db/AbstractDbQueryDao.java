package com.cnnic.whois.dao.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.DomainIndexService;
import com.cnnic.whois.service.EntityIndexService;
import com.cnnic.whois.service.NameServerIndexService;
import com.cnnic.whois.util.PermissionCache;
import com.cnnic.whois.util.WhoisUtil;

public abstract class AbstractDbQueryDao implements QueryDao{
	//	private static AbstractDbQueryDao queryDAO = new AbstractDbQueryDao();
	protected DataSource ds;
	protected PermissionCache permissionCache = PermissionCache
			.getPermissionCache();
	protected DomainIndexService domainIndexService = DomainIndexService.getIndexService();
	protected NameServerIndexService nameServerIndexService = NameServerIndexService.getIndexService();
	protected EntityIndexService entityIndexService = EntityIndexService.getIndexService();
	protected List<AbstractDbQueryDao> dbQueryDaos;
	protected abstract boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType);
	public abstract Object querySpecificJoinTable(String key, String handle,
			Connection connection)
			throws SQLException ;
	@Override
	public Map<String, Object> getAll()
			throws QueryException {
		throw new UnsupportedOperationException();
	}
	/**
	 * Connect to the datasource in the constructor
	 * 
	 * @throws IllegalStateException
	 */
	public AbstractDbQueryDao(List<AbstractDbQueryDao> dbQueryDaos) {
		super();
		this.dbQueryDaos = dbQueryDaos;
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(WhoisUtil.JNDI_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException(e.getMessage());
		}
	}

	protected Map<String, Object> query(Connection connection, String sql,
			List<String> keyFlieds, String keyName)
			throws SQLException {
		PreparedStatement stmt = null; 
		ResultSet results = null;
		try {
			stmt = connection.prepareStatement(sql);
			results = stmt.executeQuery();
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			while (results.next()) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				putQueryType(map);
				for (int i = 0; i < keyFlieds.size(); i++) {
					Object resultsInfo = null;
					String field = keyFlieds.get(i);
					if (field.startsWith(WhoisUtil.ARRAYFILEDPRX)) {
						String key = field.substring(WhoisUtil.ARRAYFILEDPRX.length());
						resultsInfo = results.getString(key);
						String[] values = null;
						if (resultsInfo != null) {
							values = resultsInfo.toString().split(WhoisUtil.VALUEARRAYPRX);
						}
						map.put(key, values);
					} else if (field.startsWith(WhoisUtil.EXTENDPRX)) {
						resultsInfo = results.getString(field);
						map.put(field.substring(WhoisUtil.EXTENDPRX.length()), resultsInfo);
					} else if (field.startsWith(WhoisUtil.JOINFILEDPRX)) {
						String fliedName = getJoinFieldName(keyName);
						String key = field.substring(WhoisUtil.JOINFILEDPRX.length());
						Object value = queryJoinTable(field,
								results.getString(fliedName),
								connection);
						if (value != null)
							map.put(key, value);//map or map-list
					} else {
						preHandleNormalField(keyName, results, map, field);
						resultsInfo = results.getObject(field) == null ? "": results.getObject(field);
						map.put(field, resultsInfo);//a different format have different name;
					}
				}
				map = postHandleFields(keyName, map);
				list.add(map);
			}
			if (list.size() == 0){
				return null;
			}
			Map<String, Object> mapInfo = new LinkedHashMap<String, Object>();
			// link , remark and notice change to array
			if(keyName.equals(WhoisUtil.JOINLINKFILED)|| 
					keyName.equals(WhoisUtil.JOINNANOTICES) ||
					keyName.equals(WhoisUtil.JOINREMARKS) ||
					keyName.equals(WhoisUtil.MULTIPRXLINK ) ||
//					keyName.equals(WhoisUtil.MULTIPRXNOTICES )||
					keyName.equals(WhoisUtil.MULTIPRXREMARKS) ||
					keyName.equals(WhoisUtil.JOINPUBLICIDS) ||
					keyName.equals(WhoisUtil.JOINDSDATA)||
					keyName.equals(WhoisUtil.JOINKEYDATA)){
				mapInfo.put(keyName, list.toArray());
			}else{
				if (list.size() > 1) {
					mapInfo.put(keyName, list.toArray());
				} else {
					mapInfo = list.get(0);
				}
			}
			return mapInfo;
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

	protected String getJoinFieldName(String keyName) {
		return WhoisUtil.HANDLE;
	}

	protected Map<String, Object> postHandleFields(String keyName,
			Map<String, Object> map) throws SQLException {
		return map;
	}

	protected void preHandleNormalField(String keyName,
			ResultSet results, Map<String, Object> map, String field)
			throws SQLException {}

	/**
	 * Determine the different types of schedule and query information according
	 * to the parameters
	 * 
	 * @param key
	 * @param handle
	 * @param sql
	 * @param role
	 * @param connection
	 * @return Returns the schedule information content
	 * @throws SQLException
	 */
	public Object queryJoinTable(String key, String handle,
			Connection connection) throws SQLException {
		String keyWithoutJoinPrefix = key.substring(WhoisUtil.JOINFILEDPRX.length());
		QueryJoinType joinType = QueryJoinType.getQueryJoinType(keyWithoutJoinPrefix);
		QueryType queryType = getQueryType();
		for (AbstractDbQueryDao dbQueryDao : dbQueryDaos) {
			if (dbQueryDao.supportJoinType(queryType, joinType)) {
				Object result = dbQueryDao.querySpecificJoinTable(key, handle,
						connection);
				if(result instanceof Map){
					addQueryJoinTypeEntry(joinType, result);
				}else if(result instanceof Object[]){
					for(Object obj:(Object[])result){
						addQueryJoinTypeEntry(joinType, obj);
					}
				}
				return result;
			}
		}
		return null;
	}
	private void addQueryJoinTypeEntry(QueryJoinType joinType, Object result) {
		Map map = (Map)result;
		map.put("queryJoinType", joinType.getName());
	}

	/**
	 * query schedule information
	 * 
	 * @param key
	 * @param handle
	 * @param sql
	 * @param role
	 * @param connection
	 * @param keyFlieds
	 * @return Returns the schedule information content
	 * @throws SQLException
	 */
	public Object querySpecificJoinTable(String key, String handle, String sql,
			Connection connection, List<String> keyFlieds)
			throws SQLException {

		Map<String, Object> map = query(connection, sql + "'" + handle + "'",
				keyFlieds, key);
		if (map != null) {
			if (null == map.get(key)) {
				return map;
			} else if (!map.isEmpty()) {
				return map.get(key);
			}
		}
		return null;
	}

	public static Map<String, Object> rdapConformance(Map<String, Object> map){
		if(map == null){
			map = new LinkedHashMap<String, Object>();
		}
		Object[] conform = new Object[1];
		conform[0] = WhoisUtil.RDAPCONFORMANCE;
		map.put(WhoisUtil.RDAPCONFORMANCEKEY, conform);
		return map;
	}
	
	public List<String> getKeyFields(String role) {
		throw new UnsupportedOperationException();
	}
	
	protected Map<String, Object> formatValue(Map<String, Object> map){
		return map;
	}
	
	private void putQueryType(Map<String, Object> map){
		if(map == null){
			return;
		}
		map.put(WhoisUtil.QUERY_TYPE, getQueryType().getName());
	}
}