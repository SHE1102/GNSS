<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Solution</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/solution.css"/>
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <script type="text/javascript" src="../js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="../css/leaflet.css" >
        <script src="../js/leaflet.js"></script>
        
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	<%@ include file="menuHor.jsp" %>
    	
    	<div class="Body">
    		
    		<div class="Content"  >
    		   <form id="uploadForm">
   			      <table id="solutionTable">
   			          <tr>
   			             <td class="Label">Solution type</td>
   			             <td >
   			                 <input type="radio" name="solutionType" value="0" checked="checked"><span class="radioText">static</span>
  		                     <input type="radio" name="solutionType" value="1"><span class="radioText">dynamic</span>
   			             </td>
   			             <td></td>
   			          </tr>
   			          <tr>
   			             <td class="Label">Solution method</td>
   			             <td >
   			                 <input type="radio" name="solutionMethod" value="0" checked="checked"><span class="radioText">server file</span>
  		                     <input type="radio" name="solutionMethod" value="1"><span class="radioText">upload file</span>
   			             </td>
   			             <td></td>
    			      </tr>
    			      <tr>
    			          <td colspan="3"></td>
    			      </tr>
    			      <tr class="serverConfigTr">
   			             <td class="Label">Date</td>
   			             <td ><input id="date" type="date" value="2018-11-09"></td>
   			             <td></td>
	    			  </tr>
	    			  <tr class="serverConfigTr">
   			             <td class="Label">Start time(UTC)</td>
   			             <td ><input id="startTime" type="time" value="03:00"></td>
   			             <td></td>
	    			  </tr>
	    			  <tr class="serverConfigTr">
   			             <td class="Label">End time(UTC)</td>
   			             <td> <input id="endTime" type="time" value="05:00"></td>
   			             <td></td>
	    			  </tr>
   			          <tr class="uploadTr">
   			             <td class="Label">File</td>
   			             <td ><input  id="solutionFile" name="solutionFile"  type="file" accept=".dat,.*O"/></td>
   			             <td class="Label"><input id="uploadSolutionFile" type="button" class="FuncButton" value="Upload" /></td>
    			      </tr>
   			      </table>
   			    </form>
	    	     
	    	    <div>
					 <input id="solutionBtn" type="button" value="Solution"/>
					 <span>Tips:After the solution is completed, the result file will be automatically sent to the user's registered mailbox by email, without waiting online.</span>
				</div>
    		</div>
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/solution.js" type="text/javascript"></script>
</html>