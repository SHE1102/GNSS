<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Additional Function</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <script type="text/javascript" src="../js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="../css/leaflet.css" >
        <script src="../js/leaflet.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/additionalFeature.css">
    
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	<%@ include file="menuHor.jsp" %>
    	
    	<div class="Body">
    		<div class="Content"  >
    			 
    			     <form id="ConvertForm" >
	    			      <table>
	    			           <tr>
    			                 <th colspan="4">Coordinate convert</th>
    			             </tr>
	    			           <tr>
	    			             <td class="Label">Coordinate file</td>
	    			             <td colspan="2"><input type="file"  id="CoordinateFile"  name="CoordinateFile" accept=".sp"></td>
	    			             <td >
	    			                 <input type="button"  id="UploadFile" class="funcButton" value="Upload">
	    			                 <input type="button"  id="DetailCoord"  class="funcButton" value="Detail"><br>
                                 </td>
		    			      </tr>
	    			          <tr>
	    			             <td class="Label">Type</td>
	    			             <td colspan="2" >
	    			                 <input type="radio" name="ConvertType" value="0" checked="checked">BLH->xyh
	    			                 <input type="radio" name="ConvertType" value="1">xyh->BLH
	    			             </td>
	    			             <td><input id="CoordinateConvert"  class="funcButton"  type="button" value="Convert"></td>
		    			      </tr>
		    			      <tr>
		    			          <td class="Label" id="SourceX">B:</td>
		    			          <td><input type="text" name="Bx" id="Bx"></td>
		    			          <td class="Label" id="DestinationX">x:</td>
		    			          <td><input type="text" readonly id="DestinationBx"></td>
		    			      </tr>
		    			      <tr>
		    			          <td class="Label" id="SourceY">L:</td>
		    			          <td><input type="text" name="Ly" id="Ly"></td>
		    			          <td class="Label" id="DestinationY">y:</td>
		    			          <td><input type="text" readonly id="DestinationLy"></td>
		    			      </tr>
		    			      <tr>
		    			          <td class="Label" id="SourceH">H:</td>
		    			          <td><input type="text" name="Hh" id="Hh"></td>
		    			          <td class="Label" id="DestinationH">h:</td>
		    			          <td><input type="text" readonly id="DestinationHh"></td>
		    			      </tr>
	    			      </table>
    			     </form>
	    			 
	    			 <form id="FtpForm">
    			         <table>
    			             <tr>
    			                 <th colspan="3">Station quality report</th>
    			                 <td >
	    			                 <input id="checkQCReport" class="funcButton"  type="button" value="Check">
	    			                 <input id="downloadQCReport" class="funcButton"  type="button"  value="Download" />
	    			             </td>
    			             </tr>
    			              <tr>
	    			             <td class="Label">日期</td>
	    			             <td><input id="ConvertDate"  type="date"></td>
	    			             <td class="Label">站点</td>
	    			             <td><select id="StationList" ></select></td>
	    			         </tr>
    			         </table>
    			     </form>
    			 </div>
    		
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/additionalFeature.js" type="text/javascript"></script>
</html>