<%@page import="com.my.entity.Doctor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html, charset=UTF-8">
<title>Info about Doctor</title>
<link rel="stylesheet" href="demo.css">
</head>
<body bgcolor="cyan">
	<h2>Show info about Doctor</h2>
	<p>

		<%
		Doctor doc1 = (Doctor) session.getAttribute("doctor");
				out.println(doc1);
		%>
	
</body>
</html>