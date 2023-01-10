<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> json </title>
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
	<h1> json(0110) </h1>
	<button id="btn1"> 전체조회(celeb) </button>
	<div id="target1"> 
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
		
		<script>
		window.addEventListener('load', () => {
			findAll();	
		})
		
		document.querySelector("#btn1").addEventListener('click', () => {
			findAll();	
		});
		
		const findAll = () => {
			$.ajax({
				url : "<%=request.getContextPath()%>/json/celeb/findAll",
				dataType :"json",
				success(data){
					console.log( data ); // 이렇게 나온 값은 js체임 (json string 아님 ) 
					
					// html 태그를 문자열이 아닌 js태그객체로 만들어,,? 
					const tr = document.createElement("tr")//<tr></tr>
					const td = document.createElement("td"); //<td></td>
					
					const tbody = document.querySelector("#target1 tbody")
					tbody.innerHTML = "";
					
					data.forEach( (celeb, index) => {
						
						console.log( celeb.type );
						console.log( celeb.name );
						const tr = document.createElement("tr")//<tr></tr>
						
						
						tr.onclick = () => {
							findOne(celeb.no);
						}

						const tdNo = document.createElement("td"); //<td></td>
						// append는 node 혹은 text를 인자로 받는다 
						tdNo.append(celeb.no);
						
						
						const tdProfile = document.createElement("td"); //<td></td>
						const img = document.createElement("img");
						img.src = "<%=request.getContextPath()%>/images/"+celeb.profile;
						tdProfile.append(img);
						
						
						const tdName = document.createElement("td"); //<td></td>
						tdName.append(celeb.name);
						

						const tdType = document.createElement("td"); //<td></td>
						tdType.append(celeb.type);
						
						tr.append(tdNo, tdProfile, tdName, tdType  );
						tbody.appendChild(tr);

					});
				
					
					// {} => tr>td
					
				},
				error : console.log 
			});//end-ajax		
		}
		
	const findOne = (no) => {
		console.log(no);
		
		$.ajax({
			url : "<%=request.getContextPath()%>/json/celeb/findOne",
			data : { no },
			success(data){
				console.log(data);
				const {no, name, profile, type} = data;
				const frm = document.celebUpdateFrm;
				frm.no.value  = no;
				frm.name.value = name; 
				frm.type.value = type;
				frm.querySelector("#celeb-update-profile-viewer").src = `<%=request.getContextPath()%>/images/\${profile}`;
			},
			error : console.log
		});
	}
	
		
	</script>
   <form name="celebUpdateFrm">
        <fieldset>
            <legend>Celeb 수정폼</legend>
            <table>
                <tbody>
                    <tr>
                        <th>
                            <label for="celeb-update-no">No</label>
                        </th>
                        <td>
                            <input type="text" name="no" id="celeb-update-no" readonly/>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <label for="celeb-update-name">Name</label>
                        </th>
                        <td>
                            <input type="text" name="name" id="celeb-update-name" />
                        </td>
                    </tr>
                        <tr>
			        <th>
			            <label for="celeb-update-profile">Profile</label>
			        </th>
			        <td>
			            <div>
			                <img src="" alt="" id="celeb-update-profile-viewer"/>
			            </div>
			            <input type="file" name="profile" id="celeb-update-profile" />
			        </td>
			    </tr>
                    <tr>
                        <th>
                            <label for="celeb-update-type">Type</label>
                        </th>
                        <td>
                            <select name="type" id="celeb-update-type">
                                <option value="ACTOR">ACTOR</option>
                                <option value="SINGER">SINGER</option>
                                <option value="MODEL">MODEL</option>
                                <option value="COMEDIAN">COMEDIAN</option>
                                <option value="ENTERTAINER">ENTERTAINER</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <button >수정</button>
                        </td>
                    </tr>
                </tbody>
            </table>        
        </fieldset>
    </form> 
    <script>
    //수정홈 
    document.celebUpdateFrm.addEventListener('submit', (e) => {
    	e.preventDefault(); //폼제출방지 (제일 첫줄에 )
    	// 이벤트객체는 이폼자체 ? 
    	
    	//formData 객체 생성 - 프로필사진가져올거야 ★ 
    	const frmData = new FormData(e.target);
    	const keys = frmData.keys();

    	console.log ( e.target );
    	
    	//수정 ajaxs - post 
    	$.ajax({
    		url : "<%=request.getContextPath()%>/json/celeb/update",
    		method : "post",
    		data : frmData,
    		dataType : "json",
    		contentType : false,
    		processData : false,
    		success(data){
    			console.log(data);
    			alert( data.result );
    		},
    		error : console.log,
    		complete(){
    			e.target.reset();
    		}
    	})//end-ajax
		
	});
    </script>
    
    
	<h2> Celeb 등록 - 비동기로할때는 form 제출하면안됨  </h2>
	<form name="celebEnrollFrm" >
	<fieldset>
		<legend>Celeb 등록폼</legend>
		<table>
			<tbody>
				<tr>
					<th>
						<label for="celeb-enroll-name">Name</label>
					</th>
					<td>
						<input type="text" name="name" id="celeb-enroll-name"  required/>
					</td>
				</tr>
				<tr>
					<th>
						<label for="celeb-enroll-type">Type</label>
					</th>
					<td>
						<select name="type" id="celeb-enroll-type" required >
							<option value="ACTOR">ACTOR</option>
							<option value="SINGER">SINGER</option>
							<option value="MODEL">MODEL</option>
							<option value="COMEDIAN">COMEDIAN</option>
							<option value="ENTERTAINER">ENTERTAINER</option>
						</select>
					</td>
				</tr>
				<tr>  <!-- 	file 비동기로 할거임  -->
					<th>
						<label for="celeb-enroll-profile">Profile</label>
					</th>
					<td>
						<input type="file" name="profile" id="celeb-enroll-profile" />
					</td>
				</tr>
				 
 				<tr>
					<td colspan="2">
						<button type="submit">등록</button>
					</td>
				</tr>
			</tbody>
		</table>		
	</fieldset>
</form>
<script>
/** 파일업로드가 포함된 post 요청 
  - formdata 객체 사용 

  - ajax 호출시 하기 두가지속성을 추가해야된다 
  - contentType : false //form enc타입왜쓰더라? 첨부파일떄문인가 ? // application/x-www-form-urlencoded 기본값을 사용하지않음 -> multipart/form-data 사용한다는뜻? 
  - processData : false // 전송하는 데이터를 직렬화 하지않겠다는 뜻 
  
 */

document.celebEnrollFrm.addEventListener('submit', (e) => {
	e.preventDefault(); //폼제출방지 (제일 첫줄에 )
	// 이벤트객체는 이폼자체 ? 
	
	//formData 객체 생성 - 프로필사진가져올거야 ★ 
	const frmData = new FormData(e.target);
	const keys = frmData.keys();
	
	// for(let key of keys){
	//	console.log( key, frmData.get(key) );
	//}
	
	
			
			
	//등록 post
	$.ajax({
		url : "<%=request.getContextPath()%>/json/celeb/enroll",
		method : "post",
		data : frmData,
//		data : {
//			name : e.target.name.value,
//			type : e.target.type.value
//		},
		dataType : "json",
		contentType : false,
		processData : false,
		success(data){
			console.log(data);
			alert(data.result);
		},
		error : console.log,
		complete(){
			e.target.reset();
		}
	});
});

</script>
</body>
</html>