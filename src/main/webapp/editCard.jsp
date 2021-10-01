<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit info about hospital card</title>
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
	<h3>Edit info about hospital card</h3>
	<form method="post" accept-charset="utf-8">
		<div class="containerSignUp">
			<div class="main">
				<div class="field">
					<input type="hidden" value="${card.hcId}" name="hcId" />
					<p>
						<label for="diagnosis">Diagnosis:</label> <input name="diagnosis"
							value="${card.diagnosis}" class="inPut" />
				</div>
				<p>
				<div class="field">
					<label for="medPrescr">Med_prescription:</label> <input
						name="medPrescription" value="${card.medPrescription}"
						class="inPut" />
				</div>
				<p>
					<div class="field">
					<label for="did">id's doctor:</label><input
						name="dId" value="${card.dId}" class="inPut" />
			</div>
			<input type="submit" value="Send" class="apply-button" />
		</div>
		</div>
	</form>
</body>
</html>