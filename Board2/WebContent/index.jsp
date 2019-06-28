<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String view = (String)request.getAttribute("view");
	if(view == null) {
		view = "home.jsp";
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
<style>
	html, body {
		margin: 0;
		padding:0;
		height: 100%;
	}
	#container {
		display: flex;
		height: 100%;
		flex-direction: column;
	}
	#container > header {
		height: 100px;
		background-color: #2ecc71;
	}
	#container > nav {
		height: 30px;
		background-color: #e74c3c;
	}
	#container > section {
		flex-grow: 1;
	}
	#container > footer {
		height: 30px;
		background-color: #34495e;
	}
</style>
</head>
<body>
	<div id="container">
		<header>			
			<h1>쇼핑몰 회원관리 ver1.0</h1>
		</header>
		<nav>
			<a href="list">리스트</a>
			<a href="reg">등록</a>			
			<a href="index.jsp">홈으로</a>
		</nav>
		<section>		
			<jsp:include page="<%=view %>"></jsp:include>
		</section>
		<footer>
			footer
		</footer>
	</div>
</body>
</html>







