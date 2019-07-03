package kr.hkit.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mod")
public class ModSev extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//수정 
		BoardVO vo = (BoardVO)request.getAttribute("vo");
		if(vo == null) {
			String i = request.getParameter("i");
			vo = BoardDAO.getBoard(i);
			request.setAttribute("vo", vo);	
		}
		request.setAttribute("view", "mod.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String i = request.getParameter("i");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String pic = request.getParameter("pic");
		String pw = request.getParameter("pw");
				
		BoardVO vo = new BoardVO();
		vo.setI(Integer.parseInt(i));
		vo.setTitle(title);
		vo.setContent(content);
		vo.setPic(pic);
		vo.setPw(pw);
		
		int result = BoardDAO.updateBoard(vo);
		if(result == 1) {
			response.sendRedirect("detail");
		} else {		
			request.setAttribute("vo", vo);
			request.setAttribute("msg", "수정을 할 수 없습니다.");
			doGet(request, response);
		}
	}

}
