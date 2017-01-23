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
	<p>Hello ${fullName}${updateAccount}</p>
	<p>
		<br>Play against a Computer Player:
	<form name="player" action="userchoice" method="post">

		Rock Player<input type="radio" name="opponent" value="rockPlayer">
		<br>Random Player<input type="radio" name="opponent"
			value="randomPlayer"> <br> <br> <input
			type="submit" value="Play">
	</form>
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
	<!-- Need to figure out way to update and delete account -->
	<form name="update" action="updateaccount" method="post">
		<input type="hidden" name="update" value="updateUser">
		<input type="submit" value="Update Account">
	</form>
	<form name="delete" action="login" method="post">
		<br><input type="hidden" name="delete" value="delete">
		<input type="submit" value="Delete Account">
	</form>
	<p>${owner}</p>
</body>
</html>