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
	 * - db insert 
	 * - redirect ( url이 남아서 새로고침되지않도록 index페이지로 redirect 시켜준다 )  
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 인코딩 처리 
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		try {
			// 1. 사용자 입력값 가져오기 -> 멤버객체에 옮겨담기 
			String memberId = request.getParameter("memberId"); //네임값 가져와야됨 (전송할때는 네임값)
			String password = request.getParameter("password"); //네임값 가져와야됨 (전송할때는 네임값)
			String memberName = request.getParameter("memberName"); //네임값 가져와야됨 (전송할때는 네임값)
			String _birthday = request.getParameter("birthday"); //네임값 가져와야됨 (전송할때는 네임값) /"1988-08-08" 이걸 sql date로 바꿔야됨 
			// 빈문자열이 넘어올수도있음 사용자가 아무값도 입력안했고 + 기본값이 정해져있을떄 
			String email = request.getParameter("email"); //네임값 가져와야됨 (전송할때는 네임값)
			String phone = request.getParameter("phone"); //네임값 가져와야됨 (전송할때는 네임값)
			String _gender = request.getParameter("gender"); //네임값 가져와야됨 (전송할때는 네임값)
			String[] _hobby = request.getParameterValues("hobby"); //네임값 가져와야됨 (전송할때는 네임값)
			
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
		response.sendRedirect(request.getContextPath()  + "/"  ); //클라이언트가 다시 요청할주소 
			
		
	}

}
