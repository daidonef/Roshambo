<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Match Results</title>
<link rel="stylesheet" href="resources/stylepage.css">
</head>
<body>
<h1>Match Results</h1>
	<p class="first">
		Your Choice: ${humanRPS} 
		<br>Opponent's Choice: ${opponentRPS} 
		<br>Outcome: ${outcome}
	</p>
	<p>
	<form name="playAnotherOpp" action="profile" method="post">
	<br><input type="submit" class="inside" value="Play Another Opponent">
	
	</form>
	<form name="playAgain" action="userchoice" method="post">
	<br><input type="submit" class="inside" value="Play Again">
	</form>
	</p>

</body>
</html>