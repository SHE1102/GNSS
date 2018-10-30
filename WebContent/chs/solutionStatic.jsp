<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>静态解算</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="../css/solutionStatic.css"/>
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
    			     <div id="Condition">
	    			     <span>日期:</span>
		    			 <input id="ConvertDate" type="date">
	    			     <span>站点:</span>
		    			 <select id="StationList" ></select>
	    			 </div>
	    			 <div class="TableDiv">
		    			 <table class="TableHeader">
			    			 <tr>
			    			     <th>名称</th>
								 <th>大小</th>
								 <th>时间</th>
								 <th></th>
			    			 </tr>
		    			 </table>
		    			 <div id="TableBodyDiv">
		    			     <table id="SkinTablebody" class="BodyTable"></table>
		    			 </div>
	    			 </div>
	    			 <span class="showlabel">解算列表</span>
	    			 <div class="TableDiv">
		    			 <table class="TableHeader">
			    			 <tr>
			    			     <th>名称</th>
								 <th>大小</th>
								 <th>时间</th>
								 <th></th>
			    			 </tr>
		    			 </table>
		    			 <div id="TableBodyDiv">
		    			     <table id="SolutionTablebody" class="BodyTable"></table>
		    			 </div>
	    			 </div>
	    			 <div>
					    <input id="Solution" type="button" value="解算"/>
					    <span>提示:解算完成后会自动将成果文件以邮件的形式发送到用户注册的邮箱中,无需在线等待</span>
					</div>
    			 </div>
    		</div>
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/solutionStatic.js" type="text/javascript"></script>
</html>