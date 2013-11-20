package com.cnnic.whois.service.index;

import java.io.UnsupportedEncodingException;
import java.net.IDN;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.util.WhoisUtil;

public class QueryServiceExecutor {

	private static QueryServiceExecutor executor = new QueryServiceExecutor();
	private static QueryServiceHelper queryServiceHelper = new QueryServiceHelper();
	
	public static QueryServiceExecutor getServletQueryServiceExecutor() {
		return executor;
	}
	
	public Map<String, Object> query(String queryType, String queryPara, String role, 
			String format, boolean isFuzzyQuery, PageBean page, HttpServletRequest request) throws UnsupportedEncodingException, QueryException, RedirectExecption, SQLException {
		
		Map<String, Object> map = null;
		
		int typeIndex = Arrays.binarySearch(WhoisUtil.queryTypes, queryType); //according to the type of the parameter type query
		
		switch (typeIndex) {
		case 0:
			map = queryServiceHelper.processQueryAS(queryPara, role, format);
			break;
		case 1:
			String queryParaDecode = WhoisUtil.toChineseUrl(queryPara);
			String queryParaPuny = IDN.toASCII(queryParaDecode);
//			try{
//				queryParaDecode = StringUtils.trim(queryParaDecode);
//				queryParaPuny = IDN.toASCII(queryParaDecode);//long lable exception
//			}catch(Exception e){
//				map = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);
//				processRespone(request, response, map);
//				return;
//			}
			map = queryServiceHelper.processQueryDomain(isFuzzyQuery, queryParaDecode,queryParaPuny, role, format, page, request);
			queryPara = IDN.toUnicode(IDN.toASCII(WhoisUtil.toChineseUrl(queryPara)));
			break;
		case 2:
			map = queryServiceHelper.processQueryDsData(queryPara, role, format);
			break;
		case 3:
			String queryParaWithoutPrefix = QueryServiceHelper.removeFuzzyPrefixIfHas(isFuzzyQuery, queryPara);
			map = queryServiceHelper.processQueryEntity(isFuzzyQuery,WhoisUtil.toChineseUrl(queryParaWithoutPrefix), role,
					format,request, page);
			break;
		case 4:
			map = queryServiceHelper.processQueryEvents(queryPara, role, format);
			break;
		case 5:
			map = queryServiceHelper.processQueryHelp(queryPara, role, format);
			break;
		case 6:
			map = queryServiceHelper.processQueryIP(queryPara, role, format);
			break;
		case 7:
			map = queryServiceHelper.processQueryKeyData(queryPara, role, format);
			break;
		case 8:
			map = queryServiceHelper.processQueryLinks(queryPara, role, format);
			break;
		case 9:
			map = queryServiceHelper.processQueryNameServer(isFuzzyQuery,
					IDN.toASCII(WhoisUtil.toChineseUrl(queryPara)), role, format,page,request);
			queryPara = IDN.toUnicode(IDN.toASCII(WhoisUtil.toChineseUrl(queryPara)));
			break;
		case 10:
			map = queryServiceHelper.processQueryNotices(queryPara, role, format);
			break;
		case 11:
			map = queryServiceHelper.processQueryPhones(queryPara, role, format);
			break;
		case 12:
			map = queryServiceHelper.processQueryPostalAddress(queryPara, role, format);
			break;
		case 13:
			map = queryServiceHelper.processQueryRegistrar(queryPara, role, format);
			break;
		case 14:
			map = queryServiceHelper.processQueryRemarks(queryPara, role, format);
			break;			
		case 15:
			map = queryServiceHelper.processQuerySecureDNS(queryPara, role, format);
			break;
		case 16:
			map = queryServiceHelper.processQueryVariants(queryPara, role, format);
			break;
		default:
			map = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);
			break;
		}
		return map;
		
	}

}
