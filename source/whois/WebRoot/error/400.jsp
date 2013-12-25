<%@ page import="java.security.Principal"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>400 Error</title>

		<link href="<c:url value='/css/whois-style.css'/>" type="text/css"
			rel="stylesheet" />

		<script type="text/javascript"
			src="<c:url value='/js/jquery-1.6.2.min.js'/>"></script>
	</head>
	<body>
		<div id="wrapper">
			<div id="header">
				<h1>
					<a href=""><acronym>Restful Whois</acronym> </a>
				</h1>
			</div>
			<div id="sidebar">
				<div id="web_services">
					<fieldset>
						<legend>
							<span>Error</span>
						</legend>
					</fieldset>
				</div>
			</div>
			<div id="content">
				<div id="maincontent">
				Bad Request
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
