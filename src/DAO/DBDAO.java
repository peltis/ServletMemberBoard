package DAO;

import java.util.*;
import java.sql.*;
import DTO.*;

import static DB.Jdbc.*;

public class DBDAO {
	private static DBDAO DBDAO;
	Connection con;
	PreparedStatement pstmt;
	ResultSet re;
	
	public static DBDAO getInstance() {
		if(DBDAO == null)
			DBDAO = new DBDAO();
		return DBDAO;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}

	public int joinMember(MemberDTO dto) {
		String sql = "INSERT INTO MEMBER_LIST VALUES(MEMBER_LIST_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";
		int insertResult =0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getBirthyy());
			pstmt.setInt(5, dto.getBirthmm());
			pstmt.setInt(6, dto.getBirthdd());
			pstmt.setString(7, dto.getGender());
			pstmt.setInt(8, dto.getPhone_one());
			pstmt.setInt(9, dto.getPhone_two());
			pstmt.setInt(10, dto.getPhone_thr());
			pstmt.setString(11, dto.getEmail());
			pstmt.setString(12, dto.getAddress());
			pstmt.setString(13, dto.getMemberProfile());
			insertResult = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("JoinMember error");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("DBDAO.joinMember().insertResult :  "+insertResult );
		return insertResult;
	}

	public boolean loginMember(MemberDTO dto) {
		String sql = "SELECT ID,PASSWORD FROM MEMBER_LIST WHERE ID=?";
		boolean result = false;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId() );
			re = pstmt.executeQuery();
			
			if( re.next() ) {
				if( dto.getPassword().equals( re.getString("PASSWORD")) ) {
					result = true;
				} else {
					result = false;
				}
			}
		} catch (Exception e) {
			System.out.println("Login error");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(re);
		}
		System.out.println("DBDAO.Login :  "+result );
		return result;
	}

	public ArrayList<BoardDTO> boardNewMemberList(int startRow, int endRow) {
		String sql = "SELECT * FROM BOARD_NEW_PAGING WHERE BOARD_NEW_PAGING.RN BETWEEN ? AND ?";
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		BoardDTO dto = null; 
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			re = pstmt.executeQuery(); 	
			
			while( re.next() ) { 
				dto = new BoardDTO();
				dto.setBoardNumber( re.getInt("BOARD_NUM") );
				dto.setBoardWrite( re.getString("BOARD_WRITER"));
				dto.setBoardSubject( re.getString("BOARD_SUBJECT"));
				dto.setBoardContent( re.getString("BOARD_CONTENTS"));
				dto.setBoardDate( re.getDate("BOARD_DATE"));
				dto.setBoardHit( re.getInt("BOARD_HIT"));
				
				boardList.add(dto);
			} 
		} 
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("boardNewMemberList error");
		} finally {
			close(re);
			close(pstmt);
		}
		
		return boardList;
	}

	public int boardNewMemberUpload(MemberDTO dto) {
		String sql = "INSERT INTO NEW_MEMBER_BOARD VALUES( NEW_MEMBER_BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, 0)";
		String admin="관리자";
		String subject= dto.getName() + "님이 가입하셨습니다.";
		String content= dto.getName() + "(" +dto.getId() + ") 님이 가입하셨습니다.";
		int insertResult =0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			insertResult = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardNewMemberUpload error");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertResult;
	}

	public int boardCount() {
		String sql = "SELECT COUNT(*) FROM NEW_MEMBER_BOARD";
		int boardCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
			re = pstmt.executeQuery();
			if(re.next()) {
				boardCount = re.getInt(1);//첫번째 열에 나온 조회수
			}
		} catch (Exception e) {
			System.out.println("boardCount error");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(re);
		}
		return boardCount;
	}

	public int boardFreeCount() {
		String sql = "SELECT COUNT(*) FROM FREETALK_BOARD";
		int boardCount = 0;
		try {
			pstmt = con.prepareStatement(sql);
			re = pstmt.executeQuery();
			if(re.next()) {
				boardCount = re.getInt(1);//첫번째 열에 나온 조회수
			}
		} catch (Exception e) {
			System.out.println("boardCount error");
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(re);
		}
		return boardCount;
	}

	public ArrayList<BoardDTO> boardFreetalkList(int startRow, int endRow) {
		String sql = "SELECT * FROM BOARD_FREE_PAGING WHERE BOARD_FREE_PAGING.RN BETWEEN ? AND ?";
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		BoardDTO dto = null; 
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			re = pstmt.executeQuery(); 	
			
			while( re.next() ) { 
				dto = new BoardDTO();
				dto.setBoardNumber( re.getInt("BOARD_NUM") );
				dto.setBoardWrite( re.getString("BOARD_WRITER"));
				dto.setBoardSubject( re.getString("BOARD_SUBJECT"));
				dto.setBoardContent( re.getString("BOARD_CONTENTS"));
				dto.setBoardDate( re.getDate("BOARD_DATE"));
				dto.setBoardHit( re.getInt("BOARD_HIT"));
				
				boardList.add(dto);
			} 
		} 
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("boardFreetalkList error");
		} finally {
			close(re);
			close(pstmt);
		}
		
		return boardList;
	}

	public int boardFreeWrite(BoardDTO dto) {
		String sql = "INSERT INTO FREETALK_BOARD VALUES(FREE_BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, 0, ?)";
		int insertResult=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBoardWrite() );
			pstmt.setString(2, dto.getBoardSubject() );
			pstmt.setString(3, dto.getBoardContent() );
			pstmt.setString(4, dto.getBoardFile() );
			insertResult = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("boardFreeWrite error");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertResult;
	}

	public int boardHit(int boardNumber) {
		String sql = "UPDATE FREETALK_BOARD SET BOARD_HIT=BOARD_HIT+1 WHERE BOARD_NUM=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNumber);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("boardHit error");
		} finally {
			close(pstmt);
		}
		return result;
	}

	public BoardDTO boardFreetalkContents(int boardNumber) {
		String sql = "SELECT * FROM FREETALK_BOARD WHERE BOARD_NUM=?";
		BoardDTO boardList = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNumber);
			re = pstmt.executeQuery();
			
			if ( re.next() ) {
				boardList = new BoardDTO();
				boardList.setBoardNumber( re.getInt("BOARD_NUM") );
				boardList.setBoardWrite( re.getString("BOARD_WRITER") );
				boardList.setBoardSubject( re.getString("BOARD_SUBJECT"));
				boardList.setBoardContent( re.getString("BOARD_CONTENTS"	));
				boardList.setBoardDate( re.getDate("BOARD_DATE") );
				boardList.setBoardHit( re.getInt("BOARD_HIT"));
				boardList.setBoardFile( re.getString("BOARD_FILE"));
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("boardFreetalkContents error");
		} finally {
			close(re);
			close(pstmt);
		}
		return boardList;
	}

	public BoardDTO BoardFreetalkModifyView(String boardNum) {
		String sql = "SELECT * FROM FREETALK_BOARD WHERE BOARD_NUM=?";
		BoardDTO boardList = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, boardNum);
			re = pstmt.executeQuery();
			
			if( re.next() ) {
				boardList = new BoardDTO();
				boardList.setBoardWrite( re.getString("BOARD_WRITER") );
				boardList.setBoardSubject(re.getString("BOARD_SUBJECT"));
				boardList.setBoardContent(re.getString("BOARD_CONTENTS"));
				boardList.setBoardNumber( re.getInt("BOARD_NUM") );
				boardList.setBoardFile(re.getString("BOARD_FILE"));
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("BoardFreetalkModifyView error");
		} finally {
			close(re);
			close(pstmt);
		}
		return boardList;
	}

	public int BoardFreetalkModifyComplete(BoardDTO dto) {
		String sql = "UPDATE FREETALK_BOARD SET  BOARD_SUBJECT=?, BOARD_CONTENTS=?, BOARD_FILE=? WHERE BOARD_NUM=?";
		System.out.println( "모디파이 " +dto.getBoardNumber() );
		int insertResult=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getBoardSubject() );
			pstmt.setString(2, dto.getBoardContent() );
			pstmt.setString(3, dto.getBoardFile() );
			pstmt.setInt(4, dto.getBoardNumber() );
			insertResult = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("BoardFreetalkModifyComplete error");
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return insertResult;
	}

	public int BoardFreetalkDelete(int boardNum) {
		String sql = "DELETE FROM FREETALK_BOARD WHERE BOARD_NUM=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			result = pstmt.executeUpdate(); 
		} 
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("BoardFreetalkDelete error");
		} finally {
			close(pstmt);
		}
		System.out.println(result);
		return result;
	}

	public MemberDTO memberPage(String id) {
		String sql = "SELECT * FROM MEMBER_LIST WHERE ID=?";
		MemberDTO memberList = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			re = pstmt.executeQuery();
			
			if( re.next() ) {
				memberList = new MemberDTO();
				memberList.setId( re.getString("ID"));
				memberList.setPassword(re.getString("PASSWORD") );
				memberList.setName(re.getString("NAME"));
				memberList.setBirthyy( re.getInt("BIRTH1") );
				memberList.setBirthmm(re.getInt("BIRTH2"));
				memberList.setBirthdd(re.getInt("BIRTH3"));
				memberList.setGender(re.getString("GENDER"));
				memberList.setPhone_one(re.getInt("PH1"));
				memberList.setPhone_two(re.getInt("PH2"));
				memberList.setPhone_thr(re.getInt("PH3"));
				memberList.setEmail(re.getString("MAIL"));
				memberList.setAddress(re.getString("ADDRESS"));
				memberList.setMemberProfile( re.getString("MEMBER_PROFILE") );
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("memberPage error");
		} finally {
			close(pstmt);
			close(re);
		}
		return memberList;
	}

	public int memberModify(MemberDTO dto) {
		String sql = 
				"UPDATE MEMBER_LIST SET BIRTH1=?, BIRTH2=?, BIRTH3=?, PH1=?, PH2=?, PH3=?, MAIL=?, ADDRESS=?, MEMBER_PROFILE=? WHERE ID=?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getBirthyy() );
			pstmt.setInt(2, dto.getBirthmm() );
			pstmt.setInt(3, dto.getBirthdd() );
			pstmt.setInt(4, dto.getPhone_one() );
			pstmt.setInt(5, dto.getPhone_two() );
			pstmt.setInt(6, dto.getPhone_thr() );
			pstmt.setString(7, dto.getEmail() );
			pstmt.setString(8, dto.getAddress() );
			pstmt.setString(9, dto.getMemberProfile() );
			pstmt.setString(10, dto.getId() );
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("memberModify error");
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int memberQuit(String id) {
		String sql = "DELETE FROM MEMBER_LIST WHERE ID = ?";
		int result=0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id );
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("memberQuit error");
		} finally {
			close(pstmt);
		}
		return result;
	}

	public MemberDTO BoardFreetalkUserDetail(String id) {
		String sql = "SELECT * FROM MEMBER_LIST WHERE ID=?";
		MemberDTO memberList = null;
		int year=2019;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			re = pstmt.executeQuery();
			
			if( re.next() ) {
				memberList = new MemberDTO();
				memberList.setId( re.getString("ID"));
				memberList.setPassword(re.getString("PASSWORD") );
				memberList.setName(re.getString("NAME"));
				memberList.setBirthyy( re.getInt("BIRTH1") );
				memberList.setBirthmm(re.getInt("BIRTH2"));
				memberList.setBirthdd(re.getInt("BIRTH3"));
				memberList.setGender(re.getString("GENDER"));
				memberList.setPhone_one(re.getInt("PH1"));
				memberList.setPhone_two(re.getInt("PH2"));
				memberList.setPhone_thr(re.getInt("PH3"));
				memberList.setEmail(re.getString("MAIL"));
				memberList.setAddress(re.getString("ADDRESS"));
				
				memberList.setAge( year-re.getInt("BIRTH1")+1 );
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("BoardFreetalkUserDetail error");
		} finally {
			close(pstmt);
			close(re);
		}
		return memberList;
	}

	public int duplication_id_check(String id) {
		String sql = "SELECT ID FROM MEMBER_LIST WHERE ID=?";
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			re = pstmt.executeQuery();
			
			if( re.next() ) {
				result = 1;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("duplication_id_check error");
		} finally {
			close(pstmt);
			close(re);
		}
		System.out.println(result);
		return result;
	}

	public ArrayList<MemberDTO> member_list() {
		String sql = "SELECT * FROM MEMBER_LIST ORDER BY MEMBER_SEQ DESC";
		ArrayList<MemberDTO> memberList = new ArrayList<MemberDTO>();
		MemberDTO dto = null;
		try {
			pstmt = con.prepareStatement(sql);
			re = pstmt.executeQuery();
			
			while( re.next() ) {
				dto = new MemberDTO();
				dto.setMemberNumber( re.getInt("MEMBER_SEQ"));
				dto.setId( re.getString("ID") );
				dto.setPassword(re.getString("PASSWORD"));
				dto.setName(re.getString("NAME"));
				dto.setGender(re.getString("GENDER"));
				dto.setBirthyy(re.getInt("BIRTH1"));
				dto.setBirthmm(re.getInt("BIRTH2"));
				dto.setBirthdd(re.getInt("BIRTH3"));
				dto.setPhone_one(re.getInt("PH1"));
				dto.setPhone_two(re.getInt("PH2"));
				dto.setPhone_thr(re.getInt("PH3"));
				dto.setEmail(re.getString("MAIL"));
				dto.setAddress(re.getString("ADDRESS"));
				
				memberList.add(dto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("duplication_id_check error");
		} finally {
			close(pstmt);
			close(re);
		}
		return memberList;
	}
}

























