<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>站点列表</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/stationList.css"/> 
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <script type="text/javascript" src="../js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="../css/leaflet.css" >
        <script src="../js/leaflet.js"></script>
        
        <style type="text/css">
            span#SelectList{float:right;border:20px solid transparent;border-right-color: #DDD;}
        </style>
       
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	<%@ include file="menuHor.jsp" %>
    	
    	<div class="Body">
    		<!-- <%@ include file="menu.jsp" %> -->
    		
    		<div class="Content"  >
    		     <div class="ListDiv">
	   			     <table id="StationList">
	   			         <tr>
							<th class="leftColumn">Id</th>
							<th class="leftColumn">城市</th>
							<th>纬度</th>
							<th>经度</th>
							<th>链接</th>
	   			         </tr>
   			     </table>
   			     </div>
    		</div>
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/stationList.js" type="text/javascript"></script>
 	
</html>