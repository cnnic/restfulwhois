<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	session.removeAttribute("openIdUser");
	session.removeAttribute("openIdUser");
	session.removeAttribute("manager");
	session.removeAttribute("openid-disc");
	session.removeAttribute("manager");
%>
<%@ include file="/WEB-INF/comm/queryIndex.jsp"%>
<%@ page session="true"%>