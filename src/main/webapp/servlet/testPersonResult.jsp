<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import="java.util.Arrays, java.util.List" %>

<%
// 여기는 자바영역이다 !!! 이렇게 꺽새생긴애 공간은 java의 공간이라서 이렇게 쓰면되고 
// import 할때는 <%@ 여기 page import = "~~" 이렇게 써야됨,, 어려워,,
	String name = request.getParameter("name"); // 여기서 넘어오는건 무조건 문자열임 (숫자못넘어옴)
	String color =request.getParameter("color");
	String animal = request.getParameter("animal");
	String[] foods =request.getParameterValues("food"); // 복수개를 보낸경우 values로 받아냄 +배열 
	String recommendation =  (String)request.getAttribute("recommendation"); // 다운캐스팅 

	System.out.println("name@jsp = " + name );
	System.out.println("color@jsp = " + color );
	System.out.println("animal@jsp = " + animal );
	System.out.println("foods@jsp = " + (foods != null ? Arrays.toString(foods) : "없음" ));
	System.out.println("recommendation@jsp = " + recommendation );
	
%>

<!doctype html>
<html>
<head>
<meta charset='utf-8'>
<title>개인취향검사 결과 Servlet/jsp </title>
</head>
<body>
	<h1>개인취향검사 결과 Servlet/jsp </h1>
	<p>이름 : <%=name%></p>
	<p>좋아하는 색깔 : <%=color%></p>
	<p>좋아하는 동물 : <%=animal%></p>
	<p>좋아하는 음식 : <%= foods != null ? Arrays.toString(foods) : "없음" %></p>
	<hr />
	<p>오늘은 <mark><%= recommendation%></mark>어떠세요? </p>
</body>
</html>