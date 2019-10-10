package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DTO.MemberDTO;
import Service.RaonService;

@WebServlet("/MemberModify")
public class MemberModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberModify() {
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
		
		String realPath = "";
		int size = 10*1024*1024; //10메가
		realPath = "C:/Users/user/eclipse-workspace/RaonCommunity/WebContent/MemberProfiles";
		MultipartRequest multi = new MultipartRequest(
				request, realPath, size, "UTF-8", new DefaultFileRenamePolicy()
		);
		
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
		String profile_still = multi.getParameter("profile_still");
		
		/*System.out.println( "id : "+id+"password : "+password+"birthyy : "+birthyy+"gender : "
		+gender+"phone_one : "+phone_one+"email : "+email);*/
		
		RaonService service = new RaonService(); //서비스로 데이터 넘기기 위한 객체 생성
		boolean result=false;
		
		MemberDTO dto = new MemberDTO();
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
		
		System.out.println("수정창에서 서밋한 프로파일 : "+memberProfile);
		System.out.println("기존 프로파일 : "+profile_still);
		
		if(memberProfile == null) {
			dto.setMemberProfile(profile_still);
		} else {
			dto.setMemberProfile(memberProfile);
		}
			
		result = service.memberModify(dto);
		/*System.out.println("result : " +result);
		System.out.println(dto.getId() +""+ dto.getPassword() +""+ dto.getName()+""+
		dto.getBirthdd()+""+ dto.getBirthmm()+""+dto.getBirthyy()+""+dto.getGender()+""+
		dto.getPhone_one()+""+dto.getEmail()+""+dto.getAddress());*/
		
		
		response.setContentType("text/html; charset=UTF-8;"); 
		PrintWriter out = response.getWriter();
		if(result) {
			out.println("<script>");
			out.println("alert('수정 완료')");
			out.println("location.href='MemberPage' </script>");
			
		}
		else {
			out.println("<script>");
			out.println("alert('수정 실패')");
			out.println("history.back()</script>");
		}
	}

}
