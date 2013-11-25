package com.cnnic.whois.dao.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.util.WhoisUtil;

public class NsQueryDao extends AbstractDbQueryDao {
	public NsQueryDao(List<AbstractDbQueryDao> dbQueryDaos) {
		super(dbQueryDaos);
	}

	public Map<String, Object> query(QueryParam param,
			PageBean... page) throws QueryException {
		Connection connection = null;
		Map<String, Object> map = null;

		try {
			connection = ds.getConnection();

			String selectSql = WhoisUtil.SELECT_LIST_NAMESREVER + "'"
					+ param.getQ() + "'";
			Map<String, Object> nsMap = query(connection, selectSql,
					permissionCache.getNameServerKeyFileds(param.getRole()),
					"$mul$nameServer", param);
			if (nsMap != null) {
				map = rdapConformance(map);
				map.putAll(nsMap);
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

	protected Map<String, Object> postHandleFields(String keyName,
			String format, ResultSet results, Map<String, Object> map)
			throws SQLException {
		if (keyName.equals("$mul$nameServer")
				|| keyName.equals("$join$nameServer")) {
			Map<String, Object> map_IP = new LinkedHashMap<String, Object>();
			Object IPAddressArray = map.get(WhoisUtil.getDisplayKeyName(
					"IPV4_Addresses", format));
			map_IP.put(WhoisUtil.IPV4PREFIX, IPAddressArray);
			IPAddressArray = map.get(WhoisUtil.getDisplayKeyName(
					"IPV6_Addresses", format));
			map_IP.put(WhoisUtil.IPV6PREFIX, IPAddressArray);
			map.put(WhoisUtil.IPPREFIX, map_IP);
			map.remove(WhoisUtil.getDisplayKeyName("IPV4_Addresses", format));
			map.remove(WhoisUtil.getDisplayKeyName("IPV6_Addresses", format));
		}
		return map;
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.NAMESERVER;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.NAMESERVER.equals(queryType);
	}

	@Override
	protected boolean supportJoinType(QueryType queryType,
			QueryJoinType queryJoinType) {
		return QueryJoinType.NAMESERVER.equals(queryJoinType);
	}

	@Override
	public Object querySpecificJoinTable(String key, String handle,
			QueryParam param, Connection connection) throws SQLException {
		return querySpecificJoinTable(key, handle,
				WhoisUtil.SELECT_JOIN_LIST_JOINNAMESERVER, param, connection,
				permissionCache.getNameServerKeyFileds(param.getRole()));
	}
}