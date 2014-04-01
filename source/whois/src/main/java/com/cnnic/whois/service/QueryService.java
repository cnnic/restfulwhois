package com.cnnic.whois.service;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnnic.whois.bean.EntityQueryParam;
import com.cnnic.whois.bean.IpQueryParam;
import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;
import com.cnnic.whois.bean.RedirectionQueryParam;
import com.cnnic.whois.dao.query.QueryEngine;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.util.IpUtil;
import com.cnnic.whois.util.WhoisProperties;
import com.cnnic.whois.util.WhoisUtil;
@Service
public class QueryService {
	private static Long MIN_AS_NUM = 0L;
	private static Long MAX_AS_NUM = 4294967295L;
	@Autowired
	private QueryEngine queryEngine ;
	public static int MAX_SIZE_FUZZY_QUERY = Integer.parseInt(WhoisProperties
			.getMaxFuzzyQuery());

	public Map<String, Object> queryIP(IpQueryParam ipQueryParam) throws QueryException,
		RedirectExecption {
		long[] ipLongs = IpUtil.parsingIp(ipQueryParam.getIpInfo(), ipQueryParam.getIpLength());
		ipQueryParam.setStartHighAddr(ipLongs[0]);
		ipQueryParam.setEndHighAddr(ipLongs[1]);
		ipQueryParam.setStartLowAddr(ipLongs[2]);
		ipQueryParam.setEndLowAddr(ipLongs[3]);
		Map map = queryEngine.query(QueryType.IP, ipQueryParam);
		if (map == null) {
			queryEngine.query(QueryType.IPREDIRECTION, 
					new IpQueryParam(ipQueryParam.getIpInfo(),ipLongs[0], ipLongs[1], ipLongs[2],ipLongs[3]));
			return queryError("404",ipQueryParam);
		}
		return map;
	}
	
	public Map<String, Object> queryAS(QueryParam queryParam)
			throws QueryException, RedirectExecption {
		String autnum = queryParam.getQ();
		Map<String, Object> resultMap = null;
		if (!autnum.matches("^[1-9][0-9]{0,9}$")){
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE,queryParam);
		}
		Long longValue = Long.valueOf(autnum);
		if (longValue <= MIN_AS_NUM || longValue >= MAX_AS_NUM) {
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE,queryParam);
		}
		resultMap = queryEngine.query(QueryType.AUTNUM, queryParam);
		if (resultMap == null) {
			getRedirectionURL("autnum", queryParam.getQ());
			return queryError("404",queryParam);
		}
		return resultMap;
	}

	public Map<String, Object> fuzzyQueryNameServer(QueryParam queryParam) throws QueryException,
			RedirectExecption {
		Map dnrMap = queryEngine.query(QueryType.SEARCHNS,queryParam);
		if (dnrMap == null) {
			return queryError("404",queryParam);
		}
		return dnrMap;
	}

	public Map<String, Object> queryDomain(QueryParam queryParam) throws QueryException, RedirectExecption {
		Map rirMap = queryEngine.query(QueryType.RIRDOMAIN, queryParam);
		Map dnrMap = queryEngine.query(QueryType.DNRDOMAIN, queryParam);
		if ((rirMap == null) && (dnrMap == null)) {
			String queryType = "dnrdomain";

			if (rirMap == null)
				queryType = "rirdomain";
			getRedirectionURL(queryType, queryParam.getQ());
			return queryError("404",queryParam);
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
	
	public Map<String, Object> queryEntity(QueryParam queryParam) throws QueryException, SQLException {
		try {
			Map map = queryEngine.query(QueryType.ENTITY, queryParam);
			if (map == null) {
				return queryError("404",queryParam);
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}
	
	public Map<String, Object> fuzzyQueryEntity(EntityQueryParam entityQueryParam)
			throws QueryException, SQLException {
		try {
			Map map = queryEngine.query(QueryType.SEARCHENTITY,entityQueryParam);
			if (map == null) {
				return queryError("404",entityQueryParam);
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}
	
	public Map<String, Object> query(QueryParam queryParam) throws QueryException {
		try {
			Map map = queryEngine.query(queryParam.getQueryType(), queryParam);
			if (map == null) {
				map = queryError("404",queryParam);
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryDsData(QueryParam queryParam) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.DSDATA, queryParam);
			if (map == null) {
				return queryError("404",queryParam);
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}
	
	public Map<String, Object> queryEvents(QueryParam queryParam) throws QueryException {
		try {
			Map map = queryEngine.query(QueryType.EVENTS, queryParam);
			if (map == null) {
				return queryError("404",queryParam);
			}
			return map;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryError(String errorCode,QueryParam queryParam) throws QueryException {
		try {
			Map result = queryEngine.query(QueryType.ERRORMSG, new QueryParam(errorCode));
			result = queryEngine.format(result, queryParam);
			return result;
		} catch (RedirectExecption e) {
			throw new QueryException(e);
		}
	}

	public Map<String, Object> queryHelp(QueryParam queryParam) throws QueryException {
		try {
			Map helpMap = null;
			helpMap = queryEngine.query(QueryType.HELP, queryParam);
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