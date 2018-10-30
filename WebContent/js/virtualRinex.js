$(function(){
	
   $("#date").val(getDefaultDate);
	
    function getDefaultDate() {
		var myDate = new Date();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate();
		month = (month.toString().length == 1) ? ("0" + month) : month;
		day = (day.toString().length == 1) ? ("0" + day) : day;

		var result = myDate.getFullYear() + '-' + month + '-' + day; //当前日期
		return result;
	}
    
	$(document).ready(function(){
		InitialOption();
	})
	
	function InitialOption(){
		var $zone = new Array();
		$zone[0] = $("<option value='0'>-12</option>");
		$zone[1] = $("<option value='1'>-11</option>");
		$zone[2] = $("<option value='2'>-10</option>");
		$zone[3] = $("<option value='3'>-9</option>");
		$zone[4] = $("<option value='4'>-8</option>");
		$zone[5] = $("<option value='5'>-7</option>");
		$zone[6] = $("<option value='6'>-6</option>");
		$zone[7] = $("<option value='7'>-5</option>");
		$zone[8] = $("<option value='8'>-4</option>");
		$zone[9] = $("<option value='9'>-3</option>");
		$zone[10] = $("<option value='10'>-2</option>");
		$zone[11] = $("<option value='11'>-1</option>");
		$zone[12] = $("<option value='12'>0</option>");
		$zone[13] = $("<option value='13'>+1</option>");
		$zone[14] = $("<option value='14'>+2</option>");
		$zone[15] = $("<option value='15'>+3</option>");
		$zone[16] = $("<option value='16'>+4</option>");
		$zone[17] = $("<option value='17'>+5</option>");
		$zone[18] = $("<option value='18'>+6</option>");
		$zone[19] = $("<option value='19'>+7</option>");
		$zone[20] = $("<option value='20'>+8</option>");
		$zone[21] = $("<option value='21'>+9</option>");
		$zone[22] = $("<option value='22'>+10</option>");
		$zone[23] = $("<option value='23'>+11</option>");
		$zone[24] = $("<option value='24'>+12</option>");
	
		for(var i=0; i<$zone.length; i++){
			$("#zone").append($zone[i]);
		}
		$("#zone").val("12");
		
		var $rinexVersion = new Array();
		$rinexVersion[0] = $("<option value='0'>Rinex2.10</option>");
		$rinexVersion[1] = $("<option value='1'>Rinex3.02</option>");
		
		for(var i=0; i<$rinexVersion.length; i++){
			$("#RinexVersion").append($rinexVersion[i]);
		}
		
		var $timeInterval = new Array();
		$timeInterval[4] = $("<option value='4'>1s</option>");
		$timeInterval[5] = $("<option value='5'>2s</option>");
		$timeInterval[6] = $("<option value='6'>5s</option>");
		$timeInterval[7] = $("<option value='7'>10s</option>");
		$timeInterval[8] = $("<option value='8'>15s</option>");
		$timeInterval[9] = $("<option value='9'>20s</option>");
		$timeInterval[10] = $("<option value='10'>30s</option>");
		$timeInterval[11] = $("<option value='11'>60s</option>");
		
		for(var i=0; i<$timeInterval.length; i++){
			$("#TimeInterval").append($timeInterval[i]);
		}
		
        $("#download").hide();
	}
	
	$("#start").click(function(){
		
		$("#start").attr("disabled","true");
		$("#download").hide();
		
		$.post("../VirtualRinex",$("#VirtualForm").serialize(),function(data){
			$("#state").text("Start...");
			$("#progressDiv").show();
			$("#download").attr("href","../VirtualDownload?fileName=" + data);
			interval = setInterval(GetProgress,"700"); 
		});
		return;
		
		var $lat = $("#latitude").val();
		var $lon = $("#longitude").val();
		var $alt = $("#altitude").val();
		
		if($lat.length == 0 || $lon.length == 0 || $alt.length == 0) {
			alert("is null");
			return;
		}
		
		$("#start").attr("disabled","true");
		$("#download").hide();
		
		$.post("../VirtualRinex",function(data){
						//data//转换文件存放的绝对路径
						$("#state").text("Start...");
						$("#progressDiv").show();
						$("#download").attr("href","../VirtualDownload?path=" + data);
						interval = setInterval(GetProgress,"700"); 
				});
		
	})
	
	function GetProgress() {
		$.post("../Progress",
			  {
	              name:"progress"
		      },
			  function(data,status){
				if(status == "success"){
					parseData(data);
				}
		});
	}
	
	function parseData(json) {
		 var process = eval("(" + json + ")");
		 
		 if(process.stepName == "null" || process.stepName == ""){
				return;
		 }
		 //更新进度信息
		 //process.stepName;
		 //process.pos/process.total;
		 $("#state").text(process.stepName);
		 $("#progressBar").val(process.pos);
		 $("#progressBar").text(process.pos + "%");
		 //setTimeout(function(){$("#progressBar").val(40);},2000);
		 
		 if(process.success == 0) {
			 clearInterval(interval);
			 $("#start").removeAttr("disabled");
		 } else if(process.success == 1) {
			 
		 } else if(process.success == 2) {
			 clearInterval(interval);
			 $("#start").removeAttr("disabled");
			 $("#download").show();
		 }
	}
	
});