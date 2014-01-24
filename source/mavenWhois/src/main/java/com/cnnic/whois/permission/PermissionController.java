package com.cnnic.whois.permission;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.dao.query.db.DbQueryExecutor;
import com.cnnic.whois.util.AuthenticationHolder;
import com.cnnic.whois.util.WhoisUtil;

@Component
public class PermissionController {
	@Autowired
	private DbQueryExecutor dbQueryExecutor;

	public Map<String, Object> removeUnAuthedEntries(Map<String, Object> map) {
		String role = AuthenticationHolder.getAuthentication().getRole();
		if (null == map) {
			return map;
		}
		Object[] multiObjs = getMultiObjs(map);
		if (null != multiObjs) {
			removeUnAuthedEntriesObject(multiObjs, role);
			return map;
		}
		return removeUnAuthedEntriesMap(map, role);
	}

	private Map<String, Object> removeUnAuthedEntriesMap(
			Map<String, Object> map, String role) {
		if (null == map) {
			return map;
		}
		QueryType queryType = getQueryType(map);
		List<String> dnrKeyFields = dbQueryExecutor.getKeyFields(queryType,
				role);
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		List<String> keyFieldsWithoutPrefix = this
				.removeFieldPrefix(dnrKeyFields);
		keyFieldsWithoutPrefix.add(WhoisUtil.RDAPCONFORMANCEKEY);
		keyFieldsWithoutPrefix.add(WhoisUtil.QUERY_TYPE);
		keyFieldsWithoutPrefix.add(WhoisUtil.QUERY_JOIN_TYPE);
		for (Iterator<Entry<String, Object>> it = map.entrySet().iterator(); it
				.hasNext();) {
			Entry<String, Object> entry = it.next();
			if (keyFieldsWithoutPrefix.contains(entry.getKey())) {
				resultMap.put(entry.getKey(), entry.getValue());
				removeUnAuthedEntriesObject(entry.getValue(), role);
			}
		}
		return resultMap;
	}

	private Object[] getMultiObjs(Map<String, Object> map) {
		for (Iterator<Entry<String, Object>> it = map.entrySet().iterator(); it
				.hasNext();) {
			Entry<String, Object> entry = it.next();
			if (entry.getKey().startsWith(WhoisUtil.MULTIPRX)) {
				return (Object[]) entry.getValue();
			}
		}
		return null;
	}

	private List<String> removeFieldPrefix(List<String> fields) {
		List<String> result = new ArrayList<String>();
		for (String field : fields) {
			if (field.startsWith(WhoisUtil.JOINFILEDPRX)) {
				field = field.substring(WhoisUtil.JOINFILEDPRX.length());
			} else if (field.startsWith(WhoisUtil.MULTIPRX)) {
				field = field.substring(WhoisUtil.MULTIPRX.length());
			} else if (field.startsWith(WhoisUtil.ARRAYFILEDPRX)) {
				field = field.substring(WhoisUtil.ARRAYFILEDPRX.length());
			} else if (field.startsWith(WhoisUtil.EXTENDPRX)) {
				field = field.substring(WhoisUtil.EXTENDPRX.length());
			}
			result.add(field);
		}
		return result;
	}

	private QueryType getQueryType(Map<String, Object> map) {
		Object queryTypeObj = map.get(WhoisUtil.QUERY_TYPE);
		if (null != queryTypeObj) {
			return QueryType.getQueryType((String) queryTypeObj);
		}
		return null;
	}

	private void removeUnAuthedEntriesObject(Object object, String role) {
		if (null == object) {
			return;
		}
		if (object instanceof Object[]) {
			removeUnAuthedEntriesArray((Object[]) object, role);
		} else if (object instanceof JSONArray) {
			removeUnAuthedEntriesJsonArray((JSONArray) object, role);
		} else if (object instanceof Map) {
			Map<String, Object> map = (Map<String, Object>) object;
			Map<String, Object> result = removeUnAuthedEntriesMap(map, role);
			map.clear();
			map.putAll(result);
		}
	}

	private void removeUnAuthedEntriesJsonArray(JSONArray object, String role) {
		for (int i = 0; i < object.size(); i++) {
			removeUnAuthedEntriesObject(object.get(i), role);
		}
	}

	private void removeUnAuthedEntriesArray(Object[] array, String role) {
		for (Object object : array) {
			removeUnAuthedEntriesObject(object, role);
		}
	}
}