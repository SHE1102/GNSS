<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>静态解算</title>
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
   			      <table>
   			          <tr>
   			             <td class="Label">解算类型</td>
   			             <td >
   			                 <input type="radio" name="solutionType" value="0" checked="checked"><span class="radioText">静态解算</span>
  		                     <input type="radio" name="solutionType" value="1"><span class="radioText">动态解算</span>
   			             </td>
   			              <td></td>
   			              <td></td>
   			          </tr>
   			          <tr>
   			             <td class="Label">解算方式</td>
   			             <td >
   			                 <input type="radio" name="solutionMethod" value="0" checked="checked"><span class="radioText">使用服务器文件</span>
  		                     <input type="radio" name="solutionMethod" value="1"><span class="radioText">使用上传文件</span>
   			             </td>
   			             <td></td>
   			              <td></td>
    			      </tr>
   			      </table>
    			     
    		     
    		     <div id="serverSolution">
    		          <table>
   			          <tr>
   			             <td class="Label">日期</td>
   			             <td ><input id="date" type="date" value="2018-11-09"></td>
   			             <td colspan="2"></td>
    			      </tr>
    			      <tr>
   			             <td class="Label">起始时间</td>
   			             <td ><input id="startTime" type="time" value="03:00"></td>
   			             <td class="Label">终止时间</td>
   			             <td > <input id="endTime" type="time" value="05:00"></td>
    			      </tr>
   			      </table>
    			 <div id="TableBodyDiv">
    			     <table id="SkinTablebody" class="BodyTable"></table>
    			 </div>
	    	     </div>
	    	     
	    	     <div id="uploadSolution">
	    	         <form id="uploadForm">
			    	     <table>
	    			         <tr>
	    			             <th colspan="4">选择上传文件</th>
	    			         </tr>
	    			         <tr>
	    			             <td class="Label">文件</td>
	    			             <td ><input  id="solutionFile" name="solutionFile"  type="file" accept=".dat"/></td>
	    			             <td class="Label"><input id="uploadSolutionFile" type="button" class="FuncButton" value="上传" /></td>
	    			             <td></td>
		    			     </tr>
	    			     </table>
    			     </form>
	    	    </div>
	    	     
	    	    <div>
					 <input id="solutionBtn" type="button" value="解算"/>
					 <span>提示:解算完成后会自动将成果文件以邮件的形式发送到用户注册的邮箱中,无需在线等待</span>
				</div>
    		</div>
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/solution.js" type="text/javascript"></script>
</html>