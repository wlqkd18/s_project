<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>member/info.jsp<br>
<c:import url="../default/header.jsp"/>
<div class="wrap">
	<div style="width:400px; margin: 0 auto;">
		<h3 align="center">개 인 정 보</h3>
	<table class="table">
		<tr>
			<th>아이디</th> <td>${info.id }</td>
		</tr>
		<tr>
			<th>비밀번호</th> <td>********</td>
		</tr>
		<tr>
			<th>주 소</th> <td>${info.addr }</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<button type="button" class="btn btn-warning" onclick="location.href='modify_form?id=${info.id}'">수정</button>
				&nbsp; &nbsp;
				<button type="button" class="btn btn-danger" onclick="location.href='delete?id=${info.id}'">삭제</button>
			</td>
		</tr>
	</table>
	</div>
</div>


</body>
</html>

















