<?xml version="1.0" encoding="UTF-8" ?>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.sh.ajax.celeb.dto.Celeb"%>
<%@page import="java.util.List"%>
<%
	List<Celeb> celebList = (List<Celeb>) request.getAttribute("celebList"); 
%>
<celebs>
	<%
		for(int i=0; i<celebList.size(); i++){
			Celeb c = celebList.get(i);
	%>
	<celeb>
		<no><%=c.getNo()%></no>
		<name><%=c.getName()%></name>
		<profile><%=c.getProfile()%></profile>
		<type><%=c.getType()%></type>
	</celeb>
	<%		
		}
	%>
</celebs>

