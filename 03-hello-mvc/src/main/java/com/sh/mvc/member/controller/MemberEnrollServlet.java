package com.sh.mvc.member.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mvc.member.model.dto.Gender;
import com.sh.mvc.member.model.dto.Member;
import com.sh.mvc.member.model.service.MemberService;

/**
 * Servlet implementation class MemberEnrollServlet
 * 
 * 회원가입 !!! 
 * 
 */
@WebServlet("/member/memberEnroll")
public class MemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 *  get : 회원가입홈페이지 요청할 목적 
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/member/memberEnroll.jsp" )
		.forward(request, response);
		
	}

	/**
	 * post : 회원가입 처리 
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩 처리 
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		try {
			// 1. 사용자 입력값 가져오기 -> 멤버객체에 옮겨담기 
			String memberId = request.getParameter("memberId"); 
			String password = request.getParameter("password"); 
			String memberName = request.getParameter("memberName"); 
			String _birthday = request.getParameter("birthday"); 
			String email = request.getParameter("email"); 
			String phone = request.getParameter("phone"); 
			String _gender = request.getParameter("gender"); 
			String[] _hobby = request.getParameterValues("hobby"); 
			
			Date birthday = !"".equals(_birthday) ?  ( Date.valueOf(_birthday)) : null;
			Gender gender =  _gender != null ? ( Gender.valueOf(_gender) ) : null;
			String hobby = _hobby != null ? ( String.join(",", _hobby)) : null;
			
			Member member = new Member(memberId, password, memberName, null, gender, birthday, email, phone, hobby, 0, null);
			System.out.println( member );
			
			// 2. 업무로직 - db insert 
			int result = MemberService.insertMember( member );
			
			// 3. 사용자 피드백 준비
			if(result > 0) {
				 System.out.println("회원가입 성공 !");
				 session.setAttribute("msg", "회원가입을 축하드립니다😊");
				//회원가입 성공 메세지 
			}
		} catch(Exception e){
			session.setAttribute("msg","회원가입을 할 수 없습니다.");
			e.printStackTrace();
		}
		
		// 4. 리다이렉트  - /mvc/ 
		response.sendRedirect(request.getContextPath()  + "/"  );
			
		
	}

}
