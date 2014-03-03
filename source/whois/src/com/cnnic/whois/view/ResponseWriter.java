package com.cnnic.whois.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cnnic.whois.bean.QueryParam;
import com.cnnic.whois.bean.QueryType;

public interface ResponseWriter {
	boolean support(FormatType formatType);

	Map<String, Object> format(Map<String, Object> map);

	void writeResponse(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map)
		throws IOException, ServletException;
	
	void displayOverTimeMessage(HttpServletRequest request, HttpServletResponse response, 
			String role,QueryParam queryParam) 
					throws IOException, ServletException;
	
	Map<String, Object> getMapKey(QueryType queryType, Map<String, Object> map);
}