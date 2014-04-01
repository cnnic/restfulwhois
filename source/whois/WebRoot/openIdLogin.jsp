<%@page import="net.sf.json.JSONObject"%>
<%@ page import="java.security.Principal"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>Restful Whois</title>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

		<link href="<c:url value='/css/whois-style.css'/>" type="text/css"
			rel="stylesheet" />
		<script type="text/javascript"
			src="<c:url value='/js/jquery-1.6.2.min.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/js/json2.js'/>"></script>
		<script type="text/javascript"
			src="<c:url value='/js/whois-query.js'/>" charset="utf-8"></script>
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h1>
					<a href="<%=request.getContextPath()%>/index.jsp"><acronym>Restful Whois</acronym> </a>
				</h1>
				<ul id="menu">
					<li class="one">
						<dl>
							<dt>
								<a href="" onclick="aboutContent();return false">About Us</a>
							</dt>
						</dl>
					</li>
				</ul>
			</div>
			<div id="sidebar">
				<div id="web_services">
					<fieldset>
						<legend>
							<span>Restful Whois</span>
						</legend>
						<br />
						<hr />
					</fieldset>
				</div>
			</div>
			<div id="content">
				<div id="maincontent">
					<%
				String errorMesaage = (String)request.getAttribute("errorMessage");
				if(errorMesaage == null) errorMesaage="";
				
				 %>

					<form id="OpenIdRegistrationForm" action="authOpenId.do">
						<span style="color: red"><%=errorMesaage %></span><br /><br />
						<h1>
							Please enter and confirm your OpenID below to register with this
							site.
						</h1>
						<br />

						Your Open ID:

						<input type="text" name="openIdValue" size="60" value="http://" />
						<input type="submit" value="OpenID Login" />

					</form>
				</div>

			</div>
			<div style="clear: both;"></div>
		</div>
		<div id="footer">
			<p>
				By using the Restful Whois service, you are agreeing to the Whois Terms of Use
				<br />
				&copy; Copyright 2013, CNNIC & ICANN
			</p>
			<img name='image1' alt="" src="<c:url value='/image/cnnicLogo.jpg'/>">
			<img alt="" name='image2' src="<c:url value='/image/icannLogo.jpg'/>">
		</div>
	</body>
</html>
