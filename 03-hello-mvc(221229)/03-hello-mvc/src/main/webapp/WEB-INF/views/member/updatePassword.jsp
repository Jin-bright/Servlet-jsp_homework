<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

	<section id=enroll-container>
		<h2>비밀번호 변경</h2>
		<form 
			name="passwordUpdateFrm" 
			method="post" >
			<table>
				<tr>
					<th>현재 비밀번호</th>
					<td><input type="password" name="oldPassword" id="oldPassword" required></td>
				</tr>
				<tr>
					<th>변경할 비밀번호</th>
					<td>
						<input type="password" name="newPassword" id="newPassword" required>
					</td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td>	
						<input type="password" id="newPasswordCheck" required><br>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;">
						<input type="submit"  value="변경" />
					</td>
				</tr>
			</table>
		</form>
	</section>
<script>
//유효성 검사 
document.passwordUpdateFrm.onsubmit = () => {

	const oldPassword = document.querySelector("#oldPassword"); // 원래 내 비번 
	const newPassword = document.querySelector("#newPassword"); // 새로 입력한 비밀번호 
	const newPasswordCk = document.querySelector("#newPasswordCheck");  // 비번 체크 
	
	
	
	if(!/^[a-zA-Z0-9!@#$%]{4,}$/.test(newPassword.value)){
		alert("비밀번호는 영문자/숫자/특수문자!@#$% 포함 4글자 이상이어야 합니다.");
	
		newPassword.select();
		return false; 
	}
	
	if( newPassword.value !== newPasswordCheck.value ){
		alert ("새로 설정한 두 비밀번호가 일치하지 않습니다.");

		newPasswordCheck.select();
		return false;
	}
	
	if( oldPassword.value === newPassword.value ){
		alert ("현재 비밀번호와 변경하시려는 두 비밀번호가 동일합니다😣 \n다시확인해주세요!");
		newPasswordCheck.select();
		return false;
	}
	
	return true;
}

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
