<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax</title>
</head>
<body>
	<h1>Ajax</h1>
	
	<h2>Javascript</h2>
	<a href="<%=request.getContextPath()%>/javascript/js.jsp">js로 XMLHttpRequest객체 직접 제어하기 </a>
	
	<h2>J-Query Ajax</h2>
	<h3>1)text</h3>
	<ul>
		<li><a href="<%=request.getContextPath()%>/text/text.jsp">text</a></li>
		<li><a href="<%=request.getContextPath()%>/text/autocomplete.jsp"> jQueryUI - autocomplete  </a></li>
		
	</ul>
	
	
	<h3>2)html</h3>
	<ul>
		<li><a href="<%=request.getContextPath()%>/html/html.jsp">html</a></li>
	</ul>
	
	
	<h3>3)xml</h3>
	<ul>
		<li><a href="<%=request.getContextPath()%>/xml/xml.jsp"> xml </a></li>
	</ul>
	
	<h3>4)json(0110) - crud 다 할거임 </h3>
	<ul>
		<li><a href="<%=request.getContextPath()%>/json/json.jsp"> json </a></li>
	</ul>
	
</body>
</html>