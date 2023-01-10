<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQueryUI - autocomplete  </title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="<%=request.getContextPath()%>/js/jquery-3.6.1.js"></script>
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<!-- 반드시 ui src 가 밑에 오게 해야됨   -->
<script>
 $( function() {
  
   $( "#classmates" ).autocomplete({
      source(request, response){
    	 console.log( request ); //사용자입력데이터 / {term : 사용자입력값 } //객체임 
   // 	 console.log( response ); // 함수임 // 목록화할 데이터를 전달할 함수 (=데이터를 시각화할 함수임) 
    	 // 하나의 목록은 {label, value} 속성을 기본적으로 갖고있어야함  <-- 이걸 객체로 전달함  
    	 
		 findClassmates(request, response);
      },// end-source문
      
      focus( event, selected){  //커서로 목록 이동 시 선택되는 걸 방지함	
    	  return false;
      },
      
      select( event, selected){
    	  console.log ( selected );
    	  const {item : {value}} = selected;
    	  alert( value );
      }
   });
 });
 
 const findClassmates = (request, response) => {
	 
	 $.ajax({
		 url : "<%=request.getContextPath()%>/csv/autocomplete",
		 method : "GET",
		 data : {
			 term : request.term //?term=kim 이렇게 바꿔줌 jq가 중간 bridge 역할 
		 },
		 dataType : "text",
		 success(data){
			 console.log(data);
			 
			 if(data ==="") return; // 빈문자열이 넘어온걸 걸러낸다 
			 const _arr = data.split(',');
			 const arr = _arr.map( (name, index) => {
				 return {
					 		label : name, //외부노출되는값 
					 		value : name // 내부적으로 처리되는 값  // 두개는 다 써줘야됨 
						 };
			 });
			 console.log( _arr );
			 console.log( arr );
			 
			 response(arr);
		 },
		 error(xhr, textStatus, err){
			 console.log(xhr, textStatus, err);
		 }
	 });//end - ajaxs
	 
	 
 }
</script>
</head>
<body>	 
	<h1> jQueryUI - autocomplete  </h1>
	<div class="ui-widget">
	  <label for="classmates"> 반친구 검색 : </label>
	  <input id="classmates">
	</div>
</body>
</html>