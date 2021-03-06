function checkFormPatient() {
	let fnm = document.getElementById("firstname").value
	let lastnm = document.getElementById("lastname").value
	let usernm = document.getElementById("username").value
	let bday = document.getElementById("birthd").value	
	let password = document.getElementById("psw").value
	let role = document.getElementById("rid").value
	let card = document.getElementById("hid").value
	
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
	if (!bday.match("[1990-01-01]{1}")) {
		alert("Birthday should be chosen!")
		return false
	}
	
	if (!password.match("[A-Za-z0-9]{8,}")) {
		alert("Password should contain at least 8 letters or digits!")
		return false
	}
	
	if (!role.match("[1]{1}")) {
		alert("Choose a number of role 1!")
		return false
	}
	if (!card.match("[0-9]+")) {
		alert("Number of card is 2!")
		return false
	}
	
	return true
}