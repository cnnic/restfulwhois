package com.cnnic.whois.view;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import net.sf.json.JSONArray;

import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.dao.query.db.DbQueryExecutor;
import com.cnnic.whois.util.WhoisUtil;

public abstract class AbstractResponseWriter implements ResponseWriter {
	@Autowired
	private DbQueryExecutor dbQueryExecutor ;

	abstract protected String formatKey(String keyName);

	@Override
	public Map<String, Object> format(Map<String, Object> map) {
		Map<String, Object> result = formatMap(map);
		return result;
	}
	
	@Override
	public Map<String, Object> getMapField(QueryType queryType, Map<String, Object> map) {
		if(null != queryType){
			Iterator<String> iterr = map.keySet().iterator();
			String multiKey = null;
			while(iterr.hasNext()){
				String key =  iterr.next();
				if(key.startsWith(WhoisUtil.MULTIPRX)){
					multiKey = key;
				}
			}
			if( null != multiKey){
				Object jsonObj = map.get(multiKey);
				map.remove(multiKey);
				if(queryType.equals(QueryType.SEARCHDOMAIN))
					map.put("domainSearchResults", jsonObj);
				else if(queryType.equals(QueryType.SEARCHENTITY))
					map.put("entitySearchResults", jsonObj);
				else if(queryType.equals(QueryType.SEARCHNS))
					map.put("nameserverSearchResults", jsonObj);
				else
					map.put(multiKey.substring(WhoisUtil.MULTIPRX.length()), jsonObj);
			}
		} 
		return map;
	}
	
	protected boolean isUnusedEntry(Entry<String, Object> entry) {
		String key = entry.getKey();
		boolean endwithId = key.substring(key.length() - 2).toLowerCase()
				.equals("id");
		if (endwithId) {
			return true;
		}
		return false;
	}

	private Map<String, Object> formatMap(Map<String, Object> map) {
		if (null == map) {
			return null;
		}
		QueryType queryType = getQueryType(map);
		if (null != queryType) {
			dbQueryExecutor.formatValue(queryType, map);
		}
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (null == entry.getValue()) {
				continue;
			}
			if (isQueryTypeEntry(entry)) {
				continue;
			}
			if (isUnusedEntry(entry)) {
				continue;
			}
			Object value = formatObject(entry.getValue());
			String formatedKey = formatKey(entry.getKey());
			resultMap.put(formatedKey, value);
		}
		return resultMap;
	}

	private boolean isQueryTypeEntry(Entry<String, Object> entry) {
		String key = entry.getKey();
		return WhoisUtil.QUERY_TYPE.equals(key)
				|| WhoisUtil.QUERY_JOIN_TYPE.equals(key);
	}

	private QueryType getQueryType(Map<String, Object> map) {
		Object queryTypeObj = map.get(WhoisUtil.QUERY_TYPE);
		if (null != queryTypeObj) {
			return QueryType.getQueryType((String) queryTypeObj);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private Object formatObject(Object object) {
		if (object instanceof String) {
			return object;
		}
		if (object instanceof Object[]) {
			return formatArray((Object[]) object);
		} else if (object instanceof JSONArray) {
			return formatJSONArray((JSONArray) object);
		} else if (object instanceof Map) {
			return format((Map<String, Object>) object);
		}
		return object;
	}

	private Object formatJSONArray(JSONArray object) {
		JSONArray result = new JSONArray();
		for (int i = 0; i < object.size(); i++) {
			Object formatedObj = formatObject(object.get(i));
			result.add(formatedObj);
		}
		return result;
	}

	private Object formatArray(Object[] array) {
		Object[] result = new Object[array.length];
		int i = 0;
		for (Object object : array) {
			result[i] = formatObject(object);
			i++;
		}
		return result;
	}

	protected String formatKeyToCamelCaseIfNotJoinKey(String keyName) {
		if (WhoisUtil.JOIN_FIELDS_WITH_CAMEL_STYLE.contains(keyName)) {
			return keyName;
		}
		return this.formatKeyToCamelCase(keyName);
	}

	protected String formatKeyToCamelCase(String keyName) {
		String[] names = keyName.split("_");
		keyName = names[0].toLowerCase();
		for (int i = 1; i < names.length; i++) {
			keyName += names[i];
		}
		return keyName;
	}
	
	protected String delTrim(String data) {
		if (data.startsWith("$mul$"))
			return data.substring("$mul$".length());
		return data.replaceAll(" ", "");
	}
}
