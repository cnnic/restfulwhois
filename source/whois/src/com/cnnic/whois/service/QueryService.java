package com.cnnic.whois.service;

import com.cnnic.whois.dao.QueryDAO;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.util.WhoisProperties;
import com.cnnic.whois.util.WhoisUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class QueryService {
	private static QueryService queryService = new QueryService();
	private QueryDAO queryDAO = QueryDAO.getQueryDAO();
	public static int MAX_SIZE_FUZZY_QUERY = WhoisProperties
			.getMaxSizeFuzzyQuery();

	public static QueryService getQueryService() {
		return queryService;
	}

	public Map<String, Object> queryIP(String ipInfo, int ipLength,
			String role, String format) throws QueryException,
			RedirectExecption {
		long[] ipLongs = WhoisUtil.parsingIp(ipInfo, ipLength);
		Map map = this.queryDAO.queryIP(ipLongs[0], ipLongs[1], ipLongs[2],
				ipLongs[3], role, format);

		if (map == null) {
			this.queryDAO.queryIPRedirection(ipLongs[0], ipLongs[1],
					ipLongs[2], ipLongs[3]);
			return queryError("404", role, format);
		}

		if ((map.get("$mul$IP") instanceof Object[])) {
			Object[] mapObj = (Object[]) map.get("$mul$IP");
			List list = new ArrayList();
			Map mapInfo = new LinkedHashMap();

			mapInfo.put("$mul$IP", list.toArray());
			return mapInfo;
		}
		return map;
	}

	private Map<String, Object> longToIP(Map<String, Object> map) {
		Object ipversion = map.get("IP Version");

		String startHightAddress = map.get("StartHighAddress").toString();
		String startLowAddress = map.get("StartLowAddress").toString();
		String endHighAddress = map.get("EndHighAddress").toString();
		String endLowAddress = map.get("EndLowAddress").toString();

		map.remove("StartHighAddress");
		map.remove("StartLowAddress");
		map.remove("EndHighAddress");
		map.remove("EndLowAddress");
		String startAddress = "";
		String endAddress = "";
		if (ipversion != null) {
			if (ipversion.toString().indexOf("v6") != -1) {
				startAddress = WhoisUtil.ipV6ToString(
						Long.parseLong(startHightAddress),
						Long.parseLong(startLowAddress));
				endAddress = WhoisUtil.ipV6ToString(
						Long.parseLong(endHighAddress),
						Long.parseLong(endLowAddress));
			} else {
				startAddress = WhoisUtil.longtoipV4(Long
						.parseLong(startLowAddress));
				endAddress = WhoisUtil
						.longtoipV4(Long.parseLong(endLowAddress));
			}
			map.put("Start Address", startAddress);
			map.put("End Address", endAddress);
		}
		return map;
	}

	public Map<String, Object> queryAS(int asInfo, String role, String format)
			throws QueryException, RedirectExecption {
		Map map = this.queryDAO.queryAS(asInfo, role, format);

		if (map == null) {
			getRedirectionURL("autnum", Integer.toString(asInfo));
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> queryNameServer(String ipInfo, String role,
			String format) throws QueryException {
		Map map = this.queryDAO.queryNameServer(ipInfo, role, format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> fuzzyQueryNameServer(String nameServer,
			String role, String format) throws QueryException,
			RedirectExecption {
		Map dnrMap = this.queryDAO.fuzzyQueryNameServer(nameServer, role,
				format);
		if (dnrMap == null) {
			String queryType = "dnrdomain";
			getRedirectionURL(queryType, nameServer);
			return queryError("404", role, format);
		}
		Map wholeMap = new LinkedHashMap();
		if (dnrMap != null) {
			wholeMap.putAll(dnrMap);
		}
		return wholeMap;
	}

	public Map<String, Object> fuzzyQueryDomain(String domain,
			String domainPuny, String role, String format)
			throws QueryException, RedirectExecption {
		Map dnrMap = this.queryDAO.fuzzyQueryDoamin(domain, domainPuny, role,
				format);
		if (dnrMap == null) {
			String queryType = "dnrdomain";
			getRedirectionURL(queryType, domain);
			return queryError("404", role, format);
		}
		Map wholeMap = new LinkedHashMap();
		if (dnrMap != null) {
			wholeMap.putAll(dnrMap);
		}
		return wholeMap;
	}

	public Map<String, Object> queryDomain(String ipInfo, String role,
			String format) throws QueryException, RedirectExecption {
		Map rirMap = this.queryDAO.queryRIRDoamin(ipInfo, role, format);
		Map dnrMap = this.queryDAO.queryDNRDoamin(ipInfo, role, format);

		if ((rirMap == null) && (dnrMap == null)) {
			String queryType = "dnrdomain";

			if (rirMap == null)
				queryType = "rirdomain";
			getRedirectionURL(queryType, ipInfo);
			return queryError("404", role, format);
		}

		Map wholeMap = new LinkedHashMap();
		if (rirMap != null) {
			wholeMap.putAll(rirMap);
		}

		if (dnrMap != null) {
			wholeMap.putAll(dnrMap);
		}

		return wholeMap;
	}

	public Map<String, Object> queryEntity(String queryPara, String role,
			String format) throws QueryException, SQLException {
		Map map = this.queryDAO.queryEntity(queryPara, role, format);
		if (map == null) {
			return queryError("404", role, format);
		}
		return map;
	}

	public Map<String, Object> fuzzyQueryEntity(String fuzzyQueryParamName,
			String queryPara, String role, String format)
			throws QueryException, SQLException {
		Map map = this.queryDAO.fuzzyQueryEntity(fuzzyQueryParamName,
				queryPara, role, format);
		if (map == null) {
			return queryError("404", role, format);
		}
		return map;
	}

	public Map<String, Object> queryLinks(String queryPara, String role,
			String format) throws QueryException {
		Map map = this.queryDAO.queryLinks(queryPara, role, format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> queryPhones(String queryPara, String role,
			String format) throws QueryException {
		Map map = this.queryDAO.queryPhones(queryPara, role, format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> queryPostalAddress(String queryPara,
			String role, String format) throws QueryException {
		Map map = this.queryDAO.queryPostalAddress(queryPara, role, format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> queryVariants(String queryPara, String role,
			String format) throws QueryException {
		Map map = this.queryDAO.queryVariants(queryPara, role, format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> querySecureDNS(String queryPara, String role,
			String format) throws QueryException {
		Map map = this.queryDAO.querySecureDNS(queryPara, role, format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> queryDsData(String queryPara, String role,
			String format) throws QueryException {
		Map map = this.queryDAO.queryDsData(queryPara, role, format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> queryKeyData(String queryPara, String role,
			String format) throws QueryException {
		Map map = this.queryDAO.queryKeyData(queryPara, role, format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> queryDelegationKeys(String queryPara,
			String role, String format) throws QueryException {
		Map map = this.queryDAO.queryDelegationKeys(queryPara, role, format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> queryNotices(String queryPara, String role,
			String format) throws QueryException {
		Map map = this.queryDAO.queryNotices(queryPara, role, format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> queryRegistrar(String queryPara, String role,
			boolean isJoinTable, String format) throws QueryException {
		Map map = this.queryDAO.queryRegistrar(queryPara, role, isJoinTable,
				format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> queryRemarks(String queryPara, String role,
			String format) throws QueryException {
		Map map = this.queryDAO.queryRemarks(queryPara, role, format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> queryEvents(String queryPara, String role,
			String format) throws QueryException {
		Map map = this.queryDAO.queryEvents(queryPara, role, format);

		if (map == null) {
			return queryError("404", role, format);
		}

		return map;
	}

	public Map<String, Object> queryError(String errorCode, String role,
			String format) throws QueryException {
		Map ErrorMessageMap = null;
		QueryDAO queryDAO = QueryDAO.getQueryDAO();
		ErrorMessageMap = queryDAO.getErrorMessage(errorCode, role, format);
		return ErrorMessageMap;
	}

	public Map<String, Object> queryHelp(String helpCode, String role,
			String format) throws QueryException {
		Map helpMap = null;
		QueryDAO queryDAO = QueryDAO.getQueryDAO();
		helpMap = queryDAO.getHelp(helpCode, role, format);
		return helpMap;
	}

	private void getRedirectionURL(String queryType, String queryPara)
			throws QueryException, RedirectExecption {
		this.queryDAO.queryRedirection(queryType, queryPara);
	}
}