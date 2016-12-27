<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
</head>
<body>
<p>
Hello ${fullName}
</p>
<p>

<br>Play against a Computer Player:
<form name="player" action="userchoice" method="post">

Rock Player<input type="radio" name="opponent" value="rockPlayer">
<br>Random Player<input type="radio" name="opponent" value="randomPlayer">

<br><br><input type="submit" value="Play">
</form>
</p>
</body>
</html>