package com.cnnic.whois.service.index;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.service.QueryService;
import com.cnnic.whois.util.WhoisUtil;
import com.cnnic.whois.util.validate.ValidateUtils;

public class QueryServiceHelper {

	private static Long MIN_AS_NUM = 0L;
	private static Long MAX_AS_NUM = 4294967295L;
	
	private static QueryServiceHelper queryServiceHelper = new QueryServiceHelper();

	public static QueryServiceHelper getQueryServiceHelper() {
		return queryServiceHelper;
	}
	
	/**
	 * Query as type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 * @throws RedirectExecption
	 */
	protected Map<String, Object> processQueryAS(String queryPara, String role, String format)
			throws QueryException, RedirectExecption {
		if (!ValidateUtils.isCommonInvalidStr(queryPara))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);
		if (!queryPara.matches("^[1-9][0-9]{0,9}$"))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);
		Long longValue = Long.valueOf(queryPara);
		if(longValue<=MIN_AS_NUM || longValue>=MAX_AS_NUM){
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);
		}
		
		try {
			int queryInfo = Integer.parseInt(queryPara);
			QueryService queryService = QueryService.getQueryService();
			return queryService.queryAS(queryInfo, role, format);
		} catch (NumberFormatException e) {
			e.printStackTrace();
//			this.log(e.getMessage(), e);
			throw new QueryException(e);
		}

	}
	
	/**
	 * Query domain type
	 * 
	 * @param queryPara
	 * @param role
	 * @param page 
	 * @param request 
	 * @return map collection
	 * @throws QueryException
	 * @throws RedirectExecption
	 * @throws UnsupportedEncodingException 
	 */
	protected Map<String, Object> processQueryDomain(boolean isFuzzyQuery,String queryPara, String queryParaPuny,
			String role, String format, PageBean page, HttpServletRequest request)
			throws QueryException, RedirectExecption, UnsupportedEncodingException {
		if(!ValidateUtils.validateDomainName(queryParaPuny)){
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);
		}
