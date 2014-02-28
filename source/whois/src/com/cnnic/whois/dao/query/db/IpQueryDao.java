package com.cnnic.whois.dao.query.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnnic.whois.bean.IpQueryParam;
import com.cnnic.whois.bean.QueryJoinType;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.util.ColumnCache;
import com.cnnic.whois.util.IpUtil;
import com.cnnic.whois.util.PermissionCache;
import com.cnnic.whois.util.WhoisUtil;

@Repository
public class IpQueryDao extends AbstractDbQueryDao {

	@Override
	public Map<String, Object> query(QueryParam param) throws QueryException {
		IpQueryParam ipParam = (IpQueryParam) param;
		long startHighAddr = ipParam.getStartHighAddr();
		long endHighAddr = ipParam.getEndHighAddr();
		long startLowAddr = ipParam.getStartLowAddr();
		long endLowAddr = ipParam.getEndLowAddr();
		Map<String, Object> map = null;
		String selectSql = "";
		try {
			if (startHighAddr == 0) { // If fuzzy matching with the SQL
										// statement
				selectSql = (endLowAddr == 0) ? (WhoisUtil.SELECT_LIST_IPv4_1
						+ startLowAddr + WhoisUtil.SELECT_LIST_IPv4_2
						+ startLowAddr + WhoisUtil.SELECT_LIST_IPv4_3)
						: (WhoisUtil.SELECT_LIST_IPv4_4 + startLowAddr
								+ WhoisUtil.SELECT_LIST_IPv4_2 + endLowAddr + WhoisUtil.SELECT_LIST_IPv4_5);
			} else {
				selectSql = (endLowAddr == 0) ? (WhoisUtil.SELECT_LIST_IPv6_1
						+ startHighAddr + WhoisUtil.SELECT_LIST_IPv6_2
						+ startHighAddr + WhoisUtil.SELECT_LIST_IPv6_3
						+ startLowAddr + WhoisUtil.SELECT_LIST_IPv6_4
						+ startHighAddr + WhoisUtil.SELECT_LIST_IPv6_5
						+ startHighAddr + WhoisUtil.SELECT_LIST_IPv6_6
						+ startLowAddr + WhoisUtil.SELECT_LIST_IPv6_7)

						: (WhoisUtil.SELECT_LIST_IPv6_8 + startHighAddr
								+ WhoisUtil.SELECT_LIST_IPv6_2 + startHighAddr
								+ WhoisUtil.SELECT_LIST_IPv6_3 + startLowAddr
								+ WhoisUtil.SELECT_LIST_IPv6_4 + endHighAddr
								+ WhoisUtil.SELECT_LIST_IPv6_5 + endHighAddr
								+ WhoisUtil.SELECT_LIST_IPv6_6 + endLowAddr
								+ WhoisUtil.SELECT_LIST_IPv6_7 + WhoisUtil.SELECT_LIST_IPv6_9);
			}

			Map<String, Object> ipMap = query(selectSql,
					columnCache.getIPKeyFileds(), "$mul$IP");
			if (ipMap != null) {
				map = rdapConformance(map);
				map.putAll(ipMap);
			}
			if (map != null) {
				map.remove("StartLowAddress");
				map.remove("EndLowAddress");
				map.remove("StartHighAddress");
				map.remove("EndHighAddress");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
		return map;
	}

	protected void preHandleNormalField(String keyName, ResultSet results,
			Map<String, Object> map, String field) throws SQLException {
		if (keyName.equals(WhoisUtil.MULTIPRXIP)
				&& field.equals("StartLowAddress")) {
			if ((map.get("Start Address") == null && map.get("End Address") == null)
					|| (map.get("startAddress") == null && map
							.get("endAddress") == null)) {
				String ipVersion = results.getString("IP_Version");

				String startHighAddress = results.getString("StartHighAddress");
				String startLowAddress = results.getString("StartLowAddress");
				String endHighAddress = results.getString("EndHighAddress");
				String endLowAddress = results.getString("EndLowAddress");

				String startAddress = "";
				String endAddress = "";

				if (ipVersion != null) {
					if (ipVersion.toString().indexOf("v6") != -1) { // judgment
																	// is IPv6
																	// or IPv4
						startAddress = IpUtil.ipV6ToString(
								Long.parseLong(startHighAddress),
								Long.parseLong(startLowAddress));
						endAddress = IpUtil.ipV6ToString(
								Long.parseLong(endHighAddress),
								Long.parseLong(endLowAddress));
					} else {
						startAddress = IpUtil.longtoipV4(Long
								.parseLong(startLowAddress));
						endAddress = IpUtil.longtoipV4(Long
								.parseLong(endLowAddress));
					}
					map.put("Start_Address", startAddress);
					map.put("End_Address", endAddress);
				}
			}
		}
	}

	@Override
	public QueryType getQueryType() {
		return QueryType.IP;
	}

	@Override
	public boolean supportType(QueryType queryType) {
		return QueryType.IP.equals(queryType);
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
		List<String> cacheFields = permissionCache
				.getIPKeyFileds(role);
		List<String> result = new ArrayList<String>(cacheFields);
		result.add("Start_Address");
		result.add("End_Address");
		return result;
	}
}
