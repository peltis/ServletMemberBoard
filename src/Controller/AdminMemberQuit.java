package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.RaonService;

@WebServlet("/AdminMemberQuit")
public class AdminMemberQuit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminMemberQuit() {
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
		String id = request.getParameter("id");
		RaonService service = new RaonService();
		
		boolean result = service.memberQuit(id);
		
		response.setContentType("text/html; charset=UTF-8;"); 
		PrintWriter out = response.getWriter();
		if(result) {
			out.println("<script>");
			out.println("alert('해당 회원이 탈퇴처리 되었습니다.')");
			out.println("location.href='MemberList' </script>");
		}
		else {
			out.println("<script>");
			out.println("alert('동작에 실패했습니다. 관리자에게 문의하세요.')");
			out.println("history.back()</script>");
		}
	}

}