//		TODO : 似乎多余的判断
		QueryService queryService = QueryService.getQueryService();
		if(isFuzzyQuery){
			request.setAttribute("pageBean", page);
			request.setAttribute("queryPath", "domains");
			
			return queryService.fuzzyQueryDomain(queryPara,queryParaPuny, role, format,page);
		}
		return queryService.queryDomain(queryParaPuny, role, format);
	}
	
	/**
	 * Query entity type
	 * 
	 * @param queryParaValue
	 * @param role
	 * @param request 
	 * @param page 
	 * @return map collection
	 * @throws QueryException
	 * @throws SQLException 
	 */
	protected Map<String, Object> processQueryEntity(boolean isFuzzyQuery,String queryParaValue, 
			String role, String format, HttpServletRequest request, PageBean page)
			throws QueryException, SQLException {
		if (!StringUtils.isNotBlank(queryParaValue))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);

		QueryService queryService = QueryService.getQueryService();
		if(isFuzzyQuery){
			String fuzzyQueryParamName = getEntityFuzzyQueryParamName(request.getQueryString());
			String fuzzyQuerySolrPropName = convertEntityFuzzyQueryParamNameToSolrPropName(fuzzyQueryParamName);
			Map<String, Object> result = queryService.fuzzyQueryEntity(fuzzyQuerySolrPropName,
					queryParaValue, role, format,page);
			request.setAttribute("pageBean", page);
			request.setAttribute("queryPath", "entities");
			return result;
		}
		return queryService.queryEntity(queryParaValue, role, format);
	}
	
	protected Map<String, Object> processQueryHelp(String queryPara, String role, String format) throws QueryException {
		QueryService queryService = QueryService.getQueryService();
		if(!queryPara.equals("")){
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);
		}
		return queryService.queryHelp("helpID", role, format);
	}

	
	/**
	 * Query ip type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 * @throws NumberFormatException
	 * @throws RedirectExecption
	 */
	protected Map<String, Object> processQueryIP(String queryPara, String role, String format)
			throws QueryException, NumberFormatException, RedirectExecption {
		String ipLength = "0";
		String strInfo = queryPara;

		if (queryPara.indexOf(WhoisUtil.PRX) >= 0) {
			String[] infoArray = queryPara.split(WhoisUtil.PRX);
			if(infoArray.length > 2){//1.1.1.1//32
				return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);
			}
			if(infoArray.length > 1){
				strInfo = infoArray[0];
				ipLength = infoArray[1];
			}
		}

		if (!ValidateUtils.verifyIP(strInfo, ipLength)) {
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);
		}

		QueryService queryService = QueryService.getQueryService();
		return queryService.queryIP(strInfo, Integer.parseInt(ipLength), role, format);
	}

	

	

	private String convertEntityFuzzyQueryParamNameToSolrPropName(
			String fuzzyQueryParamName) {
		if("fn".equals(fuzzyQueryParamName)){
			return "entityNames";
		}
		return fuzzyQueryParamName;
	}

	/**
	 * Query nameServer type
	 * 
	 * @param queryPara
	 * @param role
	 * @param page 
	 * @param request 
	 * @return map collection
	 * @throws QueryException
	 * @throws RedirectExecption 
	 */
	protected Map<String, Object> processQueryNameServer(boolean isFuzzyQuery,String queryPara,
			String role, String format, PageBean page, HttpServletRequest request) throws QueryException, RedirectExecption {
		if (!ValidateUtils.verifyNameServer(queryPara))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);

		QueryService queryService = QueryService.getQueryService();
		if(isFuzzyQuery){
			request.setAttribute("pageBean", page);
			request.setAttribute("queryPath", "nameservers");
			return queryService.fuzzyQueryNameServer(queryPara, role, format,page);
		}
		return queryService.queryNameServer(queryPara, role, format);
	}
	
	/**
	 * Query link type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	protected Map<String, Object> processQueryLinks(String queryPara, String role, String format)
			throws QueryException {
		if (!ValidateUtils.isCommonInvalidStr(queryPara))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);

		QueryService queryService = QueryService.getQueryService();
		return queryService.queryLinks(queryPara, role, format);
	}

	/**
	 * Query phone type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	protected Map<String, Object> processQueryPhones(String queryPara, String role, String format)
			throws QueryException {
		if (!ValidateUtils.isCommonInvalidStr(queryPara))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);

		QueryService queryService = QueryService.getQueryService();
		return queryService.queryPhones(queryPara, role, format);
	}

	/**
	 * Query postalAddress type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	protected Map<String, Object> processQueryPostalAddress(String queryPara,
			String role, String format) throws QueryException {
		if (!ValidateUtils.isCommonInvalidStr(queryPara))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);

		QueryService queryService = QueryService.getQueryService();
		return queryService.queryPostalAddress(queryPara, role, format);
	}

	/**
	 * Query variants type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	protected Map<String, Object> processQueryVariants(String queryPara,
			String role, String format) throws QueryException {
		if (!ValidateUtils.isCommonInvalidStr(queryPara))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);

		QueryService queryService = QueryService.getQueryService();
		return queryService.queryVariants(queryPara, role, format);
	}
	
	/**
	 * Query SecureDNS
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	protected Map<String, Object> processQuerySecureDNS(String queryPara,
			String role, String format) throws QueryException {
		if (!ValidateUtils.isCommonInvalidStr(queryPara))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);

		QueryService queryService = QueryService.getQueryService();
		return queryService.querySecureDNS(queryPara, role, format);
	}
	
	/**
	 * Query DsData
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	protected Map<String, Object> processQueryDsData(String queryPara,
			String role, String format) throws QueryException {
		if (!ValidateUtils.isCommonInvalidStr(queryPara))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);

		QueryService queryService = QueryService.getQueryService();
		return queryService.queryDsData(queryPara, role, format);
	}
	
	/**
	 * Query KeyData
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	protected Map<String, Object> processQueryKeyData(String queryPara,
			String role, String format) throws QueryException {
		if (!ValidateUtils.isCommonInvalidStr(queryPara))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);

		QueryService queryService = QueryService.getQueryService();
		return queryService.queryKeyData(queryPara, role, format);
	}

	/**
	 * Query notices type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	protected Map<String, Object> processQueryNotices(String queryPara,
			String role, String format) throws QueryException {
		if (!ValidateUtils.isCommonInvalidStr(queryPara))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);

		QueryService queryService = QueryService.getQueryService();
		return queryService.queryNotices(queryPara, role, format);
		
//		DbQueryExecutor dbQueryExecutor = DbQueryExecutor.getExecutor();
//		Map map = dbQueryExecutor.query(QueryType.NOTICES, new QueryParam(queryPara), role, format);
//		if (map == null) {
//			return queryError("404", role, format);
//		}
//		return map;
		
	}

	/**
	 * Query registrar type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	protected Map<String, Object> processQueryRegistrar(String queryPara,
			String role, String format) throws QueryException {
		QueryService queryService = QueryService.getQueryService();
		return queryService.queryRegistrar(queryPara, role, true, format);
	}
	/**
	 * Query Remarks type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	protected Map<String, Object> processQueryRemarks(String queryPara,
			String role, String format) throws QueryException {
		if (!ValidateUtils.isCommonInvalidStr(queryPara))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);

		QueryService queryService = QueryService.getQueryService();
		return queryService.queryRemarks(queryPara, role, format);
	}
	
	/**
	 * Query Events type
	 * 
	 * @param queryPara
	 * @param role
	 * @return map collection
	 * @throws QueryException
	 */
	protected Map<String, Object> processQueryEvents(String queryPara,
			String role, String format) throws QueryException {
		if (!ValidateUtils.isCommonInvalidStr(queryPara))
			return WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);

		QueryService queryService = QueryService.getQueryService();
		return queryService.queryEvents(queryPara, role, format);
	}
	

	public static String getEntityFuzzyQueryParamName(String queryString) {
		Map<String, String> paramsMap = parseQueryParameter(queryString);
		if(paramsMap.containsKey("fn")){
			return "fn";
		}
		return "handle";
	}
	
	public static Map<String, String> parseQueryParameter(String queryParameter) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isEmpty(queryParameter)) {
			return map;
		}
		int index = queryParameter.lastIndexOf("=");
		int pos = queryParameter.length();
		while (index != -1) {
			String value = queryParameter.substring(index + 1, pos);
			pos = index;
			index = queryParameter.lastIndexOf("&", index);
			String keyName = queryParameter.substring(index + 1, pos);
			pos = index;
			index = queryParameter.lastIndexOf("=", pos);
			map.put(keyName, value);
		}
		return map;
	}
	
	public static String removeFuzzyPrefixIfHas(boolean isFuzzyQuery,String queryPara) {
		if(StringUtils.isBlank(queryPara)){
			return queryPara;
		}
		if(!isFuzzyQuery){
			return queryPara;
		}
		return queryPara.replaceFirst("fn:", "").replaceFirst("handle:", "");
	}
	
}
