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
        <style type="text/css">
        	
        	.container {
				width: 980px;
				margin-left: auto;
				margin-right: auto;
				-moz-box-sizing: border-box;
				box-sizing: border-box;
				margin-top: 20px;
			}
			
        	.oauth-section .flash {
				margin-bottom: 20px;
				padding: 0 15px;
			}
			
			.flash {
				padding: 15px;
				border-radius: 3px;
				box-shadow: 0 1px 3px rgba(0,0,0,0.1);
			}
        	
        	.flash, .flash-global {
				position: relative;
				border: 1px solid #97c1da;
				color: #264c72;
				background-color: #d0e3ef;
				background-image: -moz-linear-gradient(#d8ebf8, #d0e3ef);
				background-image: -webkit-linear-gradient(#d8ebf8, #d0e3ef);
				background-image: linear-gradient(#d8ebf8, #d0e3ef);
				background-repeat: repeat-x;
				text-shadow: 0 1px 0 rgba(255,255,255,0.5);
			}
			
			.oauth-section .access-details .user-box {
				float: left;
				width: 92px;
				margin-right: 32px;
			}
			
			.oauth-section .access-details .octicon-arrow-down {
				color: #ddd;
				margin: -7px 0 6px 36px;
			}
			
			.mega-octicon {
				font: normal normal 32px octicons;
				line-height: 1;
				display: inline-block;
				text-decoration: none;
				-webkit-font-smoothing: antialiased;
			}
			
			.oauth-section .access-details .details-user p.login {
				margin-top: 5px;
				padding: 0;
				border: none;
				font-size: 14px;
				font-weight: bold;
			}
			
			.oauth-section .access-details .details-user p {
				color: #999;
				margin: 8px 0 0 0;
				border-top: 1px solid #DDD;
				padding-top: 12px;
				font-size: 11px;
			}
			
			.oauth-section .access-details .permissions {
				float: left;
				width: 500px;
				color: #666;
			}
			
			.oauth-section ul.permission-list {
				font-size: 14px;
				/*margin: 20px 0;*/
			}
			
			ul, ol {
				padding: 0;
				margin-top: 0;
				margin-bottom: 0;
			}
			
			.oauth-section ul.permission-list>li {
				list-style-type: none;
				padding: 7px 0 7px 0;
			}
			
			.oauth-section span.read {
				color: #7cc45c;
				background: rgba(124,196,92,0.2);
				border-color: #7cc45c;
			}
			
			.oauth-section span.label {
				display: inline-block;
				font-weight: bold;
				text-align: center;
				font-size: 14px;
				padding: 3px 10px;
				margin-right: 4px;
				border-radius: 3px;
				border: 2px solid;
				text-shadow: 0 1px 0 #fff;
			}
			
			.oauth-section span.read .circle {
				background: #7cc45c;
				border-color: #7cc45c;
				}
				
			.oauth-section .circle {
				display: inline-block;
				width: 9px;
				height: 9px;
				border-radius: 9px;
				margin-right: 4.5px;
				border: 2px solid;
				-moz-box-sizing: border-box;
				box-sizing: border-box;
			}
			
			.oauth-section span.write {
				color: #ee9c49;
				background: rgba(238,156,73,0.2);
				border-color: #ee9c49;
			}
			
			.oauth-section span.off {
				color: #dddddd;
				background: #eeeeee;
				border-color: #dddddd;
			}
			
			.oauth-section span.label {
				display: inline-block;
				font-weight: bold;
				text-align: center;
				font-size: 14px;
				padding: 3px 10px;
				margin-right: 4px;
				border-radius: 3px;
				border: 2px solid;
				text-shadow: 0 1px 0 #fff;
			}
			
			.oauth-section span.write .circle {
				background: #ee9c49;
				border-color: #ee9c49;
			}
			
			.oauth-section span.off .circle {
				background: none;
				border-color: #dddddd;
			}
			
			.oauth-section .circle {
				display: inline-block;
				width: 9px;
				height: 9px;
				border-radius: 9px;
				margin-right: 4.5px;
				border: 2px solid;
				-moz-box-sizing: border-box;
				box-sizing: border-box;
			}
			
			.oauth-section ul.permission-list>li:first-child span.permission {
				font-weight: bolder;
			}
			
			.oauth-section span.permission {
				display: inline-block;
				margin-left: 4px;
			}

			.oauth-section .access-details .question {
				font-size: 16px;
				border-top: 1px solid #eee;
				padding: 30px 0 40px 0;
			}
			
			.oauth-section .access-details .question button {
				padding: 7px 32px;
				margin-right: 3px;
			}
			
			.button.primary, .minibutton.primary {
				color: #fff;
				text-shadow: 0 -1px 0 rgba(0,0,0,0.25);
				background-color: #60b044;
				background-image: -moz-linear-gradient(#8add6d, #60b044);
				background-image: -webkit-linear-gradient(#8add6d, #60b044);
				background-image: linear-gradient(#8add6d, #60b044);
				background-repeat: repeat-x;
				border-color: #5ca941;
			}
			
			.oauth-section .access-details .sidebar {
				float: right;
				width: 277px;
				background: #EEE;
				margin-top: 20px;
				border-radius: 2px;
			}
			
			.oauth-section .access-details .infotip {
				font-size: 13px;
				color: #777;
				background: #fff;
				border: 1px solid #BBB;
				border-radius: 2px;
				margin: 3px;
				padding: 15px;
			}
			
			.oauth-section .access-details .infotip {
				font-size: 13px;
				color: #777;
			}
			
			.infotip p {
				margin: 0;
			}
			
			.infotip p+p {
				margin-top: 15px;
			}
			
			a {
				color: #4183c4;
				text-decoration: none;
			}
			
			strong {
				font-weight: bold;
			}
			
			.oauth-section .access-details .infotip {
				font-size: 13px;
				color: #777;
			}

        </style>
        
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
<!--  -->
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
      
        <button type="submit" name="authorize" value="1" tabindex="1" class="button primary">Allow access</button>
        <!--  
        <span>
          or <a href="/login/oauth/authorize?client_id=14fd1001cd3a7f7e0848" data-method="post" style="color: #4183c4;text-decoration: none;">deny this request</a>
        </span>
        -->
      </div>

    </div> <!-- permissions -->


</form>
  </div> <!-- access-details -->

</div>
    
    </body>
</html>
