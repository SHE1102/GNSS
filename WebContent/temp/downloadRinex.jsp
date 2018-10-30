<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>转换Rinex</title>
        <link rel="SHORTCUT ICON" href="img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="css/downloadRinex.css"/>
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
								 <th>日期</th>
								 <th>开始时间</th>
								 <th>结束时间</th>
								 <th></th>
			    			 </tr>
		    			 </table>
		    			 <div id="TableBodyDiv">
		    			     <table id="tablebody"></table>
		    			 </div>
	    			 </div>
	    			 
	    			 <div id="rinexSetDiv">
	    			 <form id="RinexForm">
		                <span id="rinexVer-label" class="lineHead">Rinex版本:</span>
						<select id="RinexVersion" name="RinexVersion"></select>
						<input type="checkbox" id="Mixture"  name="Mixture" value="">
					    <span id="mixture-label" >混合v3.02</span>
						<br/>
						
						<span id="timeInterval-label" class="lineHead">输出时间间隔:</span>
						<select id="TimeInterval" name="TimeInterval"></select>
					    <br>
					
					    <input type="checkbox" class="lineHead" id="checkbox1" value="0" checked name="CheckFlag">
					    <span id="checkbox1-label">输出电离层改正参数</span>
					    
					    <input type="checkbox" id="checkbox2" value="1" checked name="CheckFlag">
					    <span id="checkbox2-label" >天线相位中心</span>
					    
					    <input type="checkbox" id="checkbox3" value="2" checked name="CheckFlag">
					    <span id="checkbox3-label" >标记周跳</span>
					    
					    <input type="checkbox" id="checkbox4" value="3"  name="CheckFlag">
					    <span id="checkbox4-label" >单点定位给定测站坐标</span>
					    <br>
					    
					    <span id="satellite-label" class="lineHead">导出卫星系统:</span>
					
					    <input type="checkbox" class="lineHead" id="SatelliteSystem1" value="0" checked name="SystemFlag">
					    <span>GPS</span>
					    
					    <input type="checkbox" id="SatelliteSystem2" value="1" checked name="SystemFlag">
					    <span>GLO</span>
					    
					    <input type="checkbox" id="SatelliteSystem3" value="2" checked name="SystemFlag">
					    <span>BeiDou</span>
					    
					    <input type="checkbox" id="SatelliteSystem4" value="3" checked name="SystemFlag">
					    <span>Galileo</span>
					    
					    <input type="checkbox" id="SatelliteSystem5" value="4" checked name="SystemFlag">
					    <span>QZSS</span>
					    
					    <input type="checkbox" id="SatelliteSystem6" value="5" checked name="SystemFlag">
					    <span>SBAS</span>
					    <br>
					    
					    <span id="exportFre-label" class="lineHead">导出频点:</span>
					    
					    <input type="radio" class="lineHead" id="radio1" name="FrequencyPoint" value="0" checked name="FrePoint">
					    <span id="singleFre-label" >单频</span>
					    
					    <input type="radio" id="radio2" name="FrequencyPoint" value="1" name="FrePoint">
					    <span id="mulFre-label" >多频</span>
					    <br>
		                </form>
		            </div>
		            
		            <div id="progressDiv">
		                <i id="state"></i>
		                <progress id="progressBar" value="0" max="100"></progress>
		            </div>
		            
		            <div>
		                <input id="start" type="button" value="开始">
		                <a id="downloadFile">下载</a>
		            </div>
            
    			 </div>
    		</div>
    	</div>
    	
    	<div class="Foot"></div>
 	</body>
 	<script src="js/main.js" type="text/javascript"></script>
 	<script src="js/downloadRinex.js" type="text/javascript"></script>
</html>