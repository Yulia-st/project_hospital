<%@page import="com.my.entity.Patient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>List of setting up doctors for patients</title>
<link rel="stylesheet" href="demo.css">
<style>
table {
	bgcolor: white;
	width: 1000px; 
	border: 1px solid green; 
	margin: auto; 
}

td, th {
	text-align: center; /* <table border="1" cellpadding="5" cellspacing="5"> */
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
				<a class="apply-button" href="aboutus.jsp">Shedule</a>
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


	<h2>Doctors have been set up for patients</h2>
	<table border="1">
		<caption>LIST OF SETTING UP DOCTORS FOR PATIENTS</caption>
		<tr>
			<th>ID_PATIENT</th>
			<th>ID_DOCTOR</th>
		</tr>
		<c:forEach items="${requestScope.patsDocs}" var="pd">
			<tr>
				<td><c:out value="${pd.pId }"></c:out></td>
				<td><c:out value="${pd.dId }" ></c:out></td>
				<td><a href='<c:out value="${pd.dId }" ></c:out>'></a></td>
				<td><a href='<c:url value="/edit?pId=${pd.pId }" />'><c:out value="${pd.dId }" ></c:out></a></td>
				
				<%-- <td><a href ="/">Edit</a></td>--%>
				<td><a href='<c:url value="/edit?pId=${pd.pId }" />'>Edit</a></td>
				<td>
					<form method="post" action='<c:url value="deleteP" />'
						style="display: inline;">
						<input type="hidden" name="pId" value="${pd.pId}">
						<input type="submit" value="Delete" onclick="return confirm('Delete this connection?')">
					</form>
				</td>
				
			</tr>
		</c:forEach>

	</table>
</body>
</html>