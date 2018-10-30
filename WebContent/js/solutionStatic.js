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
    
    
    var skinDirArray = new Array();
	var selectArray = new Array();
    function addToTableBody(fileArray){
    	$(".add-tr").remove();
    	skinDirArray = fileArray.file;
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
			
			var $select = $("<input></input>");
			$select.attr("type","button").val("select").addClass("Select");
			//$check.attr("name","add-check");
			//$check.attr("relativePath",$file.relativePath);
			
			$td4.append($select);
			
			$tr.append($td1).append($td2).append($td3);
			$tr.append($td4).addClass("add-tr");
			
			$("#SkinTablebody").append($tr);
    	}
    }
    
    $("#SkinTablebody").on("click",".Select",function(){
		var index = $(this).parent().parent().index("#SkinTablebody tr");
		appendSelectTable(skinDirArray[index]);
	})
	
	function appendSelectTable(file){
		selectArray.push(file);
		
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
		
		var $delete = $("<input></input>").attr("type","button").addClass("Delete").val("delete");
		//$delete.attr("href","javascript:void(0);").addClass("delete").text("delete");
		$td4.append($delete);
		
		$tr.append($td1).append($td2).append($td3).append($td4).addClass("add-select-tr");
		$("#SolutionTablebody").append($tr);
	}
    
    $("#SolutionTablebody").on("click",".Delete",function(){
		var $tr = $(this).parent().parent();
		selectArray.splice($tr.index("#SolutionTablebody tr"),1);
		$tr.remove();
	})
    
    $("#Solution").click(function(){
    	if(selectArray.length == 0){
			alert($langXml.find("ids_choose_two_file").text());
			return;
		}
		
		var $selectFile = "";
		for(var i=0; i<selectArray.length; i++){
			if($selectFile.indexOf(selectArray[i].relativePath) == -1){
				$selectFile += selectArray[i].relativePath;
				$selectFile += ":";
			}
		}
		$.post("../SolutionStatic",
				{
			        file:$selectFile
				});
		
    })
})