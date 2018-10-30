$(function(){
	var $langXml;
	$.post("../LangResource",function(xml){
		$langXml = $(xml);
	})
	
	$("#ConvertDate").val(getDefaultDate);
	
    function getDefaultDate() {
		var myDate = new Date();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate();
		month = (month.toString().length == 1) ? ("0" + month) : month;
		day = (day.toString().length == 1) ? ("0" + day) : day;

		var result = myDate.getFullYear() + '-' + month + '-' + day; //当前日期
		return result;
	}
    
    $.getJSON("../Station", {userType: "Tourist"}, function(json){
		showSelectList(json);
	})
	
	function showSelectList(json){
		var $option = $("<option></option>").val(0).text("--select--");
		$("#StationList").append($option);
		
		for(var i=0;i<json.station.length;i++){
			var station = json.station[i];
			
			var $option = $("<option></option>").val(1).text(station.id + "-" + station.name);
			$("#StationList").append($option);
		}
	}
    
    InitialOption();
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
			$("#Zone").append($zone[i]);
		}
		$("#Zone").val("12");
		
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
		
		$("#downloadFile").hide();
	}
    
    
//    $("#StationList").change(function(){
//    	var $date = $("#ConvertDate").val();
//    	var $stationName = $(this).children("option:selected").text();
//    	
//    	$.getJSON("../SkinFile",
//    			{
//    		        type: "RawData",
//    		        date: $date,
//    		        stationName: $stationName
//    			},
//    			function(data){
//    				addToTableBody(data);
//    	})
//    })
    
    function addToTableBody(fileArray){
    	for(var i=0; i<fileArray.file.length; i++){
    		var $file = fileArray.file[i];
    		
    		var $tr = $("<tr></tr>");
			var $td1 = $("<td></td>");
			var $td2 = $("<td></td>");
			var $td3 = $("<td></td>");
			var $td4 = $("<td></td>");
			var $td5 = $("<td></td>");
			var $td6 = $("<td></td>");
			
			$td1.text($file.name);
			$td2.text($file.size + "kb");
			$td3.text($file.date);
			$td4.text($file.startDateTime);
			$td5.text($file.endDateTime);
			
			var $check = $("<input></input>");
			$check.attr("type","checkbox");
			$check.attr("name","add-check");
			$check.val($file.fileRelativePath);
			$check.attr("relativePath",$file.relativePath);
			
			$td6.append($check);
			
			$tr.append($td1).append($td2).append($td3);
			$tr.append($td4).append($td5).append($td6).addClass("add-tr");
			
			$("#TableBodyDiv table").append($tr);
    	}
    }
    
    $("#start").click(function(){
    	var $FileString = "";
		$("input:checkbox[name='add-check']:checked").each(function() { // 遍历name=test的多选框
			$FileString += $(this).attr("relativePath") + ":";  // 每一个被选中项的值
			});
		
		//var $par = $("#RinexForm").serialize();
		
		//alert($par + "&FileString=" + $FileString);
		
//		$.post("ConvertRinex",$par, function(data){
//			//interval = setInterval(GetProgress,"500"); 
//			//$("#state").text("Start...");
//			//$("#progressDiv").show();
//			//$("#downloadFile").attr("href","../RinexDownloadServlet?file=" + data);
//		})
		var $Zone = $("#Zone").val();
		var $RinexVersion, $Mixture, $TimeInterval, $FrePoint;
		var $CheckFlag = new Array(); 
		var $SystemFlag = new Array(),
		
		$RinexVersion = $("#RinexVersion").find("option:selected").val(); 
		$Mixture = $("#Mixture").prop('checked');
		$TimeInterval = $("#TimeInterval").find("option:selected").val();
		
		$CheckFlag[0] = $("#checkbox1").prop('checked');
		$CheckFlag[1] = $("#checkbox2").prop('checked');
		$CheckFlag[2] = $("#checkbox3").prop('checked');
		$CheckFlag[3] = $("#checkbox4").prop('checked');
		
		$SystemFlag[0] = $("#SatelliteSystem1").prop('checked');
		$SystemFlag[1] = $("#SatelliteSystem2").prop('checked');
		$SystemFlag[2] = $("#SatelliteSystem3").prop('checked');
		$SystemFlag[3] = $("#SatelliteSystem4").prop('checked');
		$SystemFlag[4] = $("#SatelliteSystem5").prop('checked');
		$SystemFlag[5] = $("#SatelliteSystem6").prop('checked');
		
		if($SystemFlag[0] == false && $SystemFlag[1] == false &&
			$SystemFlag[2] == false && $SystemFlag[3] == false &&
			$SystemFlag[4] == false && $SystemFlag[5] == false ){
			
			alert($langXml.find("ids_choose_satellite").text());
			return;
		}
		
		$("#downloadFile").hide();
		
		$FrePoint = $("input[name='FrequencyPoint']:checked").val();
		
		var $StartTime,$EndTime;
		$StartTime = $("#StartTime").val();
		$EndTime = $("#EndTime").val();
		var $Date = $("#ConvertDate").val();
    	var $StationName = $("#StationList").children("option:selected").text();
    	
    	var $arr = $StationName.split("-");
		
		$.post("../ConvertRinex",
				{
			        RinexVersion:$RinexVersion,
			        Mixture:$Mixture,
			        TimeInterval:$TimeInterval,
			        CheckFlag0:$CheckFlag[0],
			        CheckFlag1:$CheckFlag[1],
			        CheckFlag2:$CheckFlag[2],
			        CheckFlag3:$CheckFlag[3],
			        SystemFlag0:$SystemFlag[0],
			        SystemFlag1:$SystemFlag[1],
			        SystemFlag2:$SystemFlag[2],
			        SystemFlag3:$SystemFlag[3],
			        SystemFlag4:$SystemFlag[4],
			        SystemFlag5:$SystemFlag[5],
			        FrePoint:$FrePoint,
			        Date:$Date,
			        StationName:$arr[1],
			        StartTime:$StartTime,
			        EndTime:$EndTime,
			        FileString:$FileString,
			        Zone:$Zone
				},
				function(data,status){
					if(status == "success"){
						if(data == "false"){
							alert($langXml.find("ids_no_data").text());
						}else{
							$("#start").attr('disabled',"true");
							
							interval = setInterval(GetProgress,"500"); 
							$("#state").text("Start...");
							$("#progressDiv").show();
							$("#downloadFile").attr("href","../DownloadConvertRinex?file=" + data);
						}
					}
				});
    })
    
    function GetProgress() {
		$.post("../Progress",{name:"progress"},function(data,status){
				   if(status == "success"){
					    ParseData(data);
				   }
			});
	}
	
	function ParseData(json) {
		var process = eval("(" + json + ")");
		
		if(process.stepName == "null" || process.stepName == ""){
			return;
		}
		
		 //更新进度信息
		 $("#state").text(process.stepName);
		 $("#progressBar").val(process.pos);
		 $("#progressBar").text(process.pos + "%");
		 
		 if(process.success == 0) {
			clearInterval(interval);
			$("#start").removeAttr("disabled");
		 } else if(process.success == 1) {
		 } else if(process.success == 2) {
			clearInterval(interval);
			$("#start").removeAttr("disabled");
			$("#downloadFile").show();
		}
	}
    
})