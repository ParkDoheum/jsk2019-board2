<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kr.hkit.board.*" %>
<%
	String search = request.getParameter("search");
	if(search == null) {
		search = "";
	}
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
	
%>    
<div>
	<form method="get" action="list">
		<input type="text" name="search" value="<%=search%>">
		<input type="submit" value="검색">
	</form>
</div>
<table>
	<tr>
		<th>no</th>
		<th>제목</th>
		<th>날짜</th>
	</tr>	
	<% for(BoardVO vo : list) {%>
	<tr>
		<td><%=vo.getI() %></td>
		<td><a href="detail?i=<%=vo.getI()%>"><%=vo.getTitle() %></a></td>
		<td><%=vo.getRdate() %></td>
	</tr>
	<% } %>
</table>
<div>
	
</div>






