package com.cnnic.whois.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	public Map<String, Object> queryIP(String ipInfo, int ipLength, String role)
			throws QueryException, RedirectExecption {

		long[] ipLongs = WhoisUtil.parsingIp(ipInfo, ipLength);
		Map<String, Object> map = queryDAO.queryIP(ipLongs[0], ipLongs[1],
				ipLongs[2], ipLongs[3], role);
		
		if (map == null) { //If the collection is empty, then go to redirect queries table
			queryDAO.queryIPRedirection(ipLongs[0], ipLongs[1], ipLongs[2],
					ipLongs[3]);
			return queryError(WhoisUtil.ERRORCODE, role);
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
	public Map<String, Object> queryAS(int asInfo, String role)
			throws QueryException, RedirectExecption {
		Map<String, Object> map = queryDAO.queryAS(asInfo, role);

		if (map == null) {
			getRedirectionURL(WhoisUtil.AUTNUM, Integer.toString(asInfo));
			return queryError(WhoisUtil.ERRORCODE, role);
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
	public Map<String, Object> queryNameServer(String ipInfo, String role)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryNameServer(ipInfo, role);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role);
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
	public Map<String, Object> queryDoamin(String ipInfo, String role)
			throws QueryException, RedirectExecption {
		Map<String, Object> rirMap = queryDAO.queryRIRDoamin(ipInfo, role);
		Map<String, Object> dnrMap = queryDAO.queryDNRDoamin(ipInfo, role);

		if (rirMap == null && dnrMap == null) {
			String queryType = WhoisUtil.DNRDOMAIN;

			if (rirMap == null)
				queryType = WhoisUtil.RIRDOMAIN;
			getRedirectionURL(queryType, ipInfo);
			return queryError(WhoisUtil.ERRORCODE, role);
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
	public Map<String, Object> queryEntity(String queryPara, String role)
			throws QueryException {
		Map<String, Object> rirMap = queryDAO.queryRIREntity(queryPara, role);
		Map<String, Object> dnrMap = queryDAO.queryDNREntity(queryPara, role);
		Map<String, Object> regMap = queryRegistrar(queryPara, role, false);
		if (rirMap == null && dnrMap == null && regMap.get("errorCode") != null) {
			return queryError(WhoisUtil.ERRORCODE, role);
		}

		Map<String, Object> wholeMap = new LinkedHashMap<String, Object>();
		if (rirMap != null) {
			wholeMap.putAll(rirMap);
		}

		if (dnrMap != null) {
			wholeMap.putAll(dnrMap);
		}
		
		if (regMap.get("errorCode") == null) {
			wholeMap.putAll(regMap);
		}
		return wholeMap;
	}

//	/**
//	 * Changes will be part of the data into the VCard style
//	 * 
//	 * @param map
//	 * @return map collection
//	 */
//	private Map<String, Object> toVCard(Map<String, Object> map) {
//		//TODO:vacard format modified
//		
//		List<List<String>> list = new ArrayList<List<String>>();
//		Object entityNames = map.get("Entity Names");
//		Object postalAddress = map.get("postalAddress");
//		Object emails = map.get("Emails");
//		Object phones = map.get("phones");
//
//		List<String> firstNameList = new ArrayList<String>();
//		firstNameList.add("version");
//		firstNameList.add("{}");
//		firstNameList.add("text");
//		firstNameList.add("4.0");
//		list.add(firstNameList);
//		if (entityNames != null) {
//			if (entityNames instanceof String[]) {
//				String[] namesArray = (String[]) entityNames;
//				for (String names : namesArray) {
//					List<String> nameList = new ArrayList<String>();
//					nameList.add("fn");
//					nameList.add("{}");
//					nameList.add("text");
//					nameList.add(names);
//					list.add(nameList);
//				}
//			}else{
//				List<String> nameList = new ArrayList<String>();
//				nameList.add("fn");
//				nameList.add("{}");
//				nameList.add("text");
//				nameList.add(entityNames.toString());
//				list.add(nameList);
//			}
//			map.remove("Entity Names");
//		}
//		if (postalAddress != null) {
//			if (postalAddress instanceof Map) {
//				Set<String> key = ((Map) postalAddress).keySet();
//				List<String> nameList = new ArrayList<String>();
//				nameList.add("label");
//				nameList.add("{}");
//				nameList.add("text");
//				for (String name : key) {
//					nameList.add(((Map) postalAddress).get(name).toString());
//				}
//				list.add(nameList);
//			} else if (postalAddress instanceof Object[]) {
//				for (Object postalAddressObject : ((Object[]) postalAddress)) {
//					Set<String> key = ((Map) postalAddressObject).keySet();
//					List<String> nameList = new ArrayList<String>();
//					nameList.add("label");
//					nameList.add("{}");
//					nameList.add("text");
//					for (String name : key) {
//						nameList.add(((Map) postalAddressObject).get(name)
//								.toString());
//					}
//					list.add(nameList);
//				}
//			}
//			map.remove("postalAddress");
//		}
//		;
//		if (emails != null) {
//			String[] namesArray = (String[]) emails;
//			for (String names : namesArray) {
//				List<String> nameList = new ArrayList<String>();
//				nameList.add("email");
//				nameList.add("{}");
//				nameList.add("text");
//				nameList.add(names);
//				list.add(nameList);
//			}
//			map.remove("Emails");
//		}
//		;
//		if (phones != null) {
//			if (phones instanceof Map) {
//				Set<String> key = ((Map) phones).keySet();
//				List<String> nameList = new ArrayList<String>();
//				for (String name : key) {
//					Object values = ((Map) phones).get(name);
//					if (values instanceof String[]) {
//						String typeName = "";
//						if (name.equals("Office")) {
//							typeName = "{type:work}";
//						} else if (name.equals("Fax")) {
//							typeName = "{type:fax}";
//						} else if (name.equals("Mobile")) {
//							typeName = "{type:cell}";
//						}
//						for (String valueName : (String[]) values) {
//							List<String> nameListArray = new ArrayList<String>();
//							nameListArray.add("tel");
//							nameListArray.add(typeName);
//							nameListArray.add("text");
//							nameListArray.add(valueName);
//							list.add(nameListArray);
//						}
//
//						continue;
//					}
//					nameList.add("tel");
//					nameList.add("{}");
//					nameList.add("text");
//					nameList.add(values.toString());
//					list.add(nameList);
//				}
//
//			} else if (phones instanceof Object[]) {
//				for (Object phonesObject : ((Object[]) phones)) {
//					Set<String> key = ((Map) phonesObject).keySet();
//					List<String> nameList = new ArrayList<String>();
//					for (String name : key) {
//						Object values = ((Map) phonesObject).get(name);
//						if (values instanceof String[]) {
//							String typeName = "";
//							if (name.equals("Office")) {
//								typeName = "{type:work}";
//							} else if (name.equals("Fax")) {
//								typeName = "{type:fax}";
//							} else if (name.equals("Mobile")) {
//								typeName = "{type:cell}";
//							}
//							for (String valueName : (String[]) values) {
//								List<String> nameListArray = new ArrayList<String>();
//								nameListArray.add("tel");
//								nameListArray.add(typeName);
//								nameListArray.add("text");
//								nameListArray.add(valueName);
//								list.add(nameListArray);
//							}
//							continue;
//						}
//						nameList.add("tel");
//						nameList.add("{}");
//						nameList.add("text");
//						nameList.add(values.toString());
//						list.add(nameList);
//					}
//				}
//			}
//			map.remove("phones");
//		}
//		map.put("vCard", list.toArray());
//		return map;
//	}

	/**
	 * Query link type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	public Map<String, Object> queryLinks(String queryPara, String role)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryLinks(queryPara, role);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role);
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
	public Map<String, Object> queryPhones(String queryPara, String role)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryPhones(queryPara, role);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role);
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
	public Map<String, Object> queryPostalAddress(String queryPara, String role)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryPostalAddress(queryPara, role);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role);
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
	public Map<String, Object> queryVariants(String queryPara, String role)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryVariants(queryPara, role);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role);
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
	public Map<String, Object> queryDelegationKeys(String queryPara, String role)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryDelegationKeys(queryPara, role);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role);
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
	public Map<String, Object> queryNotices(String queryPara, String role)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryNotices(queryPara, role);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role);
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
	public Map<String, Object> queryRegistrar(String queryPara, String role,boolean isJoinTable)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryRegistrar(queryPara, role, isJoinTable);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role);
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
	public Map<String, Object> queryRemarks(String queryPara, String role)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryRemarks(queryPara, role);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role);
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
	public Map<String, Object> queryEvents(String queryPara, String role)
			throws QueryException {
		Map<String, Object> map = queryDAO.queryEvents(queryPara, role);

		if (map == null) {
			return queryError(WhoisUtil.ERRORCODE, role);
		}

		return map;
	}

	/**
	 * The processing Error
	 * 
	 * @return error map collection
	 * @throws QueryException 
	 */
	private Map<String, Object> queryError(String errorCode, String role) throws QueryException {
		Map<String, Object>ErrorMessageMap = null;
		QueryDAO queryDAO = QueryDAO.getQueryDAO();
		ErrorMessageMap = queryDAO.getErrorMessage(errorCode, role);
		return ErrorMessageMap;
		//return WhoisUtil.getErrorMessage(WhoisUtil.ERRORCODE,
				//WhoisUtil.ERRORTITLE, WhoisUtil.ERRORDESCRIPTION);
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
