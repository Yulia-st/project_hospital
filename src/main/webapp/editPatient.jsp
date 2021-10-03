<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>

<meta charset="UTF-8">
<title>Edit info about patient</title>
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
	<h3>Edit info about patient</h3>
	<form method="post" accept-charset="utf-8">
		<div class="containerSignUp">
			<div class="main">
				<div class="field">
					<input type="hidden" value="${patient.pId}" name="pId" />
					<p>
						<label for="firstname">Firstname:</label> <input name="firstname"
							value="${patient.firstname}" class="inPut" required="required" />
				</div>
				<p>
				<div class="field">
					<label for="lastname">Lastname:</label> <input name="lastname"
						value="${patient.lastname}" class="inPut" required="required" />

				</div>
				<p>
				<div class="field">
					<label for="username">Username:</label> <input name="username"
						value="${patient.username}" class="inPut" required="required" />

				</div>
				<p>
				<div class="field">
					<label for="birthday">Birthday:</label> <input name="birthday"
						value="${patient.birthday}" class="inPut" required="required" />

				</div>
				<p>
				<div class="field">
					<label for="psw">Password:</label> <input name="password"
						value="${patient.password}" class="inPut" required="required" />

				</div>
				<p>
				<div class="field">
					<label for="rid">Role's number:</label> <input name="rId"
						value="${patient.rId}" class="inPut" required="required" />

				</div>
				<p>
				<div class="field">
					<label for="hcid">H_C's number:</label><input name="hcId"
						value="${patient.hcId}" class="inPut" required="required" />

				</div>
				<p>
					<input type="submit" value="Send" class="apply-button" />
			</div>
		</div>
	</form>
	
	<p>
</body>
</html>