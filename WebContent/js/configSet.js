$(function(){
	var $langXml;
	$.post("../LangResource",function(xml){
		$langXml = $(xml);
	})
	
	$.getJSON("../Config",{method:"read"},function(json){
		$("#rawPath").val(json.rawPath);
		$("#hostEmail").val(json.hostEmail);
		$("#hostEmailPassword").val(json.hostEmailPassword);
		$("#hostEmailProtocol").val(json.hostEmailProtocol);
		$("#centerLat").val(json.centerLat);
		$("#centerLon").val(json.centerLon);
		$("#zoom").val(json.mapZoom);
	})
	
	$.getJSON("../ReadFtpConfig",function(json){
		$("#FtpPort").val(json.port);
		$("#HomeDirectory").val(json.homeDirectory);
		$("#FtpName").val(json.userName);
		$("#FtpPassword").val(json.password);
	})
	
	$.post("../FtpStartStatus",function(status){
		if(status == "true"){
				$("#StartFtp").prop("disabled","disabled");
				$("#StopFtp").removeProp("disabled");
			}else{
				$("#StartFtp").removeProp("disabled");
				$("#StopFtp").prop("disabled","disabled");
			}
	})
	
	var $optionArr = new Array(17);
	for(var i=0;i<17;i++){
		$optionArr[i] = $("<option></option>").val(i+2).text(i+2);
		$("#zoom").append($optionArr[i]);
	}
	
	$("#UploadLogo").click(function(){
		var $icon = $("#LogoIcon").val();
		if($icon == "" && $icon.length == 0){
			alert($langXml.find("ids_choose_logo_first").text());
			return;
		};
		
		$.ajax({ 
			url: '../UploadIcon', 
			type: 'POST', 
			cache: false, 
			data: new FormData($('#LogoForm')[0]), 
			processData: false, 
			contentType: false 
			}).done(function(res) { 
				alert($langXml.find("ids_upload_success").text());
			})
			.fail(function(res) {
				alert($langXml.find("ids_upload_failed").text());
			});

	})
	
	$("#SaveConfig").click(function(){
		var par = $("#ConfigForm").serialize()+"&method=save";
		
		$.post("../Config",par, function(data){
			if(data == "true"){
				alert($langXml.find("ids_save_success").text());
			}else{
				alert($langXml.find("ids_save_failed").text());
			}
		});
	})
	
	$("#StartFtp").click(function(){
		var par = $("#FtpForm").serialize()+"&Steup=StartFtp";
		
		$.post("../FtpSteup",par, function(data){
			if(data == "true"){
				$("#StartFtp").prop("disabled","disabled");
				$("#StopFtp").removeProp("disabled");
				alert($langXml.find("ids_start_ftp_success").text());
			}else{
				$("#StartFtp").removeProp("disabled");
				$("#StopFtp").prop("disabled","disabled");
				alert($langXml.find("ids_start_ftp_failed").text());
			}
		});
	})
	
	$("#StopFtp").click(function(){
		var par = $("#FtpForm").serialize()+"&Steup=StopFtp";
		
		$.post("../FtpSteup",par, function(data){
			if(data == "true"){
				$("#StartFtp").prop("disabled","disabled");
				$("#StopFtp").removeProp("disabled");
				alert($langXml.find("ids_stop_ftp_failed").text());
			}else{
				$("#StartFtp").removeProp("disabled");
				$("#StopFtp").prop("disabled","disabled");
				alert($langXml.find("ids_stop_ftp_success").text());
			}
		});
		
	})
	
})