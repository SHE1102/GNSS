<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>设置</title>
        <link rel="SHORTCUT ICON" href="../img/btn.jpg"/>
        
        <link rel="stylesheet" type="text/css" href="../css/Frame.css"/>
        <link rel="stylesheet" type="text/css" href="../css/configSet.css"/>
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
    			 <div class="ConfigDiv">
    			     <form id="LogoForm" enctype="multipart/form-data">
	    			      <table>
	    			          <tr>
	    			             <th colspan="4">更改Logo图片</th>
	    			         </tr>
	    			          <tr>
	    			             <td class="Label">Logo图片</td>
	    			             <td ><input  id="LogoIcon" name="LogoIcon"  type="file" accept=".png"/></td>
	    			             <td class="Label"><input id="UploadLogo" type="button" class="FuncButton" value="上传" /></td>
	    			             <td></td>
		    			      </tr>
	    			      </table>
    			     </form>
    			     
    			     <form id="FtpForm">
    			         <table>
    			             <tr>
    			                 <th colspan="3">FTP服务器设置</th>
    			                 <td class="Label">
	    			                 <input id="StartFtp" type="button"  class="FuncButton" value="启动" />
	    			                 <input id="StopFtp" type="button"  class="FuncButton" value="停止" />
	    			             </td>
    			             </tr>
    			              <tr>
	    			             <td class="Label">端口</td>
	    			             <td><input  id="FtpPort" name="FtpPort"  type="text"/></td>
	    			             <td class="Label">文件根目录</td>
	    			             <td><input  id="HomeDirectory" name="HomeDirectory" type="text"/></td>
	    			         </tr>
    			              <tr>
	    			             <td class="Label">用户名</td>
	    			             <td><input  id="FtpName" name="FtpName"  type="text"/></td>
	    			             <td class="Label">密码</td>
	    			             <td><input  id="FtpPassword" name="FtpPassword" type="text"/></td>
	    			         </tr>
    			         </table>
    			     </form>
    			     
    			     <form id="ConfigForm">
	    			     <table id="ConfigTable">
	    			         <tr>
	    			             <th colspan="4">原始数据目录</th>
	    			         </tr>
	    			         <tr>
	    			             <td class="Label">原始目录</td>
	    			             <td colspan="3"><input  id="rawPath" name="rawPath"  type="text"/></td>
	    			         </tr>
	    			         <tr><th colspan="4"></th></tr>
	    			         <tr>
	    			             <th colspan="4">主机邮箱设置</th>
	    			         </tr>
	    			         <tr>
	    			             <td class="Label">主机邮箱</td>
	    			             <td><input  id="hostEmail" name="hostEmail"  type="text"/></td>
	    			             <td class="Label">邮箱密码</td>
	    			             <td><input  id="hostEmailPassword" name="hostEmailPassword" type="text"/></td>
	    			         </tr>
	    			         <tr>
	    			             <td class="Label">传输协议</td>
	    			             <td><input  id="hostEmailProtocol" name="hostEmailProtocol"  type="text"/></td>
	    			             <td class="Label" colspan="2"></td>
	    			         </tr>
	    			         <tr><th colspan="4"></th></tr>
	    			         <tr>
	    			             <th colspan="4">地图中心点设置</th>
	    			         </tr>
	    			         <tr>
	    			             <td class="Label">纬度</td>
	    			             <td><input  id="centerLat" name="centerLat"  type="text"/></td>
	    			             <td class="Label">经度</td>
	    			             <td><input  id="centerLon" name="centerLon"  type="text"/></td>
	    			         </tr>
	    			         <tr>
	    			             <td class="Label">缩放</td>
	    			             <td>
										<select id="zoom" name="zoom"></select>
                                 </td>
	    			             <td colspan="2"></td>
	    			         </tr>
	    			     </table>
	    			     <input id="SaveConfig" type="button" value="保存"/><br>
    			     </form>
    			 </div>
    		</div>
    		
    	</div>
    	
    	<%@ include file="foot.jsp" %>
 	</body>
 	<script src="../js/main.js" type="text/javascript"></script>
 	<script src="../js/configSet.js" type="text/javascript"></script>
</html>