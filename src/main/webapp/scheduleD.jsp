<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Schedule of doctors</title>
<link rel="stylesheet" href="demo.css">
<style>
table {
	bgcolor: white;
	width: 1400px;
	border: 1px solid green;
	margin: auto;
}

td, th {
	text-align: center;
}
</style>
</head>
<div class="containerNavig">
	<div class="itemHome">
		<div>
			<a class="apply-button" href="index.jsp">Home</a>
		</div>
		<div>
			<a class="apply-button" href="scheduleD.jsp">Doctor</a>
		</div>
		<div>
			<a class="apply-button" href="welcome.jsp">Admin</a>
		</div>
	</div>
	<div class="itemWithout"></div>
	<div class="itemWithout"></div>
	<div class="itemLog">
		<%-- 
			<div>
				<a class="apply-button" href="logout">Logout</a>
			</div>--%>
	</div>
</div>
<body>
	<h2>Information about schedule of doctors</h2>
	<table border="1">
		<caption>SCHEDULE OF DOCTORS</caption>
		<tr>
			<th>ID</th>
			<th bgcolor="lightgreen">Firstname</th>
			<th bgcolor="lightgreen">Lastname</th>
			<th>Category</th>
			<th>Mon</th>
			<th bgcolor="lightgreen">Tue</th>
			<th>Wed</th>
			<th bgcolor="lightgreen">Thu</th>
			<th>Fri</th>
			<th bgcolor="lightgreen">Sat</th>
			<th>Sun</th>
		</tr>
		<tr>
			<td>1</td>
			<td>Anna</td>
			<td>Ivanova</td>
			<td>Therapist</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>day off</td>
		</tr>
		<tr>
			<td>2</td>
			<td>Irina</td>
			<td>Vetrova</td>
			<td>Dentist</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>day off</td>
		</tr>
		<tr>
			<td>3</td>
			<td>Mike</td>
			<td>Tyson</td>
			<td>Traumatologist</td>
			<td>8:00-13:00</td>
			<td>12:00-18:00</td>
			<td>8:00-13:00</td>
			<td>12:00-18:00</td>
			<td>8:00-13:00</td>
			<td>12:00-13:00</td>
			<td>day off</td>
		</tr>
		<tr>
			<td>4</td>
			<td>Naomi</td>
			<td>Ivanova</td>
			<td>Psychatrist</td>
			<td>8:00-13:00</td>
			<td>12:00-18:00</td>
			<td>8:00-13:00</td>
			<td>12:00-18:00</td>
			<td>8:00-13:00</td>
			<td>12:00-13:00</td>
			<td>day off</td>
		</tr>
		<tr>
			<td>5</td>
			<td>Kate</td>
			<td>Moss</td>
			<td>Pediatrist</td>
			<td>8:00-13:00</td>
			<td>12:00-18:00</td>
			<td>8:00-13:00</td>
			<td>12:00-18:00</td>
			<td>8:00-13:00</td>
			<td>12:00-13:00</td>
			<td>day off</td>
		</tr>
		<tr>
			<td>6</td>
			<td>Serg</td>
			<td>Bezrukov</td>
			<td>Dermatologist</td>
			<td>8:00-13:00</td>
			<td>12:00-18:00</td>
			<td>8:00-13:00</td>
			<td>12:00-18:00</td>
			<td>8:00-13:00</td>
			<td>12:00-13:00</td>
			<td>day off</td>
		</tr>
		<tr>
			<td>7</td>
			<td>Konstantin</td>
			<td>Sidorov</td>
			<td>Allergist</td>
			<td>8:00-13:00</td>
			<td>12:00-18:00</td>
			<td>8:00-13:00</td>
			<td>12:00-18:00</td>
			<td>8:00-13:00</td>
			<td>12:00-13:00</td>
			<td>day off</td>
		</tr>
		<tr>
			<td>8</td>
			<td>Alexey</td>
			<td>Radionov</td>
			<td>Microbiologist</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>day off</td>
		</tr>
		<tr>
			<td>9</td>
			<td>Zina</td>
			<td>Krilova</td>
			<td>Anaesthesiologist</td>
			<td>8:00-13:00</td>
			<td>12:00-18:00</td>
			<td>8:00-13:00</td>
			<td>12:00-18:00</td>
			<td>8:00-13:00</td>
			<td>12:00-13:00</td>
			<td>day off</td>
		</tr>
		<tr>
			<td>10</td>
			<td>Gleb</td>
			<td>Ford</td>
			<td>Sergeon</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>day off</td>
		</tr>
		<tr>
			<td>11</td>
			<td>Harry</td>
			<td>Simonov</td>
			<td>Cardiologist</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>8:00-18:00</td>
			<td>day off</td>
		</tr>
	</table>
</body>
</html>