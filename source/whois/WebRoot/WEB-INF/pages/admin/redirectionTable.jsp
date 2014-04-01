<%@ page import="com.cnnic.whois.service.admin.RedirectionService"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<form action="" method="post">
<input type="button" value="add redirection"
		onclick="changeAddRedirect('${tableName}');"></input>
		<br/><br/>
	<table>
		<tr>
			<c:if test="${tableName == 'domain'}">
				<th>
					Name
				</th>
			</c:if>
			<c:if test="${tableName == 'ip'}">
				<th>
					Start Address
				</th>
				<th>
					End Address
				</th>
			</c:if>
			<c:if test="${tableName == 'autnum'}">
				<th>
					Start Number
				</th>
				<th>
					End Number
				</th>
			</c:if>
			<th>
				URL
			</th>
			<th>
				Operation
			</th>
		</tr>

		<c:forEach items="${redirectList}" var="redirectList">
			<tr>
				<c:forEach items="${redirectList.value}" var="urlList">
					<td>
						${urlList}
					</td>

				</c:forEach>
				<td>
					<select id='operation'
						<c:if test="${tableName == 'autnum'}">
						onchange="javascript:operationRedirect(this,${redirectList.key},'${redirectList.value[0]}','${redirectList.value[2]}','${redirectList.value[1]}','${tableName}')"
					</c:if>
						<c:if test="${tableName == 'ip'}">
						onchange="javascript:operationRedirect(this,${redirectList.key},'${redirectList.value[0]}','${redirectList.value[2]}','${redirectList.value[1]}','${tableName}')"
					</c:if>
						onchange="javascript:operationRedirect(this,${redirectList.key},'${redirectList.value[0]}','${redirectList.value[1]}','','${tableName}')"
						style="width: 180px">
						<option value='0'>
							...Please select Operation...
						</option>
						<option value='1'>
							update
						</option>
						<option value='2'>
							delete
						</option>
					</select>

				</td>
			</tr>
		</c:forEach>

	</table>
	
</form>