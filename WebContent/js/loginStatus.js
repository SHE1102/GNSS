$(function(){
	
	$.getJSON("../LoginStatus",function(json){
		var $name = json.name;
		var $authority = json.authority;
		
		if($name.length <= 0){
			$("#login").val("login");
			$("loginstaus").text("");
		} else {
			$("#login").val("logout");
			$("#loginstaus").text($name);
		}
		
		//HOR
		if($name.length <= 0){
			$(".MenuBarHor>ul>li:lt(3)").show();
			return;
		} else if($authority == 0){
			$(".MenuBarHor>ul>li:lt(4)").show();
		} else if($authority >= 1 ){
			$(".MenuBarHor>ul>li").show();
		} 
		
		if(json.downloadRinex == 0){
			$("#downloadRinexModule").hide();
		}
		if(json.downloadVirtual == 0){
			$("#downloadVirtualModule").hide();
		}
		if(json.solution == 0){
			$("#solutionModule").hide();
		}
		if(json.additionalFeature == 0){
			$("#additionalModule").hide();
		}
	})
	
})