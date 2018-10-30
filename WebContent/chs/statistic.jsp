<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>数据统计</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <script type="text/javascript" src="../js/jquery-1.9.0.min.js" ></script>
        <script type="text/javascript" src="../js/highcharts.js" ></script>
        <link rel="stylesheet" type="text/css" href="../css/leaflet.css" >
        <script src="../js/leaflet.js"></script>
        
        <style>
            #Graph{width:100%;height:100%;}
            .GraphDiv{width:100%;height:auto;}
            span#SelectGraph{float:right;border:20px solid transparent;border-right-color: #DDD;}
        </style>
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	<%@ include file="menuHor.jsp" %>
    	
    	<div class="Body">
    		<!-- <%@ include file="menu.jsp" %> -->
    		
    		<div class="Content"  >
    			 <div id="Graph">
    			     <div id="RegisterGraph" class="GraphDiv"></div>
    			     <div id="DownloadGraph" class="GraphDiv"></div>
    			     <!-- <div id="" class="GraphDiv"></div>
    			     <div id="" class="GraphDiv"></div>
    			     <div id="" class="GraphDiv"></div> -->
    			 </div>
    		</div>
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/statistic.js" type="text/javascript"></script>
</html>