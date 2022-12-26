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
 * Servlet implementation class MemberView
 */
@WebServlet("/member/memberView")
public class MemberView extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();


	/**  GET /mvc/member/memberView
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/views/member/memberView.jsp")
		.forward(request, response);
	}

	/** POST - /mvc/member/memberUpdate 
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		//1.사용자입력값 가져오기  
		try {
			Member loginMember = (Member) session.getAttribute("loginMember"); // ★이거맞는지확인			
			String memberId = loginMember.getMemberId();
			
			String memberName = request.getParameter("memberName"); 
			String _birthday = request.getParameter("birthday"); 
			String _email = request.getParameter("email"); 
			String phone = request.getParameter("phone");
			String _gender = request.getParameter("gender"); 
			String[] _hobby = request.getParameterValues("hobby"); 
			
			String email = !"".equals(_email) ? (  request.getParameter("email") ) : loginMember.getEmail(); // 입력안했으면 기존 정보 그대로 나오도록 
			Date birthday = !"".equals(_birthday) ? ( Date.valueOf(_birthday)) : loginMember.getBirthday();
			Gender gender = _gender != null ? ( Gender.valueOf(_gender)) : loginMember.getGender(); 
			String hobby =_hobby != null ? String.join(",", _hobby) : loginMember.getHobby();
			System.out.println("== 변경된 정보 출력 ==");
			System.out.print("아이디 : " + memberId + "\n이름 : " + memberName + "\n생년월일 : " + birthday + "\n이메일 : " + email  + "\n휴대폰 : " + phone +  "\n성별 : " + gender + "\n취미 : " + hobby  );
			System.out.println();
			//2. 업무로직 
			int result = MemberService.updateMember(memberId, memberName, birthday , email, phone, gender, hobby );
		
			// 3. 사용자 피드백 준비
			if(result > 0) {
				 System.out.println("회원정보 수정 성공 !");
				 session.setAttribute("msg", "회원정보를 수정하였습니다😊");
			}
		}
		catch(Exception e){
			session.setAttribute("msg","회원정보 수정을 실패하였습니다😣");
			e.printStackTrace();
		}
		// 4. 리다이렉트  - /mvc/ 
		response.sendRedirect(request.getContextPath()  + "/"  ); 
				
	}

}
