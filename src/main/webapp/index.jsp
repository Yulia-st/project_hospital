<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My application</title>
<link rel="stylesheet" href="demo.css">
</head>
<style>
.class1 {
	color: blue;
}

.btn1 {
	width: 120px;
	height: 20px;
	padding: 20px;
	color: white;
	background: #2EE59D;
}

.active {
	background: #524f4e;
}

.disabled {
	background: #2EE59D;
}
</style>
<body>

	<h1>The site of some hospital</h1>

	<div class="containerIndex">

		<div class="containerNavig">
			<div class="itemHome">
				<div>
					<a class="apply-button" href="index.jsp">Home</a>
				</div>
				<div>
					<a class="apply-button" href="aboutus.jsp">About us</a>
				</div>
				<div>
					<a class="apply-button" href="scheduleD.jsp">Schedule</a>
				</div>
				<div>
					<a class="apply-button" href="welcomeDoc.jsp">Employee</a>
				</div>
				<div>
					<a class="apply-button" href="welcome.jsp">Admin</a>
				</div>
			</div>
			<%-- <div><div class="itemWithout"></div>--%>
			<div class="itemWithout"></div>
			<div class="itemWithout"></div>
			<div class="itemLog">
				<div>
					<a class="apply-button" href="login.jsp">Login</a>
				</div>
				<div>
					<a class="apply-button" href="logout">Logout</a>
				</div>
				<div>
					<a class="apply-button" href="signup.jsp">Sign up</a>
				</div>
			</div>
		</div>
		<div class="containerContent">
			<div class="item">
				<a href="https://thearchitect.pro"> <img src="jpg/hosp.jpg"
					alt="hospital of future" width="100" height="100" title="hospital">
				</a>
			</div>
			<div class="item">Learn</div>
			<div class="item">more</div>
			<div class="item">about</div>
			<div class="item">our hospital</div>
		</div>
	</div>


	<p>


		<!--  <script type="text/javascript" src="/scripts/button.js"></script>-->
</body>
</html>
</html>