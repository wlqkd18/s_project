<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function readURL(input){
	var file = input.files[0]
	console.log(file)
	if( file != "" ){
		var reader = new FileReader()
		reader.readAsDataURL(file)
		reader.onload = function(e){
			console.log( e.target.result )
			$('#preview').attr('src', e.target.result )
		}
	}
}
</script>
</head>
<body>
<c:import url="../default/header.jsp"/>
<div class="wrap">
	<div style="width:300px; margin: 0 auto;">
		<form action="modify" enctype="multipart/form-data" method="post">
			<input type="hidden" name="writeNo" value="${info.writeNo}">
			<input type="hidden" name="originFileName" value="${info.imageFileName}">
			제목 <input type="text" name="title" value="${info.title}"><hr>
			내용 <textarea rows="5" cols="30" name="content">${info.content}</textarea><hr>
			<c:if test="${info.imageFileName == 'nan'}">
				<img src="#" id="preview" width="100px" height="100px">
			</c:if>
			<c:if test="${info.imageFileName != 'nan'}">
			<img src="/root/board/download?file=${info.imageFileName}" id="preview" width="100px" height="100px">
			</c:if>
			<input type="file" name="imageFileName" onchange="readURL(this)">
			<hr>
			<input type="submit" value="수정">
			<input type="button" onclick="history.back()" value="이전">
		</form>
	</div>
</div>
</body>
</html>