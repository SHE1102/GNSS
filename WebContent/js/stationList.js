$(function(){
	
	$.getJSON("../Station", {userType: "Tourist"}, function(json){
		showList(json);
	})
	
	function showList(json){
		
		$(".addTr").remove();
		for(var i=0;i<json.station.length;i++){
			var station = json.station[i];
			
			var $tr = $("<tr></tr>");
			var $td1 = $("<td></td>");
			var $td2 = $("<td></td>");
			var $td3 = $("<td></td>");
			var $td4 = $("<td></td>");
			var $td5 = $("<td></td>");
			
			$td1.text(station.id).addClass("leftColumn");
			$td2.text(station.name).addClass("leftColumn");
			$td3.text(station.latitudeFormat);
			$td4.text(station.longitudeFormat);
			
			var $a = $("<a></a>").attr("href","../Monograph?name="+station.name).attr("target","_blank").text("link");
     		$td5.append($a);
			
			$tr.append($td1).append($td2).append($td3).append($td4);
			$tr.append($td5).addClass("addTr");
			
			$("#StationList").append($tr);
			
		}
	}
	
	setInterval(function(){
		$.getJSON("../Station", {userType: "Tourist"}, function(json){
			showList(json);
		})
	}, 60*1000);
	
})