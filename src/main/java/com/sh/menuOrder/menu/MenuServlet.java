package com.sh.menuOrder.menu;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MenuServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	//1. 사용자입력값 확인 
	String mainMenu = request.getParameter("mainMenu");
	String sideMenu = request.getParameter("sideMenu");
	String beverageMenu = request.getParameter("beverageMenu");
	
	
	//2. 응답처리 -- servlet -> jsp
	RequestDispatcher reqDispatcher = request.getRequestDispatcher("/servlet/menuResult.jsp");
	reqDispatcher.forward(request, response);
	
	
	}
	
	
	
}
