package com.sh.mvc.member.model.service;

import static com.sh.mvc.common.JdbcTemplate.*;

import java.sql.Connection;
import java.sql.Date;

import com.sh.mvc.member.model.dao.MemberDao;
import com.sh.mvc.member.model.dto.Gender;
import com.sh.mvc.member.model.dto.Member;

/** Service 
 * - connection 만들고 전달 
 * 
 *
 */
public class MemberService {

	private static MemberDao memberDao = new MemberDao();

	public Member selectOneMember(String memberId) {
		// 1. connection 생성
		Connection conn = getConnection();
		
		// 2. dao요청 
		Member member = memberDao.selectOneMember(conn, memberId);	
		
		// 3. connection 반환 
		close(conn);
		return member;
	}
	
	// 회원가입 시키기 - dml - insert into member 	
	public static int insertMember(Member member) {
		//1. connection 생성 
		int result = 0;
		Connection conn = getConnection(); 
		
		//2. dao 요청 
		try {
			result = memberDao.insertOneMember(conn, member);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}

		return result;
	}
	
	// 회원정보 수정하기 - dml - update 
	public static int updateMember(String memberId, String memberName, Date birthday, String email, String phone,
			Gender gender, String hobby) {
		//1. connection 생성 
			int result = 0;
			Connection conn = getConnection(); 
		
		//2. dao 요청 
		try {
			result = memberDao.updateMember(conn, memberId, memberName, birthday , email, phone, gender, hobby );
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
		return result;
	} 
		
}
