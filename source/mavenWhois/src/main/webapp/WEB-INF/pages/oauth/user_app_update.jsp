<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/comm/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache,s-maxage=0"/>
<meta http-equiv="expires" content="0"/>
<script type="text/javascript" src="${remote_ctx }/resources/js/jquery.js"></script>
<script type="text/javascript"
	src="${remote_ctx }/resources/artDialog/artDialog.source.js?skin=blue"></script>
<script type="text/javascript"
	src="${remote_ctx }/resources/artDialog/plugins/iframeTools.source.js"></script>
<title>whois</title>

</head>
<body style="min-height: 100%;min-width: 300px;">
	<div>
		<table align="center" border="1" cellspacing="0" cellpadding="0" id="lt" style="margin-top: 50px">
			<form action="${ctx }/app/save" method="post">
			<input type="hidden" id="id" name="id" value="${userApp.id }"/>
			<input type="hidden" id="user_id" name="user_id" value="${user_id }"/>
				<tr style="text-align: center;">
					<td style="width: 300px;height: 60px;">
						<input type="text" id="app_description" name="app_description" value="${userApp.app_description }"/>
					</td>
					
					<td style="width: 100px;height: 60px;">
						<input type="submit" value="submit"/>
					</td>
					
				</tr>
			</form>
			
		</table>
	</div>
</body>
</html>
