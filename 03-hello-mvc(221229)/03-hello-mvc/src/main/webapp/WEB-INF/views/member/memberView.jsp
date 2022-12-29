<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<section id=enroll-container>
	<h2>회원 정보</h2>
	<form name="memberUpdateFrm"  action="<%=request.getContextPath()%>/member/memberView" method="post">
		<table>
			<tr>
				<th>아이디<sup>*</sup></th>
				<td>
					<input type="text" name="memberId" id="memberId"  value=<%=loginMember.getMemberId()%> readonly>
				</td>
			</tr>
			<tr>
				<th>이름<sup>*</sup></th>
				<td>	
				<input type="text"  name="memberName" id="memberName" value=<%=loginMember.getMemberName()%>  required><br>
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>	
				<input type="date" name="birthday" id="birthday" value=""><br>
				</td>
			</tr> 
			<tr>
				<th>이메일</th>
				<td>	
					<input type="email" placeholder="abc@xyz.com" name="email" id="email" value=""><br>
				</td>
			</tr>
			<tr>
				<th>휴대폰<sup>*</sup></th>
				<td>	
					<input type="tel" placeholder="(-없이)01012345678" name="phone" id="phone" maxlength="11" value=<%=loginMember.getPhone()%> required><br>
				</td>
			</tr>
			<tr>
				<th>포인트</th>
				<td>	
					<input type="text" placeholder="" name="point" id="point" value="" readonly><br>
				</td>
			</tr>
			<tr>
				<th>성별 </th>
				<td>
			       		 <input type="radio" name="gender" id="gender0" value="M">
						 <label for="gender0">남</label>
						 <input type="radio" name="gender" id="gender1" value="F">
						 <label for="gender1">여</label>
				</td>
			</tr>
			<tr>
				<th>취미 </th>
				<td>
					<input type="checkbox" name="hobby" id="hobby0" value="운동" ><label for="hobby0">운동</label>
					<input type="checkbox" name="hobby" id="hobby1" value="등산" ><label for="hobby1">등산</label>
					<input type="checkbox" name="hobby" id="hobby2" value="독서" ><label for="hobby2">독서</label><br />
					<input type="checkbox" name="hobby" id="hobby3" value="게임" ><label for="hobby3">게임</label>
					<input type="checkbox" name="hobby" id="hobby4" value="여행" ><label for="hobby4">여행</label><br />


				</td>
			</tr>
		</table>
        <input type="submit" value="정보수정"/>
        <input type="button" value="비밀번호변경" onclick="updatePassword();" />
        <input type="button" onclick="deleteMember();" value="탈퇴"/>
	</form>
</section>
<script>

// <비밀번호수정>  updatePassword 함수어떻게할거야
/**
 *  1) 기존비밀번호입력 
 	2) 새비밀번호입력 
 	3) 새비밀번호확인
 => 기존비밀번호랑 일치하면 새비밀번호 업데이트 불가  / 
 => 기존 비밀번호가 일치하지 않으면 새 비밀번호 업데이트 가능  
 
 Get 방식  /mvc/member/updatePassword 비밀번호 변경 폼 요청 
 Post 방식 /mvc/member/updatePassword db비밀번호 변경 요청 
 
 */
const updatePassword = () => {
	location.href = "<%=request.getContextPath()%>/member/updatePassword"	
}



// <내정보수정> 유효성 검사 - 이름 / 휴대폰 
document.memberUpdateFrm.onsubmit = () => {
	const memberName = document.querySelector("#memberName");
	const phone = document.querySelector("#phone");
	//이름
	if( ! /^[가-힣]{2,}$/.test(memberName.value) ){
		alert("이름은 한글 2글자 이상이어야 합니다.");
		
		memberName.select();
		return false;		
	}
	//휴대폰
	if( !/^010[0-9]{8}$/.test(phone.value)){
		alert("전화번호가 유효하지 않습니다.");
		
		phone.select();
		return false;
	}
	
	return true;
}

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
