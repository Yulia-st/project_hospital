<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="demo.css">
<title>SignUp Page</title>

</head>
<body>

	<h1>Please sign up!</h1>
	<%
	String login_msg = (String) request.getAttribute("errorExistUser");
	if (login_msg != null)

		out.println("<font color=red size=6px>" + login_msg + "</font>");
	%>
	<p>
	<form action="signup" method="post" name="sign"
		onsubmit="return checkForm()">
		<div class="containerSignUp">
			<div class="main">
				<div class="field">

					<label for="firstname">Firstname:</label> <input type="text"
						name="firstname" placeholder="At least 1 letter" autofocus
						class="inPut" id="firstname">
				</div>
				<p>
				<div class="field">
					<label for="lastname">Lastname:</label> <input type="text"
						name="lastname" placeholder="At least 1 letter" class="inPut"
						required="required" id="lastname">
				</div>
				<p>
				<div class="field">
					<label for="username">Username:</label> <input type="text"
						name="username" placeholder="At least 1 letter" class="inPut"
						required="required" id="username">
				</div>

				<p>
				<div class="field">
					<label for="password">Password:</label> <input type="password"
						name="password" placeholder="8 letter or digits" class="inPut"
						required="required" id="psw">
				</div>
				<p>
				<div class="field">
					<label for="rid">Role's number:</label> <input type="text"
						name="rid" placeholder="number of role" class="inPut"
						required="required" id="rid">
				</div>
				<p>
				<div>
					<input type="submit" value="Sign up" class="apply-button">
				</div>
				<p>
			</div>
		</div>
	</form>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/scripts/check.js"></script>
	<p>
		Do you have an account? <a href="login.jsp"> Sign in</a>
</body>
</html>