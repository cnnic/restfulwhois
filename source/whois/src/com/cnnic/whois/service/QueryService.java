package com.cnnic.whois.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cnnic.whois.dao.QueryDAO;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.util.WhoisUtil;
import java.util.LinkedHashMap;

public class QueryService {

	private static QueryService queryService = new QueryService();
	private QueryDAO queryDAO = QueryDAO.getQueryDAO();

	private QueryService() {
	}

	/**
	 * Get QueryService objects
	 * 
	 * @return QueryService Object
	 */
	public static QueryService getQueryService() {
		return queryService;
	}

	/**
	 * Ip of the string is converted to type long data, the corresponding query.
	 * 
	 * @param ipInfo
	 * @param ipLength
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 * @throws RedirectExecption
	 */
	public Map<String, Object> queryIP(String ipInfo, int ipLength, String role, String format)
			throws QueryException, RedirectExecption {

		long[] ipLongs = WhoisUtil.parsingIp(ipInfo, ipLength);
		Map<String, Object> map = queryDAO.queryIP(ipLongs[0], ipLongs[1],
				ipLongs[2], ipLongs[3], role, format);
		
		if (map == null) { //If the collection is empty, then go to redirect queries table
			queryDAO.queryIPRedirection(ipLongs[0], ipLongs[1], ipLongs[2],
					ipLongs[3]);
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		if (map.get("$mul$IP") instanceof Object[]) {
			Object[] mapObj = (Object[]) map.get("$mul$IP");
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> mapInfo = new LinkedHashMap<String, Object>();
//			for (Object childMap : mapObj) {
//				list.add(longToIP(((Map<String, Object>) childMap))); //long type data into ip address
//			}
			mapInfo.put("$mul$IP", list.toArray());
			return mapInfo;
		} else {
			return map;
		}
	}

	/**
	 * Long type data into ip address
	 * 
	 * @param map
	 * @return map
	 */
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
			if (ipversion.toString().indexOf("v6") != -1) { //judgment is IPv6 or IPv4
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

	/**
	 * Query as type
	 * 
	 * @param asInfo
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 * @throws RedirectExecption
	 */
	public Map<String, Object> queryAS(int asInfo, String role, String format)
			throws QueryException, RedirectExecption {
		Map<String, Object> map = queryDAO.queryAS(asInfo, role, format);

		if (map == null) {
			getRedirectionURL(WhoisUtil.AUTNUM, Integer.toString(asInfo));
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}

	/**
	 * Query nameServer type
	 * 
	 * @param ipInfo
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryNameServer(String ipInfo, String role, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryNameServer(ipInfo, role, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}

	/**
	 * Query doamin type
	 * 
	 * @param ipInfo
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 * @throws RedirectExecption
	 */
	public Map<String, Object> queryDoamin(String ipInfo, String role, String format)
			throws QueryException, RedirectExecption {
		Map<String, Object> rirMap = queryDAO.queryRIRDoamin(ipInfo, role, format);
		Map<String, Object> dnrMap = queryDAO.queryDNRDoamin(ipInfo, role, format);

		if (rirMap == null && dnrMap == null) {
			String queryType = WhoisUtil.DNRDOMAIN;

			if (rirMap == null)
				queryType = WhoisUtil.RIRDOMAIN;
			getRedirectionURL(queryType, ipInfo);
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		Map<String, Object> wholeMap = new LinkedHashMap<String, Object>();
		if (rirMap != null) {
			wholeMap.putAll(rirMap);
		}

		if (dnrMap != null) {
			wholeMap.putAll(dnrMap);
		}

		return wholeMap;
	}

	/**
	 * Query entity type
	 * 
	 * @param ipInfo
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryEntity(String queryPara, String role, String format)
			throws QueryException {
		Map<String, Object> rirMap = queryDAO.queryRIREntity(queryPara, role, format);
		Map<String, Object> dnrMap = queryDAO.queryDNREntity(queryPara, role, format);
		Map<String, Object> regMap = queryRegistrar(queryPara, role, false, format);
		if (rirMap == null && dnrMap == null && regMap.get(WhoisUtil.getDisplayKeyName("Error_Code", format)) != null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		Map<String, Object> wholeMap = new LinkedHashMap<String, Object>();
		if (rirMap != null) {
			wholeMap.putAll(rirMap);
		}

		if (dnrMap != null) {
			wholeMap.putAll(dnrMap);
		}
		
		if (regMap.get(WhoisUtil.getDisplayKeyName("Error_Code", format)) == null) {
			wholeMap.putAll(regMap);
		}
		return wholeMap;
	}

	/**
	 * Query link type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryLinks(String queryPara, String role, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryLinks(queryPara, role, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}

	/**
	 * Query phone type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryPhones(String queryPara, String role, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryPhones(queryPara, role, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}

	/**
	 * Query postalAddress type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryPostalAddress(String queryPara, String role, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryPostalAddress(queryPara, role, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}

	/**
	 * Query variant type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryVariants(String queryPara, String role, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryVariants(queryPara, role, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}
	
	/**
	 * Query SecureDNS
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> querySecureDNS(String queryPara, String role, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.querySecureDNS(queryPara, role, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}
	
	/**
	 * Query DsData
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryDsData(String queryPara, String role, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryDsData(queryPara, role, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}

	/**
	 * Query KeyData
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryKeyData(String queryPara, String role, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryKeyData(queryPara, role, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}

	/**
	 * Query delegationKey type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryDelegationKeys(String queryPara, String role, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryDelegationKeys(queryPara, role, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}

	/**
	 * Query notice type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryNotices(String queryPara, String role, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryNotices(queryPara, role, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}

	/**
	 * Query registrar type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryRegistrar(String queryPara, String role, boolean isJoinTable, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryRegistrar(queryPara, role, isJoinTable, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}

	/**
	 * Query remarks type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryRemarks(String queryPara, String role, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryRemarks(queryPara, role, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}

	/**
	 * Query events type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryEvents(String queryPara, String role, String format)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryEvents(queryPara, role, format);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role, format);
		}

		return map;
	}

	/**
	 * The processing Error
	 * 
	 * @return error map collection
	 * @throws QueryException 
	 */
	public Map<String, Object> queryError(String errorCode, String role, String format) throws QueryException {
		Map<String, Object>ErrorMessageMap = null;
		QueryDAO queryDAO = QueryDAO.getQueryDAO();
		ErrorMessageMap = queryDAO.getErrorMessage(errorCode, role, format);
		return ErrorMessageMap;
	}

	/**
	 * Redirect the forwarding address
	 * 
	 * @param queryType
	 * @param queryPara
	 * @throws QueryException
	 * @throws RedirectExecption
	 */
	private void getRedirectionURL(String queryType, String queryPara)
			throws QueryException, RedirectExecption {
		queryDAO.queryRedirection(queryType, queryPara);
	}
}
