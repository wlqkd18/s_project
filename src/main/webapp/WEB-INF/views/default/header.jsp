<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
			uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<style type="text/css">
	* { margin: 0; }
	.wrap{ width: 1000px; margin: auto; }
	.header{ width: 1000px; }
	.title{
		font-size: 70pt;
		text-align: center;
		text-shadow: 10px 10px 15px black;
		margin-top: 0;
		padding-bottom: 20px;
		color : burlywood;
		font-family: Gabriola;
		
	}
	.decor:hover{
		text-decoration: none;
	}
	
	nav{ background-color: olive; width: 1000px;}
	.navdiv{ width: 100%; background-color: olive; }
	
	nav ul{ list-style: none; display: flex;
				justify-content: end; }
	nav ul li{ padding: 10px; }
	nav ul li a{text-decoration: none; color: white; }
	nav ul li a:hover {
		color: orange; border-bottom:  2px solid black;
		transition: all 0.25s; padding-bottom: 3px; 
	}
	
</style>

</head>
<body>
	<div class="wrap">
		<div class="header">
			<a href="/root/index" class="decor"><h1 class="title">CARE LAB</h1></a>
		</div>
	</div>
<div class="navdiv">	
	<div class="wrap">
		<nav>
			<ul>
				<li> <a href="/root/index">HOME</a> </li>
				
				<li>
					<a href="/root/member/memberInfo">MEMBER</a>
				</li>
				<%-- <li> 
					<c:if test="${loginUser == null}">
						<a href="/root/member/login">MEMBER</a> 
					</c:if>
					
					<c:if test="${loginUser != null}">
						<a href="/root/member/memberInfo">MEMBER</a> 
					</c:if>
				</li> --%>
				<li>
					<a href="/root/board/boardAllList">BOARD</a>
				</li>
				<li> 
					<c:if test="${loginUser == null}">
						<a href="/root/member/login">LOGIN</a> 
					</c:if>
					
					<c:if test="${loginUser != null}">
						<a href="/root/member/logout">LOGOUT</a> 
					</c:if>
				</li>
			</ul>
		</nav>
	</div>
</div>
</body>
</html>








