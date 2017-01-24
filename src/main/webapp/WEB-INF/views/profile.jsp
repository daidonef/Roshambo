<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet" href="resources/stylepage.css">
</head>
<body>
	<h1>${fullName} Profile</h1>
	<p class="first">Hello ${fullName}${updateAccount}</p>
	<div>
		<br><h2>Play against a Computer Player:</h2>
	<form name="player" action="userchoice" method="post">

		Rock Player<input type="radio" name="opponent" value="rockPlayer">
		<br>Random Player<input type="radio" name="opponent" value="randomPlayer"> 
		<br><input type="submit" class="inside" value="Play">
	</form>
	</div>
	<table>
		<tr>
			<th>Opponent</th>
			<th>Wins</th>
			<th>Loses</th>
			<th>Ties</th>
		</tr>
		<c:forEach items="${scores }" var="score">
			<tr>
				<td>${score.opponent }</td>
				<td>${score.wins }</td>
				<td>${score.loses }</td>
				<td>${score.ties }</td>
			</tr>
		</c:forEach>
	</table>
	</p>
	<div>
	<form name="update" action="updateaccount" method="post">
		<input type="hidden" name="update" value="updateUser">
		<input type="submit" class="inside" value="Update Account">
	</form>
	<form name="delete" action="login" method="post">
		<br><input type="hidden" name="delete" value="delete">
		<input type="submit" class="inside" value="Delete Account">
	</form>
	</div>
	<p>${owner}</p>
</body>
</html>