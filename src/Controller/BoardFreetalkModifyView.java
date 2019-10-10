package Controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.*;
import DTO.*;

@WebServlet("/BoardFreetalkModifyView")
public class BoardFreetalkModifyView extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardFreetalkModifyView() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RaonService service = new RaonService();
		HttpSession session = request.getSession();
		String boardNum = request.getParameter("boardNum");
		System.out.println(boardNum);
		
		BoardDTO boardList = service.BoardFreetalkModifyView(boardNum);
		
		request.setAttribute("boardList", boardList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("board_freetalk_modify.jsp");
		dispatcher.forward(request, response); 
	}
}






