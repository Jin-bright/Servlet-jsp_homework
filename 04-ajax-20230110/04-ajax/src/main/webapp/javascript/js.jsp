<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> js로 XMLHttpRequest 제어하기_기억필요 </title>
</head>
<body>
	<h1> js로 XML-제어하기</h1>
	<h3>비동기에서는 폼을만들긴하나 폼을 제출하면안됨(폼제출한다? 그럼 동기식임 ) 그럼 어떻게 요청 보냄  xml어쩌꼬,,</h3>
	<form action="">
		<fieldset>
			<legend> 폼 </legend>
			<div> 
				<input type="text" name="username" id="username" placeholder="아이디 입력" />
			</div>
			<div>
				<input type="email" name="email" id="email" placeholder="이메일 입력" />
			</div>
			<div>
				<button type="button" onclick="submitGet()">GET </button>
				<button type="button" onclick="submitPost()" >POST </button>
			</div>
		</fieldset>
	</form>
	<div id="target"></div>
<script>
let xhr;

const submitPost = () => {
	//1. XMLHttpRequest객체 생성 
	xhr = new XMLHttpRequest();
	
	//2. readystatechange이벤트핸들러 바인딩 
	xhr.onreadystatechange = readyStateChangeHandler;
	//3. open 
	xhr.open("POST", "<%=request.getContextPath()%>/javascript");
	xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;'); // post 전용설정
		
	//4. send 
	xhr.send(`username=\${username.value}$email=\${email.value}`);
}


const submitGet = () => {
	//console.log(XMLHttpRequest);
	//console.log(new XMLHttpRequest);
	//1.xml객체생성 
	xhr = new XMLHttpRequest();
	//2. 핸들러 readystatechane 달기 
	xhr.onreadystatechange = readyStateChangeHandler;
	//3. open
<%--	xhr.open("GET",`<%=request.getContextPath()%>/js?username=\${username.value}&email=\${email.value}`): --%>
	xhr.open("GET", `<%= request.getContextPath() %>/javascript?username=\${username.value}&email=\${email.value}`);
	//4. send - message body에 작성될 내용 
	xhr.send(null);
	
};

const readyStateChangeHandler = (e) => {
	//console.log ( e.target );
	switch(xhr.readyState){
	case 1 : 
		console.log( "readyState 1 : loading "); //open 이후 send호출 전 
		break;
	case 2 : 
		console.log( "readyState 2 : loaded "); //send호출  
		break;
	case 3 : 
		console.log( "readyState 3 : interactive "); //응답도착 의 시작 
		break;
	case 4 : 
		console.log( "readyState 4 : completed  "); // 응답도착완료 
		if(xhr.status == 200){
			console.log("요청처리 성공!");
			console.log(xhr.responseText); // 응답데이터
			target.innerHTML = xhr.responseText;
		}
		else{
			console.log("요청처리 실패!");
			console.log( xhr.status );
		}
	}
};

</script>	
</body>
</html>