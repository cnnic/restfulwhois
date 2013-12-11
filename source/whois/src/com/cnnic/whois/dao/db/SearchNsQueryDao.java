package com.cnnic.whois.dao.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.Index;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.index.SearchResult;
import com.cnnic.whois.util.WhoisUtil;

@Repository("db.searchNsQueryDao")
public class SearchNsQueryDao extends AbstractSearchQueryDao {

	@Override
	public Map<String, Object> query(QueryParam param) throws QueryException {
		Connection connection = null;
		Map<String, Object> map = null;
		PageBean page = param.getPage();
		SearchResult<? extends Index> result = searchQueryExecutor.query(
				QueryType.SEARCHNS, param);
		try {
			connection = ds.getConnection();
			Map<String, Object> nsMap = fuzzyQuery(connection, result,
					"$mul$nameServer");
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
		Object IPAddressArray = map.get(WhoisUtil
				.getDisplayKeyName("IPV4_Addresses"));
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
}
