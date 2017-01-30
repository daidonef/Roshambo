<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link rel="stylesheet" href="resources/stylepage.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<divnav>
		<form name="profile" class="navhome" action="profile" method="post">
			<input type="submit" class="inside" value="Profile">
		</form>
		<form name="login" class="navhome" action="login" method="post">
			<input type="submit" class="inside" value="Login">
		</form>
	</divnav>
	<h1>Home Page!</h1>
	<div>
	<form name="login" action="login" method="post">

		<input type="submit" class="inside" value="Login">
	</form>
	<form name="createAccount" action="createaccount" method="post">

		<input type="submit" class="inside" value="Create Account">
	</form>
	</div>
</body>
</html>
