package Controller;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.*;
import DTO.*;

@WebServlet("/LoginForm")
public class LoginForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginForm() {
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
		String password = request.getParameter("pw");
		
		System.out.println("현재 위치 : LOGIN→Controller");
		
		RaonService service = new RaonService();
		MemberDTO dto = new MemberDTO();
		
		dto.setId(id);
		dto.setPassword(password);
		
		boolean result = service.loginMember(dto);
		response.setContentType("text/html; charset=UTF-8;"); 
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		if(result) {
			session.setAttribute("id", id);
			out.println("<script>");		
			out.println("alert('로그인 성공! 메인페이지로 이동합니다.')");
			out.println("location.href='Main.jsp'</script>");
			//response.sendRedirect("Main.jsp");
		}
		else {
			out.println("<script>");
			out.println("alert(' Login Fail! Uncorrect insert ID/PW! ')");
			out.println("history.back()</script>");
		}
	}
}
