package Controller;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Service.*;
import DTO.*;

@WebServlet("/board_freetalk_write")
public class BoardFreetalkWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardFreetalkWrite() {
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
		String realPath = "";
		//String savePath = "/FreeBoardFile";
		int size = 10*1024*1024; //10메가
		//ServletContext context = request.getServletContext();
		//realPath = context.getRealPath(savePath);
		realPath = "C:/Users/user/eclipse-workspace/RaonCommunity/WebContent/FreeBoardFile";
		
		MultipartRequest multi = new MultipartRequest(
				request, realPath, size, "UTF-8", new DefaultFileRenamePolicy()
				);
		
		BoardDTO dto = new BoardDTO();
		dto.setBoardWrite( multi.getParameter("boardWriter") );
		dto.setBoardSubject(multi.getParameter("subject"));
		dto.setBoardContent(multi.getParameter("boardContent"));
		dto.setBoardFile( multi.getOriginalFileName(
				(String)multi.getFileNames().nextElement() ) );
		
		System.out.println( dto.getBoardWrite() +""+ dto.getBoardFile() );
		
		boolean writeResult = service.boardFreeWrite(dto);
		
		response.setContentType("text/html; charset=UTF-8;"); 
		PrintWriter out = response.getWriter();
		System.out.println(writeResult);
		if(writeResult) {
			out.println("<script>");
			out.println("alert('게시글 업로드 성공, 목록으로 이동합니다!')");
			out.println("location.href='BoardFreetalk'</script>");
		} else {
			out.println("<script language='javascript'>");
			out.println("alert('글쓰기 실패했어요')");
			out.println("history.back()");
			out.println("</script>");
		}
	}

}













