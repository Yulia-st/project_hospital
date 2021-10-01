function checkForm() {
	
	
	let fnm = document.getElementById("firstname").value
	let lastnm = document.getElementById("lastname").value
	let usernm = document.getElementById("username").value
		
	let password = document.getElementById("psw").value
		
	let role = document.getElementById("rid").value
	
	if (!fnm.match("[А-Яа-я]+||[a-zA-Z]+")) {
		alert("Firstname should contain at least 1 letter!")
		return false
	}
	if (!lastnm.match("[A-Za-z]{1,}|[А-Яа-я]+")) {
		alert("Lastname should contain at least 1 letter!")
		return false
	}
	if (!usernm.match("[A-Za-z0-9]{1,}")) {
		alert("Username should contain at least 1 letter!")
		return false
	}
	
	if (!password.match("[A-Za-z0-9]{8,}")) {
		alert("Password should contain at least 8 letters or digits!")
		return false
	}
	
	if (!role.match("[2]{1}")) {
		alert("Number of role is 2!")
		return false
	}
	
	
	return true
}