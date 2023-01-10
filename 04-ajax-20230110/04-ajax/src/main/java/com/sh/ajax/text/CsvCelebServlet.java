package com.sh.ajax.text;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.ajax.celeb.dto.Celeb;
import com.sh.ajax.celeb.manager.CelebManager;

/**
 * Servlet implementation class CsvCelebServlet
 */
@WebServlet("/csv/celeb")
public class CsvCelebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값 처리 
		
		//2. 업무로직 
		List<Celeb> celebList = CelebManager.getInstance().getCelebList();
		
		//csv 데이터 생성 /구분자 2개 쓸거임 
		StringBuilder csv = new StringBuilder(); //여기 append 해서 정보 넣을거임 
		for( int i =0; i<celebList.size(); i++) {
			//1,양세찬,양세찬.jpg,Comedian  - 정보들 사이는 ,로 구분 할고
			//2,김고은,김고은.jpg,Comedian  - 사람사이는 개행으로 구분할거임 
			Celeb c =celebList.get(i);
			csv.append(c.getNo() +"," + c.getName() + "," + c.getProfile() + "," + c.getType() );
			
			if(i < celebList.size() -1) {
				csv.append("\n");
			}//end-if
		}//end-for문
		System.out.println( csv );

		
		//3. view단 처리 - csv데이터를 직접 출력 (jsp이런거안씀  ) 
		response.setContentType("text/csv; charset=utf-8"); //미디어타입 
		PrintWriter out = response.getWriter();
		out.append(csv); //출력스트림에 append 했음 
	}

}
