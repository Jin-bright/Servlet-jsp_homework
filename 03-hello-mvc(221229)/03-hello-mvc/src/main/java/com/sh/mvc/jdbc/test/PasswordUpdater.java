package com.sh.mvc.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sh.mvc.common.HelloMvcUtils;
import com.sh.mvc.member.model.dto.Member;
import static com.sh.mvc.common.JdbcTemplate.*;

public class PasswordUpdater {

	public static void main(String[] args) {
		new PasswordUpdater().start();
	}

	private void start() {
		// 1. 회원아이디 조회 및 신규비번 설정
		Connection conn = getConnection();
		String sql = "select * from member";
		List<Member> list = new ArrayList<>();
		try(
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rset = pstmt.executeQuery()){
			while(rset.next()) {
				String memberId = rset.getString("member_id");
				String password = HelloMvcUtils.getEncryptedPassword("1234", memberId); //암호화 처리함
				
				Member member = new Member(); //멤버객체 만들고 
				member.setMemberId(memberId);
				member.setPassword(password);
				
				list.add(member); //멤버추가 
			}
			
			for(Member member : list)
				System.out.printf("memberId : %s, password : %s%n", 
						member.getMemberId(), member.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 2. 일괄 업데이트 = 뱃치 ?  batch 
		sql = "update member set password = ? where member_id = ?";
		try(PreparedStatement pstmt = conn.prepareStatement(sql);){
			for(Member member : list) {
				pstmt.setString(1, member.getPassword());
				pstmt.setString(2, member.getMemberId());
				pstmt.executeUpdate();
				System.out.printf("%s 비밀번호 업데이트 성공!%n", member.getMemberId());
			}
			
			commit(conn);
			
		} catch (SQLException e) {
			rollback(conn);
			e.printStackTrace();
		}
		
		close(conn);
		System.out.println("[비밀번호 일괄갱신이 끝났습니다.]");
		
		
	}
	
	

}