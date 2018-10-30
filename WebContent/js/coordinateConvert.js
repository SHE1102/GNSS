$(function(){
	
	var $langXml;
	$.post("../LangResource",function(xml){
		$langXml = $(xml);
	})
	
	$("#CoordinateConvert").click(function(){
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
		window.open('../chs/coordinateSystem.jsp', '_blank');
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
			data: new FormData($('#UploadCoordinateForm')[0]), 
			processData: false, 
			contentType: false 
			}).done(function(res) { 
				alert($langXml.find("ids_upload_success").text());
			})
			.fail(function(res) {
				alert($langXml.find("ids_upload_failed").text());
			});
	})
	
})