<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	if (session.getAttribute("username") == null) {
		response.sendRedirect("login.jsp");
	}
	%>
	
	<!--  https://www.youtube.com/watch?v=WXmTxgDg52o-->
	<iframe width="560" height="315" src="http://www.youtube.com/embed/cYc3FjhMMzI" frameborder="0" allowfullscreen></iframe>
</body>
</html>