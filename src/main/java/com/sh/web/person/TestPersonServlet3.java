package com.sh.web.person;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Servlet ? 
 *  - 웹서비스를 처리하기 위한 자바클래스 
 *  
 *  - 웹서비스를 위한 필요한 기능들은 상속하여 사용하게됨 
 *   -HttpServlet 클래스를 상속해야한다 
 *
 * Servlet 인터페이스 
 *  - GenericServlet 추상클래스 
 *    - HttpServlet 추상클래스 
 *       - TestPersonServlet1 클래스(내꺼)
 *       
 * Get /web/testPerson1.do -> TestPersonServlet#doTet
 *
 * HttpServletRequest 사용자요청관련 정보를 가진 객체 
 * - 사용자입력값 
 * - 요청방식 
 * - cookie
 * - 브라우저 
 * 
 * HttpServletResponse 응답처리 관련한 객체 
 *  - 응답 출력스트림 
 *  - cookie .. 
 * 
 *
 */

public class TestPersonServlet3 extends HttpServlet{ //이거 어떻게 메소드 가져왔지 

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// 0 ★★★★ post 방식만 필요해  요청데이터 인코딩 설정필요함 
		request.setCharacterEncoding("utf-8");
//		 1. 사용자입력값 확인 --post 버전임 
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String animal = request.getParameter("animal");
		// 단, 체크박스애들은 다르다 
		String[] foods = request.getParameterValues("food");
		
//		System.out.println("name = " + name );
//		System.out.println("color = " + color );
//		System.out.println("animal = " + animal );
//		System.out.println("foods = " + (foods != null ? Arrays.toString(foods) : foods));

		// 1과 2사이 업무로직 ★
		String recommendation = "";
		switch(color) {
		case "빨강" : recommendation = "빨간고추가 들어간 우동 "; break;
		case "노랑" : recommendation= "노랑단무지랑 따듯한 우동" ; break;
		case "파랑" : recommendation = "따듯한 우동드시고, 시원한 파워에이드 한잔" ; break;
		}
		System.out.println( "recommendation  : "  +  recommendation );
		// jsp에 값 전달 -- 직접전달불가해서(얘는 사용자입력값아니라서 ?) request에 속성으로 저장하고 jsp에서 가져다 사용한다
		// key value 형식으로 저장한다 
		request.setAttribute("recommendation", recommendation);
		
		//2.응답처리 // 응답메세지 메타정보 ( html을 돌려주면된다 ? ) -- 이 부분을 jsp로 바꿀거임
		// 이렇게하면 servlet -> jsp로 넘어가게 된다.
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/servlet/testPersonResult.jsp");
		reqDispatcher.forward(request, response);
		
		
		/* 이부분 다 지우고 jsp에 위임하게 된다 
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title> 개인취향 검사결과 POST </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1> 개인취향 검사결과 POST </h1>");
		out.println("<p>이름 : " + name + "</p>");
		out.println("<p>좋아하는 색깔 : " + color + "</p>");
		out.println("<p>좋아하는 동물 : " + animal + "</p>");
		out.print("<p>좋아하는 음식 : ");
		if(foods != null) {
			for(int i=0; i<foods.length; i++) {
				out.print( foods[i] );
				if(i != foods.length-1) {
					out.print(", ");
				}
			}
		}//end if문
		else {
			out.print("없음");
		}
		out.println("</p>");
		out.println("</body>");
		out.println("</html>");
		*/
		 
		
	}
}
