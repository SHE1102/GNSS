<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	
  		<div class="MenuBarHor">
  			<ul>
  				<li class="dropdownItemBar"><a href="#" class="">信息</a>
  					<ul class="submenuHor">
  						<li ><a href="network.jsp">网络</a></li>
  						<li><a href="contacts.jsp">通讯录</a></li>
  					</ul>
  				</li>
  				<li class="dropdownItemBar"><a class="I">站点</a>
  					<ul class="submenuHor"> 
  						<li><a href="stationMap.jsp">地图</a></li>
  						<li><a href="stationList.jsp">列表</a></li>
  					</ul>
  				</li>
  				<li class=""><a href="downloadRinex30.jsp">下载Rinex 30s</a></li>
  				<li class=""><a href="register.jsp">注册</a></li>
  				<li class="dropdownItemBar"><a class="I">用户区</a>
  					<ul class="submenuHor">
  						<li id="customerModule"><a href="accountInfo.jsp">用户信息</a></li>
  						<li id="stationMapModule"><a href="stationMapUser.jsp">网络状态</a></li>
  						<li id="stationListModule"><a href="stationListUser.jsp">站点列表</a></li>
  						<li id="skyplotModule"><a href="stationSkyplot.jsp">星图</a></li>
  						<li id="downloadRinexModule"><a href="downloadRinex.jsp">下载Rinex</a></li>
  						<li id="downloadVirtualModule"><a href="virtualRinex.jsp">虚拟站</a></li>
  						<li id="solutionStaticModule"><a href="solutionStatic.jsp">静态解算</a></li>
  						<li id="solutionDynamicModule"><a href="solutionDynamic.jsp">动态解算</a></li>
  						<li id="additionalModule"><a href="additionalFeature.jsp">附加功能</a></li>
  					</ul>
  				</li>
  				<li class="dropdownItemBar"><a class="I">管理</a>
  					<ul class="submenuHor">
  						<li><a href="configSet.jsp">设置</a></li>
  						<li><a href="customerManage.jsp">用户管理</a></li>
  						<li><a href="statistic.jsp">访问统计</a></li>
  						<li><a href="monograph.jsp">插入表格</a></li>
  						<!-- <li><a href="customerAdmin.jsp">管理员用户</a></li> -->
  					</ul>
  				</li>
  			</ul>
  			
  			<span id="loginstaus"></span>
	        <form action="../Loginout" method="post">
	             <input id="login" type="submit" value="登录"/>
	        </form> 
  		</div>
    		