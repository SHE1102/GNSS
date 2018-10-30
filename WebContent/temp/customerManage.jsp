<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户管理</title>
        <link rel="SHORTCUT ICON" href="img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="css/customerManage.css"/>
        <script type="text/javascript" src="js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="css/leaflet.css" >
        <script src="js/leaflet.js"></script>
        
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	
    	<div class="Body">
    		<%@ include file="menu.jsp" %>
    		
    		<div class="Content"  >
    			 <div id="customer">
    			 	<div id="TableDiv">
	    			     <table id="TableHeader">
	    			         <tr>
	    			             <th>用户名</th>
	    			             <th>密码</th>
	    			             <th>邮箱</th>
	    			             <th>权限</th>
	    			             <th>可用状态</th>
	    			             <th></th>
	    			         </tr>
	    			     </table>
	    			     <div id="TableBodyDiv">
	    			         <table id="TableBody">
	    			         </table>
	    			     </div>
    			     </div>
    			 </div>
    		</div>
    		
    	</div>
    	
    	<div class="Foot"></div>
 	</body>
 	<script src="js/main.js" type="text/javascript"></script>
 	<script src="js/customerManage.js" type="text/javascript"></script>
</html>