package com.cnnic.whois.dao.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.NameServerIndex;
import com.cnnic.whois.bean.index.SearchCondition;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.index.SearchResult;
import com.cnnic.whois.util.WhoisUtil;

public class SearchNsQueryDao extends AbstractSearchQueryDao {

	public SearchNsQueryDao(List<AbstractDbQueryDao> dbQueryDaos) {
		super(dbQueryDaos);
	}

	@Override
	public Map<String, Object> query(QueryParam param,
			PageBean... pageParam) throws QueryException {
		Connection connection = null;
		Map<String, Object> map = null;
		SearchCondition searchCondition = new SearchCondition(param.getQ());
		PageBean page = pageParam[0];
		int startPage = page.getCurrentPage() - 1;
		startPage = startPage >= 0 ? startPage : 0;
		int start = startPage * page.getMaxRecords();
		searchCondition.setStart(start);
		searchCondition.setRow(page.getMaxRecords());
		SearchResult<NameServerIndex> result = nameServerIndexService
				.queryNameServers(searchCondition);
		page.setRecordsCount(Long.valueOf(result.getTotalResults()).intValue());
		try {
			connection = ds.getConnection();
			String selectSql = WhoisUtil.SELECT_LIST_NAMESREVER + "'"
					+ param.getQ() + "'";
			Map<String, Object> nsMap = fuzzyQuery(connection, result,
					selectSql, "$mul$nameServer", param);
			if (nsMap != null) {
				map = rdapConformance(map);
				map.putAll(nsMap);
				addTruncatedParamToMap(map, result);
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

	protected Map<String, Object> postHandleFieldsFuzzy(String keyName,
			String format, Map<String, Object> map) {
		Map<String, Object> map_IP = new LinkedHashMap<String, Object>();
		Object IPAddressArray = map.get(WhoisUtil.getDisplayKeyName(
				"IPV4_Addresses"));
		map_IP.put(WhoisUtil.IPV4PREFIX, IPAddressArray);
		IPAddressArray = map.get(WhoisUtil.getDisplayKeyName("IPV6_Addresses"));
		map_IP.put(WhoisUtil.IPV6PREFIX, IPAddressArray);
		map.put(WhoisUtil.IPPREFIX, map_IP);
		map.remove(WhoisUtil.getDisplayKeyName("IPV4_Addresses"));
		map.remove(WhoisUtil.getDisplayKeyName("IPV6_Addresses"));
		return map;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.SEARCHNS;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.SEARCHNS.equals(queryType);
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle,
			QueryParam param, Connection connection) throws SQLException {
		return null;
	}
}