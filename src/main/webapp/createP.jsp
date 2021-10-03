<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create patient</title>
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
	<h2>Create new patient</h2>
	<form action="add" name="patient" method="post" name="creating"
		onsubmit="return checkFormPatient()">
		<p>
		<div>
			<input name="firstname" type="text" placeholder="firstname" class="inPut" id="firstname" >
		</div>
		<p>
		<div>
			<input name="lastname" type="text" placeholder="lastname" class="inPut" id="lastname" >
		</div>
		<p>
		<div>
			<input name="username" type="text" placeholder="username" class="inPut" id="username" >
		</div>
		<p>
		<div>
			<input name="birthday" type="date" placeholder="birthday" class="inPut" id="birthd" required="required">
		</div>
		<p>
		<div>
			<input name="password" type="text" placeholder="password" class="inPut" id="psw" required="required">
		</div>
		<p>
		<div>
			<input name="rId" type="text" placeholder="number of role" class="inPut" id="rid" required="required">
		</div>
		<p>
	 	<div>
			<input name="hcId" type="text" placeholder="number of h_card" class="inPut" id="hid" >
		</div>
		<p>
		<div>
		<input type="submit" value="Create new Patient" class="apply-button">
		</div>
		
	</form>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/checkNewpat.js"></script>
	
</body>
</html>