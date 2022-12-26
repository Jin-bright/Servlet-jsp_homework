package com.sh.mvc.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcTemplate {  // class.forname 등등 예외처리 등 다 해놓은 template 
	
	static String driverClass;
	static String url;   // 접속프로토콜 @url:port:sid
	static String user; 
	static String password; 
	
	static {
		//프로그램 내에서 JdbcTemplate을 처음 사용하기 직전에 단한번 실행 - 원래 기존에 했던 거  비교해보기 
		// /의 의미 -> build / classes 의 루트 디렉토리를 의미한다  
		final String datasourceConfigPath = JdbcTemplate.class.getResource("/datasource.properties").getPath();
		System.out.println( datasourceConfigPath );
		Properties prop = new Properties();
		try {
			prop.load(new FileReader(datasourceConfigPath)); // 오타조심!!!
			driverClass = prop.getProperty("driverClass");
			url = prop.getProperty("url");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			// 1. driver class 등록 : 프로그램 실행시 최초 1회만 처리
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false); // dml. dql 상관x 하지않고 걍 false로 둠 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return conn;
	}
	
	
	public static void commit (Connection conn) {
	
		 try {
			 if( conn != null && !conn.isClosed() ) { // connection이 닫히지 않을때만 커밋해주셈
				 conn.commit(); // 방어적으로 null 이 아닐때만 커밋해주세요 	 
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback (Connection conn ) {
		 try {
			 if( conn != null && !conn.isClosed() ) { // connection이 닫히지 않을때만 커밋해주셈
				 conn.rollback(); // 방어적으로 null 이 아닐때만 커밋해주세요 	 
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
		
	
	
	public static void close (Connection conn ) {
		try {
			if(conn != null && !conn.isClosed() )
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public static void close (Statement stmt ) {
		try {
			if(stmt != null && !stmt.isClosed() )
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close (ResultSet rset ) {
		try {
			if(rset != null && !rset.isClosed() )
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
