package com.sh.mvc.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sh.mvc.common.filter.*;
import com.sh.mvc.common.HelloMvcUtils;
import com.sh.mvc.member.model.dto.Member;
import com.sh.mvc.member.model.service.MemberService;

/** <<  비밀번호 수정 >> 
 * Servlet implementation class MemberUpdatePasswordServlet
 */
@WebServlet("/member/updatePassword")
public class MemberUpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//이거는 jsp랑 연결 (폼요청 ) 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/updatePassword.jsp")
		.forward(request,response);
	}

	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1. 사용자입력값 가져오기  - 쿼리문 : update member set password = ? where memberId = ? 
		HttpSession session = request.getSession(); 
		Member loginMember = (Member)session.getAttribute("loginMember");	
		
		String memberId = loginMember.getMemberId(); // 지금 로그인된 아이디  
		String newPassword = HelloMvcUtils.getEncryptedPassword( request.getParameter("newPassword"),memberId ); // 내가 창에 친 pw(new)  
		
		// 2. 기존비밀번호 일치여부 검사 
		String sessionPassword  = loginMember.getPassword(); // 원래 db에 저장되어있던 비번  
		
		// 3. 업무로직 
		// 신규비밀번호 업데이트 : update문 쿼리 update member set password = ? where member_id = ? 
		boolean passed = !sessionPassword.equals(newPassword); // <-- 내가 새로 친 비번이랑 == session에 저장된 비번이 같지않을경우
		if(passed ) { // <-- 비번을 변경해도 되는 상황 
			int result = MemberService.updatePassword(memberId, newPassword);
			session.setAttribute("msg", "비밀번호를 성공적으로 변경했습니다😊"); //비밀번호 변경 성공 메세지
			response.sendRedirect(request.getContextPath()  + "/member/memberView"  ); // 리다이렉트  :  /member/memberview 
		}
		
		else {  // <-- 내가 입력한 '기존비밀번호' 랑 진짜 db에 저장되어있는 pw랑 다른경우 
		//	System.out.println(" 누구 ? " + loginMember );
		//	System.out.println("기존 pw (session):  " + sessionPassword);
		//	System.out.println("내가창에친거 pw : " +  newPassword );
			session.setAttribute("msg", "기존 설정하신 비밀번호와 '현재 비밀번호'가 일치하지 않습니다.\n 다시 확인해주세요😣"); // 메세지 
			response.sendRedirect(request.getContextPath() + "/member/updatePassword"); // 기존비밀번호 틀리면 리다이렉트 ---- /member/updatePassword  
		}

		// 4. 세션의 정보는 db의 정보와 일치하는가? -- 최신화 시켜놔야됨 -- 그 정보업데이트하는 그거 마지막에 추가한거 그거말하는거같음 
		 session.setAttribute("loginMember", memberService.selectOneMember(memberId));

	}

}
