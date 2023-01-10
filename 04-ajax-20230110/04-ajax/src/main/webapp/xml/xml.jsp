<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> XML </title>
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
	<h1> xml </h1>
	
	<button id="btn1"> 실행 </button>
	<div id="target1"> 
		<table>
		<thead>
			<tr>
				<th> 주제 </th>
				<th> 제목 </th>
				<th> 저자 </th>
			</tr>
		</thead>
		
		<tbody>
		</tbody>
		</table>
	
	</div>
	<script>
	document.querySelector("#btn1").addEventListener('click', () => {
		$.ajax({
			url : "<%=request.getContextPath()%>/xml/sample.xml",
			method : "get",
			dataType : "xml",
			success(data){
				console.log( data );
				const root = data.querySelector(":root ");
				const books =  [ ...root.children] ; 
				
				console.log( books );

//			const books = document.querySelector("books"); <-- 이렇게는 왜안될까 ? 
//			console.log( books );

			const tbody = document.querySelector("#target1 tbody");
			tbody.innerHTML = "";
			
			books.forEach( (book, index) =>{
				console.log ( book );

				const [subject, title, author] = book.children;
				console.log ( subject )
				
				
				tbody.innerHTML += `
									<tr>
										<td>\${subject.textContent}</td>
										<td>\${title.textContent}</td>
										<td>\${author.textContent}</td>
									</tr>`;
			})

			},
			error : console.log
		});//
	})
	</script>
	
	<hr /> <br /><br /><br /><br />
	<button id="btn2">셀럽</button>
	<div id="target2">
	<table>
		<thead>
			<tr>
				<th> NO </th>
				<th> PROFILE </th>
				<th> NAME </th>
				<th> TYPE </th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	</div>
	<script> // 셀럽데이터 받을건데 xml 형식으로 받을거임 
	<%--
	document.querySelector("#btn2").addEventListener('click', () => {
		$.ajax({
			url : "<%= request.getContextPath() %>/xml/celeb",
			method : "GET",
			dataType : "xml",
			success(data) {
				console.log(data);
				const celebs = data.querySelectorAll("celeb"); // 유사배열(NodeList)이지만 forEach 사용가능
				// console.log(celebs);
				
				const tbody = document.querySelector("#target2 tbody");
				tbody.innerHTML = "";
				
				celebs.forEach((celeb, index) => {
					console.log(index, celeb);
					const [no, name, profile, type] = celeb.children;
					tbody.innerHTML += `
						<tr>
							<td>\${no.textContent}</td>
							<td>
								<img src="<%= request.getContextPath() %>/images/\${profile.textContent}" alt="" />
							</td>
							<td>\${name.textContent}</td>
							<td>\${type.textContent}</td>
						</tr>
					`;
				});
				
			},
			error : console.log
		});
	});
	--%>
	/////////////
	
	document.querySelector("#btn2").addEventListener('click', ()=>{
		$.ajax({
			url : "<%=request.getContextPath()%>/xml/celeb",
			method : "get",
			dataType : "xml",
			success(data){
				//쌤방식 //
			//	const tcelebs = data.querySelectorAll("celb");
				//유사배열이지만 foreach사용가능 
			//	tcelebs.forEach( (tceleb,index) => {	
			//	})
				
				
				///////////////////////////////////////////////////////
				console.log( data ); //
				
				const root = data.querySelector(":root");
				const celebs = [...root.children];  //얘는 유사배열임 -> 찐배열로 변환 
				console.log( celebs  ); //
				
				const tbody = document.querySelector("#target2 tbody");
				//tbody.innerHTML = ""; // 초기화 
				
				celebs.forEach((celeb, index) => {
					const[no, name, profile, type] = celeb.children; //배열 요소 하나하씩 구조분해할당 
					console.log( no );
					console.log( name );	
						
				tbody.innerHTML += `
									<tr>
					                    <td>\${no.textContent}</td>
					                    <td>\${name.textContent}</td>
					                    <td><img src="<%=request.getContextPath()%>/images/\${profile.textContent}" alt="" /></td>
					                    <td>\${type.textContent}</td>
				                	</tr>`;
			  });
				
						},
			error : console.log
		});			
	});
	
	</script>
	<hr /> <br /><br /><br /><br />
	<input type="date" id="date" />
	<button id="btn3">일별 박스오피스 </button>
	<div id="target3"> 
		<table>
			<thead>
				<tr>
					<th>순위</th>
					<th>영화제목</th>
					<th>누적관객수</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>		
	</div>
	<script>
	/* 
	페이지로딩시어제날짜로 조회 
	- 1) 페이지 로딩시 getDailyBoxOffice 호출
	- 2) 날짜가 세팅되지 않았다면 기본값을 어제날짜로 처리 
	
	*/
	
	//
	window.addEventListener('load', () => {
		getDailyBoxOffice();
	});
	
	
	//날짜 두자리수 만들기 
	const f = (n) => n >= 10 ? n : '0' + n;
	
	
	const getDailyBoxOffice = () => {
	
	
		//1.날짜조회
		const date =  document.querySelector("#date");
		console.log(date.value);
		
		
		// 기본값 어제 날짜
		if(!date.value){
			const now = new Date();
			const yesterday = new Date(now.getFullYear(), now.getMonth(), now.getDate() - 1); // 어제 날짜
			console.log(yesterday);
			console.log( yesterday.toString() );	//yyyy-MM-ddThh:mi:ss.SSSZ UTC - KST + 09:00
			
			//Sun Jan 08 2023 00:00:00 GMT+0900 (한국 표준시)
			// 지역대가 반영된 isoString 을 얻고싶으면 ? 
		
			console.log ( new Date( yesterday.getTime() + (9*60*60*1000)).toISOString());
			date.value = `\${yesterday.getFullYear()}-\${f(yesterday.getMonth()+ 1)}-\${f(yesterday.getDate())}`;
		}
		
		
		
		
		
		//2.영화api요청 
		
		const url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml";
		
		$.ajax({
			url: url,
			method : "get",
			data : {
					key : '9467546caa7d3a100adc054bc6a60cf2',
					targetDt : date.value.replace(/-/g,'')
			},
			success(data){
				console.log( data );
				
				const tbody = document.querySelector("#target3 tbody");
				tbody.innerHTML = "";
				
				const _boxOfficeResult = data.querySelector("dailyBoxOfficeList");
				const boxOfficeResult = [..._boxOfficeResult.children];
			//	const boxOfficeResult = data.querySelectorAll("dailyBoxOfficeList"); // 위 두문장을 한문장으로 합치면 이거 아닌가 ?
					 	
				console.log( "나오니 ?");
				console.log ( boxOfficeResult );				
				boxOfficeResult.forEach( (list, index) => {
					console.log( "list======")
					console.log( index, list);
					console.log( "list======")
					const [,rank,,,,movieNm,,,,,,,,,,audiAcc  ] = list.children;
					console.log( rank );
					tbody.innerHTML += `
								<tr>
									<td>\${rank.textContent}</td>
									<td>\${movieNm.textContent}</td>
									<td>\${audiAcc.textContent}</td>
								</tr>`; 
								
				});//end-foreach문
			},
			error : console.log
		});//end-ajax	
		
		
	}
	

	<%--
	const getDailyBoxOffice = () => {
		// 1. 날짜 조회
		const date = document.querySelector("#date");
		console.log(date.value);
		
		// 기본값 어제 날짜
		if(!date.value){
			const now = new Date();
			const yesterday = new Date(now.getFullYear(), now.getMonth(), now.getDate() - 1); // 어제 날짜
			console.log(yesterday);
			console.log( yesterday.toString() );	//yyyy-MM-ddThh:mi:ss.SSSZ UTC - KST + 09:00
			
			//Sun Jan 08 2023 00:00:00 GMT+0900 (한국 표준시)
			// 지역대가 반영된 isoString 을 얻고싶으면 ? 
		
			console.log ( new Date (yesterday + (9*60*60*1000)).toString()) ;
			date.value = `\${yesterday.getFullYear()}-\${f(yesterday.getMonth()+ 1)}-\${f(yesterday.getDate())}`;
		}
		
		
		// 2. 영화 api 요청
		const url = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml";
		
		$.ajax({
			url : url,
			method : "GET",
			data : {
				key : 'fa2ce7308ff7ef70205103ecc11b5d9c',
				targetDt : date.value.replace(/-/g, '')
			},
			success(data){
				console.log(data);
				const tbody = document.querySelector("#target3 tbody");
				tbody.innerHTML = "";
				
				const dailyBoxOffices = data.querySelectorAll("dailyBoxOffice");
				console.log(dailyBoxOffices);
				dailyBoxOffices.forEach((dailyBoxOffice) => {
					const rank = dailyBoxOffice.children[1];
					const movieNm = dailyBoxOffice.children[5];
					const audiAcc = dailyBoxOffice.children[15];
					console.log(rank, movieNm, audiAcc);
					
					tbody.innerHTML += `
						<tr>
							<td>\${rank.textContent}</td>
							<td>\${movieNm.textContent}</td>
							<td>\${Math.floor(audiAcc.textContent / 10000)}만</td>
						</tr>
					`;
					
				});
				
			},
			error : console.log
		});
	}
		--%>
	
	document.querySelector("#btn3").addEventListener('click', () => {
		getDailyBoxOffice();
	});
		
	</script>
</body>
</html>