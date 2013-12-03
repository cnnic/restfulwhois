<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%
    String appDesc = (String)request.getAttribute("CONS_DESC");
    String token = (String)request.getAttribute("TOKEN");
    String callback = (String)request.getAttribute("CALLBACK");
    String error_value = (String)request.getAttribute("error_value");
    if(callback == null)
        callback = "";
    
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OAuth Provider</title>
    </head>
    <body>
        <table width="100%" cellpadding="10" style="text-align: center;">
		  <tr >
		      <td bgcolor="#7799DD"><h1><font style="color: #0000FF" > <%=appDesc%> </font> is trying to access your information.</h1></td>
		  </tr>
		  
		  <%
		  	if(error_value != null && !"".equals(error_value)){
		  %>		
		  	<tr>
		  	<td style="color: red;">
				<%= error_value %>
		  	</td>
		  </tr>
		  <%
		  	}
		  %>
		  
		  
			<form name="authZForm" action="authorize.do" method="POST">
			<input type="hidden" name="oauth_token" value="<%= token %>"/>
			<input type="hidden" name="oauth_callback" value="<%= callback %>"/>
				
		  <tr>
		  	<td>
				UserName : <input type="text" name="userId" value="" size="20" /><br>
		  	</td>
		  </tr>
		  <tr>
		  	<td>
				PassWord : <input type="text" name="password" value="" size="20" /><br>
		  	</td>
		  </tr>
		  
		  <tr>
		  	<td>
				<input type="submit" name="Authorize" value="Authorize"/>
		  	</td>
		  </tr>
		  	
			</form>
		</table>
    
    </body>
</html>
