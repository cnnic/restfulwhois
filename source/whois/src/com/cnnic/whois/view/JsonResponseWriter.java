package com.cnnic.whois.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.cnnic.whois.execption.QueryException;
import com.cnnic.whois.util.DataFormat;
import com.cnnic.whois.util.WhoisUtil;
@Component("jsonResponseWriter")
public class JsonResponseWriter extends AbstractResponseWriter {
	private static JsonResponseWriter writer = new JsonResponseWriter();

	public static ResponseWriter getWriter() {
		return writer;
	}

	@Override
	public String formatKey(String keyName) {
		return formatKeyToCamelCaseIfNotJoinKey(keyName);
	}

	@Override
	public void writeResponse(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map, int queryType)
		throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		String errorCode = "200"; 
		
		request.setAttribute("queryFormat", FormatType.JSON.getName());
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
			default:
				map.put(multiKey.substring(WhoisUtil.MULTIPRX.length()), jsonObj);
			}
		}
		
		response.setHeader("Content-Type", FormatType.JSON.getName());
		out.print(DataFormat.getJsonObject(map));
	}

	public void displayErrorMessage(HttpServletRequest request, HttpServletResponse response, FilterChain chain, 
			String queryType, String role) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		try {
			map = WhoisUtil.processError(WhoisUtil.COMMENDRRORCODE);
		} catch (QueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		request.setAttribute("queryFormat", FormatType.JSON.getName());
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		if(isLegalType(queryType)){
			chain.doFilter(request, response);
		}else{
			response.setHeader("Content-Type", FormatType.JSON.getName());
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);//400 or 404
			out.print(DataFormat.getJsonObject(map));
		}
	}
	
	public void displayOverTimeMessage(HttpServletRequest request, HttpServletResponse response,  
			String role) throws IOException, ServletException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		try {
			map = WhoisUtil.processError(WhoisUtil.RATELIMITECODE);
		} catch (QueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter out = response.getWriter();
		request.setAttribute("queryFormat", FormatType.JSON.getName());
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setStatus(429);
		response.setHeader("Content-Type", FormatType.JSON.getName());
		out.print(DataFormat.getJsonObject(map));
	}
	
	@Override
	public boolean support(FormatType formatType) {
		return null != formatType && formatType.isJsonFormat();
	}
}
