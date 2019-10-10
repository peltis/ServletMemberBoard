package Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.*;
import DTO.*;

@WebServlet("/BoardNewMember")
public class BoardNewMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardNewMember() {
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
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt( request.getParameter("page"));
		}
		
		RaonService service = new RaonService(); 
		int boardCount = service.boardCount(); 
		
		int startRow = (page-1)*limit+1;
		int endRow = page*limit;
		
		ArrayList<BoardDTO> boardList = service.boardNewMemberList(startRow, endRow);
		
		int maxPage = (int)( (double)boardCount/limit + 0.9 );
		int startPage = ( ( (int) ((double)page/10 + 0.9) ) -1 ) *10 +1;
		int endPage = startPage + 10 -1;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		PagingDTO paging = new PagingDTO();
		
		paging.setPage(page);
		paging.setStartPage(startPage);
		paging.setEndPage(endPage);
		paging.setMaxPage(maxPage);
		paging.setBoardCount(boardCount);
		request.setAttribute("paging", paging);
		request.setAttribute("boardList", boardList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("board_new_main.jsp");
		dispatcher.forward(request, response); 
	}

}
