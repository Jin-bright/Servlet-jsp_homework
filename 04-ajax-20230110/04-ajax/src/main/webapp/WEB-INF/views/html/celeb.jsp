<%@page import="com.sh.ajax.celeb.dto.Celeb"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Celeb> celebList = (List<Celeb>) request.getAttribute("celebList");
%>

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
<%
 if(! celebList.isEmpty()){
	 for(Celeb celeb : celebList ){
%>

	<tr>
		<td><%=celeb.getNo()%></td>
		<td><img src="<%=request.getContextPath()%>/images/<%=celeb.getProfile()%>" alt="" /></td>
		<td><%=celeb.getName()%></td>
		<td><%=celeb.getType()%></td>
	</tr>
<%
	 }
 } else {
%>
	<tr>
		<td colspan = "4"> 조회된 데이터가 없습니다 </td>
	</tr>

<%
  }
%>
</tbody>

</table>