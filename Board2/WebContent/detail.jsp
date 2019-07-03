<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.hkit.board.*" %>    
<%
	BoardVO vo = (BoardVO)request.getAttribute("vo");	
%>

<%=vo.getI() %><br>
<%=vo.getTitle() %><br>   
<%=vo.getContent() %><br>
<% if(vo.getPic() != null && !vo.getPic().equals(""))  { %>
	<img src="<%=vo.getPic() %>"><br>
<% } %>
<a href="confirmPw?i=<%=vo.getI()%>&typ=2"><button>삭제</button></a><br>
<a href="confirmPw?i=<%=vo.getI()%>&typ=1"><button>수정</button></a>


