package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DTO.MemberDTO;
import Service.RaonService;

@WebServlet("/MemberQuit")
public class MemberQuit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberQuit() {
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
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		RaonService service = new RaonService();
		
		boolean result = service.memberQuit(id);
		
		response.setContentType("text/html; charset=UTF-8;"); 
		PrintWriter out = response.getWriter();
		if(result) {
			out.println("<script>");
			out.println("alert('회원 탈퇴 완료!')");
			out.println("location.href='Main.jsp' </script>");
			session.invalidate();
		}
		else {
			out.println("<script>");
			out.println("alert('동작에 실패했습니다. 관리자에게 문의하세요.')");
			out.println("history.back()</script>");
		}
	}

}
