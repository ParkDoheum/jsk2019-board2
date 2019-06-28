<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");

	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String pic = request.getParameter("pic");
	String pw = request.getParameter("pw");
	
	if(title == null) {
		title = "";
		content = "";
		pic = "";
		pw = "";
	}
	
%>
<div>
	<form method="post" action="reg">
		제목 : <input type="text" name="title" value="<%=title %>"><br>		
		비밀번호 : <input type="password" name="pw" value="<%=pw %>"><br>
		내용 <textarea name="content"><%=content %></textarea><br>
		사진 : <input type="text" name="pic" value="<%=pic %>"><br>
		
		<input type="submit" value="등록">
	</form>
	<div>
		<% 
		if(msg != null) {
			out.print(msg);	
		} 
		%>
	</div>
</div>