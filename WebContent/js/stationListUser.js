$(function(){
	$.getJSON("../Station", {userType:"User"}, function(json){
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
			var $td6 = $("<td></td>");
			var $td7 = $("<td></td>");
			var $td8 = $("<td></td>");
			var $td9 = $("<td></td>");
			
			$td1.text(station.id).addClass("leftColumn");
			$td2.text(station.name).addClass("leftColumn");
			$td3.text(station.workFlag);
			$td4.text(station.gps);
			$td5.text(station.glonass);
			$td6.text(station.beidou);
			$td7.text(station.latitudeFormat);
			$td8.text(station.longitudeFormat);
			
			var $a = $("<a></a>").attr("href","../Monograph?name="+station.name).attr("target","_blank").text("link");
			$td9.append($a);
			
			$tr.append($td1).append($td2).append($td3).append($td4);
			$tr.append($td5).append($td6).append($td7).append($td8);
			$tr.append($td9).addClass("addTr");
			
			$("#StationList").append($tr);
			
		}
	}
	
	setInterval(function(){
		$.getJSON("../Station", {userType:"User"}, function(json){
			showList(json);
		})
	}, 60*1000);
	
})