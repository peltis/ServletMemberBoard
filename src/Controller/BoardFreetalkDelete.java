package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.RaonService;

@WebServlet("/BoardFreetalkDelete")
public class BoardFreetalkDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardFreetalkDelete() {
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
		int boardNum = Integer.parseInt(request.getParameter("boardNum"));
		RaonService service = new RaonService();
		
		boolean result = service.BoardFreetalkDelete(boardNum);
		response.setContentType("text/html; charset=UTF-8;"); 
		PrintWriter out = response.getWriter();
		
		if(result) {
			out.println("<script language='javascript'>");
			out.println("alert(' 게시글 삭제 성공! 메인으로 돌아갑니다. ')");
			out.println	("location.href='BoardFreetalk' ");
			out.println("</script>");
		} else {
			out.println("<script language='javascript'>");
			out.println("alert('게시글 삭제에 실패했어요')");
			out.println("history.back()");
			out.println("</script>");
		}
		
	}

}
