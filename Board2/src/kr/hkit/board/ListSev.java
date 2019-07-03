package kr.hkit.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/list")
public class ListSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		
		String page = request.getParameter("page");
		if(page == null) {
			page = "1";
		}
		
		int recordCnt = 10;
		
		//페이징 숫자를 찍기위한 용도
		int totalPagingCnt = BoardDAO.getTotalPagingCnt(recordCnt, search);
		request.setAttribute("totalPagingCnt",  totalPagingCnt);
		
		List<BoardVO> list = BoardDAO.selectAll(search, page, recordCnt);
		request.setAttribute("list",  list);
		
		request.setAttribute("view", "list.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
