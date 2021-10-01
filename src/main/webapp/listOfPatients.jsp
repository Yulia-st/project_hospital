<%@page import="com.my.entity.Patient"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>List of patients</title>
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


	<h1>PATIENTS</h1>

	<table>

		<tr>
			<th><form action="sortName">
					<input type="submit" value="Sort by lastname" class="apply-button">
				</form></th>
			<th><form action="sortBirthday">
					<input type="submit" value="Sort by birthday" class="apply-button">
				</form></th>
		</tr>
	</table>
	<p>
	
	<h3>Info about Patient,enter ID</h3>
<form action="info"  method="get">

<input type="text" name="id">
<input type="submit"  value="Show">

</form>
	<table border="1">
		<caption>LIST OF PATIENTS</caption>
		<tr>
			<th>ID</th>
			<th>Firstname</th>
			<th>Lastname</th>
			<th>Username</th>
			<th>Birthday</th>
			<th>Password</th>
			<th>ID_Role</th>
			<th>ID_HospCard</th>
		</tr>
		<c:forEach items="${requestScope.patients}" var="patient">
			<tr>
				<td><c:out value="${patient.pId }"></c:out></td>
				<td><c:out value="${patient.firstname }"></c:out></td>
				<td><c:out value="${patient.lastname }"></c:out></td>
				<td><c:out value="${patient.username }"></c:out></td>
				<td><c:out value="${patient.birthday }"></c:out></td>
				<td><c:out value="${patient.password }"></c:out></td>
				<td><c:out value="${patient.rId }"></c:out></td>
				<td><c:out value="${patient.hcId }"></c:out></td>
				<td><a href='<c:url value="/edit?pId=${patient.pId }" />'>Edit</a></td>
				<td>
					<form method="post" action='<c:url value="deleteP" />'
						style="display: inline;">
						<input type="hidden" name="pId" value="${patient.pId}">
						<input type="submit" value="Delete" onclick="return confirm('Delete this patient?')">
					</form>
				</td>
				<td><a href='<c:url value="/setUpDoc?pId=${patient.pId }" />'>Set up a doctor</a></td>
			</tr>
		</c:forEach>

	</table>
	<br>
	<br>
	<%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="list?page=${currentPage - 1}">Previous</a></td>
    </c:if>
	
	    <%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="list?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
     
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="list?page=${currentPage + 1}">Next</a></td>
    </c:if>
	
	
</body>
</html>