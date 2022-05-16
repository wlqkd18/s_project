<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

function slideClick(){
	   $("#first").slideDown("slow");
	   $("#modal_wrap").show();
}

function slide_hide() {
	$("#first").hide()
	$("#modal_wrap").hide()
}

function rep() {
	let form={}; let arr = $("#frm").serializeArray();
	for(i = 0; i < arr.length; i++){
		form[arr[i].name] = arr[i].value
	}
	$.ajax({
		url: "addReply", type: "post",
		data : JSON.stringify(form),
		contentType : "application/json; charset=utf-8",
		success: function(){
			alert('답글이 달렸습니다.'); slide_hide()
			replyData();
		}
	})
}

function replyData(){
	$.ajax({
		url: "replyData/"+${info.writeNo}, type: "get",
		dataType: "json",
		success: function(data){
			let html=""
			data.forEach(function(d){
				let cDate = new Date(d.write_date)
				let wd = cDate.getFullYear()+"년"
				wd += cDate.getMonth()+1+"월"
				wd += cDate.getDate()+"일"
				wd += cDate.getHours()+"시"
				wd += cDate.getMinutes()+"분"
				wd += cDate.getSeconds()+"초"
				
				html += "<div align='center'><b>아이디 : </b>" + d.id+"님/"
				html += "<b>작성일 : </b>" + wd +"<br>"
				html += "<b>제목 : </b>" + d.title +"<br>"
				html += "<b>내용 : </b>" + d.content + "<hr></div>"
			})
			$("#reply").html(html)
		}
	})
	
}
</script>

<style type="text/css">
#modal_wrap {
	display: none;
	position: fixed;
	z-index: 9;
	margin: 0 auto;
	top: 0;
	left: 0;
	right: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.4);
}

#first {
	display: none;
	position: fixed;
	z-index: 10;
	margin: 0 auto;
	top: 30px;
	left: 0;
	right: 0; width : 350px;
	height: 450px;
	background-color: rgba(225, 225, 228, 0.5);
	width: 350px;
}
</style>

</head>
<body onload="replyData()">
	<c:import url="../default/header.jsp" />
	<p></p>
	<div class="wrap">
		<h4 align="center">개인 정보</h4>
		<table class="table table-striped">
			<tr>
				<th>글 번호</th>
				<td>${info.writeNo}</td>
				<th>작성자</th>
				<td>${info.id}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${info.title}</td>
				<th>등록일자</th>
				<td>${info.saveDate}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${info.content}</td>
				<td><c:choose>
						<c:when test="${info.imageFileName == 'nan'}">
							<b>이미지 선택 없음</b>
						</c:when>
						<c:otherwise>
							<img src="/root/board/download?file=${info.imageFileName}"
								width="100px" height="100px">
							<b>이미지 있음</b>
						</c:otherwise>
					</c:choose></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><c:if
						test="${loginUser==info.id}">
						<button type="button"
							onclick="location.href='/root/board/modify_form?writeNo=${info.writeNo}'">수정하기</button>
						<button type="button"
							onclick="location.href='/root/board/boardDelete?writeNo=${info.writeNo}&imageFileName=${info.imageFileName}'">삭제하기</button>
					</c:if>
					<button type="button" onclick="slideClick()">답글달기</button>
					<button type="button"
						onclick="location.href='/root/board/boardAllList'">리스트로
						돌아가기</button></td>
			</tr>
		</table>
		<div id="reply"></div>
	</div>


	<div id="modal_wrap">
		<div id="first">
			<div style="width: 250px; margin: 0 auto; padding-top: 20px;">
				<form id="frm">
					<input type="hidden" name="write_no" value="${info.writeNo}">
					<b>답글 작성 페이지</b><br> <b>작성자 : ${loginUser}</b><br> <b>제
						목</b><br> <input type="text" id="title" size="30" name="title">
					<hr>
					<b>내 용</b><br>
					<textarea name="content" rows="5" cols="30"></textarea>
					<hr>
					<button type="button" onclick="rep()">답글</button>
					<button type="button" onclick="slide_hide()">취소</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>