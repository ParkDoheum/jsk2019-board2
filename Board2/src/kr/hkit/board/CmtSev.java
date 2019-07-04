package kr.hkit.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cmt")
public class CmtSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String i_cmt = request.getParameter("i_cmt");
		String i_board = request.getParameter("i_board");
		
		int result = CmtDAO.delete(Integer.parseInt(i_cmt));
		int err = 0;
		if(result != 1) {
			err = 1;
		}
		response.sendRedirect("detail?i="+ i_board + "&err=" + err);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String i_board = request.getParameter("i_board");
		String cmt = request.getParameter("cmt");
		
		CmtVO vo = new CmtVO();
		vo.setI_board(Integer.parseInt(i_board));
		vo.setCmt(cmt);
		
		CmtDAO.insertCmt(vo);
		response.sendRedirect("detail?i=" + i_board);
	}

}
