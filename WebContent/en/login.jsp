<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
	<link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
	
	<script src="../js/jquery-1.9.0.min.js"></script>
	<link rel="stylesheet" type="text/css" href="../css/login.css" >

</head>

<body>
    <div id="login-content">
		<form  action="../Login" method="post" >
			<div>
				<label id="name-label" class="form-label">UserName:</label>
				<input id="name" class="form-input" name="name" type="text" placeholder="UserName" autofocus>
			</div>
			<div>
				<label id="password-label" class="form-label">Password:</label>
				<input id="password" class="form-input"  name="password" type="password" placeholder="Password">
			</div>
			
			<input id="submit" type="submit" value="Login">
		</form>
	</div>
</body>

</html>