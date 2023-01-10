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
 * Servlet implementation class JsonCelebFindOneServlet
 */
@WebServlet("/json/celeb/findOne")
public class JsonCelebFindOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //1사용자입력값
		int no = Integer.parseInt( request.getParameter("no"));
		System.out.println("no " + no );
		
		
		//2 업무로직 
		List<Celeb> celebList = CelebManager.getInstance().getCelebList();
		Celeb celeb = null;
		
		for( Celeb c : celebList ) {
			if( c.getNo() == no )
				celeb = c;
		}
		
		
		//3 응답처리 
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(celeb, response.getWriter());
		
	}

}
