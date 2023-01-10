<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery Ajax - text </title>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
<style>
table{
	border : 1px solid #000;
	border-collapse : collapse;
	margin-top:10px;
}
th, td{
	border : 1px solid #000;
	text-align : center;
	padding : 5px;
}
img{
	width:100px;
}
</style>
</head>
<body>
	<h1>jQuery Ajax - 1) text</h1>
	<button id="btn1">텍스트파일 </button>
	<p id="target"></p>
	
	<script>
	document.querySelector("#btn1").onclick = () =>{
		$.ajax({
			url : "<%=request.getContextPath()%>/text/sample.txt",
			method : "GET", //전송방식 /get이기본값 
			dataType : "text", //응답데이터의 타입임 - text | html | json 이런거 선택할수있는데, 생략 시 jquery 가 응답데이터를 확인해서 자동설정해줌
			beforeSend(){
				console.log( 'beforeSend!');
			},
			// 전송전 호출함수 
			success(data){
				console.log( 'success!', data );
				target.innerHTML = data;	
			},
			//요청처리 성공 시 호출되는 메소드 , 응답데이터가 매개인자로넘어온다 
			error(xhr, textStatus, err){
				console.log( 'error!', xhr, textStatus, err );
			},
			//에러발생시 호출되는 메서드 , 매개인자는 xhr, textStatus, err 
			complete(){
				console.log( 'complete!' );
			}
			// 요청이 성공 또는 실패 시 모두 마지막에 무조건 호출되는거임 (약간 try-catch절의 finally절같은느낌)
		});
		
//		document.querySelector("#target").innerText +=  `\${success(data)}`  ; 이거아님 
	} 
	</script>	


	<h2> csv </h2>
	<button id="btn2">셀럽</button>
	<div id="target-celeb"> </div>
	<script>
	btn2.onclick = () => {
		$.ajax({
			url : "<%=request.getContextPath()%>/csv/celeb",
			method : "get",
			dataType : "text", // csv라는 dt은 없다 
			success(data){
				console.log ( data );
				document.querySelector("#target-celeb").innerHTML = renderCelebData(data);
			},
			error(xhr, textStatus, err){
				console.log( xhr, textStatus, err )
			}
		})
	};
	
	const renderCelebData = (data) => {
			let html = `
			<table>
			<thead>
				<tr>
					<th>순번</th>
					<th>프로필</th>
					<th>이름</th>
					<th>직업</th>
				</tr>
			</thead>
			<tbody>`;
		//csv 데이터 처리 	
		const temp = data.split('\n');
		console.log( temp );
		for(let i=0; i<temp.length; i++){
			const celeb = temp[i].split(",");	
			console.log( celeb );
			
			html += `
				<tr>
					<td> \${celeb[0]}</td>
					<td><img src="<%=request.getContextPath()%>/images/\${celeb[2]}" alt="" /></td>
					<td> \${celeb[1]}</td>
					<td> \${celeb[3]}</td>
				</tr>`;
		}
				
				
		html += `</tbody>
			   </table>`;		
		console.log ( html );
		return html;
	};
	</script>
	
	
	
</body>
</html>