<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Setting</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="../css/configSet.css"/>
        <script type="text/javascript" src="../js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="../css/leaflet.css" >
        <script src="../js/leaflet.js"></script>
        
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	<%@ include file="menuHor.jsp" %>
    	
    	<div class="Body">
    		<div class="Content"  >
    			 <div class="ConfigDiv">
    			     <form action="../UploadIcon" enctype="multipart/form-data">
	    			      <table>
	    			          <tr>
	    			             <th colspan="4">change Logo icon</th>
	    			         </tr>
	    			          <tr>
	    			             <td class="Label">Logo icon</td>
	    			             <td ><input  id="LogoIcon" name="LogoIcon"  type="file" accept=".png"/></td>
	    			             <td class="Label"><input id="UploadLogo" type="button"  class="FuncButton"  value="upload" /></td>
	    			             <td></td>
		    			      </tr>
	    			      </table>
    			     </form>
    			     
    			     <form id="FtpForm">
    			         <table>
    			             <tr>
    			                 <th colspan="3">FTP Setting</th>
    			                 <td class="Label">
	    			                 <input id="StartFtp" type="button"  class="FuncButton" value="Start" />
	    			                 <input id="StopFtp" type="button"  class="FuncButton" value="Stop" />
	    			             </td>
    			             </tr>
    			              <tr>
	    			             <td class="Label">Port</td>
	    			             <td><input  id="FtpPort" name="FtpPort"  type="text"/></td>
	    			             <td class="Label">Home directory</td>
	    			             <td><input  id="HomeDirectory" name="HomeDirectory" type="text"/></td>
	    			         </tr>
    			              <tr>
	    			             <td class="Label">UserName</td>
	    			             <td><input  id="FtpName" name="FtpName"  type="text"/></td>
	    			             <td class="Label">Password</td>
	    			             <td><input  id="FtpPassword" name="FtpPassword" type="text"/></td>
	    			         </tr>
    			         </table>
    			     </form>
    			     
    			     <form id="ConfigForm">
	    			     <table id="ConfigTable">
	    			         <tr>
	    			             <th colspan="4">Raw data path</th>
	    			         </tr>
	    			         <tr>
	    			             <td class="Label">Raw path</td>
	    			             <td colspan="3"><input  id="rawPath" name="rawPath"  type="text"/></td>
	    			         </tr>
	    			         <tr><th colspan="4"></th></tr>
	    			         <tr>
	    			             <th colspan="4">Host email setting</th>
	    			         </tr>
	    			         <tr>
	    			             <td class="Label">Host email</td>
	    			             <td><input  id="hostEmail" name="hostEmail"  type="text"/></td>
	    			             <td class="Label">Password</td>
	    			             <td><input  id="hostEmailPassword" name="hostEmailPassword" type="text"/></td>
	    			         </tr>
	    			         <tr>
	    			             <td class="Label">Host protocol</td>
	    			             <td><input  id="hostEmailProtocol" name="hostEmailProtocol"  type="text"/></td>
	    			             <td class="Label" colspan="2"></td>
	    			         </tr>
	    			         <tr><th colspan="4"></th></tr>
	    			         <tr>
	    			             <th colspan="4">Map center setting</th>
	    			         </tr>
	    			         <tr>
	    			             <td class="Label">Latitude</td>
	    			             <td><input  id="centerLat" name="centerLat"  type="text"/></td>
	    			             <td class="Label">Longitude</td>
	    			             <td><input  id="centerLon" name="centerLon"  type="text"/></td>
	    			         </tr>
	    			          <tr>
	    			             <td class="Label">Zoom</td>
	    			             <td>
										<select id="zoom" name="zoom"></select>
                                 </td>
	    			             <td colspan="2"></td>
	    			         </tr>
	    			     </table>
	    			     <input id="SaveConfig" type="button" value="Save"/><br>
    			     </form>
    			 </div>
    		</div>
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/configSet.js" type="text/javascript"></script>
</html>