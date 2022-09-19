<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			SELECT 
		</tr>
		<c:forEach var="list" items="${result}" varStatus="index">
			<tr>
				<c:if test="${!index.last}">
					<td>${list.snake}, /* ${list.coment} */ </td>
				</c:if>
				<c:if test="${index.last}">
					<td>${list.snake} /* ${list.coment} */ </td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	<br><br><br><br>
	<table>
		<c:forEach var="list" items="${result}" varStatus="index">
			<tr>
				<td>private String ${list.snake}; /* ${list.coment} */ </td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>