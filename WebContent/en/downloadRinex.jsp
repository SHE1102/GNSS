<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Download Rinex 30s</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="../css/downloadRinex.css"/>
        <script type="text/javascript" src="../js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="../css/leaflet.css" >
        <script src="../js/leaflet.js"></script>
        
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	<%@ include file="menuHor.jsp" %>
    	
    	<div class="Body">
    		<div class="Content"  >
    			 <div id="ContentDiv">
		            <div id="ConvertDiv">
	    			     <form id="ConvertForm" action="">
	    			         <table id="ConvertTable">
	    			             <tr>
	    			                 <td class="Label">Date</td>
	    			                 <td><input id="ConvertDate" type="date"></td>
	    			                 <td class="Label">Station</td>
	    			                 <td><select id="StationList" ></select></td>
	    			             </tr>
	    			             <tr>
	    			                 <td class="Label">StartTime</td>
	    			                 <td><input id="StartTime" type="time" value="00:00"></td>
	    			                 <td class="Label">EndTime</td>
	    			                 <td><input id="EndTime" type="time" value="00:00"></td>
	    			             </tr>
	    			              <tr>
	    			                 <td class="Label">Zone</td>
	    			                 <td><select id="Zone"  name="Zone"></select></td>
	    			                 <td colspan="2"  class="Label"></td>
	    			             </tr>
	    			             <tr>
	    			                 <td class="Label">RinexVersion</td>
	    			                 <td><select id="RinexVersion" name="RinexVersion"></select></td>
	    			                 <td colspan="2" class="TdContent">
	    			                     <input type="checkbox" id="Mixture"  name="Mixture" value="">
					                     <span id="mixture-label" >Mixture v3.02</span>
	    			                 </td>
	    			             </tr>
	    			             <tr>
	    			                 <td class="Label">Output time interval</td>
	    			                 <td><select id="TimeInterval" name="TimeInterval"></select></td>
	    			                 <td class="Label">Frequency</td>
	    			                 <td class="TdContent">
	    			                     <input type="radio" id="radio1" name="FrequencyPoint" value="0"  name="FrePoint">
					                     <span id="singleFre-label" >Single</span>
					                     <input type="radio" id="radio2" name="FrequencyPoint" value="1" checked name="FrePoint">
					                     <span id="mulFre-label" >Multi</span></td>
	    			             </tr>
	    			             <tr>
	    			                 <td class="Label">Parameter</td>
	    			                 <td colspan="3" class="TdContent">
	    			                 	 <input type="checkbox" class="lineHead" id="checkbox1" value="0" checked name="CheckFlag">
					    				 <span id="checkbox1-label">Output ionized stratum param</span>
	    			                
	    			                     <input type="checkbox" id="checkbox2" value="1" checked name="CheckFlag">
					                     <span id="checkbox2-label" >Antenna phase center</span> 
	    			                
	    			                     <input type="checkbox" id="checkbox3" value="2" checked name="CheckFlag">
										 <span id="checkbox3-label" >Mark cycle</span>
	    			                 
	    			                     <input type="checkbox" id="checkbox4" value="3"  name="CheckFlag">
										 <span id="checkbox4-label" >Single station coordinate</span>
	    			                 </td>
	    			             </tr>
	    			             <tr>
	    			                 <td class="Label">SatelliteSystem</td>
	    			                 <td colspan="3" class="TdContent">
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
	    			                 </td>
	    			             </tr>
	    			             <tr>
	    			                 <td colspan="4" class="Label">
	    			                     <p>Hint: If a system is not enabled, its observation and navigation data will be not downloaded</p>
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
		                <a id="downloadFile">Download</a>
		            </div>
            
    			 </div>
    		</div>
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/downloadRinex.js" type="text/javascript"></script>
</html>