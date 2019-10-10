package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutMember")
public class LogoutMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutMember() {
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
		response.setContentType("text/html; charset=UTF-8;"); 
		PrintWriter out = response.getWriter();
		out.println("<script>");		
		out.println("alert('로그아웃 완료! 메인페이지로 갑니다.')");
		out.println("location.href='Main.jsp'</script>");
		session.invalidate();
		//response.sendRedirect("Main.jsp");
	}

}
