package com.cnnic.whois.dao.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.Index;
import com.cnnic.whois.dao.search.SearchQueryExecutor;
import com.cnnic.whois.service.QueryService;
import com.cnnic.whois.service.index.SearchResult;
import com.cnnic.whois.util.WhoisUtil;

public abstract class AbstractSearchQueryDao extends AbstractDbQueryDao{
	@Autowired
	protected SearchQueryExecutor searchQueryExecutor;
	public Object querySpecificJoinTable(String key, String handle,
			Connection connection)
			throws SQLException{
		throw new UnsupportedOperationException();
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return false;
	}
	protected void addTruncatedParamToMap(Map<String, Object> map,
			SearchResult<? extends Index> result) {
		if(result.getTotalResults()>QueryService.MAX_SIZE_FUZZY_QUERY){
			map.put(WhoisUtil.SEARCH_RESULTS_TRUNCATED_EKEY, true);
		}
	}
	
	protected Map<String, Object> fuzzyQuery(Connection connection, SearchResult<? extends Index> domains,
			String keyName)
			throws SQLException {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for(Index index:domains.getResultList()){
				index.initPropValueMap();
				List<String> keyFlieds = permissionCache.getKeyFiledsByClass(index);
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				putQueryType(map, index);
				for (int i = 0; i < keyFlieds.size(); i++) {
					Object resultsInfo = null;
					String field = keyFlieds.get(i);
					if (field.startsWith(WhoisUtil.ARRAYFILEDPRX)) {
						String key = field.substring(WhoisUtil.ARRAYFILEDPRX.length());
						resultsInfo = index.getPropValue(key);
						String[] values = null;
						if (resultsInfo != null) {
							values = resultsInfo.toString().split(WhoisUtil.VALUEARRAYPRX);
						}
						map.put(key, values);
					} else if (field.startsWith(WhoisUtil.EXTENDPRX)) {
						resultsInfo = index.getPropValue(field);
						map.put(field.substring(WhoisUtil.EXTENDPRX.length()), resultsInfo);
					} else if (field.startsWith(WhoisUtil.JOINFILEDPRX)) {
						String key = field.substring(WhoisUtil.JOINFILEDPRX.length());
						Object value = queryJoinTable(field,
								index.getHandle(),
								connection);
						if (value != null)
							map.put(key, value);
					} else {
						resultsInfo = index.getPropValue(field);
						resultsInfo = resultsInfo==null?"":resultsInfo;
//						CharSequence id = "id";
//						if(!keyName.equals(WhoisUtil.JOINPUBLICIDS) && field.substring(field.length() - 2).equals(id) && !format.equals("application/html")){
//							continue;
//						}else{
						map.put(field, resultsInfo);//a different format have different name;
//						}
					}
				}
				map = postHandleFieldsFuzzy(keyName, map);
				list.add(map);
			}
			if (list.size() == 0){
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

	protected Map<String, Object> postHandleFieldsFuzzy(String keyName,
			 Map<String, Object> map) {
		if (keyName.equals("$mul$nameServer") || keyName.equals("$join$nameServer")){
			Map<String, Object> map_IP = new LinkedHashMap<String, Object>();
			Object IPAddressArray = map.get("IPV4_Addresses");
			map_IP.put(WhoisUtil.IPV4PREFIX, IPAddressArray);
			IPAddressArray = map.get("IPV6_Addresses");
			map_IP.put(WhoisUtil.IPV6PREFIX, IPAddressArray);
			map.put(WhoisUtil.IPPREFIX, map_IP);
			map.remove("IPV4_Addresses");
			map.remove("IPV6_Addresses");
		}
		//vcard format
		if(keyName.equals(WhoisUtil.MULTIPRXENTITY)){
			map = WhoisUtil.toVCard(map);
		}
		return map;
	}
	
	private void putQueryType(Map<String, Object> map,Index index){
		if(map == null){
			return;
		}
		map.put(WhoisUtil.QUERY_TYPE, index.getDocType());
	}
}