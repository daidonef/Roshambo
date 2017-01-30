<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Owner Page</title>
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
<h1>Owner Page</h1>
	<div>
	Search through the Accounts by name
	<form name="search" action="ownerpage" method="post">

		<br> <input type="text" class="inside" name="name"> <br>
		<input type="submit" class="inside" value="Search">
	</form>
	</div>
	<table align="center">
		<tr>
			<th>Username</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Password</th>
			<th>Update Account</th>
			<th>Delete Account</th>
		</tr>
		<c:forEach items="${accounts}" var="account">
			<tr>
				<td>${account.userName }</td>
				<td>${account.firstName }</td>
				<td>${account.lastName }</td>
				<td>${account.password }</td>
				<td><form name="update" action="updateaccount" method="post">
						<input type="hidden" name="updateOwner" value="${account.ID}"> 
						<input type="submit" class="inside" value="Update Account">
					</form></td>
				<td><form name="delete" action="ownerpage" method="post">
						<br>
						<input type="hidden" name="delete" value="${account.ID}"> 
						<input type="submit" class="inside" value="Delete Account">
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<p>
	${accountDelete }
	</p>
</body>
</html>