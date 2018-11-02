$(function(){
	
	var $lang;
	if (navigator.userLanguage) {    
		 $lang = navigator.userLanguage.substring(0,2).toLowerCase();    
	} else {    
	     $lang = navigator.language.substring(0,2).toLowerCase();    
	}
	
	
	var $langXml;
	$.post("../LangResource",function(xml){
		$langXml = $(xml);
	})
	
	$("#DetailCoord").prop("disabled","disabled");
	$("#CoordinateConvert").prop("disabled","disabled");
	//==========================
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
    
    $("#checkQCReport").click(function(){
    	var $date = $("#ConvertDate").val();
    	var $index = $("#StationList").children("option:selected").val();
    	var $stationName = $("#StationList").children("option:selected").text();
    	
    	if($index == 0){
    		alert($langXml.find("ids_choose_station").text());
    		return;
    	}
    	
    	var $arr = $stationName.split("-");
    	
    	$.post("../getQCReport",{date:$date,stationName:$arr[1]},function(data){
    		if(data == "false"){
    			alert($langXml.find("ids_no_report").text());
    		} else {
    			var $href = "../chcekQCReport?reportPath=" + data ;
    			window.open($href, '_blank');
    		}
    	})
    	
    })
    
    $("#downloadQCReport").click(function(){
    	
    	var $date = $("#ConvertDate").val();
    	var $index = $("#StationList").children("option:selected").val();
    	var $stationName = $("#StationList").children("option:selected").text();
    	
    	if($index == 0){
    		alert($langXml.find("ids_choose_station").text());
    		return;
    	}
    	
    	var $arr = $stationName.split("-");
    	
    	$.post("../getQCReport",{date:$date,stationName:$arr[1]},function(data){
    		if(data == "false"){
    			alert($langXml.find("ids_no_report").text());
    		} else {
    			 var $downloadForm = $("<form method='post'></form>");
    			 $downloadForm.attr("action","../downloadQCReport?reportPath="+ data);
    	         $(document.body).append($downloadForm);
    	         $downloadForm.submit();
    		}
    	})
    	
    })
    ///===========================
	
	$("#CoordinateConvert").click(function(){
		
		if($("#Bx").val().length <= 0 ||
			$("#Ly").val().length <= 0 ||
			$("#Hh").val().length <= 0 ){
			alert($langXml.find("ids_coor_not_empty").text());
			return;
		}
		
		$.getJSON("../CoordinateConvert", $("#ConvertForm").serialize(),function(json){
			$("#DestinationBx").val(json.destinationBx);
			$("#DestinationLy").val(json.destinationLy);
			$("#DestinationHh").val(json.destinationHh);
		});
	})
	
	$("[name='ConvertType']").on("change",function (e) {
		//console.log($(e.target).val());
		if($(e.target).val() == "0"){
			$("#SourceX").text("B:");
			$("#SourceY").text("L:");
			$("#SourceH").text("H:");
			$("#DestinationX").text("x:");
			$("#DestinationY").text("y:");
			$("#DestinationH").text("h:");
		} else {
			$("#SourceX").text("x:");
			$("#SourceY").text("y:");
			$("#SourceH").text("h:");
			$("#DestinationX").text("B:");
			$("#DestinationY").text("L:");
			$("#DestinationH").text("H:");
		}
	} )
	
	$("#DetailCoord").click(function(){
		if($lang == "zh") {
			window.open('../chs/coordinateSystem.jsp', '_blank');
		}else {
			window.open('../en/coordinateSystem.jsp', '_blank');
		}
	})
	
	$("#UploadFile").click(function(){
		var $icon = $("#CoordinateFile").val();
		if($icon == "" && $icon.length == 0){
			alert($langXml.find("ids_choose_file_first").text());
			return;
		};
		
		$.ajax({ 
			url: '../UploadCoordinateSystem', 
			type: 'POST', 
			cache: false, 
			//data: new FormData($('#UploadCoordinateForm')[0]), 
			data: new FormData($('#ConvertForm')[0]), 
			processData: false, 
			contentType: false 
			}).done(function(res) { 
				alert($langXml.find("ids_upload_success").text());
				$("#DetailCoord").removeProp("disabled");
				$("#CoordinateConvert").removeProp("disabled");
			})
			.fail(function(res) {
				alert($langXml.find("ids_upload_failed").text());
			});
	})
	
})