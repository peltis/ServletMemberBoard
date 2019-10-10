package Controller;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import Service.*;
import DTO.*;

@WebServlet("/JoinForm")
public class JoinForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinForm() {
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
		int size = 10*1024*1024; //10메가
		realPath = "C:/Users/user/eclipse-workspace/RaonCommunity/WebContent/MemberProfiles";
		MultipartRequest multi = new MultipartRequest(
				request, realPath, size, "UTF-8", new DefaultFileRenamePolicy()
		);
		MemberDTO dto = new MemberDTO();
		
		String id = multi.getParameter("id");
		String password = multi.getParameter("pw");
		String name = multi.getParameter("name");
		int birthyy = Integer.parseInt( multi.getParameter("birthyy") );
		int birthmm = Integer.parseInt( multi.getParameter("birthmm") );
		int birthdd = Integer.parseInt( multi.getParameter("birthdd") );
		String gender = multi.getParameter("gender");
		int phone_one = Integer.parseInt( multi.getParameter("phone_one") );
		int phone_two = Integer.parseInt( multi.getParameter("phone_two") );
		int phone_thr = Integer.parseInt( multi.getParameter("phone_thr") );
		String email = multi.getParameter("email");
		String address = multi.getParameter("address");
		String memberProfile = multi.getOriginalFileName(
				(String)multi.getFileNames().nextElement() );
		
		
		boolean result=false;
		boolean boardResult=false;
		response.setContentType("text/html; charset=UTF-8;"); 
		PrintWriter out = response.getWriter();
		
		
		dto.setId(id);
		dto.setPassword(password);
		dto.setName(name);
		dto.setBirthyy(birthyy);
		dto.setBirthmm(birthmm);
		dto.setBirthdd(birthdd);
		dto.setGender(gender);
		dto.setPhone_one(phone_one);
		dto.setPhone_two(phone_two);
		dto.setPhone_thr(phone_thr);
		dto.setEmail(email);
		dto.setAddress(address);
		dto.setMemberProfile(memberProfile);
			
		result = service.joinMember(dto);
		boardResult = service.boardNewMemberUpload(dto);
		
		System.out.println("joinform con : "+ (result && boardResult));
		
		if(result && boardResult) {
			out.println("<script>");
			out.println("alert('회원가입 성공! 로그인 페이지로 이동합니다.')");
			out.println("location.href='login_main.jsp'</script>");
			
		}
		else {
			out.println("<script>");
			out.println("alert('회원가입에 실패했어요. 관리자에게 문의하세요.')");
			out.println("history.back()</script>");
		}

	}

}
