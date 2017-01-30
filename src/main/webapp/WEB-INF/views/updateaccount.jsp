<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Account</title>
<link rel="stylesheet" href="resources/stylepage.css">
</head>
<body>
	<divnav>
		<form name="home" class="navform" action="http://localhost:8080/roshambo/" method="get">
			<input type="submit" class="inside" value="Home">
		</form>
		<form name="profile" class="navform" action="profile" method="post">
			<input type="submit" class="inside" value="Profile">
		</form>
		<form name="login" class="navform" action="login" method="post">
			<input type="submit" class="inside" value="Login">
		</form>
	</divnav>
	<h1>Update Account</h1>
	<div>
	<form name="updateAccount" onsubmit="return validation()" action="profile" method="post">
	
		<input type="hidden" name="accountID" class="inside" value="${accountID}">
		<br>First Name: <input type="text" name="firstName" class="inside" value="${firstName}"> 
		<br>Last Name: <input type="text" name="lastName" class="inside" value="${lastName}"> 
		<br>password: <input type="password" name="password" class="inside"> 
		<br><br>
		<input type="submit" class="inside" value="Update Account">	
	</form>
</div>
	
<script>
	function validation() {

		var userName = document.forms["createAccount"]["userName"].value;
		var firstName = document.forms["createAccount"]["firstName"].value;
		var lastName = document.forms["createAccount"]["lastName"].value;
		var password = document.forms["createAccount"]["password"].value;

		var letters = /^[A-Za-z\s]+$/;

		if (userName.length < 8) {
			alert("Username is too short! Need at least 8 characters!");
			return false;
		}

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

		if (password.length < 10) {
			alert("Password is too short! Must have at least 10 characters!");
			return false;
		}

		if (/\d/.test(password) === false) {
			alert("Password needs at least one digit!");
			return false;
		}

		if (/[a-z]/.test(password) === false) {
			alert("Password needs at least one lower case letter!");
			return false;
		}

		if (/[A-Z]/.test(password) === false) {
			alert("Password needs at least one upper case letter!");
			return false;
		}

		if (/[!@#$%^&*]/.test(password) === false) {
			alert("Password needs at least one special character!");
			return false;
		}

	}
</script>
</body>
</html>