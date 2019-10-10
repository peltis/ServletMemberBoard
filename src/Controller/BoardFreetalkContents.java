package Controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.*;
import Service.*;

@WebServlet("/BoardFreetalkContents")
public class BoardFreetalkContents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardFreetalkContents() {
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
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber") );
		int page = Integer.parseInt( request.getParameter("page") );
		
		System.out.println("넘겨받은 게시글번호 : "+boardNumber);
		
		RaonService service = new RaonService();
		
		int hitResult = service.boardHit(boardNumber);
		
		if(hitResult > 0) {
			BoardDTO boardList = service.boardFreetalkContents(boardNumber);
			request.setAttribute("boardList", boardList);
			request.setAttribute("page", page);
			RequestDispatcher dispatcher = request.getRequestDispatcher("board_freetalk_contents.jsp");
			dispatcher.forward(request, response); 
		} else {
			System.out.println("조회수가 안올라서 실패!");
		}
	}

}
