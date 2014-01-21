<%@ page session="true"%>
<%@ page import="java.util.Map,java.util.Iterator, org.openid4java.message.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
		<title>loding...</title>
	</head>
		<body onload='document.forms[0].submit();'>
		<% AuthRequest authReq  = (AuthRequest)request.getAttribute("authReq");%>
		<form name="openid-form-redirection"
			action="<%= authReq.getOPEndpoint() %>" method="post"
			accept-charset="utf-8" id="openid_message">
			<%
                Map pm=authReq.getParameterMap();
                Iterator keyit=pm.keySet().iterator();

                Object key;
                Object value;

                while (keyit.hasNext())
                {
                    key = keyit.next();
                  
                    value = pm.get(key);
                    
                    
            %>
			<input type="hidden" name="<%= key%>" value="<%= value%>" />
			<input type="hidden" name="openid.sreg.required" value="email" />
			 <input type="hidden" name="openid.sreg.optional" value="nickname,fullname,postcode,country,timezone" />
			<%
                }
       	    %>
			<input type="submit" value="Continue" />
		</form>
		<div style='width: 400px; text-align: center; margin: 0 auto'>
			<div style='margin: 1em auto; font-size: 14px; color: #F60'>
				loding ...
			</div>
			<br clear='all' />
			<img  alt="loading..." src="<c:url value='/image/icon_indicator.gif'/>">
		</div>
		<script stype='text/javascript'>
			var elements = document.forms[0].elements;
			for ( var i = 0; i < elements.length; i++) {
				elements[i].style.display = "none";
			}
		</script>
	</body>
</html>

