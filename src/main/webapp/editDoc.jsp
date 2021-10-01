<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit info about doctor</title>
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
	<h3>Edit info about doctor</h3>
	<form method="post" accept-charset="utf-8">
		<div class="containerSignUp">
			<div class="main">
				<div class="field">
					<input type="hidden" value="${doctor.dId}" name="dId" />
					<p>
						<label for="firstname">Firstname:</label> <input name="firstname"
							value="${doctor.firstname}" class="inPut" />
				</div>
				<p>
				<div class="field">
					<label for="lastname">Lastname:</label> <input name="lastname"
						value="${doctor.lastname}" class="inPut" />

				</div>
				<p>
				<div class="field">
					<label for="lusername">Username:</label> <input name="username"
						value="${doctor.username}" class="inPut" />

				</div>
				<p>
				<div class="field">
					<label for="category">Category:</label> <input name="category"
						value="${doctor.category}" class="inPut" />

				</div>
				<p>
				<div class="field">
					<label for="psw">Password:</label>
					 <input name="password" value="${doctor.password}" class="inPut" />

				</div>
				<p>
				<div class="field">
					<label for="rid">Role's number:</label> <input name="rId"
						value="${doctor.rId}" class="inPut" />

				</div>
				<p>
				<%-- <div class="field">
					<label for="hcid">H_C's number:</label><input
						name="hc_id" value="${category.hc_id}" class="inPut" />--%>

				</div>
				
					<input type="submit" value="Send" class="apply-button" />
			</div>
		</div>
	</form>
</body>
</html>