<%@page import="com.my.entity.Doctor"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>List of doctors</title>
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
	<h1>Doctors</h1>
	<table>
		<tr>
			<th><form action="sortNameDoc">
					<input type="submit" value="Sort by lastname" class="apply-button">
				</form></th>
			<th><form action="sortCategory">
					<input type="submit" value="Sort by category" class="apply-button">
				</form></th>
			<th><form action="sortQuantity">
					<input type="submit" value="Quantity of patients" class="apply-button">
				</form></th>
		</tr>
		<br>
		<%-- 	<form action="sortName">
			<input type="submit" value="Sort by name" class="apply-button">
		</form>--%>
		<br>
		<br>
	</table>
	<p>
		<br>
	<table border="1">
		<caption>LIST OF DOCTORS</caption>
		<tr>
			<th>ID</th>
			<th>Firstname</th>
			<th>Lastname</th>
			<th>Username</th>
			<th>Category</th>
			<th>Password</th>
			<th>ID_Role</th>
			<%-- <th>Id_HC</th>--%>
		</tr>
		<c:forEach items="${requestScope.doctors}" var="doctor">
			<tr>
				<td><c:out value="${doctor.dId }"></c:out></td>
				<td><c:out value="${doctor.firstname }"></c:out></td>
				<td><c:out value="${doctor.lastname }"></c:out></td>
				<td><c:out value="${doctor.username }"></c:out></td>
				<td><c:out value="${doctor.category }"></c:out></td>
				<td><c:out value="${doctor.password }"></c:out></td>
				<td><c:out value="${doctor.rId }"></c:out></td>
				<%--<td><c:out value="${doctor.hc_id }"></c:out></td>--%>
				<td><a href='<c:url value="/editD?dId=${doctor.dId }" />'>Edit</a></td>
				<td>
					<form method="post" action='<c:url value="deleteDoc" />'
						style="display: inline;">
						<input type="hidden" name="dId" value="${doctor.dId}">
						<input type="submit" value="Delete" onclick="return confirm('Delete this doctor?')">
					</form>
				</td>
			</tr>
		</c:forEach>

	</table>
	
	<br>
	<br>
	<%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="listDoc?page=${currentPage - 1}">Previous</a></td>
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
                        <td><a href="listDoc?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
     
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="listDoc?page=${currentPage + 1}">Next</a></td>
    </c:if>
</body>
</html>