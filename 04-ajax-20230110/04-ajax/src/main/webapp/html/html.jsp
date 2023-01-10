<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery Ajax - 2)Html</title>
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
	<h1>jQuery Ajax-2)Html</h1>
	<button id="btn1">셀럽데이터_가져오기</button>
	<div id="target"> </div>
	<script>
	btn1.onclick = () =>{
		$.ajax({
			url : "<%=request.getContextPath()%>/html/celeb",
			method: "get",
			dataType : "html",  //응답받을 데이터 타입(받고싶은?)
			success(data){
				console.log(data);
				document.querySelector("#target").innerHTML = data
			},
			error(xhr, textStatus, err){
				console.log(xhr, textStatus, err);				
			}
		});
	}
	</script>
</body>
</html>