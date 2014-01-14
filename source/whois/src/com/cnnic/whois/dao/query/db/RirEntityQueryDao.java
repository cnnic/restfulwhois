package com.cnnic.whois.dao.query.db;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.util.ColumnCache;
import com.cnnic.whois.util.PermissionCache;
import com.cnnic.whois.util.WhoisUtil;
@Repository
public class RirEntityQueryDao extends EntityQueryDao {

	@Override
	public QueryType getQueryType() {
		return QueryType.RIRENTITY;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.RIRENTITY.equals(queryType);
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		if (!QueryJoinType.ENTITIES.equals(queryJoinType)) {
			return false;
		}
		return joinRirEntity(queryType);
	}

	@Override
	public Map<String, Object> getAll() throws QueryException {
		List<String> keyFields = ColumnCache.getColumnCache()
				.getRIREntityKeyFileds();
		return getAllEntity(GET_ALL_RIRENTITY, keyFields);
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle) throws SQLException {
		return querySpecificJoinTable(key, handle,
				WhoisUtil.SELECT_JOIN_LIST_JOINRIRENTITY,
				ColumnCache.getColumnCache().getRIREntityKeyFileds());
	}

	public static boolean joinRirEntity(QueryType queryType) {
		return QueryType.IP.equals(queryType)
				|| QueryType.AUTNUM.equals(queryType)
				|| QueryType.RIRDOMAIN.equals(queryType);
	}

	@Override
	public List<String> getKeyFields(String role) {
		return PermissionCache.getPermissionCache().getRIREntityKeyFileds(role);
	}
}