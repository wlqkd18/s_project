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
<div class="wrap" style="width:400px; margin: 0 auto;">
<h1 align="center">글 쓰 기</h1>
<form action="/root/board/writeSave" method="post"
						enctype="multipart/form-data">
	<b>작성자</b><br>
	<input type="text" name="id" readonly value="${loginUser }">
	<hr>
	<b>제 목</b><br>
	<input type="text" name="title" size="47" >
	<hr>
	<b>내 용</b><br>
	<textarea rows="10" cols="50" name="content"></textarea>
	<hr>
	<b>이미지파일 첨부</b><br>
	<input type="file" name="image_file_name"
							onchange="readURL(this)">
<img src="#" id="preview" width="100px" height="100px;">
	<hr>
	<input type="submit" value="글쓰기">
	<input type="button" value="목록이동"
	onclick="location.href='/root/board/boardAllList'">
</form>
</div>


</body>
</html>
















