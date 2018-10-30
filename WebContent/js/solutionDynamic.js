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
		showList(json);
	})
	
	function showList(json){
		var $option = $("<option></option>").val(0).text("--select--");
		$("#StationList").append($option);
		
		for(var i=0;i<json.station.length;i++){
			var station = json.station[i];
			
			var $option = $("<option></option>").val(1).text(station.name);
			$("#StationList").append($option);
		}
	}
    
    $("#StationList").change(function(){
    	var $date = $("#ConvertDate").val();
    	var $stationName = $(this).children("option:selected").text();
    	
    	$.getJSON("../SkinFile",
    			{
			        type: "RawData",
			        date: $date,
			        stationName: $stationName
				},
				function(data){
					addToTableBody(data);
    	})
    })
    
    var $SkinDirArray = new Array();
	var $BaseArray = new Array();
	var $RoverArray = new Array();
	
    function addToTableBody(fileArray){
    	$(".add-tr").remove();
    	$SkinDirArray = fileArray.file;
    	for(var i=0; i<fileArray.file.length; i++){
    		var $file = fileArray.file[i];
    		
    		var $tr = $("<tr></tr>");
			var $td1 = $("<td></td>");
			var $td2 = $("<td></td>");
			var $td3 = $("<td></td>");
			var $td4 = $("<td></td>");
			
			$td1.text($file.name);
			$td2.text($file.size + "kb");
			$td3.text($file.lastModifyTime);
			
			var $base = $("<input></input>");
			$base.attr("type","button").val("base").addClass("Base").addClass("ControlButton");
			var $rover = $("<input></input>");
			$rover.attr("type","button").val("rover").addClass("Rover").addClass("ControlButton");
			
			$td4.append($base).append($rover);
			
			$tr.append($td1).append($td2).append($td3);
			$tr.append($td4).addClass("add-tr");
			
			$("#SkinTablebody").append($tr);
    	}
    }
    
    $("#SkinTablebody").on("click",".Base",function(){
		var index = $(this).parent().parent().index("#SkinTablebody tr");
		appendSelectTable(0, $SkinDirArray[index]);
	})
	
	$("#SkinTablebody").on("click",".Rover",function(){
		var index = $(this).parent().parent().index("#SkinTablebody tr");
		appendSelectTable(1, $SkinDirArray[index]);
	})
	
	function appendSelectTable(index, file){
		var $tr = $("<tr></tr>");
		var $td1 = $("<td></td>");
		var $td2 = $("<td></td>");
		var $td3 = $("<td></td>");
		var $td4 = $("<td></td>");
		
		var $i = $("<i></i>");
		var $a = $("<a></a>");
		
		$i.addClass("fa fa-file-text-o");
		$a.text(file.name);
		
		$td1.append($i).append($a);
		$td2.text(file.size + "kb");
		$td3.text(file.lastModifyTime);
		
		var $delete = $("<input></input>").attr("type","button").addClass("Delete").addClass("ControlButton").val("delete");
		$td4.append($delete);
		
		$tr.append($td1).append($td2).append($td3).append($td4);
		
		if(index ==0){
			$("#SelectBaseTable").append($tr);
			$BaseArray.push(file);
		} else {
			$("#SelectRoverTable").append($tr);
			$RoverArray.push(file);
		}
	}
    
    $("#SelectBaseTable").on("click",".Delete",function(){
		var $tr = $(this).parent().parent();
		$BaseArray.splice($tr.index("#SelectBaseTable tr"),1);
		$tr.remove();
	})
	$("#SelectRoverTable").on("click",".Delete",function(){
		var $tr = $(this).parent().parent();
		$RoverArray.splice($tr.index("#SelectRoverTable tr"),1);
		$tr.remove();
	})
    
    $("#Solution").click(function(){
    	if($BaseArray.length == 0 || $RoverArray.length == 0){
			alert($langXml.find("ids_choose_base_rover").text());
			return;
		}
		
		var $BaseFile = "";
		for(var i=0; i<$BaseArray.length; i++){
			if($BaseFile.indexOf($BaseArray[i].relativePath) == -1){
				$BaseFile += $BaseArray[i].relativePath;
				$BaseFile += ":";
			}
		}
		var $RoverFile = "";
		for(var i=0; i<$RoverArray.length; i++){
			if($RoverFile.indexOf($RoverArray[i].relativePath) == -1){
				$RoverFile += $RoverArray[i].relativePath;
				$RoverFile += ":";
			}
		}
		
		$.post("../SolutionDynamic",
				{
			    	baseFile:$BaseFile,
			    	roverFile:$RoverFile
				});
		
    })
})