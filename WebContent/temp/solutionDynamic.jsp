<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>静态解算</title>
        <link rel="SHORTCUT ICON" href="img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="css/solutionDynamic.css"/>
        <script type="text/javascript" src="js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="css/leaflet.css" >
        <script src="js/leaflet.js"></script>
        
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	
    	<div class="Body">
    		<%@ include file="menu.jsp" %>
    		
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
		    			 <div class="TableBodyDiv">
		    			     <table id="SkinTablebody" class="BodyTable"></table>
		    			 </div>
	    			 </div>
	    			 <div >
						<div class="SolutionDiv1">
							<span class="showlabel">基站</span>
							<div class="SolutionTableDiv"  id="selectBaseDiv">
						        <table class="TableHeader">
									<tr>
										<th>名称</th>
										<th>大小</th>
										<th>时间</th>
										<th>选择</th>
									</tr>
							    </table>
							    <div class="TableBodyDiv">
							        <table id="SelectBaseTable" class="BodyTable"></table>
							    </div>
							</div>
						</div>
			
						<div class="SolutionDiv2">
							<span class="showlabel">移动站</span>
							<div class="SolutionTableDiv"  id="selectRoverDiv">
						        <table class="TableHeader">
									<tr >
										<th>名称</th>
										<th>大小</th>
										<th>时间</th>
										<th>选择</th>
									</tr>
							    </table>
							    <div class="TableBodyDiv">
							        <table id="SelectRoverTable" class="BodyTable"></table>
							    </div>
							</div>
						
						</div>
					 </div>
	    			 <div>
					    <input id="Solution" type="button" value="解算"/>
					    <span>提示:解算完成后会自动将成果文件以邮件的形式发送到用户注册的邮箱中,无需在线等待</span>
					 </div>
    			 </div>
    		</div>
    		
    	</div>
    	
    	<div class="Foot"></div>
 	</body>
 	<script src="js/main.js" type="text/javascript"></script>
 	<script src="js/solutionDynamic.js" type="text/javascript"></script>
</html>