package kr.hkit.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/confirmPw")
public class ConfirmPwSev extends HttpServlet {
	private static final long serialVersionUID = 1L;       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("needForPw.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String typ = request.getParameter("typ");
		String i = request.getParameter("i");
		String pw = request.getParameter("pw");		
		
		System.out.printf("typ : %s\n", typ);
		System.out.printf("i : %s\n", i);
		System.out.printf("pw : %s\n", pw);
	
		if(!BoardDAO.confirmPw(pw, Integer.parseInt(i))) {
			request.setAttribute("msg", "비밀번호가 틀렸습니다.");
			doGet(request, response);
		} else if(typ.equals("1")) { //수정
			response.sendRedirect("mod?i=" + i);
			
		} else if(typ.equals("2")) { //삭제			
			int intI = Integer.parseInt(i);
			
			int result = CmtDAO.deleteAll(intI);
			System.out.println("댓글삭제 : " + result);
			if(result != -1) {
				BoardDAO.delBoard(intI);
			} 
			response.sendRedirect("list");
		}
	}

}










