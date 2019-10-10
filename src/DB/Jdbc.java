package DB;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class Jdbc { //JAVA와 DB를 연결해주는 클래스
	public static Connection getConnection() {
		Connection con = null; // DB정보 변수 선언
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			DataSource ds = (DataSource)envCtx.lookup("jdbc/OracleDB"); //Resource name이 "jdbc/OracleDB"인 context.xml파일 정보에 접근
			con = ds.getConnection(); // 가져온 정보(ds)로 DB에 접속 후 con에 DB정보 대입
			con.setAutoCommit(false); //다른 클래스(service)에서 commit 작업을 하기 때문에 자동 commit 을 막아둠
			System.out.println("This location JDBC : connect success"); //접속 성공 시 출력문
		} catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con){
		try { con.close();
		} catch (Exception e) { e.printStackTrace();
		}	}
	public static void close(Statement stmt){
		try { stmt.close();
		} catch (Exception e) {	e.printStackTrace();
		}	}
	public static void close(ResultSet rs){
		try { rs.close();
		} catch (Exception e) {	e.printStackTrace();
		}	}
	public static void commit(Connection con){
		//DB에 완전이 반영시켜주는애
		try { con.commit();
		} catch (Exception e) {	e.printStackTrace();
		}	}
	public static void rollback(Connection con){
		try { con.rollback();
		} catch (Exception e) {	e.printStackTrace();
		}
	}
}
