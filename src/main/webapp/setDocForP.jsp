<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SetUpDoc title here</title>
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
	<h2>Set up the doctor for the patient</h2>

	<h3>Info about Patient,enter ID</h3>
	<form action="info" method="get">

		<input type="text" name="id"> <input type="submit" 
			value="Show info">

	</form>

	<h3>Info about Doctor,enter ID</h3>
	<form action="infoDoc" method="get">

		<input type="text" name="id"> <input type="submit" 
			value="Show info">

	</form>
	<br>
	<form method="post">
		<div class="containerSignUp">
			<div class="main">
				<div class="field">
					<label for="pId">ID's patient:</label> <input type="text"
						value="${patdoc.p_id}" name="pId" class="inPut" placeholder="enter ID's patient"/>
				</div>
				<p>
				<div class="field">
					<label for="dId">ID's doctor:</label> <input type="text" 
						value="${patdoc.d_id}" name="dId" class="inPut" placeholder="enter ID's doctor"/>
				</div>
				<p>
			</div>
			<p>
				<input type="submit" value="Send" class="apply-button" />
		</div>
		</div>
	</form>
</body>
</html>