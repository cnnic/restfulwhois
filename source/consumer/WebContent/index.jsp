<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<title>Restful Whois SDK Demo</title>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

		<link href="${ctx }/css/whois-style.css" type="text/css" rel="stylesheet" />

		<script type="text/javascript" src="${ctx }/js/jquery-1.6.2.min.js"></script>
		<script type="text/javascript" src="${ctx }/js/json2.js"></script>
		<script type="text/javascript" src="${ctx }/js/whois-query.js" charset="utf-8"></script>
		
	</head>
<body>

	<input type="hidden" value=''
			id="pathUrl" />
		<div id="wrapper">
			<div id="header">
				<h1>
					<a href="${ctx }/index.jsp"><acronym>Restful Whois</acronym> </a>
				</h1>
				
			</div>
			<div id="sidebar">
				<div id="web_services">
					<fieldset>
						<legend>
							<span style="padding-left: 40px;">Restful Whois</span>
						</legend>
						<br />
						<hr />

						<table>
							
							<tr>
								<td style="padding-left: 90px;padding-top: 20px;"><span>
								<a href="${ctx }/SampleProvider?queryType=ip">IP</a></span></td>
							</tr>
							
							<tr>
								<td style="padding-left: 50px;padding-top: 20px;"><span><a href="${ctx }/SampleProvider?queryType=domain">Domain Query</a></span></td>
							</tr>
							
							<tr>
								<td style="padding-left: 80px;padding-top: 20px;"><span><a href="${ctx }/SampleProvider?queryType=entity">Entity</a></span></td>
							</tr>
							<%-- 
							<tr>
								<td style="padding-left: 40px;padding-top: 20px;"><span><a href="${ctx }/SampleProvider">Autonomous System</a></span></td>
							</tr>
							
							<tr>
								<td style="padding-left: 60px;padding-top: 20px;"><span><a href="${ctx }/SampleProvider">Name Server</a></span></td>
							</tr>
							--%>
						</table>
					

					</fieldset>
				</div>
			</div>
			
			<div id="content">
				<div id="maincontent"></div>
				<div id="jsoncontent"></div>
			</div>
			<c:if test="${pageBean != null}">
				<cnnic:page href="${queryPath}" pageBeanName="pageBean" maxShowPage="5" maxRecordConfigurable= "false" id="1"/>
			</c:if>
			<div style="clear: both;"></div>
			
			<div style="clear: both;"></div>
		</div>
		<div id="footer">
			<p>
				By using the Restful Whois service, you are agreeing to the
				<a href="/whois_tou.html" class="footer_link" style="color: #000">Whois Terms of Use</a>
				<br />
				&copy; Copyright 2013, CNNIC & ICANN
			</p>
			<img name='image1' alt="" src="${ctx }/image/cnnicLogo.jpg">
			<img alt="" name='image2' src="${ctx }/image/icannLogo.jpg">
		</div>
		
<!--  
	<table align="center" style="text-align: center;border: 1px solid #999;;">
		<tr>
			<td style="width: 300px;height: 50px;"><a href="SampleProvider">Restful Whois IP </a></td>
		</tr>
		<tr>
			<td style="width: 300px;height: 50px"><a href="SampleProvider">Restful Whois Domain </a></td>
		</tr>
	</table>
-->

</body>
</HTML>
