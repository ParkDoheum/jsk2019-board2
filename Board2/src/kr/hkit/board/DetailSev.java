package kr.hkit.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/detail")
public class DetailSev extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i = request.getParameter("i");
		String err = request.getParameter("err");
		
		if(err != null && !err.equals("0")) {
			if(err.equals("1")) {
				request.setAttribute("msg", "댓글이 삭제되지 않았습니다.");
			}
		}		
		
		List<CmtVO> list = CmtDAO.selectCmt(i);
		request.setAttribute("list", list);
		
		BoardVO vo = BoardDAO.getBoard(i);		
		request.setAttribute("vo", vo);
		
		request.setAttribute("view", "detail.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
