<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns:th="https://www.thymeleaf.org">
<head th:fragment="headerfile">
<!--  <head> -->
<meta charset="UTF-8">
<meta name="google-signin-client_id" content="625784736725-iagt04hua7qsd2536cdch1udfojcj3u4.apps.googleusercontent.com">
<title>My application</title>

<link rel="stylesheet" href="demo.css">

</head>
<style>
.class1{
color: blue;
}

.btn1{
width: 120px;
height: 20px;
padding: 20px;
color: white;
background: #2EE59D;
}

.active{
background: #524f4e;
}

.disabled{
background: #2EE59D;
}
</style>
<body>
 <!--  <div class="menu" th:fragment="menu"></div>-->
	<!-- <div> -->
		<h1>This is home page</h1>
<!--   <h1 th:text="${message}"></h1> Ð´Ð»Ñ Ð¾ÑÐ¾Ð±ÑÐ°Ð¶ÐµÐ½Ð¸Ñ ÑÑÑÐ¾ÐºÐ¸ - hello, Ð¸Ð¼Ñ usera-->
 
	<!-- </div>-->


	<div class="containerIndex">

		<div class="containerNavig" th:fragment="menu">

			<div class="itemHome">
				<div>
					<a class="apply-button" href="/index">Home</a>
				</div>

				<div sec:authorize="isAuthenticated()">
				<!--<div>-->
					<a class="apply-button" href="/doctors">Doctors</a>
				</div>
				
				<div sec:authorize="isAuthenticated()">
				<!--<div>-->
					<a class="apply-button" href="/users">Users</a>
				</div>

				<div sec:authorize="hasRole('ROLE_ADMIN')">
					<a class="apply-button" href="/admin_page">Admin page</a>
				</div>
			</div>
			<div class="itemWithout"></div>
			<div class="itemWithout"></div>
			<div class="itemWithout"></div>

			<div class="itemLog">
				
				<div >
				<!--  <div sec:authorize="isAnonymous()">-->
					<a class="apply-button" href="login.jsp">Login</a>
				</div>
				<div sec:authorize="isAuthenticated()">
				<!--<div>-->
					<a class="apply-button" href="/logout">Logout</a>
				</div>
				<div sec:authorize="isAnonymous()">
					<a class="apply-button" href="/signup">Sign up</a>
				</div>
				
			</div>
		</div>
		<div class="containerContent" >
			<div class="item">
				<a href="https://www.java.com/en/about/javamagazine.jsp"> <img
					src="javva.png" alt="programming language"
					width="100" height="100" title="java">
				</a>
			</div>
			<div class="item">Learn</div>
			<div class="item">more</div>
			<div class="item">about</div>
			<div class="item">java</div>
		</div>
	</div>
	
	
<p>


<!--  <script type="text/javascript" src="/scripts/button.js"></script>-->

</body>
</html>
</html>
