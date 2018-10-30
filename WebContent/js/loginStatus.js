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
		
		//VER
		/*if($name.length <= 0){
			$(".MenuBar>ul>li:lt(4)").show();
		} else if($authority == 0){
			$(".MenuBar>ul>li:lt(5)").show();
		} else if($authority >= 1){
			$(".MenuBar>ul>li").show();
		}
		
		if($authority == 9){
			$(".MenuBar>ul>li:eq(5)>ul>li:eq(4)").show();
		} else {
			$(".MenuBar>ul>li:eq(5)>ul>li:eq(4)").hide();
		}*/
		
		//HOR
		if($name.length <= 0){
			$(".MenuBarHor>ul>li:lt(4)").show();
		} else if($authority == 0){
			$(".MenuBarHor>ul>li:lt(5)").show();
		} else if($authority >= 1){
			$(".MenuBarHor>ul>li").show();
		}
		
		//$("#customerModule").hide();
		
		if($authority == 9){
			$(".MenuBarHor>ul>li:eq(5)>ul>li:eq(4)").show();
		} else {
			$(".MenuBarHor>ul>li:eq(5)>ul>li:eq(4)").hide();
		}
		
	})
	
})