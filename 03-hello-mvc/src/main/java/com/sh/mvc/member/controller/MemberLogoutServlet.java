package com.sh.mvc.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberLogoutServlet
 */
@WebServlet("/member/logout")
public class MemberLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 사용자입력값  - 없음  ex)mode =? 이런거 
		
		// 1. 로그아웃 처리
		HttpSession session = request.getSession(false); //create 여부. 세션이 존재하지않는경우 생성하지 말고  null반환 
	// HttpSession session = request.getSession(true); //기본값이 true임 어제한거 / 세션이 존재하지않는경우 생성하고 true반환
		System.out.println( "session false처리 했을때 getid값(원래세션값인데,,): " + session.getId() ); //session객체를찾았다 ( coockie에서 value값 ) 
		if( session != null ) {
			session.invalidate();
		}
		
		// 2. redirect 
		response.sendRedirect(request.getContextPath() + "/");  // 이렇게하면 아까 썻던 세션이 무효화 되어서 세션이 변경된걸알수있음
		System.out.println( "redirect 후 세션 값 (똑같음/바뀌는건 application 탭내 세션값임 ): " + session.getId() );
	}

}
