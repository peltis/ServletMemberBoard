package Service;

import static DB.Jdbc.*;

import java.sql.Connection;
import java.util.ArrayList;

import DAO.*;
import DTO.*;

public class RaonService {

	public boolean joinMember(MemberDTO dto) {
		Connection con = getConnection(); //DB접속(jdbc)정보를 받아옴
		DBDAO dao = DBDAO.getInstance(); 
		dao.setConnection(con); 
		int insertResult=dao.joinMember(dto);
		boolean result;
		
		if(insertResult > 0 ) {
			result = true;
			commit(con);
		} else { 
			result = false;
			rollback(con);
		}
		close(con);
		return result;
	}

	public boolean loginMember(MemberDTO dto) {
		Connection con = getConnection(); //DB접속(jdbc)정보를 받아옴
		DBDAO dao = DBDAO.getInstance(); 
		dao.setConnection(con); 
		
		boolean result=dao.loginMember(dto);
		
		close(con);
		return result;
	}

	public boolean boardNewMemberUpload(MemberDTO dto) {
		Connection con = getConnection(); 
		DBDAO dao = DBDAO.getInstance(); 
		dao.setConnection(con); 
		int insertResult=dao.boardNewMemberUpload(dto);
		boolean result;
		
		if(insertResult > 0 ) {
			result = true;
			commit(con);
		} else { 
			result = false;
			rollback(con);
		}
		close(con);
		return result;
	}

	public ArrayList<BoardDTO> boardNewMemberList(int startRow, int endRow) {
		Connection con = getConnection(); //DB접속(jdbc)정보를 받아옴
		DBDAO dao = DBDAO.getInstance(); 
		dao.setConnection(con); 
		
		ArrayList<BoardDTO> boardList = dao.boardNewMemberList(startRow, endRow);
		
		close(con);
		return boardList;
	}
	public int boardCount() { //신입회원게시판 카운트
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.boardCount();
		
		close(con);
		return result;
	}

	public ArrayList<BoardDTO> boardFreetalkList(int startRow, int endRow) {
		Connection con = getConnection(); //DB접속(jdbc)정보를 받아옴
		DBDAO dao = DBDAO.getInstance(); 
		dao.setConnection(con); 
		
		ArrayList<BoardDTO> boardList = dao.boardFreetalkList(startRow, endRow);
		
		close(con);
		return boardList;
	}

	public int boardFreeCount() { //자유게시판 카운트
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int result = dao.boardFreeCount();
		
		close(con);
		return result;
	}

	public boolean boardFreeWrite(BoardDTO dto) {
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int uploadResult = dao.boardFreeWrite(dto);
		boolean result;
		if(uploadResult > 0) {
			result =true;
			commit(con);
		}
		else {
			result=false;
			rollback(con);
		}
		close(con);
		return result;
	}

	public int boardHit(int boardNumber) {
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int hitResult = dao.boardHit(boardNumber);
		if(hitResult > 0) {
			commit(con);
		}
		else {
			rollback(con);
		}
		
		close(con);
		return hitResult;
	}

	public BoardDTO boardFreetalkContents(int boardNumber) {
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		BoardDTO boardList = dao.boardFreetalkContents(boardNumber);
		
		close(con);
		return boardList;
	}

	public BoardDTO BoardFreetalkModifyView(String boardNum) {
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		BoardDTO boardList = dao.BoardFreetalkModifyView(boardNum);
		close(con);
		return boardList;
	}

	public boolean BoardFreetalkModifyComplete(BoardDTO dto) {
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		boolean modifyResult=false;
		int result = dao.BoardFreetalkModifyComplete(dto);
		
		if(result > 0) {
			modifyResult =true;
			commit(con);
		}
		else {
			modifyResult=false;
			rollback(con);
		}
		
		close(con);
		return modifyResult;
	}

	public boolean BoardFreetalkDelete(int boardNum) {
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int result = dao.BoardFreetalkDelete(boardNum);
		boolean deleteResult = false;
		
		if(result > 0) {
			deleteResult=true;
			commit(con);
		}
		else {
			deleteResult = false;
			rollback(con);
		}
		
		close(con);
		return deleteResult;
	}

	public MemberDTO memberPage(String id) {
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		MemberDTO memberList = dao.memberPage(id);
		
		close(con);
		return memberList;
	}

	public boolean memberModify(MemberDTO dto) {
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int modifyResult = dao.memberModify(dto);
		boolean result=false;
		
		if(modifyResult > 0) {
			result=true;
			commit(con);
		}
		else {
			result = false;
			rollback(con);
		}
		
		close(con);
		return result;
	}

	public boolean memberQuit(String id) {
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		int quitResult = dao.memberQuit(id);
		boolean result=false;
		
		if(quitResult > 0) {
			result=true;
			commit(con);
		}
		else {
			result = false;
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public MemberDTO BoardFreetalkUserDetail(String id) {
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		MemberDTO memberList = dao.BoardFreetalkUserDetail(id);
		
		close(con);
		return memberList;
	}

	public boolean duplication_id_check(String id) {
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		int dupl_result = dao.duplication_id_check(id);
		boolean result;
		
		if(dupl_result > 0) {
			result=true;
			commit(con);
		}
		else {
			result = false;
			rollback(con);
		}
		
		close(con);
		return result;
	}

	public ArrayList<MemberDTO> member_list() {
		DBDAO dao = DBDAO.getInstance();
		Connection con = getConnection();
		dao.setConnection(con);
		
		ArrayList<MemberDTO> memberList =  dao.member_list();
		
		close(con);
		return memberList;
	}

}
