$(function(){
	
	var $langXml;
	$.post("../LangResource",function(xml){
		$langXml = $(xml);
	})
	
	//$.getJSON("../CustomerManage",{type:"admin"}, function(json){
	//	updateTable(json);
	//})
	$.getJSON("../CustomerManage", function(json){
		updateTable(json);
	})
	
	function updateTable(json){
		for(var i=0; i<json.userArray.length; i++){
			addTr(json.userArray[i],json.authority);
		}
	}
	
	function addTr(user, authority){
		
		var $thAuthority = $("<td></td>").addClass("item");
       
		
		var $thArr = new Array(9);
		for(var i=0; i<9; i++){
			$thArr[i] = $("<td></td>");
			
			if(i<8){
				$thArr[i].addClass("item");
			} else {
				$thArr[i].addClass("func");
			}
		}
		var $name = $("<input></input>").attr("type","text").val(user.name).attr("name","UserName").attr("readonly","readonly");
		var $enable = $("<select></select>").attr("name","Enable");
		$enable.append($("<option></option>").val("false").text("disable"));
		$enable.append($("<option></option>").val("true").text("enable"));
		
		var $authority = $("<select></select>").attr("name","Authority");
		$authority.append($("<option></option>").val("0").text("user"));
		$authority.append($("<option></option>").val("1").text("admin"));
		$authority.append($("<option></option>").val("9").text("superAdmin"));
		
		var $downloadRinex = $("<select></select>").attr("name","downloadRinex");
		$downloadRinex.append($("<option></option>").val("false").text("disable"));
		$downloadRinex.append($("<option></option>").val("true").text("enable"));
		
		var $virtualRinex = $("<select></select>").attr("name","virtualRinex");
		$virtualRinex.append($("<option></option>").val("false").text("disable"));
		$virtualRinex.append($("<option></option>").val("true").text("enable"));
		
		var $solutionStatic = $("<select></select>").attr("name","solutionStatic");
		$solutionStatic.append($("<option></option>").val("false").text("disable"));
		$solutionStatic.append($("<option></option>").val("true").text("enable"));
		
		var $solutionDynamic = $("<select></select>").attr("name","solutionDynamic");
		$solutionDynamic.append($("<option></option>").val("false").text("disable"));
		$solutionDynamic.append($("<option></option>").val("true").text("enable"));
		
		var $additionalFeature = $("<select></select>").attr("name","additionalFeature");
		$additionalFeature.append($("<option></option>").val("false").text("disable"));
		$additionalFeature.append($("<option></option>").val("true").text("enable"));
		
		
		var $limitDate = $("<input></input>").attr("type","date").val(user.limitdate).attr("name","LimitDate");
		
		$thArr[0].append($name);
		$enable.val(user.enable);
		$thArr[1].append($enable);
		$thArr[2].append($limitDate);
		
		if(authority=="superAdmin"){
			$authority.val(user.authority);
			$thAuthority.append($authority);
		}
		
		$downloadRinex.val(user.downloadRinex);
		$thArr[3].append($downloadRinex);
		$virtualRinex.val(user.virtualRinex);
		$thArr[4].append($virtualRinex);
		$solutionStatic.val(user.solutionStatic);
		$thArr[5].append($solutionStatic);
		$solutionDynamic.val(user.solutionDynamic);
		$thArr[6].append($solutionDynamic);
		$additionalFeature.val(user.additionalFeature);
		$thArr[7].append($additionalFeature);
		
		var $alter = $("<input></input>").attr("type","button").attr("value","Alter").addClass("ControlButton").addClass("AlterButton");//.attr("formaction","CustomerUpdate");
		var $delete = $("<input></input>").attr("type","button").attr("value","Delete").addClass("ControlButton").addClass("DeleteButton");//.attr("formaction","CustomerDelete");
		var $detail = $("<input></input>").attr("type","button").attr("value","Detail").addClass("ControlButton").addClass("DetailButton");//.attr("formaction","CustomerDelete");
		$thArr[8].append($alter).append($delete).append($detail);
		
		var $tr = $("<tr></tr>").append($thArr[0]).append($thArr[1]).append($thArr[2]);
		if(authority=="superAdmin"){
			$tr.append($thAuthority);
		}
		$tr.append($thArr[3]).append($thArr[4]).append($thArr[5]).append($thArr[6]).append($thArr[7]).append($thArr[8]);
		var $table = $("<table></table>").append($tr);
		var $form = $("<form></form>").append($table).attr("method","post");
		$("#TableBodyDiv").append($form);
	}

	$("#TableBodyDiv").on("click", ".AlterButton", function(event){
		//alert($(this).val());
		var $form = $(event.target).closest("form");
		$.post("../CustomerUpdate", $form.serialize(), function(data){
			if(data == "true"){
				alert($langXml.find("ids_alert_success").text());
				$(window).attr("location","customerManage.jsp");
			}
		})
	})
	
	$("#TableBodyDiv").on("click", ".DeleteButton", function(event){
		var $form = $(event.target).closest("form");
		$.post("../CustomerDelete", $form.serialize(), function(data){
			if(data == "true"){
				alert($langXml.find("ids_delete_success").text());
				$(window).attr("location","customerManage.jsp");
			}
		})
	})
	$("#TableBodyDiv").on("click", ".DetailButton", function(event){
		var $form = $(event.target).closest("form");
		$.getJSON("../CustomerDetail", $form.serialize(),function(json){
			$detailDiv = $("<div></div>").prop("id","DetailDiv");
			
			$table = $("<table></table>")
			$trArray = new Array(10);
			for(var i=0; i<10; i++){
				$trArray[i] = $("<tr></tr>");
			}
			
			$tdArray = new Array(10);
			for(var j=0; j<10; j++){
				$tdArray[j] = new Array(2);
				for(var k=0; k<2; k++){
					$tdArray[j][k] = $("<td></td>").css("height","30px");
				}
			}
			
			$tdArray[0][0].text("UserName").addClass("Label");
			$tdArray[0][1].text(json.name);
			$tdArray[1][0].text("Password").addClass("Label");
			$tdArray[1][1].text(json.password);
			
			$tdArray[2][0].text("FirstName").addClass("Label");
			$tdArray[2][1].text(json.firstname);
			$tdArray[3][0].text("LastName").addClass("Label");
			$tdArray[3][1].text(json.lastname);
			
			$tdArray[4][0].text("Authority").addClass("Label");
			$tdArray[4][1].text(json.authority);
			$tdArray[5][0].text("Email").addClass("Label");
			$tdArray[5][1].text(json.email);
			
			$tdArray[6][0].text("Enable").addClass("Label");
			$tdArray[6][1].text(json.enable);
			$tdArray[7][0].text("Company").addClass("Label");
			$tdArray[7][1].text(json.company);
			
			$tdArray[8][0].text("Telephone").addClass("Label");
			$tdArray[8][1].text(json.telephone);
			$tdArray[9][0].text("LimitDate").addClass("Label");
			$tdArray[9][1].text(json.limitdate);
			
			$trArray[0].append($tdArray[0][0]).append($tdArray[0][1]);
			$trArray[1].append($tdArray[1][0]).append($tdArray[1][1]);
			$trArray[2].append($tdArray[2][0]).append($tdArray[2][1]);
			$trArray[3].append($tdArray[3][0]).append($tdArray[3][1]);
			$trArray[4].append($tdArray[4][0]).append($tdArray[4][1]);
			$trArray[5].append($tdArray[5][0]).append($tdArray[5][1]);
			$trArray[6].append($tdArray[6][0]).append($tdArray[6][1]);
			$trArray[7].append($tdArray[7][0]).append($tdArray[7][1]);
			$trArray[8].append($tdArray[8][0]).append($tdArray[8][1]);
			$trArray[9].append($tdArray[9][0]).append($tdArray[9][1]);
			
			for(var i=0; i<10; i++){
				$table.append($trArray[i]);
			}
			
			$div = $("<div><div>");
			$closeBtn = $("<input/>").prop("type","button").prop("id","CloseDetail");
			$div.append($closeBtn);
			
			$subDiv = $("<div></div>").prop("id","SubDiv");
			$subDiv.append($div).append($table);
			$detailDiv.append($subDiv);
			
			$("html").append($detailDiv);
		})
	})
	
	$("html").on("click", "#CloseDetail", function(event){
		$("#DetailDiv").remove();
	})
	
})