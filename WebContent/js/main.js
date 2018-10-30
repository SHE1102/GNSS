$(function(){
	$.getScript("../js/loginStatus.js");
	
	$(".ItemBar").click(function(){
		var bar = $(this).next(".SubMenu");
		bar.toggle();
	})
})
