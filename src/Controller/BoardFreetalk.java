package Controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DTO.*;
import Service.*;

@WebServlet("/BoardFreetalk")
public class BoardFreetalk extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardFreetalk() {
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
		int boardCount = service.boardFreeCount(); 
		
		int startRow = (page-1)*limit+1;
		int endRow = page*limit;
		
		ArrayList<BoardDTO> boardList = service.boardFreetalkList(startRow, endRow);
		
		/*String id = request.getParameter("boardWrite");
		System.out.print("id : "+id);
		MemberDTO memberList = service.BoardFreetalkUserDetail(id);*/
		
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
		//request.setAttribute("memberList", memberList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("board_freetalk_main.jsp");
		dispatcher.forward(request, response); 
	}

}
