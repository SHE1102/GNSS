<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	
  		<div class="MenuBarHor">
  			<ul>
  				<li class="dropdownItemBar"><a href="#" class="">Info</a>
  					<ul class="submenuHor">
  						<li ><a href="network.jsp">Network</a></li>
  						<li><a href="contacts.jsp">Contacts</a></li>
  					</ul>
  				</li>
  				<li class="dropdownItemBar"><a class="I">Stations</a>
  					<ul class="submenuHor"> 
  						<li><a href="stationMap.jsp">Network Map</a></li>
  						<li><a href="stationList.jsp">Station List</a></li>
  					</ul>
  				</li>
  				<li class=""><a href="downloadRinex30.jsp">Download Daily Rinex</a></li>
  				<li class=""><a href="register.jsp">Register</a></li>
  				<li class="dropdownItemBar"><a class="I">User Area</a>
  					<ul class="submenuHor">
  						<li><a href="accountInfo.jsp">Account Info</a></li>
  						<li><a href="stationMapUser.jsp">Network Status</a></li>
  						<li><a href="stationListUser.jsp">Station List</a></li>
  						<li><a href="stationSkyplot.jsp">Skyplot</a></li>
  						<li><a href="downloadRinex.jsp">Download Rinex</a></li>
  						<li><a href="virtualRinex.jsp">Virtual Rinex</a></li>
  						<!-- <li><a href="solutionStatic.jsp">静态解算</a></li>
  						<li><a href="solutionDynamic.jsp">动态解算</a></li>
  						<li><a href="coordinateConvert.jsp">坐标转换</a></li> -->
  						<li><a href="additionalFeature.jsp">Additional Function</a></li>
  					</ul>
  				</li>
  				<li class="dropdownItemBar"><a class="I">Administration</a>
  					<ul class="submenuHor">
  						<li><a href="configSet.jsp">Setting</a></li>
  						<li><a href="customerManage.jsp">User Management</a></li>
  						<li><a href="#">User Statistics</a></li>
  						<li><a href="monograph.jsp">Monograph</a></li>
  						<li><a href="customerAdmin.jsp">Interface management</a></li>
  					</ul>
  				</li>
  			</ul>
  			
  			<span id="loginstaus"></span>
	        <form action="../Loginout" method="post">
	             <input id="login" type="submit" value="Login"/>
	        </form> 
  		</div>
    		