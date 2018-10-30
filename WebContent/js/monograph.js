$(function(){
	var $langXml;
	$.post("../LangResource",function(xml){
		$langXml = $(xml);
	})
	
	$.getJSON("../Station", {userType: "Tourist"}, function(json){
		showList(json);
	})
	
	function showList(json){
		for(var i=0;i<json.station.length;i++){
			var station = json.station[i];
			
			var $td1 = $("<td></td>").append($("<input type='text' readonly id='Id'>").val(station.id));
			var $td2 = $("<td></td>").append($("<input type='text' readonly id='Name' name='Name'>").val(station.name));
			var $td3 = $("<td></td>").append($("<input type='text' readonly id='File'>").val(station.pdf));
			var $td4 = $("<td></td>").append($("<input type='file' accept='.pdf' id='ChooseFile' name='ChooseFile'>"));
			var $td5 = $("<td></td>").append($("<input type='button' value='upload' class='update'/>"));
			
			var $tr = $("<tr></tr>").append($td1).append($td2).append($td3).append($td4).append($td5);
			var $table = $("<table></table>").append($tr);
			var $form = $("<form></form>").append($table);
			$("#TableBodyDiv").append($form);
		}
	}
	
	$("#TableBodyDiv").on("click", ".update", function(event){
		var $form = $(event.target).closest("form");
		var $file = $form.find("td #ChooseFile");
		
		if($file.val().length == 0 || $file.val() == ""){
			alert($langXml.find("ids_choose_file_first").text());
			return;
		}
		//$form.submit();
		
		$.ajax({ 
			url: '../UploadMonograph', 
			type: 'POST', 
			cache: false, 
			data: new FormData($form[0]), 
			processData: false, 
			contentType: false 
			}).done(function(res) { 
				$form.find("td #File").val(res);
				alert($langXml.find("ids_upload_success").text());
			})
			.fail(function(res) {
				alert($langXml.find("ids_upload_failed").text());
			});
		
	})
})