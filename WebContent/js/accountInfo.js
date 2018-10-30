$(function(){
	
	var $langXml;
	$.post("../LangResource",function(xml){
		$langXml = $(xml);
	})
	
	$.getJSON("../CustomerAccountInfo",function(json){
		$("#UserName").val(json.name);
		$("#FirstName").val(json.firstname);
		$("#LastName").val(json.lastname);
		$("#Password").val(json.password);
		$("#Company").val(json.company);
		$("#Email").val(json.email);
		$("#Telephone").val(json.telephone);
	})
	
	$("#RegisterForm").validate({
		rules:{
			UserName:{
				required:true
			},
			Password:{
				required:true
			},
			RePassword:{
				equalTo:"#Password"
			},
			Email:{
				email:true,
				required:true
			},
			FirstName:{
				required:true
			},
			LastName:{
				required:true
			},
			Company:{
				required:true
			}
		},
		
		submitHandler:function(RegisterForm) {
			$.post("../CustomerUpdateAccount",$("#RegisterForm").serialize(),function(data){
				if(data == "true"){
					alert($langXml.find("ids_alter_success").text());
				}else{
					alert($langXml.find("ids_alter_failed").text());
				}
			})
	    }
	})
	
})