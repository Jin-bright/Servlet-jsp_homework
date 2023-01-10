package com.sh.ajax.text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CsvAutocompleteServlet
 * 자동 완성 기능  -  ajax  (230109)  
 */
@WebServlet("/csv/autocomplete")
public class CsvAutocompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<String> classmates = Arrays.asList(
			"김기훈",
	        "김성훈",
	        "김유진",
	        "김현동",
	        "김혜진",
	        "박효정",
	        "서정은",
	        "손예지",
	        "연시아",
	        "유재훈",
	        "유지형",
	        "이하나",
	        "이형진",
	        "장원정",
	        "장해라",
	        "정인철",
	        "정창훈",
	        "조용현",
	        "최민경",
	        "최아원",
	        "최이안",
	        "한혜진",
	        "황주현"
			);
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 처리 
		String term = request.getParameter("term");
		System.out.println("term : " + term );
		
		//2. 업무로직 - 원래라면 쿼리가 있는 걸 써야됨 (select * from classmates where name like '%김%'
		List<String> resultList = new ArrayList<>();
		for(String name : classmates) {
			if( name.contains(term) ) {
				resultList.add(name);
			}
		}
		System.out.println(resultList );
		
		//csv 형태로 변환 --- 문자열붙이기 
		StringBuilder sb = new StringBuilder();
		for(int i =0; i<resultList.size(); i++) {
			sb.append( resultList.get(i) );
			if( i != resultList.size()-1 ) {
				sb.append(",");
			}
		}
		//3. view단처리 - 우리응답메세지 : csv 형태로 직접출력 
		response.setContentType("text/csv; charset=utf-8");
		response.getWriter().append(sb);
	}

}
