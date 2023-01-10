package com.sh.ajax.html;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sh.ajax.celeb.dto.Celeb;
import com.sh.ajax.celeb.manager.CelebManager;

/**
 * Servlet implementation class HtmlCelebServlet
 */
@WebServlet("/html/celeb")
public class HtmlCelebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 입력값 처리 
		// 2. 업무로직 - celeb 인데,  그럼 db에가서  celeb 테이블에서 목록 조회 하는건데 지금 시간관계상 db 안만듬 
		List<Celeb> celebList = CelebManager.getInstance().getCelebList();
		System.out.println( celebList ); //db에서 만들어진거라고 걍 흐린눈,, 
		
		// 3. view단처리 
		// ★★★ 비동기로할때는 post로 해도 (dml요청해도) 리다이렉트할일이 거의 없다 
		request.setAttribute("celebList", celebList);
		request.getRequestDispatcher("/WEB-INF/views/html/celeb.jsp")
		.forward(request, response);
		
	}

	

}
