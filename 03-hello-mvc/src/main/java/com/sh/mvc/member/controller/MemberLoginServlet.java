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
		request.setCharacterEncoding("utf-8"); //대소문자구분x
		
		//2. 사용자입력값을 자바변수에 담기 ( == 가져오기  )
		String memberId = request.getParameter("memberId"); // id가 아닌 name값을 가져와야됨 
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
			//로그인 성공  System.out.println("로그인 성공 !");
			// 로그인풀리는걸어떻게 방지할건가  => HttpSession객체 - 접속한 클라이언트(브라우저)별로 제공되는 객체 
			// 얘의 생명주기는 브라우저가 첨만들어져서끌때까지 살아잇는거임 
			
			session.setAttribute("loginMember", member);
			Cookie cookie = new Cookie("saveId", memberId); // 쿠키만듬
			cookie.setPath(request.getContextPath()); // /mvc   /의 의미 =>  cookie를 전송할 url 설정인데 지정한 url하위경로에 모두 전송하는거임  
		
			
			if(saveId != null) {
				// 아이디 저장을 체크했을 경우임 (value값은 on임 )
			//	Cookie cookie = new Cookie("saveId", memberId); // 쿠키만듬
			//	cookie.setPath(request.getContextPath()); // /mvc   /의 의미 =>  cookie를 전송할 url 설정인데 지정한 url하위경로에 모두 전송하는거임  
				cookie.setMaxAge(60*60*24*7); // 7일간 클라이언트에 저장한다는 뜻 
				//만약 유효시간을 설정하지않으면 자동으로 session-cookie로 작동 / 브라우저 끄면 날라감 
				//유효시간 설정 : setMaxage, setExpire 
				//유효시간을 설정하면 persistent-cookie로 작동 / 지정한 기간동안 저장 
				
				
				response.addCookie(cookie); // 응답헤더에 set-cookie로 전송이됨 
				System.out.println("cookie뭐지 :  " +  cookie );
			}
			else { //아이디 저장 체크해제한 경우 -- 즉시 삭제 (키 value, path까지 ㅣㅇㄹ치해야됨 )  
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
		response.sendRedirect(request.getContextPath()  + "/"  ); //클라이언트가 다시 요청할주소 
		
	}

}
