<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%--   jsp 주석  --%>    
<%-- 
	<%@ page %> 기본설정 
	<%@ include %> 다른페이지를 포함하는 설정 
	<%@ taglib %> jsp tag 사용선언  

	<% %> scriptlet 자바코드 사용 
	<%= %> 출력식 (표현식? ) 
	
	**jsp는 servlet으로 변환되서 처리된다 
	( html -> servlet으로 ? ) 
	
	--%>    
 <% 
 //자바영역 
 //1 ~ 10 합구하기 
 int sum =0;
 for(int i=1; i<=10; i++){
	 sum+=i;
 }
 System.out.println(" 자바 sum = " + sum);
 
 //밀리초 구하기 
 //long  milliSecond =  System.currentTimeMillis(); // 자바랭패키지는 import없이 쓸수있다 
 
 int num = (int)(Math.random() *10) + 1; //난수만들어써 
 String mode = request.getParameter("mode");
 
 %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> JSP Basics </title>
<script>
	window.onload = () => {
		let sum =0; 
		for(let i=1; i<=10; i++){
			sum += i;
		}
		document.querySelector("#sum").innerHTML = sum;
		
		const nowSecond = Date.now(); 
		document.querySelector("#now").innerHTML = nowSecond; 
	}
	
</script>
</head>
<body>
	<h1> JSP Basics  </h1>
	<%--  jsp 주석  // 얘는 아예 화면에 주석으로도 출력이안됨  --%>
	<!--  html 주석  -->
	<!--  이걸 자바 코드 밖에 감싼다 ?  그래도 안에 값이 표시됨. 근데 걍 바로 노출이안되는거임 페이지소스에서 볼수있음 주석으로 처리된걸  -->
	
	<ul>
		<li>Server Side :<span> (자바로 했을 때 합) <%-- <%=sum%> --%> </span></li>
		<li>Client Side : <span id="sum"> </span></li>
	</ul>

<%
 //밀리초 구하기 
 long  milliSecond =  System.currentTimeMillis(); /// 밑에 출력되기 전에만 자바 코드 써주면됨 
  %>	
	<ul>
		<li>Server Side 밀리초 (server가 더 빠르다!): <span><%=milliSecond %></span> </li>
		<li>Client Side 밀리초 : <span id="now"> </span></li>
	</ul>
	
	<h2> 분기처리 </h2>
	<% if(num%2==0) { %>
		<p> <%=num %>은 짝수입니다.</p>
	<% } else { %>
		<p> <%=num %>은 홀수입니다.</p>
	<% } %>
	
	<%-- 사용할수있는 mode는 3가지 ; en / ko / num  -- 밑에 if문쓸때  "en".equals(mode) 이렇게 쓰는게 exception 예방된대  
	<% if( mode.equals("en") ) { %>
	<p> abcdefg </p>
	<% } else if ( mode.equals("ko")) { %>
	<p> 가나다라마바사 </p>
	<% } else if( mode.equals("num")) { %>
	<p> 1234567890</p>
	<% } %>
	--%>
	
	
	<h2> 반복처리 </h2>
	<ul>
	<% // 자바 
		String[] names = {"홍길동", "신사임당", "이순신"};
		for(int i=0; i<names.length; i++){
				System.out.println(" names[i]");
	%>
		<li> <%=names[i]%></li>
	<%
		}
	%>			
	</ul>
	
<%//자바코드
	List<String> webLangs = new ArrayList<>(); 
		webLangs.add("html");
		webLangs.add("css");
		webLangs.add("javascript");
%>	
	<ol>
<%
	for(int i =0; i<webLangs.size(); i++){
		System.out.println(" webLangs[i]");
%>
	 <li> <%= webLangs.get(i)%></li>
<% } %>	
	</ol>
<br /><br /><br /><br />	
<%
//		<li><a href="/web/jsp/basics.jsp?no=1&prod=아이디어패드&option=빨강&option=128gb"> @실습문제1 </a></li>
//		<li><a href="/web/jsp/basics.jsp?no=2&prod=설렁탕&option=다대기많이&option=곱빼기&option=당면빼고"> @실습문제2 </a></li>

	String no = request.getParameter("no");
	String prod =   request.getParameter("prod"); 
	String[] options =   request.getParameterValues("option");
	
	System.out.println("options@jsp = " + (options != null ? Arrays.toString(options) : "없음" ));
	
%>	

<style>
table{
	width : 300px;
	border :2px solid black;
	border-collapse : collapse;
	text-align : center;
}

th, td{
	border : 1px solid black;
}
</style>


<table>
<tr>
	<th>주문번호 </th>
	<td><%=no%></td>
</tr>
<tr>
	<th>상품명 </th>
	<td><%=prod%></td>
</tr>

<% for(int i=0; i<options.length; i++){
		%>
<tr>
	<th>옵션<%=(i+1)%> </th>
	<td><%= options[i]%> </td>
<% } %>	
</tr>
</table>

	
</body>
</html>