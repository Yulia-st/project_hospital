<%@ page contentType="text/html; charset=UTF-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/greetingTag.tld" prefix="simpletag"%>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome page for a doctor</title>
<link rel="stylesheet" href="demo.css">
<style>
table {
	bgcolor: white;
	width: 1000px;
	border: 1px solid green;
	margin: auto;
}

td, th {
	text-align: center;
	/* <table border="1" cellpadding="5" cellspacing="5"> */
}
</style>
</head>
<body>
	<div class="containerNavig">

		<div class="itemHome">
			<div>
				<a class="apply-button" href="index.jsp">Home</a>
			</div>
			<div>
				<a class="apply-button" href="scheduleD.jsp">Doctors</a>
			</div>
			<div>
				<a class="apply-button" href="welcome.jsp">Admin</a>
			</div>
		</div>
		<div class="itemWithout"></div>
		<div class="itemWithout"></div>
		<div class="itemLog">
			<div>
				<%-- <a class="apply-button" href="enterIdPat.jsp">Info about Patient</a>--%>
			</div>
			<div>
				<a class="apply-button" href="logout">Logout</a>
			</div>
		</div>
	</div>
	<br>
	<br>
	<h1>This page is a doctor's page</h1>

	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");//http1.1
	response.setHeader("Pragma", "no-cache");//http 1.0
	response.setHeader("Expires", "0");//proxies

	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}
	%>
	<p>
		<simpletag:greeting />
		<c:out value="${username}" />
		, today is

		<jsp:useBean id="now" class="java.util.Date" scope="page" />

		<fmt:formatDate type="time" value="${now}"
			pattern="dd.MM.yyyy HH:mm:ss" />
		<br> <br>
	<table>
		<tr>
			<td><form action="list">
					<input type="submit" value="Show patients' list"
						class="apply-button">
				</form></td>
			
		</tr>
		<tr>
			<td><form action="listDoc">
					<input type="submit" value="Show doctors' list"
						class="apply-button">
				</form></td>
			
		</tr>
		<tr>
			<td><form action="listCard">
					<input type="submit" value="Show cards' list" class="apply-button">
				</form></td>
			<td></td>
		</tr>
		<tr>
			<td><form action="listSetUpDoc">
					<input type="submit" value="Show patient-doctor"
						class="apply-button">
				</form></td>

			<td>
				<form action="setUpDoc">
					<input type="submit" value="Set up doctor" class="apply-button">
				</form>
			</td>
		</tr>
	</table>

	<br>
	<form action="logout">
		<input type="submit" value="logout" class="apply-button">
	</form>
</body>
</html>