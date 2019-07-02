<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String typ = request.getParameter("typ");
	String i = request.getParameter("i");
	String msg = (String)request.getAttribute("msg");			
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 입력</title>
</head>
<body>
	<div>
		<form action="confirmPw" method="post">
			<input type="hidden" name="i" value="<%=i %>">
			<input type="hidden" name="typ" value="<%=typ %>">
			비밀번호: <input type="password" name="pw">
			<input type="submit" value="확인">
		</form>
		<% 
			if(msg != null) {
				out.print(msg);
			}
		%>
	</div>
</body>
</html>






