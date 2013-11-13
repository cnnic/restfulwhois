package com.cnnic.whois.dao.query;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.Index;
import com.cnnic.whois.service.DomainIndexService;
import com.cnnic.whois.service.index.SearchResult;
import com.cnnic.whois.util.PermissionCache;
import com.cnnic.whois.util.WhoisUtil;

public abstract class AbstractSearchQueryDAO implements QueryDao {
	protected PermissionCache permissionCache = PermissionCache
			.getPermissionCache();
	protected DomainIndexService domainIndexService = DomainIndexService
			.getIndexService();
	private AbstractDbQueryDAO dbQueryDAO;

	abstract protected Map<String, Object> postHandleField(
			Map<String, Object> map, String format);

	protected Map<String, Object> fuzzyQuery(
			SearchResult<? extends Index> indexs, String keyName, String role,
			String format) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Index index : indexs.getResultList()) {
			index.initPropValueMap();
			List<String> keyFlieds = permissionCache.getKeyFiledsByClass(index,
					role);
			Map<String, Object> map = new LinkedHashMap<String, Object>();
			handleFieldsFuzzy(keyName, role, format, index, keyFlieds, map);
			map = postHandleField(map, format);
			list.add(map);
		}
		if (list.size() == 0) {
			return null;
		}
		Map<String, Object> mapInfo = new LinkedHashMap<String, Object>();
		if (list.size() > 1) {
			mapInfo.put(keyName, list.toArray());
		} else {
			mapInfo = list.get(0);
		}
		return mapInfo;
	}

	protected void handleFieldsFuzzy(String keyName, String role,
			String format, Index index, List<String> keyFlieds,
			Map<String, Object> map) {
		for (int i = 0; i < keyFlieds.size(); i++) {
			Object resultsInfo = null;
			String field = keyFlieds.get(i);
			if (field.startsWith(WhoisUtil.ARRAYFILEDPRX)) {
				String key = field.substring(WhoisUtil.ARRAYFILEDPRX.length());
				resultsInfo = index.getPropValue(key);
				String[] values = null;
				if (resultsInfo != null) {
					values = resultsInfo.toString().split(
							WhoisUtil.VALUEARRAYPRX);
				}
				map.put(WhoisUtil.getDisplayKeyName(key, format), values);
			} else if (field.startsWith(WhoisUtil.EXTENDPRX)) {
				resultsInfo = index.getPropValue(field);
				map.put(field.substring(WhoisUtil.EXTENDPRX.length()),
						resultsInfo);
			} else if (field.startsWith(WhoisUtil.JOINFILEDPRX)) {
				QueryType queryType = getQueryType();
				dbQueryDAO.queryJoinEntity(queryType, index.getHandle(), role,
						format, map, field);
			} else {
				resultsInfo = index.getPropValue(field);
				resultsInfo = resultsInfo == null ? "" : resultsInfo;
				CharSequence id = "id";
				boolean fieldEndwithId = WhoisUtil
						.getDisplayKeyName(field, format)
						.substring(field.length() - 2).equals(id);
				if (fieldEndwithId && !format.equals("application/html")) {
					continue;
				} else {
					map.put(WhoisUtil.getDisplayKeyName(field, format),
							resultsInfo);
				}
			}
		}
	}
}