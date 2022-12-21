package com.sh.web.lifeCycle;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *@WebServlet("/lifeCycle.do")
 * - 얘의 부가기능 : web.xml에 url과 servlet클래스의 매핑처리를 대신해준다
 *
 * Servlet생명주기 (spisdepre메모리반환)
 *  - tomcat은 각 servlet객체를 하나씩만 만들어서 운영한다 => singletone패턴 ( 1~3번 ? ) 
 *  - tomcat은 매요청마다 새로운 쓰레드를 할당해서 처리한다. => 응답성 향상 효과 
 * 1. servlet객체 생성 --해당 url 요청이 최초 발생할 경우 만듬 (서버 키자마자 만들어지는게 아님 )
 * 2. @PostConstruct 메소드 호출 -- 만들어진 이후에 바로 호출이 된다 ? 
 * 3. init메소드 호출 
 * 4. service 메서드 호출  <-- 부모한테이따 // 실제요청에대한 처리 ?
 *   - 전송방식별 메소드 호출(doGet, doPost, .. ) 
 * 5. destroy 메소드 호출 
 * 6. @PreDestroy 메소드 호출  <-- destroy 직전에 호출이된다고?
 * 7. 메모리 반환 
 */
@WebServlet("/lifeCycle.do") //이걸만들면 무조건 서버 껏다켜야된다
public class LifeCycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifeCycleServlet() {
       System.out.println("LifeCycleServlet 생성자 호출 ");
    }
    
    @PostConstruct
    public void postConstruct() {
    	System.out.println("LifeCycleServlet # @PostConstruct  호출");
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
	       System.out.println("LifeCycleServlet # init  호출 ");

	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       System.out.println("LifeCycleServlet # do get  호출 ");
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	
	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
	       System.out.println("LifeCycleServlet # destroy  호출 ");
	}
	
	@PreDestroy
	public void preDestroy() {
	       System.out.println("LifeCycleServlet # preDestroy  호출 ");
	}

}
