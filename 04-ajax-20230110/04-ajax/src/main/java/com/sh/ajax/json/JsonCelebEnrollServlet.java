package com.sh.ajax.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import com.sh.ajax.celeb.dto.Celeb;
import com.sh.ajax.celeb.dto.CelebType;
import com.sh.ajax.celeb.manager.CelebManager;

/**
 * Servlet implementation class JsonCelebEnrollServlet
 */
@WebServlet("/json/celeb/enroll")
public class JsonCelebEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. MultipartRequest 객체 생성 
		String saveDirectory = getServletContext().getRealPath("/images"); 
		int maxPostSize = 10 * 1024 * 1024; // 10mb 지정하기위해서 
		String encoding = "utf-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		//1. 사용자입력값 
		String name = multiReq.getParameter("name");
		CelebType type = CelebType.valueOf( multiReq.getParameter("type"));
		String profile = "default.png";
		
		if(multiReq.getFile("profile") != null ) {
			profile = multiReq.getFilesystemName("profile"); // 실제저장된이름 가져오기 
		}
		
		Celeb celeb = new Celeb(0, name, profile, type);
		System.out.println( celeb);	
		
		//2. 업무로직 - celebManager#celebList에 추가 (tomcat이 reload되면삭제) 
		List<Celeb> celebList = CelebManager.getInstance().getCelebList();
		int no = celebList.get( celebList.size() -1 ).getNo() + 1;
		celeb.setNo( no );
		celebList.add(celeb);
		
		//3. 응답처리  - Map<String, Object> 처리결과, insert된 celeb객ㅊ 반환  
		response.setContentType("application/json; charset=utf-8");
		Map<String, Object> map = new HashMap<>();
		map.put("result", "등록 성공!");
		map.put("celeb", celeb);
		
		// src를 json으로 변환해서 writer로 써주세여 
		new Gson().toJson(map, response.getWriter());
		
	}

}
