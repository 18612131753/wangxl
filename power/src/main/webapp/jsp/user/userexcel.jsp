<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=GBK"%>
<%
response.setContentType("application/msexcel;charset=GBK");
response.setHeader("Content-disposition", "attachment; filename=excelname.xls");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta content="text/html;charset=GBK" http-equiv="Content-Type"/>
	</head>
	<body>
		<table border="1">
			<tr>
				<td>登录名</td>
				<td>密码</td>
				<td>创建时间</td>
			</tr>
			<c:forEach var="user" items="${list}">
				<tr>
					<td>${user.loginname}</td>
					<td>${user.password}</td>
					<td>${user.cdate}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>