package com.cnnic.whois.dao.query.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.util.ColumnCache;
import com.cnnic.whois.util.PermissionCache;
import com.cnnic.whois.util.WhoisUtil;
@Repository
public class AsQueryDao extends AbstractDbQueryDao {

	@Override
	public Map<String, Object> query(QueryParam param)
			throws QueryException {
		Map<String, Object> map = null;
		try {
			String selectSql = WhoisUtil.SELECT_LIST_AS1 + param.getQ()
					+ WhoisUtil.SELECT_LIST_AS2 + param.getQ()
					+ WhoisUtil.SELECT_LIST_AS3;
			Map<String, Object> asMap = query(selectSql,
					ColumnCache.getColumnCache().getASKeyFileds(),
					"$mul$autnum");
			if (asMap != null) {
				map = rdapConformance(map);
				map.putAll(asMap);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return map;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.AUTNUM;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.AUTNUM.equals(queryType);
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return false;
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle) throws SQLException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<String> getKeyFields(String role) {
		return PermissionCache.getPermissionCache().getASKeyFileds(role);
	}
}
