package com.cnnic.whois.dao.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.index.Index;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.service.index.SearchResult;
import com.cnnic.whois.util.WhoisUtil;

@Repository("db.searchEntityQueryDao")
public class SearchEntityQueryDao extends AbstractSearchQueryDao {

	@Override
	public Map<String, Object> query(QueryParam param) throws QueryException {
		SearchResult<? extends Index> result = searchQueryExecutor.query(
				QueryType.SEARCHENTITY, param);
		Connection connection = null;
		Map<String, Object> map = null;
		try {
			connection = ds.getConnection();
			Map<String, Object> entityMap = fuzzyQuery(connection, result,
					"$mul$entity");
			if (entityMap != null) {
				map = rdapConformance(map);
				map.putAll(entityMap);
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
		map = WhoisUtil.toVCard(map);
		return map;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.SEARCHENTITY;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.SEARCHENTITY.equals(queryType);
	}
}
