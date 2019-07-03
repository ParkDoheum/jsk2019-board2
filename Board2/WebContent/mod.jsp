<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.hkit.board.*" %>    
<%
	String msg = (String)request.getAttribute("msg");
	BoardVO vo = (BoardVO)request.getAttribute("vo");	
	
	
%>

<div>
	<form method="post" action="mod">
		<input type="hidden" name="i" value="<%=vo.getI() %>">
		제목 : <input type="text" name="title" value="<%=vo.getTitle() %>"><br>		
		비밀번호 : <input type="password" name="pw" value=""><br>
		내용 <textarea name="content"><%=vo.getContent() %></textarea><br>
		사진 : <input type="text" name="pic" value="<%=vo.getPic() %>"><br>
		
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