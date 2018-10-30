<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="../css/register.css"/>
        <script type="text/javascript" src="../js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="../css/leaflet.css" >
        <script src="../js/leaflet.js"></script>
        <script type="text/javascript" src="../js/jquery.validate.js" ></script>
        <script type="text/javascript" src="../js/messages_en.js"></script>
        
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	<%@ include file="menuHor.jsp" %>
    	
    	<div class="Body">
    		<div class="Content"  >
    			 <div id="RegisterDiv">
    			 	<form id="RegisterForm" action="Register" method="post">
    			 	    <h1>Welcome to register</h1>
	    			 	<input name="UserName"  id="UserName"  type="text" placeholder="UserName" autofocus/>*
	    			 	<label for="UserName" class="error" style="display:none;"></label><br>
	    			 	<input name="FirstName" type="text" placeholder="FirstName" />*
	    			 	<label for="FirstName" class="error"></label><br>
	    			 	<input name="LastName" type="text" placeholder="LastName" />*
	    			 	<label for="LastName" class="error"></label><br>
	    			 	<input name="Password" id="Password" type="password" placeholder="Password" />*
	    			 	<label for="Password" class="error"></label><br>
	    			 	<input name="RePassword" type="password" placeholder="Repassword" />*
	    			 	<label for="RePassword" class="error"></label><br>
	    			 	<input name="Company" type="text" placeholder="Company" />*
	    			 	<label for="Company" class="error"></label><br>
	    			 	<input name="Email" type="text" placeholder="email" />*
	    			 	<label for="Email" class="error"></label><br>
	    			 	<input name="Telephone" type="text" placeholder="Telephone" />
	    			 	<label for="Telephone" class="error"></label><br>
	    			 	
	    			 	
	    			 	
	    			 	<input id="Register" type="submit" value="Register" /><br>
    			 	</form>
    			 </div>
    		</div>
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/register.js" type="text/javascript"></script>
</html>