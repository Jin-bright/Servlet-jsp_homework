<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	boolean available = (boolean) request.getAttribute("available");
	String memberId = request.getParameter("memberId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디중복검사</title>
<style>
div#checkId-container{text-align:center; padding-top:50px;}
span#available {color:green; font-weight:bold;}
span#duplicated {color:red; font-weight:bold;}
</style>
</head>
<body>
	<div id="checkId-container">
		<% if(available ) {  %>
		<p>
			[<span id="available"><%=memberId%></span>]는 사용가능합니다.
		</p>
		<p>
			<input type="button" value="닫기" onclick="closePopup();" />
		</p>	
		
		<% } else { %>
		<p>
			[<span id="duplicated"><%=memberId%></span>]는 이미 사용중입니다.
		</p>
		<form action="<%= request.getContextPath() %>/member/checkIdDuplicate" name = "checkIdDuplicateFrm">
			<input type="text" name="memberId" id="memberId" placeholder="새로운 아이디를 입력하세요."/>
			<input type="submit" value="제출"/>
		</form>
		<% } %>
	</div>
	<script>
	<%if(!available){ %>
	document.checkIdDuplicateFrm.onsubmit = (e) => { 
		const memberId = document.querySelector("#memberId");
		
		//1. 아이디 -영문자/숫자 4글자 이상 
		if( !/^[a-zA-Z0-9]{4,}$/.test(memberId.value)){
			alert("아이디는 영문자/숫자 4글자 이상이어야 합니다.");
	
			memberId.select();
			return;  //e.pre~이거 onsubmit은 필수아님 		
		}
	}
	<%}%>
	const closePopup = () => { 
		
		// 부모창의 윈도우 객체에 대한 참조
//		console.log( opener ) ; 
		const parentFrm = opener.document.memberEnrollFrm;
		parentFrm.memberId.value = "<%= memberId %>";    //따옴표 안쓰면 왜안되지 
		parentFrm.idValid.value = "1";
		
		self.close(); //this.close();
	};
	</script>
</body>
</html>
