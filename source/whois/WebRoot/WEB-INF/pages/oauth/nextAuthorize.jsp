<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/comm/taglib.jsp"%>
<%
    String appDesc = (String)request.getAttribute("CONS_DESC");
    String token = (String)request.getAttribute("TOKEN");
    String callback = (String)request.getAttribute("CALLBACK");
    String success = (String)request.getAttribute("success");
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
				
			</div>


<div class="oauth-section container">

  <div class="flash">
    <h3>Hi ${user.user_name }, a third-party application would like to access your account.</h3>
    <p>Please read this carefully! Only grant access to applications you know and trust.</p>
  </div>
<!--  
  <div class="application">
    <a class="box" href="https://passport.csdn.net">
      <img height="80" src="https://2.gravatar.com/avatar/0cc1f65030fa59b3af5c674e96800917?d=https%3A%2F%2Fidenticons.github.com%2F5bbcd59dd32c031db0edc5aa88d0a048.png&amp;r=x&amp;s=160" width="80">
    </a>
    <div class="description">
      <h1 class="access-heading"><a href="https://passport.csdn.net">CSDN.NET</a></h1>
        <p class="access-owner">– developed by <a href="/sqzhuyi">sqzhuyi</a></p>

    </div>
  </div>
-->
  <div class="access-details">

<form action="${ctx }/authorize" method="post">
<div style="margin:0;padding:0;display:inline">

	<%
	  	if(success != null && !"".equals(success)){
	%>
         <input type="hidden" name="success" value="<%= success %>"/>
	<%
	  	}
	%>
		  
    <input type="hidden" name="oauth_token" value="<%= token %>"/>
    <input type="hidden" name="oauth_callback" value="<%= callback %>"/>

</div>    
<div class="user-box">
      <span class="mega-octicon octicon-arrow-down"></span>
      <div class="details-user" style="margin-top: 6px;text-align: center;">
        <img class="avatar" height="60" src="${ctx }/image/identicon.png" width="60">
        <p class="login">${user.user_name }</p>
      </div>
    </div>
    
    <div class="sidebar">
      <div class="infotip">
        <div class="inner">
          <p>
            <strong>What is all this?</strong>
          </p>
          <p>
            oauthConsumer wants to use <a href="http://developer.github.com/v3/oauth/">OAuth</a>  to identify you by your restfulwhois login
            <strong>${user.user_name }</strong>.
            OAuth allows third party applications to leverage your identity on GitHub, saving you from creating a new username and password on <a href="http://rdap.restfulwhois.org/">http://rdap.restfulwhois.org/</a>.
          </p>
        </div>
      </div>
    </div>

    <div class="permissions">

      <ul class="permission-list">
    <li>
  <span class="label read on"><span class="circle"></span>read</span>
  <span class="label write off"><span class="circle"></span>write</span>
  <span class="permission">Your user profile</span>
</li>

    <li>
  <span class="label read on"><span class="circle"></span>read</span>
  <span class="label write off"><span class="circle"></span>write</span>
  <span class="permission">RestfulWhois IP</span>
</li>

    <li>
  <span class="label read on"><span class="circle"></span>read</span>
  <span class="label write off"><span class="circle"></span>write</span>
  <span class="permission">RestfulWhois Domain</span>
</li>

</ul>

<ul class="permission-list">
    <li>
  <span class="label read on"><span class="circle"></span>read</span>
  <span class="label write off"><span class="circle"></span>write</span>
  <span class="permission">RestfulWhois Entity</span>
</li>
<!--  
    <li>
  <span class="label read off"><span class="circle"></span>read</span>
  <span class="label write off"><span class="circle"></span>write</span>
  <span class="permission">Your private email addresses</span>
</li>
-->
</ul>
      <div class="question">
        <button type="submit" tabindex="1" class="button primary">Allow access</button>
      </div>

    </div>

</form>
  </div>

</div>
    
    </body>
</html>
