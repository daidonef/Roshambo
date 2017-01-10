<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Login</h1>
<p>
${wrongLogin }
<form name="login" onsubmit="return validation()" action="profile" method="post">

<br>Username: <input type="text" name="userName">
<br>Password: <input type="password" name="password">

<br><br><input type="submit" value="Login">
</form>
</p>

<script>
function validation() {
	var firstName = document.forms["login"]["firstName"].value;
	var lastName = document.forms["login"]["lastName"].value;
	
	var letters = /^[A-Za-z\s]+$/;
	
	if (firstName.length < 2) {
		alert("First name is too short!");
		return false;
	}
	
	if (letters.test(firstName) == false) {
		alert("First name can only have letters!");
		return false;
	}
	
	if (lastName.length < 2) {
		alert("Last name is too short!");
		return false;
	}
	
	if (letters.test(lastName) == false) {
		alert("Last name can only have letters!");
		return false;
	}
}

</script>

</body>
</html>