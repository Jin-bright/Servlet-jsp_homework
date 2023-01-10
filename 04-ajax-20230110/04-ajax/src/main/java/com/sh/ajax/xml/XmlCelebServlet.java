package com.sh.ajax.xml;

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
 * Servlet implementation class XmlCelebServlet
 * 230109 - xml방식으로 celeb 데이터 가져오기 
 */
@WebServlet("/xml/celeb")
public class XmlCelebServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값 - 없음 ! 
		
		//2. 업무로직 
		List<Celeb> celebList = CelebManager.getInstance().getCelebList();
		
		//3. view단 처리 - xml데이터를 써줘야된다 . db에조회된결과를 xml로 변환해야하는데, html만드는거랑 똑같이 생각하기 
		// so, xml처리를 위해 jsp 쪽 forwarding (위임)
		// 패키지 구분 잘하기 
		request.setAttribute("celebList", celebList);
		request.getRequestDispatcher("/WEB-INF/views/xml/celeb.jsp")
		.forward(request, response);
		
	}

}
