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
		<script language="javascript"></script>
		<script type="text/javascript">
		function changeHide(contentDivId){
			var contentDiv=document.getElementById(contentDivId);
			if(contentDiv.style.display=="none"){
				contentDiv.style.display="";
			}else{
				contentDiv.style.display="none";
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
				<div id="maincontent">
				    <h1 id="API" style="font-size:36px">API</h1>
				    <br />
                    <ul id="markdown-toc">
                        <li>
                            <a href="#queryIP" style="line-height:200%">Query IP</a>
                        </li>
                        <li>
                            <a href="#queryDomain" style="line-height:200%">Query domain</a>
                        </li>
                        <li>
                            <a href="#queryEntity" style="line-height:200%">Query entity</a>
                        </li>
                        <li>
                            <a href="#queryAS" style="line-height:200%">Query autonomous system</a>
                        </li>
                        <li>
                            <a href="#queryNS" style="line-height:200%">Query name server</a>
                        </li>
                        <li>
                            <a href="#fuzzyQueryDomain" style="line-height:200%">Query domain with wildcard</a>
                        </li>
                        <li>
                            <a href="#fuzzyQueryEntity" style="line-height:200%">Query entity with wildcard</a>
                        </li>
                        <li>
                            <a href="#fuzzyQueryNS" style="line-height:200%">Query name server with wildcard</a>
                        </li>
                    </ul>
                    <br />
                    <hr />
                    <h3 id="queryIP" style="font-size:20px">query IP</h3>
                    <br />
                    <div id="query_IP">
                        <div>
                            <div>Function</div>
                            <table >
                                <tr>
                                    <td >Connect to the database to query IP information</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Path</div>
                            <table >
                                <tr>
                                    <td >/.well-known/rdap/ip/{ipAddress}</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Parameter</div>
                            <table>
                                <thead>
                                    <tr>
                                        <th colspan="2" width="20%" >Parameter</th>
                                        <th colspan="2" width="20%" >Type</th>
                                        <th colspan="2">Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="2">ipAddress</td>
                                        <td colspan="2">String</td>
                                        <td >IP address in dotted decimal representation</td>
                                    </tr>                                    
                                </tbody>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Request</div>
                            <div>
                                <p>Example:&nbsp;&nbsp;&nbsp;&nbsp;Query information with IP address <span style="color:blue">&nbsp;9.0.0.1</span></p>
                            </div>
                            <table >
                                <tr>
                                    <td >                           
                                        GET /.well-known/rdap/ip/9.0.0.1 HTTP/1.1<br />
                                        Accept: text/html,application/rdap+json,application/json,application/rdap+json;application/json<br />                                                                              
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Response</div>
                            <table >
                                <tr>
                                    <td>
                                        HTTP/1.1 200 OK<br />
                                        Server: Apache-Coyote/1.1<br />                                        
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2" onclick="changeHide('ip-json')">JSON Format</th>
                                </tr>
                                <tr id="ip-json"  style="display:none">
                                    <td>
                                        <code>{<br /></code>
                                        <blockquote><code>"rdapConformance":["rdap_level_0"],</code></blockquote>
                                        <blockquote><code>"handle":"NET-9-0-0-0-1",</code></blockquote>
                                        <blockquote><code>"startAddress":"9.0.0.0",</code></blockquote>
                                        <blockquote><code>"endAddress":"9.255.255.255",</code></blockquote>
                                        <blockquote><code>"ipVersion":"v4",</code></blockquote>
                                        <blockquote><code>"name":"IBM",</code></blockquote>
                                        <blockquote><code>"type":"Direct Assignment",</code></blockquote>
                                        <blockquote><code>"country":"US",</code></blockquote>
                                        <blockquote><code>"parentHandle":"",</code></blockquote>
                                        <blockquote><code>"status":[""],</code></blockquote>
                                        <blockquote><code>"testip":""</code></blockquote>                                      
                                        <code>}<br /></code>
                                    </td>
                                </tr>                                
                            </table>
                        </div>
                        <br />         
                    </div>
                    <hr />
                    <h3 id="queryDomain" style="font-size:20px">query domain</h3>
                    <br />
                    <div id="query_Domain">
                        <div>
                            <div>Function</div>
                            <table >
                                <tr>
                                    <td >Connect to the database to query domain information</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Path</div>
                            <table >
                                <tr>
                                    <td >/.well-known/rdap/domain/{domainName}</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Parameter</div>
                            <table>
                                <thead>
                                    <tr>
                                        <th colspan="2" width="20%" >Parameter</th>
                                        <th colspan="2" width="20%" >Type</th>
                                        <th colspan="2">Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="2">domainName</td>
                                        <td colspan="2">String</td>
                                        <td >Domain name</td>
                                    </tr>                                    
                                </tbody>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Request</div>
                            <div>
                                <p>Example:&nbsp;&nbsp;&nbsp;&nbsp;Query information with domain name <span style="color:blue">&nbsp;sina.cn</span></p>
                            </div>
                            <table >
                                <tr>
                                    <td >                           
                                        GET /.well-known/rdap/domain/sina.cn HTTP/1.1<br />
                                        Accept: text/html,application/rdap+json,application/json,application/rdap+json;application/json<br />                                                                              
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />                      
                        <div>
                            <div>Response</div>
                            <table >
                                <tr>
                                    <td>
                                        HTTP/1.1 200 OK<br />
                                        Server: Apache-Coyote/1.1<br />                                        
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2"  onclick="changeHide('domain-json')">JSON Format</th>
                                </tr>
                                <tr id="domain-json"  style="display:none">
                                    <td>
                                        <code>{<br /></code>
                                        <blockquote><code>"rdapConformance":["rdap_level_0"],</code></blockquote>
                                        <blockquote><code>"handle":"20030311s10001s00038551-cn",</code></blockquote>
                                        <blockquote><code>"ldhName":"sina.cn",</code></blockquote>
                                        <blockquote><code>"unicodeName":" ",</code></blockquote>
                                        <blockquote><code>"lang":" ",</code></blockquote>
                                        <blockquote><code>"status":["ok"],</code></blockquote>
                                        <blockquote><code>"port43":"whois.cnnic.cn"</code></blockquote>                                                                           
                                        <code>}<br /></code>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />         
                    </div>   
                    <hr />
                    <h3 id="queryEntity" style="font-size:20px">query entity</h3>
                    <br />
                    <div id="query_Entity">
                        <div>
                            <div>Function</div>
                            <table >
                                <tr>
                                    <td >Connect to the database to query entity information</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Path</div>
                            <table >
                                <tr>
                                    <td >/.well-known/rdap/entity/{entityName}</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Parameter</div>
                            <table>
                                <thead>
                                    <tr>
                                        <th colspan="2" width="20%" >Parameter</th>
                                        <th colspan="2" width="20%" >Type</th>
                                        <th colspan="2">Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="2">entityName</td>
                                        <td colspan="2">String</td>
                                        <td >Entity name</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Request</div>
                            <div>
                                <p>Example:&nbsp;&nbsp;&nbsp;&nbsp;Query information with entity name <span style="color:blue">&nbsp;APNIC</span></p>
                            </div>
                            <table >
                                <tr>
                                    <td >                           
                                        GET /.well-known/rdap/entity/APNIC HTTP/1.1<br />
                                        Accept: text/html,application/rdap+json,application/json,application/rdap+json;application/json<br />                                                                              
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Response</div>
                            <table >
                                <tr>
                                    <td>
                                        HTTP/1.1 200 OK<br />
                                        Server: Apache-Coyote/1.1<br />                                        
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2"  onclick="changeHide('entity-json')">JSON Format</th>
                                </tr>
                                <tr id="entity-json"  style="display:none">
                                    <td>
                                        <code>{<br /></code>
                                        <blockquote><code>"rdapConformance":["rdap_level_0"],</code></blockquote>
                                        <blockquote><code>"handle":"APNIC",</code></blockquote>
                                        <blockquote><code>"lang":" ",</code></blockquote>
                                        <blockquote><code>"roles":["registrant"],</code></blockquote>
                                        <blockquote><code>"links":[{</code></blockquote>  
                                        <blockquote><blockquote><code>"value":"http://rdap.restfulwhois.org/entity/APNIC",</code></blockquote></blockquote>       
                                        <blockquote><blockquote><code>"rel":"self",</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"href":"http://rdap.restfulwhois.org/entity/APNIC",</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"hreflang":[" "],</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"title":[" "],</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"media":" ",</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"type":"application/rdap+json"</code></blockquote></blockquote> 
                                        <blockquote><code>}],</code></blockquote> 
                                        <blockquote><code>"publicIds":[{</code></blockquote> 
                                        <blockquote><blockquote><code>"type":"ENS_Auth ID",</code></blockquote></blockquote> 
                                        <blockquote><blockquote><code>"identifier":"1234567890"</code></blockquote></blockquote> 
                                        <blockquote><code>}],</code></blockquote>
                                        <blockquote><code>"vcardArray":[</code></blockquote> 
                                        <blockquote><blockquote><code>"vcard",</code></blockquote></blockquote> 
                                        <blockquote><blockquote><code>[</code></blockquote></blockquote> 
                                        <blockquote><blockquote><blockquote><code>["version",{},"text","4.0"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["fn",{},"text","Asia Pacific Network Information Centre (APNIC)"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["bday",{},"date-and-or-time","1983-10-23T00:00:00Z"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["anniversary",{},"date-and-or-time","1983-10-23T00:00:00Z"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["gender",{},"text","M"],</code></blockquote></blockquote></blockquote>                                                                      
                                        <blockquote><blockquote><blockquote><code>["kind",{},"text","individual"],</code></blockquote></blockquote></blockquote>                                                                     
                                        <blockquote><blockquote><blockquote><code>["lang",{"pref":"1"},"language-tag","en"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["lang",{"pref":"2"},"language-tag","fr"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["org",{"type":"work"},"text","example inc."],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["title",{},"text","employee"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["role",{},"text","testrole"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["adr",{"type":"work"},"text",["",",","PO Box 3646","South Brisbane","QLD","4101","AU"]],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["email",{},"text",""],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["geo",{"type":"work"},"uri","geo:46.772673,-71.282945"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["key",{"type":"work"},"uri","http://www.example.com/joe.user/joe.asc"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["tz",{},"utc-offset","-05:00"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>["url",{"type":"home"},"uri","http://example.org"]</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><code>]</code></blockquote></blockquote>
                                        <blockquote><code>]</code></blockquote>                                      
                                        <code>}<br /></code>                                      
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />         
                    </div>
                    <hr />
                    <h3 id="queryAS" style="font-size:20px">query autonomous system</h3>
                    <br />
                    <div id="query_AS">
                        <div>
                            <div>Function</div>
                            <table >
                                <tr>
                                    <td >Connect to the database to query autonomous system information</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Path</div>
                            <table >
                                <tr>
                                    <td >/.well-known/rdap/autnum/{autnum}</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Parameter</div>
                            <table>
                                <thead>
                                    <tr>
                                        <th colspan="2" width="20%" >Parameter</th>
                                        <th colspan="2" width="20%" >Type</th>
                                        <th colspan="2">Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="2">autnum</td>
                                        <td colspan="2">String</td>
                                        <td >Autonomous system number</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Request</div>
                            <div>
                                <p>Example:&nbsp;&nbsp;&nbsp;&nbsp;Query information with autonomous system number <span style="color:blue">&nbsp;1221</span></p>
                            </div>
                            <table >
                                <tr>
                                    <td >                           
                                        GET /.well-known/rdap/autnum/1221 HTTP/1.1<br />
                                        Accept: text/html,application/rdap+json,application/json,application/rdap+json;application/json<br />                                                                              
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Response</div>
                            <table >
                                <tr>
                                    <td>
                                        HTTP/1.1 200 OK<br />
                                        Server: Apache-Coyote/1.1<br />                                        
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2"  onclick="changeHide('as-json')">JSON Format</th>
                                </tr>
                                <tr id="as-json"  style="display:none">
                                    <td>
                                        <code>{<br /></code>
                                        <blockquote><code>"rdapConformance":["rdap_level_0"],</code></blockquote>
                                        <blockquote><code>"handle":"AS1221",</code></blockquote>
                                        <blockquote><code>"startAutnum":1221,</code></blockquote>
                                        <blockquote><code>"endAutnum":1221,</code></blockquote>
                                        <blockquote><code>"name":"APNIC-1221",</code></blockquote>
                                        <blockquote><code>"lang":" ",</code></blockquote>
                                        <blockquote><code>"type":" ",</code></blockquote>
                                        <blockquote><code>"status":["allocated"],</code></blockquote>  
                                        <blockquote><code>"country":"AU",</code></blockquote>
                                        <blockquote><code>"xnicauttest":""</code></blockquote>                              
                                        <code>}<br /></code>                                                                           
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />         
                    </div>  
                    <hr />
                    <h3 id="queryNS" style="font-size:20px">query name server</h3>
                    <br />
                    <div id="query_NS">
                        <div>
                            <div>Function</div>
                            <table >
                                <tr>
                                    <td >Connect to the database to query name server information</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Path</div>
                            <table >
                                <tr>
                                    <td >/.well-known/rdap/nameserver/{nsName}</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Parameter</div>
                            <table>
                                <thead>
                                    <tr>
                                        <th colspan="2" width="20%" >Parameter</th>
                                        <th colspan="2" width="20%" >Type</th>
                                        <th colspan="2">Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="2">nsName</td>
                                        <td colspan="2">String</td>
                                        <td >Name server</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Request</div>
                            <div>
                                <p>Example:&nbsp;&nbsp;&nbsp;&nbsp;Query information with name server <span style="color:blue">&nbsp;ns1.google.com</span></p>
                            </div>
                            <table >
                                <tr>
                                    <td >                           
                                        GET /.well-known/rdap/nameserver/ns1.google.com HTTP/1.1<br />
                                        Accept: text/html,application/rdap+json,application/json,application/rdap+json;application/json<br />                                                                              
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Response</div>
                            <table >
                                <tr>
                                    <td>
                                        HTTP/1.1 200 OK<br />
                                        Server: Apache-Coyote/1.1<br />                                        
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2"  onclick="changeHide('ns-json')">JSON Format</th>
                                </tr>
                                <tr id="ns-json"  style="display:none">
                                    <td>
                                        <code>{<br /></code>
                                        <blockquote><code>"rdapConformance":["rdap_level_0"],</code></blockquote>
                                        <blockquote><code>"handle":"dnr-27",</code></blockquote>
                                        <blockquote><code>"ldhName":"ns1.google.com",</code></blockquote>
                                        <blockquote><code>"unicodeName":" ",</code></blockquote>
                                        <blockquote><code>"lang":" ",</code></blockquote>
                                        <blockquote><code>"status":[""],</code></blockquote>
                                        <blockquote><code>"entities":{</code></blockquote>
                                        <blockquote><blockquote><code>"handle":"mmr-32107",</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"lang":" ",</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"status":[""],</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"roles":["registrant"],</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"port43":"whois.cnnic.cn",</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"vcardArray":[</code></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>"vcard",</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>[</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["version",{},"text","4.0"],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["fn",{},"text","Google Ireland Holdings"],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["fn",{},"text","dns-admin@google.com"],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["bday",{},"date-and-or-time",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["anniversary",{},"date-and-or-time",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["gender",{},"text",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["kind",{},"text",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["lang",{"pref":""},"language-tag",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["lang",{"pref":""},"language-tag",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["org",{"type":"work"},"text",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["title",{},"text",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["role",{},"text",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["email",{},"text","dns-admin@google.com"],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["geo",{"type":"work"},"uri",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["key",{"type":"work"},"uri",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["tz",{},"utc-offset",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["url",{"type":"home"},"uri",""]</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>]</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><code>]</code></blockquote></blockquote>
                                        <blockquote><code>},</code></blockquote>
                                        <blockquote><code>"ipAddresses":{</code></blockquote>    
                                        <blockquote><blockquote><code>"v4":[""],</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"v6":[" "]</code></blockquote></blockquote>
                                        <blockquote><code>}</code></blockquote>                              
                                        <code>}<br /></code>                                                                         
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />         
                    </div>
                    <hr />
                    <h3 id="fuzzyQueryDomain" style="font-size:20px">query domain with wildcard</h3>
                    <br />
                    <div id="fuzzy_query_Domain">
                        <div>
                            <div>Function</div>
                            <table >
                                <tr>
                                    <td >Connect to the database to query domain information with wildcard</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Path</div>
                            <table >
                                <tr>
                                    <td >/.well-known/rdap/domains?name={wildcardDomainName}</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Parameter</div>
                            <table>
                                <thead>
                                    <tr>
                                        <th colspan="2" width="20%" >Parameter</th>
                                        <th colspan="2" width="20%" >Type</th>
                                        <th colspan="2">Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="2">wildcardDomainName</td>
                                        <td colspan="2">String</td>
                                        <td >domain name with wildcard (*)</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Request</div>
                            <div>
                                <p>Example:&nbsp;&nbsp;&nbsp;&nbsp;Query information with domain name <span style="color:blue">&nbsp;清华*.中国</span></p>
                            </div>
                            <table >
                                <tr>
                                    <td >                           
                                        GET /.well-known/rdap/domains?name=%E6%B8%85%E5%8D%8E*.%E4%B8%AD%E5%9B%BD HTTP/1.1<br />
                                        Accept: text/html,application/rdap+json,application/json,application/rdap+json;application/json<br />                                                                              
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Response</div>
                            <table >
                                <tr>
                                    <td>
                                        HTTP/1.1 200 OK<br />
                                        Server: Apache-Coyote/1.1<br />                                        
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2"  onclick="changeHide('fuzzy-domain-json')">JSON Format</th>
                                </tr>
                                <tr id="fuzzy-domain-json"  style="display:none">
                                    <td>
                                        <code>{<br /></code>
                                        <blockquote><code>"rdapConformance":["rdap_level_0"],</code></blockquote>
                                        <blockquote><code>"domainSearchResults":[{</code></blockquote>
                                        <blockquote><blockquote><code>"handle":"unicode1",</code> </blockquote></blockquote>                              
                                        <blockquote><blockquote><code>"ldhName":"xn--xkry9kk1bz66a.xn--fiqs8s",</code>  </blockquote></blockquote>      
                                        <blockquote><blockquote><code>"unicodeName":"清华大学.中国",</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"lang":" ",</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"variants":{</code></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>"relation":["registered"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>"variantNames":["variantname"],</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>"idntable":"idntable_zh"</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><code>},</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"status":["clientDeleteProhibited","clientTransferProhibited"],</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"port43":"whois.cnnic.cn "</code> </blockquote></blockquote>                                       
                                        <blockquote><code>}]</code></blockquote>
                                        <code>}<br /></code>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />         
                    </div>
                    <hr />
                    <h3 id="fuzzyQueryEntity" style="font-size:20px">query entity with wildcard</h3>
                    <br />
                    <div id="fuzzy_query_Entity">
                        <div>
                            <div>Function</div>
                            <table >
                                <tr>
                                    <td >Connect to the database to query entity information with wildcard</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Path</div>
                            <table >
                                <tr>
                                    <td >/.well-known/rdap/entities?fn={wildcardEntityName}</td>
                                </tr>
                                <tr>
                                    <td >or&nbsp;&nbsp;/.well-known/rdap/entities?handle={wildcardEntityName}</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Parameter</div>
                            <table>
                                <thead>
                                    <tr>
                                        <th colspan="2" width="20%" >Parameter</th>
                                        <th colspan="2" width="20%" >Type</th>
                                        <th colspan="2">Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="2">wildcardEntityName</td>
                                        <td colspan="2">String</td>
                                        <td >entity name with wildcard (*)</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Request</div>
                            <div>
                                <p>Example:&nbsp;&nbsp;&nbsp;&nbsp;Query information with entity name <span style="color:blue">&nbsp;fn:*搜狐*</span></p>
                            </div>
                            <table >
                                <tr>
                                    <td >                           
                                        GET /.well-known/rdap/entities?fn:*搜狐* HTTP/1.1<br />
                                        Accept: text/html,application/rdap+json,application/json,application/rdap+json;application/json<br />                                                                              
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Response</div>
                            <table >
                                <tr>
                                    <td>
                                        HTTP/1.1 200 OK<br />
                                        Server: Apache-Coyote/1.1<br />                                        
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2"  onclick="changeHide('fuzzy-entity-json')">JSON Format</th>
                                </tr>
                                <tr id="fuzzy-entity-json"  style="display:none">
                                    <td>
                                        <code>{<br /></code>
                                        <blockquote><code>"rdapConformance":["rdap_level_0"],</code></blockquote>
                                        <blockquote><code>"entitySearchResults":[{</code></blockquote>
                                        <blockquote><blockquote><code>"handle":"s1100582478155",</code> </blockquote></blockquote>                              
                                        <blockquote><blockquote><code>"lang":" ",</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"status":[" "],</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"roles":["registrant"],</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"port43":"whois.cnnic.cn",</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"vcardArray":[</code></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>"vcard",</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>[</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["version",{},"text","4.0"],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["fn",{},"text","北京搜狐新时代信息技术有限公司"],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["fn",{},"text","jjzhang@sohu-inc.com"],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["bday",{},"date-and-or-time",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["anniversary",{},"date-and-or-time",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["gender",{},"text",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["kind",{},"text",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["lang",{"pref":""},"language-tag",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["lang",{"pref":""},"language-tag",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["org",{"type":"work"},"text",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["title",{},"text",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["role",{},"text",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["email",{},"text","jjzhang@sohu-inc.com"],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["geo",{"type":"work"},"uri",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["tz",{},"utc-offset",""],</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><blockquote><code>["url",{"type":"home"},"uri",""]</code></blockquote></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><blockquote><code>]</code></blockquote></blockquote></blockquote>
                                        <blockquote><blockquote><code>]</code></blockquote></blockquote>                                                                               
                                        <blockquote><code>}]</code></blockquote>
                                        <code>}<br /></code>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />         
                    </div>
                    <hr />
                    <h3 id="fuzzyQueryNS" style="font-size:20px">query name server  with wildcard</h3>
                    <br />
                    <div id="fuzzy_query_NS">
                        <div>
                            <div>Function</div>
                            <table >
                                <tr>
                                    <td >Connect to the database to query name server information with wildcardh</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Path</div>
                            <table >
                                <tr>
                                    <td >or/.well-known/rdap/nameservers?name={wildcardNsName}</td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Parameter</div>
                            <table>
                                <thead>
                                    <tr>
                                        <th colspan="2" width="20%" >Parameter</th>
                                        <th colspan="2" width="20%" >Type</th>
                                        <th colspan="2">Description</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td colspan="2">wildcardNsName</td>
                                        <td colspan="2">String</td>
                                        <td >name server with wildcard (*)</td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Request</div>
                            <div>
                                <p>Example:&nbsp;&nbsp;&nbsp;&nbsp;Query information with domain name <span style="color:blue">&nbsp;pro*.com</span></p>
                            </div>
                            <table >
                                <tr>
                                    <td >                           
                                        GET /.well-known/rdap/nameservers?name=pro*.com HTTP/1.1<br />
                                        Accept: text/html,application/rdap+json,application/json,application/rdap+json;application/json<br />                                                                              
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />
                        <div>
                            <div>Response</div>
                            <table >
                                <tr>
                                    <td>
                                        HTTP/1.1 200 OK<br />
                                        Server: Apache-Coyote/1.1<br />                                        
                                    </td>
                                </tr>
                                <tr>
                                    <th colspan="2"  onclick="changeHide('fuzzy-ns-json')">JSON Format</th>
                                </tr>
                                <tr id="fuzzy-ns-json"  style="display:none">
                                    <td>
                                        <code>{<br /></code>
                                        <blockquote><code>"rdapConformance":["rdap_level_0"],</code></blockquote>
                                        <blockquote><code>"nameserverSearchResults":[{</code></blockquote>
                                        <blockquote><blockquote><code>"handle":"dnr-3",</code> </blockquote></blockquote>                              
                                        <blockquote><blockquote><code>"ldhName":"proxy.baidu.com",</code>  </blockquote></blockquote>      
                                        <blockquote><blockquote><code>"unicodeName":" ",</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"lang":" ",</code></blockquote></blockquote>                                        
                                        <blockquote><blockquote><code>"status":[""],</code></blockquote></blockquote>
                                        <blockquote><blockquote><code>"ipv4Addresses":[""],</code> </blockquote></blockquote>  
                                        <blockquote><blockquote><code>"ipv6Addresses":[" "]</code> </blockquote></blockquote>                                      
                                        <blockquote><code>}]</code></blockquote>
                                        <code>}<br /></code>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <br />         
                    </div>   
                </div>
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
