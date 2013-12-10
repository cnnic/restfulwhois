package com.cnnic.whois.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnnic.whois.bean.DomainQueryParam;
import com.cnnic.whois.bean.EntityQueryParam;
import com.cnnic.whois.bean.IpQueryParam;
import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.RedirectionQueryParam;
import com.cnnic.whois.dao.QueryEngine;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.util.WhoisProperties;
import com.cnnic.whois.util.WhoisUtil;
@Service
public class QueryService {
	private static QueryService queryService = new QueryService();
	@Autowired
	private QueryEngine queryEngine ;
	public static int MAX_SIZE_FUZZY_QUERY = WhoisProperties
			.getMaxSizeFuzzyQuery();

	public static QueryService getQueryService() {
		return queryService;
	}

	public Map<String, Object> queryIP(String ipInfo, int ipLength) throws QueryException,
			RedirectExecption {
		long[] ipLongs = WhoisUtil.parsingIp(ipInfo, ipLength);
		Map map = queryEngine.query(QueryType.IP, new IpQueryParam("",ipLongs[0], ipLongs[1], ipLongs[2],
				ipLongs[3]));
		if (map == null) {
			queryEngine.query(QueryType.IPREDIRECTION, 
					new IpQueryParam("",ipLongs[0], ipLongs[1], ipLongs[2],ipLongs[3]));
			return queryError("404");
		}

		if ((map.get("$mul$IP") instanceof Object[])) {
			List list = new ArrayList();
			Map<String, Object> mapInfo = new LinkedHashMap<String, Object>();

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
		Map map = queryEngine.query(QueryType.AUTNUM, new QueryParam(asInfo+""));
		if (map == null) {
			getRedirectionURL("autnum", Integer.toString(asInfo));
			return queryError("404");
		}

		return map;
	}

	public Map<String, Object> queryNameServer(String ipInfo, String role,
			String format) throws QueryException, RedirectExecption {
		Map map = queryEngine.query(QueryType.NAMESERVER, new QueryParam(ipInfo));
		if (map == null) {
			return queryError("404");
		}
		return map;
	}

	public Map<String, Object> fuzzyQueryNameServer(String nameServer,
			String role, String format, PageBean page) throws QueryException,
			RedirectExecption {
		Map dnrMap = queryEngine.query(QueryType.SEARCHNS,
				new QueryParam(nameServer));
		if (dnrMap == null) {
			return queryError("404");
		}
		return dnrMap;
	}

	public Map<String, Object> fuzzyQueryDomain(String domain,
			String domainPuny, String role, String format, PageBean page)
			throws QueryException, RedirectExecption {
		Map dnrMap = queryEngine.query(QueryType.SEARCHDOMAIN, 
				new DomainQueryParam(domain,domainPuny));
		if (dnrMap == null) {
			return queryError("404");
		}
		return dnrMap;
	}

	public Map<String, Object> queryDomain(QueryParam queryParam) throws QueryException, RedirectExecption {
		Map rirMap = queryEngine.query(QueryType.RIRDOMAIN, queryParam);
		//cache.queryDoamin("");
		Map dnrMap = queryEngine.query(QueryType.DNRDOMAIN, queryParam);
		//this.queryDAO.queryDNRDoamin(ipInfo);

		if ((rirMap == null) && (dnrMap == null)) {
			String queryType = "dnrdomain";

			if (rirMap == null)
				queryType = "rirdomain";
			getRedirectionURL(queryType, queryParam.getQ());
			return queryError("404");
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
	
	public Map<String, Object> queryDomain(String ipInfo, String role,
			String format) throws QueryException, RedirectExecption {
		Map rirMap = queryEngine.query(QueryType.RIRDOMAIN, new QueryParam(ipInfo));
		//cache.queryDoamin("");
		Map dnrMap = queryEngine.query(QueryType.DNRDOMAIN, new QueryParam(ipInfo));
		//this.queryDAO.queryDNRDoamin(ipInfo);

		if ((rirMap == null) && (dnrMap == null)) {
			String queryType = "dnrdomain";

			if (rirMap == null)
				queryType = "rirdomain";
			getRedirectionURL(queryType, ipInfo);
			return queryError("404");
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

	public Map<String, Object> queryEntity(String queryPara, String role,
			String format) throws QueryException, SQLException {
		try {
			Map map = queryEngine.query(QueryType.ENTITY, new QueryParam(queryPara));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> fuzzyQueryEntity(String fuzzyQueryParamName,
			String queryPara, String role, String format, PageBean page)
			throws QueryException, SQLException {
		try {
			Map map = queryEngine.query(QueryType.SEARCHENTITY, 
					new EntityQueryParam(queryPara,fuzzyQueryParamName));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryLinks(String queryPara, String role,
			String format) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.LINKS, new QueryParam(queryPara));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryPhones(String queryPara, String role,
			String format) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.PHONES, new QueryParam(queryPara));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryPostalAddress(String queryPara,
			String role, String format) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.POSTALADDRESS, new QueryParam(queryPara));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryVariants(String queryPara, String role,
			String format) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.VARIANTS, new QueryParam(queryPara));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> querySecureDNS(String queryPara, String role,
			String format) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.SECUREDNS, new QueryParam(queryPara));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryDsData(String queryPara, String role,
			String format) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.DSDATA, new QueryParam(queryPara));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryKeyData(String queryPara, String role,
			String format) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.KEYDATA, new QueryParam(queryPara));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryDelegationKeys(String queryPara,
			String role, String format) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.DELETATIONKEY, new QueryParam(queryPara));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryNotices(String queryPara, String role,
			String format) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.NOTICES, new QueryParam(queryPara));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	@Deprecated
	public Map<String, Object> queryRegistrar(String queryPara, String role,
			boolean isJoinTable, String format) throws QueryException {
//		Map<String, Object> map = this.queryDAO.queryRegistrar(queryPara, role, isJoinTable,
//				format);
//
//		if (map == null) {
//			return queryError("404");
//		}

		return null;
	}

	public Map<String, Object> queryRemarks(String queryPara, String role,
			String format) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.REMARKS, new QueryParam(queryPara));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryEvents(String queryPara) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.EVENTS, new QueryParam(queryPara));
			if (map == null) {
				return queryError("404");
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryError(String errorCode) throws QueryException {
		try {
			Map ErrorMessageMap = null;
			ErrorMessageMap = queryEngine.query(QueryType.ERRORMSG, new QueryParam(errorCode));
			return ErrorMessageMap;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryHelp(String helpCode, String role,
			String format) throws QueryException {
		try {
			Map helpMap = null;
			helpMap = queryEngine.query(QueryType.HELP, new QueryParam(helpCode));
			return helpMap;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	private void getRedirectionURL(String queryType, String queryPara)
			throws QueryException, RedirectExecption {
		queryEngine.query(QueryType.REDIRECTION, 
				new RedirectionQueryParam(queryType,queryPara));
	}
}
