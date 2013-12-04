/*
 * Copyright 2007 AOL, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cnnic.whois.controller.oauth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.execption.RedirectExecption;
import com.cnnic.whois.service.QueryService;
import com.cnnic.whois.util.DataFormat;
import com.cnnic.whois.util.OAuthProvider;
import com.cnnic.whois.util.WhoisUtil;
import com.cnnic.whois.util.validate.ValidateUtils;

import net.oauth.OAuthAccessor;
import net.oauth.OAuthMessage;
import net.oauth.server.OAuthServlet;

/**
 * A text servlet to echo incoming "echo" param along with userId
 *
 * @author Praveen Alavilli
 * @author John Kristian
 */
public class EchoServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try{
            OAuthMessage requestMessage = OAuthServlet.getMessage(request, null);
            OAuthAccessor accessor = OAuthProvider.getAccessor(requestMessage);
            OAuthProvider.VALIDATOR.validateMessage(requestMessage, accessor);
            
            
//            String userId = (String) accessor.getProperty("user");
            
//            response.setContentType("text/plain");
//            PrintWriter out = response.getWriter();
            
            Map<String, Object> map = map = processQueryIP("1.1.1.1", "anonymous", "application/html");
            processRespone(request, response, map);
            
//            out.println("[Your UserId:" + userId + "]");
//            for (Object item : request.getParameterMap().entrySet()) {
//                Map.Entry parameter = (Map.Entry) item;
//                String[] values = (String[]) parameter.getValue();
//                for (String value : values) {
//                    out.println(parameter.getKey() + ": " + value);
//                }
//            }
//            out.close();
            
        } catch (Exception e){
            OAuthProvider.handleException(e, request, response, false);
        }
    }

    private Map<String, Object> processQueryIP(String queryPara, String role, String format)
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
		if (format == null)
			format = "application/json";
		
		return format;
	}
    
    
}
