<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Home Page!  
</h1>
<p>
<form name="login" action="login" method="post">

<input type="submit" value="Login">
</form>
</p>
<p>
<form name="createAccount" action="createaccount" method="post">

<input type="submit" value="Create Account">
</form>
</p>
</body>
</html>
