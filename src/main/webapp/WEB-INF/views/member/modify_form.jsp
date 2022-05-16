<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>

<script>
function daumPost(){
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		    console.log(data.zonecode)
		    console.log(data.userSelectedType)
		    console.log(data.roadAddress)
		    console.log(data.jibunAddress)
		    var addr=""
		    if(data.userSelectedType == 'R'){
		    	addr = data.roadAddress
		    }else{
		    	addr = data.jibunAddress
		    }
            $("#addr1").val( data.zonecode )
            $("#addr2").val( addr )

            $("#addr3").val("")
            $("#addr3").focus()
        }
    }).open();
}
function register(){
	addr1 = $("#addr1").val()
    addr2 = $("#addr2").val()
    addr3 = $("#addr3").val()
    console.log( addr1 +"/"+ addr2 + "/" + addr3 )
    addr1 = addr1 +"/"+ addr2 + "/" + addr3
    
    $("#addr1").val( addr1 )
    fo.submit()
}
</script>

</head>
<body>
	<c:import url="../default/header.jsp"/>
	<div class="wrap">
	<div style="width:500px; margin: 0 auto;">
		<h3 align="center">개 인 정 보</h3>
		<form id="fo" action="modify" method="post">
	<table class="table">
		<tr>
			<th>아이디</th> 
			<td>
				<input type="text" readonly value="${info.id }" name="id">
			</td>
		</tr>
		<tr>
			<th>비밀번호</th> 
			<td>
			<input type="password" value="${info.pw }" name="pw">
			</td>
		</tr>
		<tr>
			<th>주 소</th> 
			<td>
				<input readonly type="text" id="addr1" value="${addr[0] }" name="addr">
				<input type="button" value="우편번호 찾기" onclick="daumPost()">
				<br>
				<input readonly type="text" id="addr2" value="${addr[1] }">
				<input type="text" id="addr3" value="${addr[2] }"><br>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<button type="button" onclick="register()" class="btn btn-warning" >수정</button>
			</td>
		</tr>
	</table>
	</form>
	</div>
</div>
</body>
</html>