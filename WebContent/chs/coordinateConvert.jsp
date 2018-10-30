<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>坐标转换</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <script type="text/javascript" src="../js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="../css/leaflet.css" >
        <script src="../js/leaflet.js"></script>
        <link rel="stylesheet" type="text/css" href="../css/coordinateConvert.css">
    
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	<%@ include file="menuHor.jsp" %>
    	
    	<div class="Body">
    		<!-- <%@ include file="menu.jsp" %> -->
    		
    		<div class="Content"  >
    			 <div id="OSM">
    			     <form id="UploadCoordinateForm" enctype="multipart/form-data">
	    			      <table>
	    			          <tr>
	    			             <td class="Label">坐标文件</td>
	    			             <td colspan="2"><input type="file"  id="CoordinateFile"  name="CoordinateFile" accept=".sp"></td>
	    			             <td colspan="2">
	    			                 <input type="button"  id="UploadFile" value="上传">
	    			                 <input type="button"  id="DetailCoord" value="详细信息"><br>
                                 </td>
		    			      </tr>
	    			      </table>
    			     </form>
    			     
    			     <form id="ConvertForm" >
	    			      <table>
	    			          <tr>
	    			             <td class="Label">转换方式</td>
	    			             <td colspan="4" >
	    			                 <input type="radio" name="ConvertType" value="0" checked="checked">BLH->xyh
	    			                 <input type="radio" name="ConvertType" value="1">xyh->BLH
	    			             </td>
		    			      </tr>
		    			      <tr>
		    			          <td class="Label" id="SourceX">B:</td>
		    			          <td><input type="text" name="Bx"></td>
		    			          <td class="Label" rowspan="3"><input id="CoordinateConvert" type="button" value="转换"></td>
		    			          <td class="Label" id="DestinationX">x:</td>
		    			          <td><input type="text" readonly id="DestinationBx"></td>
		    			      </tr>
		    			      <tr>
		    			          <td class="Label" id="SourceY">L:</td>
		    			          <td><input type="text" name="Ly"></td>
		    			          <td class="Label" id="DestinationY">y:</td>
		    			          <td><input type="text" readonly id="DestinationLy"></td>
		    			      </tr>
		    			      <tr>
		    			          <td class="Label" id="SourceH">H:</td>
		    			          <td><input type="text" name="Hh"></td>
		    			          <td class="Label" id="DestinationH">h:</td>
		    			          <td><input type="text" readonly id="DestinationHh"></td>
		    			      </tr>
	    			      </table>
    			     </form>
    			 </div>
    		</div>
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/coordinateConvert.js" type="text/javascript"></script>
</html>