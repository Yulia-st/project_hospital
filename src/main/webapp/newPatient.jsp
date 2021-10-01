<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Patient</title>
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
<h2>Create New Patient</h2>
	<form action="create" name="note" method="post">
		<div>
			<input hidden name="p_id" type="text" placeholder="p_id">
		</div>
		<div>
			<input name="username" type="text" placeholder="username" class="inPut">
		</div>
		<br>
		<div>
			<input name="birthday" type="date" placeholder="birthday" class="inPut">
		</div>
		<br>
		<div>
			<input name="password" type="password" placeholder="password" class="inPut">
		</div>
		<br>
		<div>
			<input name="r_id" type="text" placeholder="number of role" class="inPut">
		</div>
		<br>
		<div>
			<input name="hc_id" type="text" placeholder="number of hospital card" class="inPut">
		</div>
		<br>
		<div>
			<textarea name="message" placeholder="message"></textarea>
		</div>
		<br>
		<input type="submit" value="Create" class="apply-button">
	</form>
</body>
</html>