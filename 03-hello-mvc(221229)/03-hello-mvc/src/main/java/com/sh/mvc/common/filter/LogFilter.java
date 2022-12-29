package com.sh.mvc.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Filter 
 * - Filter인터페이스를 구현 
 * - 특정 url 별로 처리되도록 등록하는 작업도 필요하다 ( web.xml에 )  --> 그래서 @WebFilter라는 어노테이션 쓰면 자동동록됨 
 *
 * - servlet/jsp에 대해서 전처리 /후처리 작업 가능 
 * - 전치리 : 권한검사(ex)로그인안했음 meeberveiw사이트 못들어가게 ) , 인증여부  , 인코딩 처리, ,,, 
 * - 후처리 : 파일압축, 
 * 
 * - 여러개의 필터를 등록하는 경우, FilterChain으로관리되어 순차적으로 호출
   - ★doFilter 오버라이딩 시 FilterChain#doFilter을 반드시 호출해야 다음 filter 또는 servlet 으로 넘어간다 
   
   
   - filter처리순서 우선순위 
   1.web.xml에 작성된 순서 
   2.@WebFilter 등록시 Filter이름순 
   3.url-pattern이 servlet이름작성보다 우선순위가 높음 
   
 */


public class LogFilter implements Filter{

	/** 
	 * Servlet#doGet, Servlet#doPost의 ㅐㅁ개변수는 HttpServeltRequest, HttpServletResponse타입임 
	 * 근데 Filer#doFilter의 매개변수는 ServletRequest, servletresponse타입 
	 * 원래 tomcat이 req/res를 만듦 server가아냐 ..? 

	 * 밑에 애들은 그럼 tomcat이 만든 req/ response 객체의 부모타입? ( thtp는 자식ㅌ아비 ) 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		
		// 전처리 
		// - 요청주소  : /mvc/member/memberView
		String uri = httpReq.getRequestURI();
		String method = httpReq.getMethod(); // get or post 
		System.out.println("============== 전처리  ================");
		System.out.println( method + " |  " + uri );
		System.out.println("======================================");
		
		// FilterChain의 다음 Filter을 호출하던지 또는 Servlet 호출 
		chain.doFilter(request, response); //- ★doFilter 오버라이딩 시 FilterChain#doFilter을 반드시 호출해야함
		
		
		//후처리 
		System.out.println("-------------- 후처리 ----------------");  //jsp ????? 끝나고 찍힌다 ? 
		System.out.println("_____________________________________");
		System.out.println();
	}

}
