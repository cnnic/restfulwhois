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
		<table align="center" border="1" cellspacing="0" cellpadding="0" id="lt" style="text-align: center;">
			
			<c:forEach var="lists" items="${list }">
				
				<tr >
					<td style="width: 100px;height: 40px;">
						${lists.id }
					</td>
					<td style="width: 100px;height: 40px;">
						${lists.user_name }
					</td>
					<td>
						<a href="${ctx }/user/add-update?id=${lists.id }" style="text-decoration: none;">&nbsp;&nbsp;修改</a>&nbsp;&nbsp;&nbsp;
						<a href="${ctx }/user/delete?id=${lists.id }" style="text-decoration: none;">删除&nbsp;&nbsp;</a>
						
						<a href="${ctx }/app?user_id=${lists.id }" style="text-decoration: none;">设置APP&nbsp;&nbsp;</a>
					</td>
					
				</tr>
		
			</c:forEach>
			
			<tr>
				<td style="width: 100px;height: 40px;">
					<a href="${ctx }/user/add-update">添加</a>
				</td>
			</tr>

		</table>
	</div>
</body>
</html>
