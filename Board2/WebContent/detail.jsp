<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.hkit.board.*" %>
    <%@ page import="java.util.*" %>
<%
	BoardVO vo = (BoardVO)request.getAttribute("vo");	
	List<CmtVO> list = (List<CmtVO>)request.getAttribute("list");
%>

<%=vo.getI() %><br>
<%=vo.getTitle() %><br>   
<%=vo.getContent() %><br>
<% if(vo.getPic() != null && !vo.getPic().equals(""))  { %>
	<img src="<%=vo.getPic() %>"><br>
<% } %>
<a href="confirmPw?i=<%=vo.getI()%>&typ=2"><button>삭제</button></a>
<a href="confirmPw?i=<%=vo.getI()%>&typ=1"><button>수정</button></a>

<div>
	<form action="cmt" method="post">
		<input type="hidden" name="i_board" value="<%=vo.getI()%>">
		<textarea name="cmt"></textarea>
		<input type="submit" value="댓글달기">
	</form>
</div>

<div>
	<table>
		<tr>
			<th>내용</th>
			<th>등록일시</th>
			<th>삭제</th>
		</tr>
		<% for(CmtVO item : list) { %>
		<tr>
			<td><%=item.getCmt() %></td>
			<td><%=item.getRdate() %></td>
			<td><a href=""><button>삭제</button></a></td>
		</tr>		
		<% } %>
	</table>
</div>










