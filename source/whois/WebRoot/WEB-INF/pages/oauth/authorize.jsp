<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/comm/taglib.jsp"%>
<%
    String appDesc = (String)request.getAttribute("CONS_DESC");
    String token = (String)request.getAttribute("TOKEN");
    String callback = (String)request.getAttribute("CALLBACK");
    String error_value = (String)request.getAttribute("error_value");
    if(callback == null)
        callback = "";
    
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
        <title>OAuth Provider</title>
        <link href="<c:url value='/css/oauth-style.css'/>" type="text/css" rel="stylesheet" />
    </head>
    <body>
    <div id="header">
				<h1>
					<a href="${ctx }/index.jsp"><acronym>Restful Whois</acronym> </a>
				</h1>
				
				<ul id="menu">
					<li class="one">
						<dl>
							<dt>
								<a class="button signin" href="${ctx }/index.jsp">Sign in</a>
							</dt>
						</dl>
					</li>
				</ul>
				
			</div>

<div class="auth-form" id="login">

    <form action="${ctx }/authorize" method="post">
    <div style="margin:0;padding:0;display:inline">
    <input type="hidden" name="oauth_token" value="<%= token %>"/>
			<input type="hidden" name="oauth_callback" value="<%= callback %>"/>
    </div>      <div class="auth-form-header">
        <h1>
        	Sign in
        	<%--   
        	<%=appDesc%> </font> is trying to access your information.
        	--%>
        </h1>
      </div>
      
      <div class="auth-form-body">
      
      	<%
		  	if(error_value != null && !"".equals(error_value)){
		  %>
		  <font style="color: red;margin-left: 70px;" >
          <%= error_value %>
          <br /><br />
        </font>
        
		  <%
		  	}
		  %>
      
        <label for="login_field">
          Username
        </label>
        <input autocapitalize="off" autofocus="autofocus" class="input-block" id="login_field" name="userId" tabindex="1" type="text">

        <label for="password">
          Password
        </label>
        <input class="input-block" id="password" name="password" tabindex="2" type="password">

        <input class="button" name="commit" tabindex="3" type="submit" value="Sign in">
      </div>
</form>
</div>

          </div>
    
    </body>
</html>
