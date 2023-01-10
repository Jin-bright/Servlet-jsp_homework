package com.sh.ajax.json;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.sh.ajax.celeb.dto.Celeb;
import com.sh.ajax.celeb.manager.CelebManager;

/**
 * Servlet implementation class JsonCelebFindAllServlet
 */
@WebServlet("/json/celeb/findAll")
public class JsonCelebFindAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// findAll => select * from celeb 
		// 1. 사용자입력값 처리 
		
		// 2. 업무로직 - celeb 목록조회 
		List<Celeb> celebList = CelebManager.getInstance().getCelebList();
		// 리스트는 배열/ 자바객체는 json으로 변환할려면 ? [{"no" : 1, "name" : "양세찬", "profile" : "양세찬.jsp", "type" : "Comedian"}, ...]
//[{"no":1,"name":"양세찬","profile":"양세찬.jpg","type":"Comedian"},{"no":2,"name":"김고은","profile":"김고은.jpg","type":"Actor"},{"no":3,"name":"아이유","profile":"아이유.jpg","type":"Singer"},{"no":4,"name":"조정석","profile":"조정석.jpg","type":"Actor"},{"no":5,"name":"강동원","profile":"강동원.jpg","type":"Actor"}]		
		// 근데 이렇게 하면 노가다라서 외부 라이브러리를 사용할거야 
		
		// 3. view단처리 - json문자열을 응답에 직접출력★★★★
		// 3-1. header 설정필수 (리턴한데이터가 json이야 )
		response.setContentType("application/json; charset=utf-8");
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(celebList); // 이러면 자바객체 -> json으로 변환 
		System.out.println( jsonStr );
	
		response.getWriter().append(jsonStr);
		
		
		 
	}

}
