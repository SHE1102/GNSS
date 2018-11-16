$(function(){
	var $langXml;
	$.post("../LangResource",function(xml){
		$langXml = $(xml);
	})
	
	$("input[name='solutionType']").change(function(){
		var $type = $(this).val();
		if($("input[name='solutionMethod']:checked").val() == 0){
			if($type == 0){
				showList(false);
			} else if ($type == 1){
				showList(true);
			}
		}
	})
	
	$("input[name='solutionMethod']").change(function(){
		$("tr.serverConfigTr").toggle();
		$("tr.uploadTr").toggle();
		
		var $type = $("input[name='solutionType']:checked").val()
		
		if($(this).val() == 0){
			if($type == 0){
				showList(false);
			} else if ($type == 1){
				showList(true);
			}
		} else {
			$("tr.stationTr").remove();
		}
	})
	
	$("#date").val(getDefaultDate());
	function getDefaultDate() {
		var myDate = new Date();
		var month = myDate.getMonth() + 1;
		var day = myDate.getDate() - 1;
		month = (month.toString().length == 1) ? ("0" + month) : month;
		day = (day.toString().length == 1) ? ("0" + day) : day;

		var result = myDate.getFullYear() + '-' + month + '-' + day; //当前日期
		return result;
	}
	
	var $stationJson;
	$.getJSON("../Station", {userType: "Tourist"}, function(json){
		$stationJson = json;
		showList(false);
		
		if(json.station.length <= 0){
			$("#solutionBtn").prop("disabled","disabled");
		}
	})
		
	function showList(isRover){
		$("tr.stationTr").remove();
		
		$trBlank = $("<tr></tr>").append($("<td colspan='3'></td>")).addClass("stationTr");
		$("#solutionTable").append($trBlank);
		for(var i=0;i<$stationJson.station.length;i++){
			
			var $station = $stationJson.station[i];
			
			var $tr = $("<tr></tr>").addClass("stationTr");
			var $td1 = $("<td></td>").addClass("Label").text($station.id);
			
			var $td2 = $("<td></td>");
			var $td3 = $("<td></td>");
			
			var $span1 = $("<span></span>").text("none");
			var $span2 = $("<span></span>").text("base");
			var $span3 = $("<span></span>").text("rover");
			
			var $input1 = $("<input/>").addClass("solutionRadio").prop("type","radio").prop("name",$station.id).prop("checked","checked").val("0");
			var $input2 = $("<input/>").addClass("solutionRadio").prop("type","radio").prop("name",$station.id).val("1");
			var $input3 = $("<input/>").addClass("solutionRadio").prop("type","radio").prop("name",$station.id).val("2");
			
			$td2.append($input1).append($span1).append($input2).append($span2);
			
			if(isRover){
				$td2.append($input3).append($span3);
			}
			
			$tr.append($td1).append($td2).append($td3);
			
			$("#solutionTable").append($tr);
		}
	}
	
	var $uploadFolderName = "";
	
	$("#solutionBtn").click(function(){
		var $type = $("input[name='solutionType']:checked").val();
		var $method = $("input[name='solutionMethod']:checked").val();
		var $date = $("#date").val();
		var $startTime = $("#startTime").val();
		var $endTime = $("#endTime").val();
		var $stationList="";
		
		var $baseCount =0,$roverCount = 0;
		$(".solutionRadio:checked").each(function(){
			if($(this).val() == 1){
				$baseCount += 1;
				$stationList += $(this).prop("name");
				$stationList += ":";
			} 
		})
		
		$stationList += "?";
		$(".solutionRadio:checked").each(function(){
			if($(this).val() == 2){
				$roverCount += 1;
				$stationList += $(this).prop("name");
				$stationList += ":";
			} 
		})

		if($method == 0){
			if($type == 0 && $baseCount < 2){
				alert($langXml.find("ids_choose_base_gt_2").text());
				return;
			}else if($type == 1 && ($baseCount != 1|| $roverCount != 1)){
				alert($langXml.find("ids_must_choose_base_rover").text());
				return;
			}
			
			$.post("../solution",
			          {
				           type:$type,
				           method:$method,
				           date:$date,
				           startTime:$startTime,
				           endTime:$endTime,
				           stationList:$stationList
			          }	,
			          function(data){
			          }
			)
		} else if ($method == 1) {
			if($uploadFolderName == "" || $uploadFolderName.length == 0){
				alert($langXml.find("ids_upload_file_first").text());
				return;
			}
			
			$.post("../solution",
		          {
			           type:$type,
			           method:$method,
			           folderName:$uploadFolderName
		          }	,
		          function(data){
		          }
			)
		}
		alert($langXml.find("ids_back_solution").text());
	})
	
	$("#uploadSolutionFile").click(function(){
		var $file = $("#solutionFile").val();
		if($file == "" && $file.length == 0){
			alert($langXml.find("ids_choose_file_first").text());
			return;
		};
		
		$.ajax({ 
			url: '../uploadSolution', 
			type: 'POST', 
			cache: false, 
			data: new FormData($('#uploadForm')[0]), 
			processData: false, 
			contentType: false 
			}).done(function(res) { 
				$uploadFolderName = res;
				alert($langXml.find("ids_upload_success").text());
			})
			.fail(function(res) {
				alert($langXml.find("ids_upload_failed").text());
			});
	})
	
})