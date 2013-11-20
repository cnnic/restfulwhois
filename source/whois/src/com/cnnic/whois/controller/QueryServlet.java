package com.cnnic.whois.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.cnnic.whois.bean.PageBean;
import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.service.index.QueryServiceExecutor;
import com.cnnic.whois.service.index.QueryServiceHelper;
import com.cnnic.whois.util.DataFormat;
import com.cnnic.whois.util.WhoisUtil;
import com.cnnic.whois.util.validate.ValidateUtils;


public class QueryServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		try {
			processRequest(request, response);
		} catch (QueryException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (QueryException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The request data analysis query type and parameters, depending on the
	 * type to call the corresponding method.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 *             if an input or output error is detected when the servlet
	 *             handles the request
	 * @throws ServletException
	 *             if the request could not be handled
	 * @throws QueryException 
	 * @throws SQLException 
	 */
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException, QueryException, SQLException {

		Map<String, Object> map = null;
//		char[] n = request.getRequestURI().toCharArray();
//		byte[] b = new byte[n.length];
//        for (int i = 0; i < n.length; i++) {
//            b[i] = (byte)n[i];
//        }
//        String str = new String(b, "UTF-8"); 
        String str = new String(request.getRequestURI().trim().getBytes("ISO8859-1"), "UTF-8");
		String queryInfo = str.substring(request.getContextPath().length() + 1);
		String queryType = "";
		String queryPara = "";
		
		String format = getFormatCookie(request);
		String role = WhoisUtil.getUserRole(request);
		
		if(queryInfo.indexOf("/") != -1){
			if(StringUtils.isNotBlank(queryPara)){// domains/xxx?name=z*.cn
				map = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);
				processRespone(request, response, map);
				return;
			}
			queryType = queryInfo.substring(0, queryInfo.indexOf("/"));
			queryPara = queryInfo.substring(queryInfo.indexOf("/") + 1); //get the parameters from the request scope and parse
		}else{
			queryType = queryInfo;
			//map = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE, role, format);
		}
		boolean isFuzzyQuery = ValidateUtils.isFuzzyQueryType(queryType);
//		if(isFuzzyQuery){
//			queryPara = getFuzzyQueryString(request,queryType);
//			queryType = ValidateUtils.convertFuzzyQueryType(queryType);
//		}
		request.setAttribute("queryType", queryType);
		int typeIndex = Arrays.binarySearch(WhoisUtil.queryTypes, queryType); //according to the type of the parameter type query
		PageBean page = getPageParam(request);
//		if(isFuzzyQuery && isJsonOrXmlFormat(request)){
//			page.setMaxRecords(QueryService.MAX_SIZE_FUZZY_QUERY);//json/xml set max size
//		}
		try {
			QueryServiceExecutor queryServiceExecutor = QueryServiceExecutor.getServletQueryServiceExecutor();
			map = queryServiceExecutor.query(queryType, queryPara, role, format, isFuzzyQuery, page, request);
			
			String queryParaInput = queryPara;
			queryParaInput = addPrefixBeforeParaIfEntityFuzzy(isFuzzyQuery,request, queryPara,typeIndex);;
			request.setAttribute("queryPara", WhoisUtil.toChineseUrl(queryParaInput));
		} catch (RedirectExecption re) {
			String redirectUrl = re.getRedirectURL(); //to capture to exception of rediectionException and redirect
			
			if (!(redirectUrl.endsWith("/")))
				redirectUrl += "/";
			
			response.setStatus(301);
			
			response.setHeader("Accept", format);
			response.setHeader("Location", redirectUrl + queryPara);
			response.setHeader("Connection", "close");
			return;	
		} catch (QueryException e) {
			e.printStackTrace();
			this.log(e.getMessage(), e);
			map = WhoisUtil.processError(WhoisUtil.ERRORCODE, role, format);
		}
		if(isFuzzyQuery && isJsonOrXmlFormat(request)){
			processFuzzyQueryJsonOrXmlRespone(request, response, map, typeIndex);
		}else{
			processRespone(request, response, map);
		}
	}

	private PageBean getPageParam(HttpServletRequest request) {
		Object currentPageObj = request.getParameter("currentPage");
		PageBean page = new PageBean();
		if(null != currentPageObj){
			page.setCurrentPage(Integer.valueOf(currentPageObj.toString()));
		}
		return page;
	}

	private String addPrefixBeforeParaIfEntityFuzzy(boolean isFuzzyQuery,
			HttpServletRequest request,
			String queryPara, int typeIndex) {
		if(typeIndex != 3){//only hadle entity type
			return queryPara;
		}
		if(!isFuzzyQuery){
			return queryPara;
		}
		if(StringUtils.isBlank(queryPara)){
			return queryPara;
		}
		String fuzzyQueryParamName = QueryServiceHelper.getEntityFuzzyQueryParamName(request.getQueryString());
		if(!queryPara.startsWith(fuzzyQueryParamName + ":")){
			queryPara = fuzzyQueryParamName + ":" + queryPara;
		}
		return queryPara;
	}

	private void processFuzzyQueryJsonOrXmlRespone(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map,int queryType)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String format = getFormatCookie(request);
		PrintWriter out = response.getWriter();
		String errorCode = "200"; 
		
		request.setAttribute("queryFormat", format);
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		//set response status
		if(map.containsKey("errorCode") || map.containsKey("Error Code")){
			if(map.containsKey("errorCode"))
				errorCode = map.get("errorCode").toString();
			if (map.containsKey("Error Code"))
				errorCode = map.get("Error Code").toString();
			if (errorCode.equals(WhoisUtil.ERRORCODE)){
				response.setStatus(404);
			}
			if (errorCode.equals(WhoisUtil.COMMENDRRORCODE)){
				response.setStatus(400);
			}
		}
		Iterator<String> iterr = map.keySet().iterator();
		String multiKey = null;
		while(iterr.hasNext()){
			String key =  iterr.next();
			if(key.startsWith(WhoisUtil.MULTIPRX)){
				multiKey = key;
			}
		}
		if(multiKey != null){
			Object jsonObj = map.get(multiKey);
			map.remove(multiKey);
			switch (queryType) {
			case 1:
				map.put("domainSearchResults", jsonObj);
				break;
			case 3:
				map.put("entitySearchResults", jsonObj);
				break;
			case 9:
				map.put("nameserverSearchResults", jsonObj);
				break;
			}
		}
		if (format.equals("application/json") || format.equals("application/rdap+json") || format.equals("application/rdap+json;application/json")) { // depending on the return type of the response corresponding data
			response.setHeader("Content-Type", format);
			out.print(DataFormat.getJsonObject(map));
		} else if (format.equals("application/xml")) {
			response.setHeader("Content-Type", "application/xml");
			out.write(DataFormat.getXmlString(map));
		} 
	}
	
	

	/**
	 * Returned in response to the corresponding data according to the type of
	 * request types
	 * 
	 * @param request
	 * @param response
	 * @param map
	 * @throws IOException
	 * @throws ServletException
	 */
	private void processRespone(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String format = getFormatCookie(request);
		Map<String, Object> htmlMap = new LinkedHashMap<String, Object>();
		htmlMap.putAll(map);
		
		PrintWriter out = response.getWriter();
		String errorCode = "200"; 
		
		request.setAttribute("queryFormat", format);
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		//set response status
		if(map.containsKey("errorCode") || map.containsKey("Error Code")){
			if(map.containsKey("errorCode"))
				errorCode = map.get("errorCode").toString();
			if (map.containsKey("Error Code"))
				errorCode = map.get("Error Code").toString();
			if (errorCode.equals(WhoisUtil.ERRORCODE)){
				response.setStatus(404);
			}
			if (errorCode.equals(WhoisUtil.COMMENDRRORCODE)){
				response.setStatus(400);
			}
		}
		//multi-responses
		
		Iterator<String> iterr = map.keySet().iterator();
		String multiKey = null;
		while(iterr.hasNext()){
			String key =  iterr.next();
			if(key.startsWith(WhoisUtil.MULTIPRX)){
				multiKey = key;
			}
		}
		if(multiKey != null){
			Object jsonObj = map.get(multiKey);
			map.remove(multiKey);
			map.put(multiKey.substring(WhoisUtil.MULTIPRX.length()), jsonObj);
		}
		
		if (format.equals("application/json") || format.equals("application/rdap+json") || format.equals("application/rdap+json;application/json")) { // depending on the return type of the response corresponding data
			response.setHeader("Content-Type", format);
			out.print(DataFormat.getJsonObject(map));
		} else if (format.equals("application/xml")) {
			response.setHeader("Content-Type", "application/xml");
			out.write(DataFormat.getXmlString(map));
		} else if (format.equals("application/html")) {
			if(!errorCode.equals(WhoisUtil.ERRORCODE) && !errorCode.equals(WhoisUtil.COMMENDRRORCODE)){
				if(htmlMap.containsKey(WhoisUtil.RDAPCONFORMANCEKEY))
					htmlMap.remove(WhoisUtil.RDAPCONFORMANCEKEY);
				if(htmlMap.containsKey(WhoisUtil.SEARCH_RESULTS_TRUNCATED_EKEY))
					htmlMap.remove(WhoisUtil.SEARCH_RESULTS_TRUNCATED_EKEY);
				request.setAttribute("JsonObject", DataFormat.getJsonObject(htmlMap));
				RequestDispatcher rdsp = request.getRequestDispatcher("/index.jsp");
				response.setContentType("application/html");
				rdsp.forward(request, response);
			}else{
				response.sendError(Integer.parseInt(errorCode));
			}
		} else {
			response.setHeader("Content-Type", "text/plain");
			out.write(DataFormat.getPresentation(map));
		}
	}

	private boolean isJsonOrXmlFormat(HttpServletRequest request) {
		String format = getFormatCookie(request);
		if (format.equals("application/json") || format.equals("application/rdap+json") 
				|| format.equals("application/rdap+json;application/json") || format.equals("application/xml")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Get cookies format parameters
	 * 
	 * @param request
	 * @return If data is returned to format data, null is returned if there is
	 *         no
	 */
	private String getFormatCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String format = null;
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("Format")) {
					return cookie.getValue();
				}
			}
		}
		
//		if (format == null)
//			format = request.getHeader("Accept"); // determine what kind of return type
//
//		CharSequence sqhtml = "html";			
//		if(format.contains(sqhtml))
//			format = "application/html";

		if (format == null)
			format = "application/json";
		
		return format;
	}

	/**
	 * key is the name of the value converted to unicode map collection
	 * 
	 * @param map
	 * @return Conversion completed map collection
	 */
//	private Map<String, Object> nameToUnicode(Map<String, Object> map) {
//		String name = (String) map.get("LdhName");
//		if (name != null)
//			map.put("LdhName", IDN.toUnicode(name));
//
//		return map;
//	}
}
