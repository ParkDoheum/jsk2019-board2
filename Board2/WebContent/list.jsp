<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="kr.hkit.board.*" %>
<%!
	int[] recordCntArray = {10, 20, 30, 40};


%>
<%
	String search = request.getParameter("search");
	if(search == null) {
		search = "";
	}
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
	int totalPagingCnt = (int)request.getAttribute("totalPagingCnt");
	int recordCnt = (int)request.getAttribute("recordCnt");
	
%>    
<div>
	<form id="frm" method="get" action="list">
		<input type="text" name="search" value="<%=search%>">
		<input type="submit" value="검색">
		레코드수: 
		<select name="recordCnt" onchange="changeRecordCnt()">
			<% for(int i=0; i<recordCntArray.length; i++)  {%>
				<option value="<%=recordCntArray[i] %>" 
				<% if(recordCnt == recordCntArray[i]) { %> selected <% } %>>
					<%=recordCntArray[i] %>
				</option>
			<% } %>		
		</select>
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
	<% for(int i=1; i<=totalPagingCnt; i++) { %>
		<a href="list?page=<%=i %>&search=<%=search%>&recordCnt=<%=recordCnt%>"><%=i %></a>
	<% } %> 
</div>
<script>
	function changeRecordCnt() {
		var recordCntVal = frm.recordCnt.value;
		var searchVal = frm.search.value;
		location.href = 'list?recordCnt=' + recordCntVal + '&search=' + searchVal;
	}
</script>





