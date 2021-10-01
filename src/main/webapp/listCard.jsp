<%@page import="com.my.entity.HospitalCard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of cards</title>
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
				<a class="apply-button" href="scheduleD.jsp">Shedule</a>
			</div>
			<div>
				<a class="apply-button" href="welcome.jsp">Admin</a>
			</div>
		</div>
		<div class="itemWithout"></div>
		<div class="itemWithout"></div>
		<div class="itemLog">
			<div>
				<a class="apply-button" href="logout">Logout</a>
			</div>
		</div>
	</div>
	<h1>HOSPITAL CARDS OF PATIENTS</h1>
	<p>
	<h3>Info about Patient,enter ID</h3>
	<form action="info" method="get">
		<input type="text" name="id"> <input type="submit"
			value="Show"> <br> <br>
	</form>
	<table border="1">
		<caption>LIST OF HOSPITAL CARDS</caption>
		<tr>
			<th>ID</th>
			<th>Diagnosis</th>
			<th>Med_prescription</th>
			<th>ID of doctor</th>
		</tr>
		<c:forEach items="${requestScope.cards}" var="card">
			<tr>
				<td><c:out value="${card.hcId }"></c:out></td>
				<td><c:out value="${card.diagnosis }"></c:out></td>
				<td><c:out value="${card.medPrescription }"></c:out></td>
				<td><c:out value="${card.dId }"></c:out></td>
				<td><a href='<c:url value="/editCard?hcId=${card.hcId }" />'>Edit</a></td>
				<td>
					<form method="post" action='<c:url value="deleteC" />'
						style="display: inline;">
						<input type="hidden" name="hcId" value="${card.hcId}"> <input
							type="submit" value="Delete" onclick="return confirm('Delete this card?')">
					</form>
				</td>
				<td>
					<form method="post" action='<c:url value="dischargeP" />'
						style="display: inline;">
						<input type="hidden" name="hcId" value="${card.hcId}"> <input
							type="submit" value="Discharge">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>