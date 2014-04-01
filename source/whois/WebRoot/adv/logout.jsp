<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
	if(session != null){
    	session.invalidate();
	}
%>
<%@ include file="/WEB-INF/comm/queryIndex.jsp" %>
<%@ page session="true" %>