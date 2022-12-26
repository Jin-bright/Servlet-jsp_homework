<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section id=enroll-container>
	<h2>회원 가입 정보 입력</h2>
	<form name="memberEnrollFrm" action="<%=request.getContextPath()%>/member/memberEnroll" method="POST" >
		<table>
			<tr>
				<th>아이디<sup>*</sup></th>
				<td>
					<input type="text" placeholder="4글자이상" name="memberId" id="_memberId"  value="sinsa" required>
				</td>
			</tr>
			<tr>
				<th>패스워드<sup>*</sup></th>
				<td>
					<input type="password" name="password" id="_password"  value="1234" required><br>
				</td>
			</tr>
			<tr>
				<th>패스워드확인<sup>*</sup></th>
				<td>	
					<input type="password" id="passwordCheck" value="1234" required><br>
				</td>
			</tr>  
			<tr>
				<th>이름<sup>*</sup></th>
				<td>	
				<input type="text"  name="memberName" id="memberName" required value="신사임당" ><br>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
				<input type="date" name="birthday" id="birthday" value="1988-08-08" ><br />
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value="sinsa@naver.com" ><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰<sup>*</sup></th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" required value="01012341234"><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
					<input type="radio" name="gender" id="gender0" value="M">
					<label for="gender0">남</label>
					<input type="radio" name="gender" id="gender1" value="F" checked>
					<label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동" checked ><label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산" checked ><label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서"><label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임" checked ><label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행"><label for="hobby4">여행</label><br />
				</td>
			</tr>
		</table>
		<input type="submit" value="가입" >
		<input type="reset" value="취소">
	</form>
</section>

<script>
document.memberEnrollFrm.onsubmit  = () => {
	const memberId = document.querySelector("#_memberId");
	const password = document.querySelector("#_password");
	const passwordCheck = document.querySelector("#passwordCheck");
	const memberName = document.querySelector("#memberName");
	const phone = document.querySelector("#phone");
	
	//1. 아이디 -영문자/숫자 4글자 이상 
	if( !/^[a-zA-Z0-9]{4,}$/.test(memberId.value)){
		alert("아이디는 영문자/숫자 4글자 이상이어야 합니다.");

		memberId.select();
		return false;  //e.pre~이거 onsubmit은 필수아님 		
	}
	

	
	//2. 비밀번호 - 영문자/숫자/특수문자 4글자 이상  + 비밀번호/비밀번호 확인이 동일한가 
	if( !/^[a-zA-Z0-9!@#$%]{4,}$/.test(password.value)){
		alert("비밀번호는 영문자/숫자/특수문자!@#$% 포함 4글자 이상이어야 합니다.");
	
		password.select();
		return false;  //e.pre~이거 onsubmit은 필수아님 
	}
	
	if( password.value !== passwordCheck.value ){
		alert ("두 비밀번호가 일치하지 않습니다.")
		password.select();
		return false;
	}
	
	//3. 이름 - 한글 2글자 이상 
	if( !/^[가-힣]{2,}$/.test(memberName.value)){
		alert("이름은 한글 2글자 이상이어야 합니다.");
	
		memberName.select();
		return false;  //e.pre~이거 onsubmit은 필수아님 
	}
	
	//4. 전화번호는 숫자 01012345678 형식  
	if( !/^010[0-9]{8}$/.test(phone.value)){  //앞에 010 세글자로 시작한다고해서 뒤에 $붙이면 안됨,, 
		alert("전화번호가 유효하지 않습니다.");
	
		phone.select();
		return false;  //e.pre~이거 onsubmit은 필수아님 
	}
	

	
	return true;
}


</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
