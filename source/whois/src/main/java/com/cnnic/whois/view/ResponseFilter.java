package com.cnnic.whois.view;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnnic.whois.dao.query.db.DbQueryExecutor;
import com.cnnic.whois.util.WhoisUtil;

@Component
public class ResponseFilter {
	@Autowired
	private DbQueryExecutor dbQueryExecutor;

	public Map<String, Object> removeNoticesEntries(Map<String, Object> map) {
		if (null == map) {
			return map;
		}
		for (Iterator<Entry<String, Object>> it = map.entrySet().iterator(); it
				.hasNext();) {
			Entry<String, Object> entry = it.next();
			removeUnAuthedEntriesObject(entry.getValue());
		}
		return map;
	}

	private Map<String, Object> removeUnAuthedEntriesMap(
			Map<String, Object> map) {
		if (null == map) {
			return map;
		}
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		for (Iterator<Entry<String, Object>> it = map.entrySet().iterator(); it
				.hasNext();) {
			Entry<String, Object> entry = it.next();
			if (!entry.getKey().equals(WhoisUtil.NOTICES)) {
				resultMap.put(entry.getKey(), entry.getValue());
				removeUnAuthedEntriesObject(entry.getValue());
			}
		}
		return resultMap;
	}

	private void removeUnAuthedEntriesObject(Object object) {
		if (null == object) {
			return;
		}
		if (object instanceof Object[]) {
			removeUnAuthedEntriesArray((Object[]) object);
		} else if (object instanceof JSONArray) {
			removeUnAuthedEntriesJsonArray((JSONArray) object);
		} else if (object instanceof Map) {
			Map<String, Object> map = (Map<String, Object>) object;
			Map<String, Object> result = removeUnAuthedEntriesMap(map);
			map.clear();
			map.putAll(result);
		}
	}

	private void removeUnAuthedEntriesJsonArray(JSONArray object) {
		for (int i = 0; i < object.size(); i++) {
			removeUnAuthedEntriesObject(object.get(i));
		}
	}

	private void removeUnAuthedEntriesArray(Object[] array) {
		for (Object object : array) {
			removeUnAuthedEntriesObject(object);
		}
	}
}