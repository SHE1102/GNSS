<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>虚拟Rinex</title>
        <link rel="SHORTCUT ICON" href="img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="css/virtualRinex.css"/>
        <script type="text/javascript" src="js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="css/leaflet.css" >
        <script src="js/leaflet.js"></script>
        
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	
    	<div class="Body">
    		<%@ include file="menu.jsp" %>
    		
    		<div class="Content"  >
    			 <div id="Virtual">
	    			 <div class="virtualInput">
			                <form id="localForm">
			                    <fieldset>
			                       <legend>位置</legend>
								   <label id="longitude-label" for="latitude">纬度:</label>
								   <input id="latitude" type="text" value="45.3">
								   
					               <label id="latitude-label" for="longitude">经度:</label>
								   <input id="longitude" type="text" value="8.8">
								   <br>
								    
								   <label id="altitude-label" for="altitude">大地高:</label>
								   <input id="altitude" type="text" value="150">
								  
								   <label id="format-label" for="coordinateFormat">坐标格式:</label>
					               <select class="selectlist" id="coordinateFormat">
								      <option value="0">dd.dddddd</option>
								      <option value="1">dd.mmssssss</option>
								   </select>
			                    </fieldset>
			                </form>
			            </div>
			            
			           
			            <div class="virtualInput">
			                <form id="timeForm">
			                    <fieldset>
			                       <legend>时间</legend> 
			                       
			                       <label id="date-label" for="date">日期:</label>
								   <input id="date" type="date" value="2018-08-23">
								   
								   <label id="startTime-label" for="startTime">起始时间:</label>
								   <input id="startTime" type="time" value="16:00:01">
								   <br>
								   
								   <label id="zone-label" for="zone">时区:</label>
					               <select class="selectlist" id="zone"></select>
					               
								   <label id="endTime-label" for="endTime">结束时间:</label>
								   <input id="endTime" type="time" value="17:00:01">
			                    </fieldset>
			                </form>
			            </div>
			            
			            <div id="progressDiv">
			                <i id="state"></i>
			                <progress id="progressBar" value="0" max="100"></progress>
			            </div>
			            
			            <div>
			                <input id="start" type="button" value="开始">
			                <a id="download" >下载</a>
			            </div>
    			 </div>
    		</div>
    		
    	</div>
    	
    	<div class="Foot"></div>
 	</body>
 	<script src="js/main.js" type="text/javascript"></script>
 	<script src="js/virtualRinex.js" type="text/javascript"></script>
</html>