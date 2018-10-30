$(function(){
	
	var $langXml;
	$.post("../LangResource",function(xml){
		$langXml = $(xml);
	})
	
	$("#RegisterForm").validate({
		rules:{
			UserName:{
				required:true,
				remote:{
					url:"../CheckRegisiterName",
					type:"post",
					data:{
						UserName:function(){return $("#UserName").val();}
					}
				}
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
			$.post("../Register",$("#RegisterForm").serialize(),function(data){
				if(data == "true"){
					alert($langXml.find("ids_register_success").text());
				}else{
					alert($langXml.find("ids_register_failed").text());
				}
			})
	    }
	})
	
})