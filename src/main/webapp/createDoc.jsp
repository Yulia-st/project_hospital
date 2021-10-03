<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head>
<meta charset="UTF-8">
<title>Create doctor</title>
<link rel="stylesheet" href="demo.css">
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
	<h2>Create New Doctor</h2>
	<form action="addDoc" name="doctor" method="post" name="createDoc"
		onsubmit="return checkFormDoc()">
		<div>
			<input hidden name="dId" value="${doctor.dId}">
		</div>
		<p>
		<div>
			<input name="firstname" type="text" placeholder="firstname" class="inPut" required="required" id="firstname">
		</div>
		<p>
		<div>
			<input name="lastname" type="text" placeholder="lastname" class="inPut" required="required" id="lastname">
		</div>
		<p>
		<div>
			<input name="username" type="text" placeholder="username" class="inPut" required="required" id="username">
		</div>
		<p>
		<div>
			<input name="cathegory" type="text" placeholder="cathegory" class="inPut" required="required" id="cat">
		</div>
		<p>
		<div>
			<input name="password" type="password" placeholder="password" class="inPut" required="required" id="psw">
		</div>
		<p>
		<div>
			<input name="rId" type="number" placeholder="number of role" class="inPut" required="required" id="rid">
		</div>
		<p>
		<input type="submit" value="Create new Doctor" class="apply-button">
	</form>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/checkNewDoc.js"></script>
	
</body>
</html>