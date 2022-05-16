<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="../default/header.jsp"/>
<div class="wrap">
<table class="table table-striped">
	<tr>
		<th>번호</th> <th>id</th> <th>제목</th> <th>날짜</th> 
		<th>조회수</th> <th>이미지 이름</th> 
	</tr>
	<c:if test="${boardList.size() == 0 }">
		<tr><th colspan="6">등록된 글이 없습니다</th></tr>
	</c:if>
	<c:forEach var="dto" items="${boardList }">
	<tr>
		<td>${dto.writeNo }</td> <td>${dto.id }</td>
		<td><a href="/root/board/contentView2?writeNo=${dto.writeNo}">${dto.title }</a></td> <td>${dto.saveDate }</td>
		<td>${dto.hit }</td> <td>${dto.imageFileName }</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="6" align="right">
			<a href="/root/board/writeForm">글작성</a>
		</td>
	</tr>
</table>
<hr>
	<div style="text-align: right;">
		<div align="left">
			<c:forEach var="num" begin="1" end="${repeat}">
				<a href="boardAllList?num=${num}">
					[${num}]
				</a>
			</c:forEach>
		</div>
		
	</div>
</div>
</body>
</html>
















