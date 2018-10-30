<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	
  		<div class="MenuBar">
  			<ul>
  				<li>
  					<div class="ItemBar">信息</div>
  					<ul class="SubMenu">
  						<li ><a href="#">网络</a></li>
  						<li><a href="#">通讯录</a></li>
  					</ul>
  				</li>
  				<li>
  					<div class="ItemBar">站点</div>
  					<ul class="SubMenu"> 
  						<li><a href="stationMap.jsp">地图<span id="SelectMap"></span></a></li>
  						<li><a href="stationList.jsp">列表<span id="SelectList"></span></a></li>
  						<li><a href="stationSkyplot.jsp">星图<span id="SelectSkyplot"></span></a></li>
  					</ul>
  				</li>
  				<li class="LinkItemBar"><a href="downloadRinex30.jsp">下载Rinex 30s<span id="SelectDownloadDaily"></span></a></li>
  				<li class="LinkItemBar"><a href="register.jsp">注册<span id="SelectRegister"></span></a></li>
  				<li>
  					<div class="ItemBar">用户区</div>
  					<ul class="SubMenu">
  						<li><a href="stationMapUser.jsp">网络状态<span id="SelectMapUser"></span></a></li>
  						<li><a href="stationListUser.jsp">站点列表<span id="SelectListUser"></span></a></li>
  						<li><a href="downloadRinex.jsp">下载Rinex<span id="SelectDownload"></span></a></li>
  						<li><a href="virtualRinex.jsp">虚拟站<span id="SelectVirtual"></span></a></li>
  						<li><a href="solutionStatic.jsp">静态解算<span id="SelectSolutionStatic"></span></a></li>
  						<li><a href="solutionDynamic.jsp">动态解算<span id="SelectSolutionDynamic"></span></a></li>
  					</ul>
  				</li>
  				<li>
  					<div class="ItemBar">管理</div>
  					<ul class="SubMenu">
  						<li><a href="configSet.jsp">设置<span id="SelectSetting"></span></a></li>
  						<li><a href="customerManage.jsp">用户管理<span id="SelectCustomer"></span></a></li>
  						<li><a href="#">访问统计</a></li>
  						<li><a href="#">插入表格</a></li>
  					</ul>
  				</li>
  			</ul>
  		</div>
    		