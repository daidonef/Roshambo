<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Owner Page</title>
</head>
<body>
	<p>
	<form name="search" action="ownerpage" method="post">

		<br> <input type="text" name="name"> <br> <br>
		<input type="submit" value="Search">
	</form>
	</p>
	<p>
	<table>
		<tr>
			<th>Username</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Password</th>
		</tr>
		<c:forEach items="${accounts}" var="account">
			<tr>
				<td>${account.userName }</td>
				<td>${account.firstName }</td>
				<td>${account.lastName }</td>
				<td>${account.password }</td>
				<td><form name="update" action="updateaccount" method="post">
						<input type="hidden" name="updateOwner" value="account.ID"> 
						<input type="submit" value="Update Account">
					</form></td>
				<td><form name="delete" action="ownerpage" method="post">
						<br>
						<input type="hidden" name="delete" value="account.ID"> 
						<input type="submit" value="Delete Account">
					</form></td>
			</tr>
		</c:forEach>
	</table>
	</p>
	<p>
	${accountDelete }
	</p>
</body>
</html>