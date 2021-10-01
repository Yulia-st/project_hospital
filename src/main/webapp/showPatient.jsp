<%@page import="com.my.entity.Patient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html, charset=UTF-8">
<title>Info about Patient</title>
<link rel="stylesheet" href="demo.css">
</head>
<body bgcolor="cyan">
	<h2>Show info about patient</h2>
	<p>

		<%
		Patient p1 = (Patient) session.getAttribute("patient");
		out.println(p1);
		%>
	
</body>
</html>