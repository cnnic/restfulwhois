package com.cnnic.whois.dao.query;

import java.util.Map;

import javax.sql.DataSource;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.util.PermissionCache;

public class IpQueryDAO extends AbstractDbQueryDAO {
	protected DataSource ds;
	protected PermissionCache permissionCache = PermissionCache
			.getPermissionCache();

	@Override
	public boolean supportType(QueryType queryType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, Object> query(String q, String role, String format,
			PageBean... page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> queryJoins(String handle, String role,
			String format) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJoinFieldIdColumnName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryType getQueryType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		// TODO Auto-generated method stub
		return false;
	}

}
