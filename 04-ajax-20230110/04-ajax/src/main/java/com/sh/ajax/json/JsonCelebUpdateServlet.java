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
 * Servlet implementation class JsonCelebUpdateServlet
 */
@WebServlet("/json/celeb/update")
public class JsonCelebUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String saveDirectory = getServletContext().getRealPath("/images");
		int maxPostSize = 10 *1024 * 1024;
		String encoding = "utf-8";
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		
		
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		//1.사용자 입력값  - no는 원래있던거 name profile type
		int no =   Integer.parseInt(  multiReq.getParameter("no"));
		String name = multiReq.getParameter("name");
		CelebType type = CelebType.valueOf( multiReq.getParameter("type"));
		String profile = "default.png";
		
		if(multiReq.getFile(profile) != null) {
			profile = multiReq.getParameter("profile");
		}
		else { //원래그사람프로필그대로 들고오기,, 
			List<Celeb> celebList = CelebManager.getInstance().getCelebList();
			profile = celebList.get(no-1).getProfile(); // 원래그사람 프로필 등록할때 사진그대로 들고오는거 
		}

		Celeb celeb = new Celeb(no, name, profile, type);
	
	
		//2. 업무로직 
		List<Celeb> celebList = CelebManager.getInstance().getCelebList();
		System.out.println( "올바른 인덱스값 구해야돼 : " + (no-1) );
		System.out.println( "파일명 들거와죠 : " + profile  );
		System.out.println( celeb );
		
		celebList.set(no-1, celeb);
		
		//3.응답처리 
		response.setContentType("application/json; charset=utf-8");
		Map<String, Object> map = new HashMap<>();
		map.put("result", "수정 성공!");
		map.put("celeb", celeb);
		
		// src를 json으로 변환해서 writer로 써주세여 
		new Gson().toJson(map, response.getWriter());
		
	}

}
