package com.sh.mvc.jdbc.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class JdbcTestServlet
 */
@WebServlet("/jdbc/test")
public class JdbcTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		response.getWriter().append("jdbc 연결 테스트 중입니다 !! ");
		
		
		try {
			testJdbcConnection();
			out.append("<h2>성공!</h2>");
		} catch (ClassNotFoundException | SQLException e) {
			out.append("<h2>실패</h2>"); // 클라이언트 전송용 
			e.printStackTrace();// 서버로깅용
		} //jdbc 호출할거임 
	}

	private void testJdbcConnection() throws ClassNotFoundException, SQLException {

		String driverClass = "oracle.jdbc.OracleDriver";
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		//  "jdbc:oracle:thin:@localhost:1521:xe"; 이거 오타조심
		String user ="web";
		String password = "web";
		
		String sql = "select * from member";
		
		//1. 드라이버 클래스 등록 
		Class.forName(driverClass);
		System.out.println("[ driverClass 등록완료! ] ");

		//2. 커넥션 객체 생성 
		Connection conn = DriverManager.getConnection(url,user, password);
		conn.setAutoCommit(false);
		System.out.println("[ Connection 객체 생성 완료! ] ");
		
		//3. PreparedStatement 객체 생성 및 미완성 쿼리 값 대입 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		//4. 쿼리실행 ( dml - pstmt.executeUpdate() :int반환  / dql - pstmt.executeQuery() :ResultSet반환 )
		ResultSet rset =  pstmt.executeQuery();
		System.out.println("[ PreparedStatement 객체 생성 및 실행완료! ] ");
		
		//5. 이후처리 (dml - 트랜잭션처리필요(commit or rollback )  / dql - ResultSet자바변수에 옮겨담기 
		while(rset.next()) {
			String memberId = rset.getString("member_id");
			String memberName = rset.getString("member_name");	
			
			System.out.printf("%s \t %s%n", memberId, memberName);
		}
		
		//6. 자원 반납 필요 
		rset.close();
		pstmt.close();
		conn.close();
		System.out.println("[ 자원 반납 완료! ] ");
	}
	
	

}
