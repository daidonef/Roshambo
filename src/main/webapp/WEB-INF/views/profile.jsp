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
	<h1>${userName} Profile</h1>
	<p class="first">Hello ${fullName}${updateAccount}</p>
	<div>
		<br><h2>Play against a Computer Player:</h2>
	<form name="player" action="userchoice" method="post">

		Rock Player<input type="radio" name="opponent" value="rockPlayer">
		<br>Random Player<input type="radio" name="opponent" value="randomPlayer"> 
		<br>Smart Player<input type="radio" name="opponent" value="smartPlayer">
		<br><input type="submit" class="inside" value="Play">
	</form>
	</div>
	<table align="center">
		<tr>
			<th>OPPONENT</th>
			<th>WINS</th>
			<th>LOSES</th>
			<th>TIES</th>
		</tr>
		<c:forEach items="${scores }" var="score">
			<tr>
				<td><c:if test="${score.opponent == 'rockPlayer'}">
				<c:out value="Rock Player"/></c:if>
				<c:if test="${score.opponent == 'randomPlayer'}">
				<c:out value="Ramdom Player"/></c:if></td>
				<td>${score.wins }</td>
				<td>${score.loses }</td>
				<td>${score.ties }</td>
			</tr>
		</c:forEach>
	</table>
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
	<div>${owner}</div>
</body>
</html>