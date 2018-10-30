<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Virtual Rinex</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="../css/virtualRinex.css"/>
        <script type="text/javascript" src="../js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="../css/leaflet.css" >
        <script src="../js/leaflet.js"></script>
        
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	<%@ include file="menuHor.jsp" %>
    	
    	<div class="Body">
    		<div class="Content"  >
    			 <div id="Virtual">
			            <div id="VirtualDiv">
		    			     <form id="VirtualForm" action="">
		    			         <table id="VirtualTable">
		    			             <tr>
		    			                 <td class="Label">Latitude</td>
		    			                 <td><input id="latitude"  name="Latitude" type="text" value="0.0"/></td>
		    			                 <td class="Label">Longitude</td>
		    			                 <td><input id="longitude"  name="Longitude" type="text" value="0.0"/></td>
		    			             </tr>
		    			             <tr>
		    			                 <td class="Label">Altitude</td>
		    			                 <td><input id="altitude"  name="Altitude" type="text" value="0.0"/></td>
		    			                 <td class="Label">Unit</td>
		    			                 <td>
			    			                 <select  id="coordinateFormat" name="CoordinateFormat">
			    			                     <option value="0">dd.dddddd</option>
									              <option value="1">dd.mmssssss</option>
			    			                 </select>
		    			                 </td>
		    			             </tr>
		    			             <tr>
		    			                 <td class="Label">Date</td>
		    			                 <td><input id="date"  name="Date" type="date"/></td>
		    			                 <td class="Label">Zone</td>
		    			                 <td><select id="zone" name="Zone" ></select></td>
		    			             </tr>
		    			             <tr>
		    			                 <td class="Label">StartTime</td>
		    			                 <td><input id="startTime"  name="StartTime" type="time" value="00:00"/></td>
		    			                 <td class="Label">EndTime</td>
		    			                 <td><input id="endTime"  name="EndTime" type="time" value="00:00"/></td>
		    			             </tr>
		    			             <tr>
		    			                 <td class="Label">RinexVersion</td>
		    			                 <td><select id="RinexVersion" name="RinexVersion" ></select></td>
		    			                 <td colspan="2" class="TdContent">
		    			                 <input type="checkbox" id="Mixture"  name="Mixture" value="1">
		    			                 <span >Mixture v3.02</span>
		    			                 </td>
		    			             </tr>
		    			              <tr>
		    			                 <td class="Label">Output time interval</td>
		    			                 <td > 
			    			                 <select id="TimeInterval" name="TimeInterval"></select>
							            </td>
							            <td class="Label">Frequency</td>
		    			                <td class="TdContent"> 
			    			                 <input type="radio" id="radio1" name="FrequencyPoint" value="0"  >
								             <span id="singleFre-label" >Single</span>
								             <input type="radio" id="radio2" name="FrequencyPoint" value="1" checked >
								             <span id="mulFre-label" >Multi</span>
							            </td>
		    			             </tr>
		    			             <tr>
		    			                 <td class="Label">SatelliteSystem</td>
		    			                 <td colspan="3" class="TdContent"> 
			    			                <input type="checkbox" id="SatelliteSystem1" value="0" checked name="SystemFlag">
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
							            </td>
		    			             </tr>
		    			            
		    			         </table>
		    			     </form>
		    			 </div>
			            
			            <div id="progressDiv">
			                <i id="state"></i>
			                <progress id="progressBar" value="0" max="100"></progress>
			            </div>
			            
			            <div>
			                <input id="start" type="button" value="Start">
			                <a id="download" >Download</a>
			            </div>
    			 </div>
    		</div>
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/virtualRinex.js" type="text/javascript"></script>
</html>