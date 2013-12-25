<%@ page import="net.sf.json.JSONObject"%>
<%@ page import="java.security.Principal"%>
<%@ page import="com.cnnic.whois.util.WhoisProperties"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<%@ taglib prefix="cnnic" uri="http://cnnic.cn" %>
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
		<script language="javascript">
	function go() {
		var event = window.event || arguments.callee.caller.arguments[0];
		if (event.keyCode == 13) {
			document.getElementById("go").click();
			return false;
		}
	}
</script>
	</head>
	<body>
		<%
			Principal user = request.getUserPrincipal();
			String username = "";
			String openIdName = null;
			if (user != null) {
				if (!request.isUserInRole("administrator")) {
					username = user.getName();
				}else{
					user = null;
				}
			}else if(request.getSession().getAttribute("openIdUser") != null){
				openIdName = "openIdName";
				username = (String)request.getSession().getAttribute("openIdUser");
			}
			
			request.setAttribute("user", user);
			request.setAttribute("openIdName", openIdName);
			JSONObject jsonObject = (JSONObject) request
					.getAttribute("JsonObject");
					
			String queryType = "";
			String queryPara = "";
			String queryFormat = "";
			if (request.getAttribute("queryType") != null){
				queryType = (String)request.getAttribute("queryType");
				queryPara = (String)request.getAttribute("queryPara");
				queryFormat = (String)request.getAttribute("queryFormat");
			}
		%>
		<input type="hidden" value='<%=request.getContextPath()+"/"+WhoisProperties.getRdapUrl()%>'
			id="pathUrl" />
		<div id="wrapper">
			<div id="header">
				<h1>
					<a href="<%=request.getContextPath()%>/index.jsp"><acronym>Restful Whois</acronym> </a>
				</h1>
				<ul id="menu">
					<li class="one">
						<dl>
							<dt>
								<a href="<%=request.getContextPath()+"/"+WhoisProperties.getRdapUrl()%>/help">Help</a>
							</dt>
						</dl>
					</li>
					<li class="one">
						<dl>
							<dt>
								<a href="" onclick="aboutContent();return false">About Us</a>
							</dt>
						</dl>
					</li>
					<%if (openIdName == null && user == null) {%>
					<li class="two">
						<dl>
							<dt>
								<a href="<c:url value='/adv/advindex.jsp'/>">Login</a>
							</dt>
						</dl>
					</li>
					<li class="four">
						<dl>
							<dt>
								<a href="<c:url value='/openIdLogin.jsp'/>">OpenId Login</a>
							</dt>
						</dl>
					</li>
					<%} else if( openIdName != null){%>
					<li class="six">
						<dl>
							<dt>
								<font>Current User: <big style="color: red"><%=username%></big>

								</font>
							</dt>
						</dl>
					</li>
					<li class="five">
						<dl>
							<dt>
								<a href="<c:url value='/'/>">logout</a>
							</dt>
						</dl>
					</li>
					<%} else {%>
					<li class="three">
						<dl>
							<dt>
								<font>Current User: <big style="color: red"><%=username%></big>

								</font>
							</dt>
						</dl>
					</li>
					<% }%>
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

						<input id="optionType" type="radio" name="optionType" value="ip"
							checked="checked" />
						<span>IP</span>
						<br />

						<input id="optionType" name="optionType" type="radio"
							<%
							if (queryType.equals("domain")) {
						 %>
							checked="checked" <%} %> value="domain" />
						<span>Domain Query</span>
						<br />

						<input id="optionType" type="radio" name="optionType"
							<%
							if (queryType.equals("entity")) {
						 %>
							checked="checked" <%} %> value="entity" />
						<span>Entity</span>
						<br />

						<input id="optionType" type="radio" name="optionType"
							<%
							if (queryType.equals("autnum")) {
						 %>
							checked="checked" <%} %> value="autnum" />
						<span>Autonomous System</span>
						<br />

						<input id="optionType" type="radio" name="optionType"
							<%
							if (queryType.equals("nameserver")) {
						 %>
							checked="checked" <%} %> value="nameserver" />
						<span>Name Server</span>
						<br />
						<hr />

						<input id="showType" name="showType" type="radio"
							<%
							if (queryType.equals("application/json")) {
						 %>
							checked="checked" <%} %> value="json">
						<span>JSON Format</span>
						<br />

						<input id="showType" name="showType" type="radio"
							<%
							if (queryType.equals("application/xml")) {
						 %>
							checked="checked" <%} %> value="xml">
						<span>XML Format</span>
						<br />

						<input id="showType" name="showType" type="radio" value="html"
							checked="checked">
						<span>Presentation Format</span>
						<br />
						<hr />
						<br />

						<input size="20" id="queryInfo" type="text" onkeydown="go()"
							value="<%=queryPara %>" />
						<input type="button" value="go" onclick="processQuery()" id='go' />
						<br />
					</fieldset>
				</div>
			</div>
			<div id="content">
				<div id="maincontent"><%=jsonObject%></div>
				<div id="jsoncontent"></div>
			</div>
			<c:if test="${pageBean != null}">
				<cnnic:page href="${queryPath}" pageBeanName="pageBean" maxShowPage="5" maxRecordConfigurable= "false" id="1"/>
			</c:if>
			<div style="clear: both;"></div>
		</div>
		<div id="footer">
			<p>
				By using the Restful Whois service, you are agreeing to the Whois Terms of Use
				<br />
				&copy; Copyright 2013, CNNIC & ICANN
			</p>
			<img name='image1' alt="" src="/image/cnnicLogo.jpg">
			<img alt="" name='image2' src="<c:url value='/image/icannLogo.jpg'/>">
		</div>
	</body>
</html>