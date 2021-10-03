<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>
<link rel="stylesheet" type="text/css" href="demo.css">
<%
if (session.getAttribute("error") != null) {
%>
<script type="text/javascript">
	alert("Invalid Username or Password");
</script>

<%
session.removeAttribute("error");
}
%>

</head>
<body>
	<h1>Please, sign in!</h1>

	<!--  <form action="login" method="get">-->
	<form action="login" method="post" class="containerLogIn">
		<div>
			Enter username: <input type="text" name="username"
				placeholder="Enter username" autofocus class="inPut" required="required"><br>
			<!-- <input name="command" type="hidden" value="Login"> -->

		</div>
		<p>
		<div>
			Enter password: <input type="password" name="pass"
				placeholder="Enter your password" class="inPut" required="required"><br>

		</div>
		<p>
		<div id="error"></div>

		<div>
			<input type="submit" value="Sign in" class="apply-button">
		</div>

	</form>

</body>
</html>