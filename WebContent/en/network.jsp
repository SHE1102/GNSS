<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Network</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <script type="text/javascript" src="../js/jquery-1.9.0.min.js" ></script>
        <link rel="stylesheet" type="text/css" href="../css/leaflet.css" >
        <script src="../js/leaflet.js"></script>
        
        <style>
            #NetWorkDiv{width:100%;height:100%;}
            span#SelectNetWork{float:right;border:20px solid transparent;border-right-color: #DDD;}
            #img{
                 height:auto;
			     width:100%;
			     color:#FFFFFF;
			     text-align:center;
            }
            
             #img img{
                 height:auto;
			     width:auto;
			     align:center;
             }
             
           
            #NetWorkDiv li{
                 margin-left:40px;
                 color:#000;
             }
        </style>
    </head>
    <body>
    	<%@ include file="header.jsp" %>
    	<%@ include file="menuHor.jsp" %>
    	
    	<div class="Body">
    		<div class="Content"  >
    			 <div id="NetWorkDiv">
    			     <p>
    			     Welcome to <b>Cube-net Web</b> site.This site shows the GNSS Network managed by STONEX <b>Cube-net</b> software. 
    			     The Network consists of more than 50 permanent stations whose data stream is kindly offered by various national 
    			     and international public institutions. The Network has no commercial purpose, it is used to test and to demonstrate 
    			     our software.
    			     </p>
    			     <div id="img">
    			         <img  src="../img/network1.png"/>
    			     </div>
    			     <p>
    			         The <b>Cube-net</b> software is the STONEX application that allows to manage the GNSS Networks. The software is 
    			         compatible with data streams produced by STONEX receivers and also with receivers of other brands using the 
    			         standard RTCM format. It allows to:
    			     </p>
    			     <ul>
    			         <li>Monitor in real time the status of the Network</li>
    			         <li>Record the data of the permanent stations</li>
    			         <li>Calculate RTK corrections to be sent to users for real-time precise positioning</li>
    			     </ul><br>
    			     
    			     <p>
    			         The <b>Cube-net Web</b> site is the Web interface to Cube-net. It allows to:
    			     </p>
    			     <ul>
    			         <li>Manage the users of the Network</li>
    			         <li>Publish the data of the stations belonging to the Network</li>
    			         <li>Download the data for the post-processing users</li>
    			     </ul>

    			 </div>
    		</div>
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
</html>