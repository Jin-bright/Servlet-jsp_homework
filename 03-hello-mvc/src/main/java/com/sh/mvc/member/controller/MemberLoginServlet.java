package com.sh.mvc.member.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mvc.member.model.dto.Member;
import com.sh.mvc.member.model.service.MemberService;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 인코딩 처리 
		request.setCharacterEncoding("utf-8"); 
		
		//2. 사용자입력값을 자바변수에 담기 ( == 가져오기  )
		String memberId = request.getParameter("memberId"); 
		String password = request.getParameter("password");
		String saveId = request.getParameter("saveId");
		
		System.out.println("[memberId = " + memberId  );
		System.out.println("[password = " + password );
		System.out.println("[saveId = " + saveId );
		
		//2-2(3번). 업무로직 
		Member member = memberService.selectOneMember(memberId);
		System.out.println("member = " + member );
		HttpSession session = request.getSession();
		
	//	System.out.println("세션id : " + session.getId() );
	//	System.out.println("해당세션id의 유효시간  : " + session.getMaxInactiveInterval() ); //초단위 60*30
	//	System.out.println("세션id 생성시각 : " + new Date(session.getCreationTime() ));
	//	System.out.println("세션id 마지막 접속 시각 : " + new Date(session.getLastAccessedTime() )); //이번말고 최근 접속시각
		
		
		
		if( member!= null && password.equals(member.getPassword())) {
			session.setAttribute("loginMember", member);
			Cookie cookie = new Cookie("saveId", memberId);
			cookie.setPath(request.getContextPath()); 
		
			
			if(saveId != null) {
			//	Cookie cookie = new Cookie("saveId", memberId); // 쿠키만듬
			//	cookie.setPath(request.getContextPath()); 
				cookie.setMaxAge(60*60*24*7); // 7일간 클라이언트에 저장
				
				response.addCookie(cookie); // 응답헤더에 set-cookie로 전송이됨 
				System.out.println("cookie뭐지 :  " +  cookie );
			}
			else {
		//		Cookie cookie = new Cookie("saveId", memberId); // 쿠키만듬
		//		cookie.setPath(request.getContextPath());
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			
			
		}
		else {
			//로그인실패
			System.out.println("로그인 실패 ㅠㅠ");
			session.setAttribute("msg","아이디가 존재하지 않거나 비밀번호가 틀립니다.");
			
		}
		
		//4. view단처리 -- redirect (url변경 - 새로고침 안되도록) 
		response.sendRedirect(request.getContextPath()  + "/"  ); 
		
	}

}
