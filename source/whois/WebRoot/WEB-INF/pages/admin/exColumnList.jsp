<%@ page import="com.cnnic.whois.service.admin.ExColumnService"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>
<input type="button" value="add column" onclick="changeAddColumn('${tableType }')"><br/><br/>

<table>
	<tr>
		<th>
			Column Name
		</th>
		<th>
			Column Length
		</th>
		<th>
			Operation
		</th>
	</tr>
	<c:forEach items="${columnInfoList}" var="columnInfoList">
		<tr>
			<td>
				${columnInfoList.key}
			</td>
			<td>
				${columnInfoList.value}
			</td>
			<td>
				<select id='operat'
					onchange="javascript:operationColumn(this,'${tableType }','${columnInfoList.key}','${columnInfoList.value}')"
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

