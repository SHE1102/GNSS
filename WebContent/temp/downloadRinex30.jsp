<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>下载每日Rinex</title>
        <link rel="SHORTCUT ICON" href="img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="css/downloadRinex30.css"/>
        <script type="text/javascript" src="js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="css/leaflet.css" >
        <script src="js/leaflet.js"></script>
        
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	
    	<div class="Body">
    		<%@ include file="menu.jsp" %>
    		
    		<div class="Content"  >
    			 <div id="ContentDiv">
    			     <div id="Condition">
	    			     <span>日期:</span>
		    			 <input id="ConvertDate" type="date">
	    			     <span>站点:</span>
		    			 <select id="StationList" ></select>
	    			 </div>
	    			 <div id="TableDiv">
		    			 <table id="TableHeader">
			    			 <tr>
			    			     <th>名称</th>
								 <th>大小</th>
								 <th>时间</th>
								 <th></th>
			    			 </tr>
		    			 </table>
		    			 <div id="TableBodyDiv">
		    			     <table id="tablebody"></table>
		    			 </div>
	    			 </div>
	    			 <div>
					    <input id="Download" type="button" value="下载"/>
					</div>
    			 </div>
    		</div>
    		
    	</div>
    	
    	<div class="Foot"></div>
 	</body>
 	<script src="js/main.js" type="text/javascript"></script>
 	<script src="js/downloadRinex30.js" type="text/javascript"></script>
</html>