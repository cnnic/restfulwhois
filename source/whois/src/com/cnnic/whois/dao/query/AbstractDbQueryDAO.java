package com.cnnic.whois.dao.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.service.EntityIndexService;
import com.cnnic.whois.util.PermissionCache;
import com.cnnic.whois.util.WhoisUtil;

public abstract class AbstractDbQueryDAO implements QueryDao {
	protected DataSource ds;
	List<AbstractDbQueryDAO> queryDaos;
	protected PermissionCache permissionCache = PermissionCache
			.getPermissionCache();
	protected EntityIndexService entityIndexService = EntityIndexService
			.getIndexService();

	protected abstract boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType);

	protected abstract String getJoinFieldIdColumnName();

	protected abstract Map<String, Object> queryJoins(String handle,
			String role, String format);

	protected void handleField(String role, String format, ResultSet results,
			Map<String, Object> map, String field) throws SQLException {
		Object resultsInfo;
		if (field.startsWith(WhoisUtil.ARRAYFILEDPRX)) {
			String key = field.substring(WhoisUtil.ARRAYFILEDPRX.length());
			resultsInfo = results.getString(key);
			String[] values = null;
			if (resultsInfo != null) {
				values = resultsInfo.toString().split(WhoisUtil.VALUEARRAYPRX);
			}
			map.put(WhoisUtil.getDisplayKeyName(key, format), values);
		} else if (field.startsWith(WhoisUtil.EXTENDPRX)) {
			resultsInfo = results.getString(field);
			map.put(field.substring(WhoisUtil.EXTENDPRX.length()), resultsInfo);
		} else if (field.startsWith(WhoisUtil.JOINFILEDPRX)) {
			String joinFieldIdColumnName = getJoinFieldIdColumnName();
			String joinFieldValue = results.getString(joinFieldIdColumnName);
			QueryType queryType = getQueryType();
			queryJoinEntity(queryType, joinFieldValue, role, format, map, field);
		} else {
			queryNormalField(format, results, map, field);
		}
	}

	protected void queryNormalField(String format, ResultSet results,
			Map<String, Object> map, String field) throws SQLException {
		Object resultsInfo;
		resultsInfo = results.getObject(field) == null ? "" : results
				.getObject(field);

		CharSequence id = "id";
		boolean fieldEndwithId = WhoisUtil.getDisplayKeyName(field, format)
				.substring(field.length() - 2).equals(id);
		if (fieldEndwithId && !format.equals("application/html")) {
			// continue;
		} else {
			map.put(WhoisUtil.getDisplayKeyName(field, format), resultsInfo);
		}
	}

	public void queryJoinEntity(QueryType queryType, String handle,
			String role, String format, Map<String, Object> map, String field) {
		String key = field.substring(WhoisUtil.JOINFILEDPRX.length());
		QueryJoinType joinType = QueryJoinType.getQueryJoinType(key);
		for (AbstractDbQueryDAO queryDao : queryDaos) {
			if (queryDao.supportJoinType(queryType, joinType)) {
				Object value = queryDao.queryJoins(handle, role, format);
				if (value != null) {
					map.put(key, value);
				}
				break;
			}
		}
	}

	protected Map<String, Object> rdapConformance(Map<String, Object> map) {
		if (map == null) {
			map = new LinkedHashMap<String, Object>();
		}
		Object[] conform = new Object[1];
		conform[0] = WhoisUtil.RDAPCONFORMANCE;
		map.put(WhoisUtil.RDAPCONFORMANCEKEY, conform);
		return map;
	}
}