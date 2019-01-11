<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="test.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>
<body>
	<form>
		<table align="center" style="width: 60%;" border="1">
			<tr>
				<th>学生编号</th>
				<th>学生姓名</th>
				<th>学生年龄</th>
				<th>学生性别</th>
				<th>学生生日</th>
			</tr>
			<tr>
					<td>1</td>
					<td>zhangsan</td>
					<td>18</td>
					<td>男</td>
					<td>1995-01-01</td>
				</tr>
			<c:forEach items="${pageView.records}" var="list">
				<tr>
					<td>${list.id }</td>
					<td>${list.name }</td>
					<td>${list.age }</td>
					<td>${list.sex }</td>
					<td>${list.birth}</td>
				</tr>
			</c:forEach>

		</table>
		
	</form>

</body>
</html>