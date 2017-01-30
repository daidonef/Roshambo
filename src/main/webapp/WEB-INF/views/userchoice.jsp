<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Choice</title>
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
<h1>Make a Choice</h1>
	<div>
	<form name="humanChoice" action="match" method="post">

		<br>Rock<input type="radio" name="humanPlayer" value="Rock">
		<br>Paper<input type="radio" name="humanPlayer" value="Paper">
		<br>Scissors<input type="radio" name="humanPlayer"
			value="Scissors"> <br>
		<br>
		<input type="submit" class="inside" value="Your Choice">
	</form>
	</div>
</body>
</html>