$(function(){
	
	$("#ConvertDate").val(getDefaultDate);
	
    function getDefaultDate() {
		var myDate = new Date();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate() - 1;
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
			
			var $option = $("<option></option>").val(1).text(station.id + "-" + station.name);
			$("#StationList").append($option);
		}
	}
    
    $("#ConvertDate").change(function(){
    	searchFile();
    })
    
    $("#StationList").change(function(){
    	searchFile();
    })
    
    function searchFile(){
    	var $date = $("#ConvertDate").val();
    	var $index = $("#StationList").children("option:selected").val();
    	var $stationName = $("#StationList").children("option:selected").text();
    	
    	if($index == 0){
    		return;
    	}
    	
    	var $arr = $stationName.split("-");
    	
    	$.getJSON("../SkinFile",
    			{
			        type: "DailyRinexData",
			        date: $date,
			        stationName: $arr[1]
				},
				function(data){
					addToTableBody(data);
    	})
    }
    
    function addToTableBody(fileArray){
    	$(".add-tr").remove();
    	
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
			
			var $check = $("<input></input>");
			$check.attr("type","checkbox");
			$check.attr("name","add-check");
			$check.attr("relativePath",$file.relativePath);
			
			$td4.append($check).css("text-indent","15px");
			
			$tr.append($td1).append($td2).append($td3);
			$tr.append($td4).addClass("add-tr");
			
			$("#tablebody").append($tr);
    	}
    }
    
    $("#Download").click(function(){
    	var $fileString="";
		$("#tablebody input:checked").each(function(){
			$fileString += $(this).attr("relativePath");
			$fileString += ":";
		})
		
		 var $downloadForm = $("<form method='post'></form>");
		 $downloadForm.attr("action","../Download?fileString="+$fileString);
         $(document.body).append($downloadForm);
         $downloadForm.submit();
    })
    
    $("#CheckAll").click(function(){
    	if($(this).prop("checked")){
    		$("#tablebody input").prop("checked",true);    		
    	} else {
    		$("#tablebody input").prop("checked",false);    		
    	}
    })
    
})