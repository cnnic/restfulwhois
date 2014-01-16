<%@ page import="com.cnnic.whois.service.admin.AccessControlService"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<form action="" method="post">
	<input type="hidden" name="tableType" id="tableType" value='${tableType }' />
		<div class="sucessFlag">
			<input type="button" value="update permission" onclick="updatePermission();"></input>
			<font color="red" style="font-size: 12px; float: right">${requestScope.successFlag}</font>
		</div>
	<br/>
	<table>
		<tr>
			<th>
				Role
			</th>
			<th>
				Columns
			</th>
		</tr>
		<tr>
			<td>
				anonymous
			</td>
			<td>
				<c:forEach items="${columnPermissionList}"
					var="columnPermissionList" varStatus="brCount">

					<c:forEach items="${columnPermissionList.value}"
						var="permissionList">

						<c:if test="${permissionList.key=='anonymous'}">
							<input type="checkbox" name="anonymousUser"
								<c:if test="${permissionList.value=='1'}"> checked="checked"</c:if>
								value="${columnPermissionList.key}" />
							<c:choose>
								<c:when test="${fn:startsWith(columnPermissionList.key, '$x$')}">
												${fn:substringAfter(columnPermissionList.key, '$x$')}
												</c:when>
								<c:when
									test="${fn:startsWith(columnPermissionList.key, '$join$')}">
												${fn:substringAfter(columnPermissionList.key, '$join$')}
												</c:when>
								<c:when
									test="${fn:startsWith(columnPermissionList.key, '$array$')}">
												${fn:substringAfter(columnPermissionList.key, '$array$')}
												</c:when>
								<c:otherwise>${columnPermissionList.key}</c:otherwise>
							</c:choose>
												&nbsp;&nbsp;&nbsp;&nbsp; <c:if
								test="${brCount.count %3 == 0}">
								<br />
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>
				authenticated
			</td>
			<td>
				<c:forEach items="${columnPermissionList}"
					var="columnPermissionList" varStatus="brCount">
					<c:forEach items="${columnPermissionList.value}"
						var="permissionList">
						<c:if test="${permissionList.key=='authenticated'}">
							<input type="checkbox" name="authenticatedUser"
								<c:if test="${permissionList.value=='1'}"> checked="checked"</c:if>
								value="${columnPermissionList.key}" />
							<c:choose>
								<c:when test="${fn:startsWith(columnPermissionList.key, '$x$')}">
												${fn:substringAfter(columnPermissionList.key, '$x$')}
												</c:when>
								<c:when
									test="${fn:startsWith(columnPermissionList.key, '$join$')}">
												${fn:substringAfter(columnPermissionList.key, '$join$')}
												</c:when>
								<c:when
									test="${fn:startsWith(columnPermissionList.key, '$array$')}">
												${fn:substringAfter(columnPermissionList.key, '$array$')}
												</c:when>
								<c:otherwise>${columnPermissionList.key}</c:otherwise>
							</c:choose>
										&nbsp;&nbsp;&nbsp;&nbsp; <c:if test="${brCount.count %3 == 0}">
								<br />
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach>
			</td>
		</tr>
		<tr>
			<td>
				root
			</td>
			<td>
				<c:forEach items="${columnPermissionList}"
					var="columnPermissionList" varStatus="brCount">
					<c:forEach items="${columnPermissionList.value}"
						var="permissionList">
						<c:if test="${permissionList.key=='root'}">
							<input type="checkbox" name="rootUser"
								<c:if test="${permissionList.value=='1'}"> checked="checked"</c:if>
								value="${columnPermissionList.key}" />
							<c:choose>
								<c:when test="${fn:startsWith(columnPermissionList.key, '$x$')}">
												${fn:substringAfter(columnPermissionList.key, '$x$')}
												</c:when>
								<c:when
									test="${fn:startsWith(columnPermissionList.key, '$join$')}">
												${fn:substringAfter(columnPermissionList.key, '$join$')}
												</c:when>
								<c:when
									test="${fn:startsWith(columnPermissionList.key, '$array$')}">
												${fn:substringAfter(columnPermissionList.key, '$array$')}
									</c:when>
								<c:otherwise>${columnPermissionList.key}</c:otherwise>
							</c:choose>
										&nbsp;&nbsp;&nbsp;&nbsp; <c:if test="${brCount.count %3 == 0}">
								<br />
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach>
			</td>
		</tr>
	</table>
	
</form>