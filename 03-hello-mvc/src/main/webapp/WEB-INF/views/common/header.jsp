<%@page import="com.sh.mvc.member.model.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 
	Member loginMember = (Member) session.getAttribute("loginMember"); //object -> member  
	
	String msg = (String)session.getAttribute("msg");

	if( msg != null )
		session.removeAttribute("msg");
	
	Cookie[] cookies = request.getCookies();
	String saveId = null;
	
	if(cookies != null){
		for(Cookie cookie : cookies){
			String name = cookie.getName();
			String value = cookie.getValue();
		//	System.out.println ( name + " = " + value );
			if("saveId".equals(name)){
				saveId = value;
			}
		}
	}
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello MVC</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/style.css" />
<script>


window.addEventListener('load', () => {
	
	<% if( msg != null) {%> 
		alert("<%=msg%>"); 
	
	<% } %>		
	
	<%if(loginMember == null) { %>
	document.loginFrm.addEventListener('submit', (e) => {
		const memberId = document.querySelector("#memberId");
		const password = document.querySelector("#password");
		
		if(!/^\w{4,}$/.test(memberId.value)){
			alert("유효한 아이디를 입력하세요.");
			memberId.select();
			
			e.preventDefault(); 
			return; 
		}
		
		if(!/^\w{4,}$/.test(password.value)){
			alert("유효한 아이디를 입력하세요.");
			password.select();
	
			e.preventDefault(); 
			return; 
		}
	});
	<% } %>	
	
});

</script>

</head>
<body>
<div id="container">
		<header>
			<h1>Hello MVC</h1>
			<div class="login-container">
			
			<%if(loginMember == null) { %>
				<!-- 로그인폼 시작 -->
				<form id="loginFrm" name="loginFrm" action="<%=request.getContextPath() %>/member/login" method="POST">
					<table>
						<tr>
							<td><input type="text" name="memberId" id="memberId" placeholder="아이디" tabindex="1" required  value="<%=saveId !=null ? saveId : ""%>" ></td>
							<td><input type="submit" value="로그인" tabindex="3" ></td>
						</tr>
						<tr>
							<td><input type="password" name="password" id="password" placeholder="비밀번호" tabindex="2" required></td>
							<td></td>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox" name="saveId" id="saveId" <%=saveId != null ? "checked" : "" %> />
								<label for="saveId">아이디저장</label>&nbsp;&nbsp;
								<input type="button" value="회원가입" onclick = "location.href='<%= request.getContextPath()%>/member/memberEnroll';">
							</td>
						</tr>
					</table>
				</form>
				<!-- 로그인폼 끝-->	
			<% } else { %>
				<table id="login">
					<tr>
						<td><%= loginMember.getMemberName() %> 님, 안녕하세요 😍😍😍 </td>
					</tr>
					<tr>
						<td> 
							<input type="button" value="내정보보기"  onclick="location.href= '<%=request.getContextPath()%>/member/memberView';" /> 
							<input type="button" value="로그아웃" onclick="location.href= '<%=request.getContextPath()%>/member/logout'"/> 
						</td>
					</tr>
				
				</table>
			
			<% } %>
				
			</div>
			<!-- 메인메뉴 시작 -->
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="<%= request.getContextPath() %>">Home</a></li>
					<li class="notice"><a href="#">공지사항</a></li>
					<li class="board"><a href="#">게시판</a></li>
				</ul>
			</nav>
			<!-- 메인메뉴 끝-->
					
		</header>
		
		<section id="content">
