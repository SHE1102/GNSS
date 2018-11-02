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
			$(".MenuBarHor>ul>li:lt(4)").show();
			return;
		} else if($authority == 0){
			$(".MenuBarHor>ul>li:lt(5)").show();
		} else if($authority >= 1 && $authority < 9){
			$(".MenuBarHor>ul>li").show();
			$(".MenuBarHor>ul>li:eq(5)>ul>li:eq(4)").hide();
		} else if($authority == 9){
			$(".MenuBarHor>ul>li").show();
			$(".MenuBarHor>ul>li:eq(5)>ul>li:eq(4)").show();
		}
		
		if(json.downloadRinex == 0){
			$("#downloadRinexModule").hide();
		}
		if(json.downloadVirtual == 0){
			$("#downloadVirtualModule").hide();
		}
		if(json.solutionStatic == 0){
			$("#solutionStaticModule").hide();
		}
		if(json.solutionDynamic == 0){
			$("#solutionDynamicModule").hide();
		}
		if(json.coordinateConvert == 0){
			$("#coordinateConvertModule").hide();
		}
		
		/*if($authority == 9){
			$(".MenuBarHor>ul>li:eq(5)>ul>li:eq(4)").show();
		} else {
			$(".MenuBarHor>ul>li:eq(5)>ul>li:eq(4)").hide();
		}*/
		
	})
	
})