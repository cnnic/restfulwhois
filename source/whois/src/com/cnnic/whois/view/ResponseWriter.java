package com.cnnic.whois.view;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ResponseWriter {
	boolean support(FormatType formatType);

	Map<String, Object> format(Map<String, Object> map);

	void writeResponse(HttpServletRequest request,
			HttpServletResponse response, Map<String, Object> map, int queryType)
		throws IOException, ServletException;
	
	void displayErrorMessage(HttpServletRequest request, HttpServletResponse response, 
			FilterChain chain, String queryType, String role) 
					throws IOException, ServletException;
	
	void displayOverTimeMessage(HttpServletRequest request, HttpServletResponse response, 
			String role) 
					throws IOException, ServletException;
}