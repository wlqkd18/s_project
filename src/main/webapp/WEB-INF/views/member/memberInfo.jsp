<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>member/memberInfo.jsp<br>
	<c:import url="../default/header.jsp"/>
	<div class="wrap">
		<table class="table table-striped">
		<tr>
			<th>아이디</th> <th>비밀번호</th> <th>주 소</th>
		</tr>
		<c:forEach var="mem" items="${memberList }">
			<tr>
				<td>
					<a href="info?id=${mem.id }">
						${mem.id}
					</a>
				</td>
				
				<td>${mem.pw}</td>
				<td>${mem.addr}</td>
			</tr>
		</c:forEach>
			
		</table>
	</div>
</body>
</html>















