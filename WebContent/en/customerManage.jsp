<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Manage</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="../css/customerManage.css"/>
        <script type="text/javascript" src="../js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="../css/leaflet.css" >
        <script src="../js/leaflet.js"></script>
        
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	<%@ include file="menuHor.jsp" %>
    	
    	<div class="Body">
    		<div class="Content"  >
    			 <div id="customer">
    			 	<div id="TableDiv">
	    			     <table id="TableHeader">
	    			         <tr>
	    			             <th class="item">UserName</th>
	    			             <th class="item">Enable</th>
	    			             <th class="item">LimitDate</th>
	    			              <% if((Integer)session.getAttribute("authority") == 9){
	    			            	 out.println("<th class='item'>Authority</th>");
	    			             }
	    			             %>
	    			             <th class="item">Download rinex</th>
	    			             <th class="item">Virtual</th>
	    			             <th class="item">Solution</th>
	    			             <th class="item">Additional</th>
	    			             <th class="func"></th>
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
 	<script src="../js/customerManage.js" type="text/javascript"></script>
</html>