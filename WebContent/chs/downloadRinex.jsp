<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>转换Rinex</title>
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
    		<!-- <%@ include file="menu.jsp" %> -->
    		
    		<div class="Content"  >
    			 <div id="ContentDiv">
		            <div id="ConvertDiv">
	    			     <form id="ConvertForm" action="">
	    			         <table id="ConvertTable">
	    			             <tr>
	    			                 <td class="Label">日期</td>
	    			                 <td><input id="ConvertDate" type="date"></td>
	    			                 <td class="Label">站点</td>
	    			                 <td><select id="StationList" ></select></td>
	    			             </tr>
	    			             <tr>
	    			                 <td class="Label">起始时间</td>
	    			                 <td><input id="StartTime" type="time" value="00:00"></td>
	    			                 <td class="Label">终止时间</td>
	    			                 <td><input id="EndTime" type="time" value="00:00"></td>
	    			             </tr>
	    			             <!-- <tr>
	    			                 <td class="Label">时区</td>
	    			                 <td><select id="Zone"  name="Zone"></select></td>
	    			                 <td colspan="2"  class="Label"></td>
	    			             </tr> -->
	    			             <tr>
	    			                 <td class="Label">Rinex版本</td>
	    			                 <td><select id="RinexVersion" name="RinexVersion"></select></td>
	    			                 <td colspan="2" class="TdContent">
	    			                     <input type="checkbox" id="Mixture"  name="Mixture" value="">
					                     <span id="mixture-label" >混合v3.02</span>
	    			                 </td>
	    			             </tr>
	    			             <tr>
	    			                 <td class="Label">输出时间间隔</td>
	    			                 <td><select id="TimeInterval" name="TimeInterval"></select></td>
	    			                 <td class="Label">导出频点</td>
	    			                 <td class="TdContent">
	    			                     <input type="radio" id="radio1" name="FrequencyPoint" value="0"  name="FrePoint">
					                     <span id="singleFre-label" >单频</span>
					                     <input type="radio" id="radio2" name="FrequencyPoint" value="1" checked name="FrePoint">
					                     <span id="mulFre-label" >多频</span></td>
	    			             </tr>
	    			             <tr>
	    			                 <td class="Label">相关参数</td>
	    			                 <td colspan="3" class="TdContent">
	    			                 	 <input type="checkbox" class="lineHead" id="checkbox1" value="0" checked name="CheckFlag">
					    				 <span id="checkbox1-label">输出电离层改正参数</span>
	    			                
	    			                     <input type="checkbox" id="checkbox2" value="1" checked name="CheckFlag">
					                     <span id="checkbox2-label" >天线相位中心</span> 
	    			                
	    			                     <input type="checkbox" id="checkbox3" value="2" checked name="CheckFlag">
										 <span id="checkbox3-label" >标记周跳</span>
	    			                 
	    			                     <input type="checkbox" id="checkbox4" value="3"  name="CheckFlag">
										 <span id="checkbox4-label" >单点定位给定测站坐标</span>
	    			                 </td>
	    			             </tr>
	    			             <tr>
	    			                 <td class="Label">导出卫星系统</td>
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
	    			                     <p>提示:如果未启用系统，则不会下载观察和导航数据</p>
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
		                <input id="start" type="button" value="开始">
		                <a id="downloadFile">下载</a>
		            </div>
            
    			 </div>
    		</div>
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/downloadRinex.js" type="text/javascript"></script>
</html>