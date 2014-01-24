package com.cnnic.whois.dao.query.db;

import java.sql.SQLException;
import java.util.Map;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.util.ColumnCache;
import com.cnnic.whois.util.WhoisUtil;

public class RegistrarQueryDao extends AbstractDbQueryDao {

	/**
	 * Connect to the database query variant information
	 * 
	 * @param queryInfo
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryVariants(String queryInfo, String format)
			throws QueryException {
		Map<String, Object> map = null;
		try {
			String selectSql = WhoisUtil.SELECT_LIST_VARIANTS + "'" + queryInfo
					+ "'";
			map = query(selectSql, ColumnCache.getColumnCache()
					.getRegistrarKeyFileds(), "$mul$variants");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return map;
	}

	@Override
	public QueryType getQueryType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> query(QueryParam param) throws QueryException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle) throws SQLException {
		return querySpecificJoinTable(key, handle,
				WhoisUtil.SELECT_JOIN_LIST_REGISTRAR, ColumnCache
						.getColumnCache().getRegistrarKeyFileds());
	}
}
