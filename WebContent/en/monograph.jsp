<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Monograph</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <script type="text/javascript" src="../js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="../css/leaflet.css" >
        <script src="../js/leaflet.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/monograph.css" >
        
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	<%@ include file="menuHor.jsp" %>
    	
    	<div class="Body">
    		<div class="Content"  >
    			 <div id="Monograph">
    			 	 <div id="TableDiv">
	    			     <table id="TableHeader">
	    			         <tr>
	    			             <th>ID</th>
	    			             <th>City</th>
	    			             <th>File</th>
	    			             <th>Choose</th>
	    			             <th>Operate</th>
	    			         </tr>
	    			     </table>
	    			     <div id="TableBodyDiv">
	    			         <table id="TableBody">
	    			         </table>
	    			     </div>
    			     </div>
    			 </div>
    		</div>
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/monograph.js" type="text/javascript"></script>
</html>