<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dynamic Solution</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="../css/solutionDynamic.css"/>
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
    			     <div id="Condition">
	    			     <span>Date:</span>
		    			 <input id="ConvertDate" type="date">
	    			     <span>Stations:</span>
		    			 <select id="StationList" ></select>
	    			 </div>
	    			 <div class="TableDiv">
		    			 <table class="TableHeader">
			    			 <tr>
			    			     <th>Name</th>
								 <th>Size</th>
								 <th>Time</th>
								 <th></th>
			    			 </tr>
		    			 </table>
		    			 <div class="TableBodyDiv">
		    			     <table id="SkinTablebody" class="BodyTable"></table>
		    			 </div>
	    			 </div>
	    			 <div >
						<div class="SolutionDiv1">
							<span class="showlabel">Base</span>
							<div class="SolutionTableDiv"  id="selectBaseDiv">
						        <table class="TableHeader">
									<tr>
										<th>Name</th>
										<th>Size</th>
										<th>Time</th>
										<th></th>
									</tr>
							    </table>
							    <div class="TableBodyDiv">
							        <table id="SelectBaseTable" class="BodyTable"></table>
							    </div>
							</div>
						</div>
			
						<div class="SolutionDiv2">
							<span class="showlabel">Rover</span>
							<div class="SolutionTableDiv"  id="selectRoverDiv">
						        <table class="TableHeader">
									<tr >
										<th>Name</th>
										<th>Size</th>
										<th>Time</th>
										<th></th>
									</tr>
							    </table>
							    <div class="TableBodyDiv">
							        <table id="SelectRoverTable" class="BodyTable"></table>
							    </div>
							</div>
						
						</div>
					 </div>
	    			 <div>
					    <input id="Solution" type="button" value="Solution"/>
					    <span>Tips:After the solution is completed, the result file will be automatically sent to the user's registered mailbox by email, without waiting online.</span>
					 </div>
    			 </div>
    		</div>
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/solutionDynamic.js" type="text/javascript"></script>
</html>