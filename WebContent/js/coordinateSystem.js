$(function(){
	var $langXml;
	$.post("../LangResource",function(xml){
		$langXml = $(xml);
	})
	
	$.getJSON("../CoordinateDetail", function(json){
		showDetail(json);
	})
	
	function showDetail(json){
		
		var $table = $("<table></table>");
		
		$table.append($("<tr></tr>").append($("<th></th>").text($langXml.find("ids_ellipsoid").text()).prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_ellipsoid_name").text())).append($("<td></td>").text(json.EllipsoidPar.Name)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_axis").text())).append($("<td></td>").text(json.EllipsoidPar.Axis)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_f").text())).append($("<td></td>").text(json.EllipsoidPar.FaltRate)));
		
		$table.append($("<tr></tr>").append($("<th></th>").text($langXml.find("ids_project").text()).prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_project_mode").text())).append($("<td></td>").text(json.ProjectPar.Type)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_central_meridian").text())).append($("<td></td>").text(json.ProjectPar.CenterMeridian)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_false_north").text())).append($("<td></td>").text(json.ProjectPar.Tx)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_false_east").text())).append($("<td></td>").text(json.ProjectPar.Ty)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_scale_factor").text())).append($("<td></td>").text(json.ProjectPar.Tk)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_project_height").text())).append($("<td></td>").text(json.ProjectPar.ProjectHeight)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_orign_latitude").text())).append($("<td></td>").text(json.ProjectPar.ReferenceLatitude)));
		
		$table.append($("<tr></tr>").append($("<th></th>").text($langXml.find("ids_saven_par").text()).prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_is_use").text())).append($("<td></td>").text(json.SevenPar.Use)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_mode").text())).append($("<td></td>").text(json.SevenPar.Mode)));
		$table.append($("<tr></tr>").append($("<td></td>").text("x")).append($("<td></td>").text(json.SevenPar.Dx)));
		$table.append($("<tr></tr>").append($("<td></td>").text("y")).append($("<td></td>").text(json.SevenPar.Dy)));
		$table.append($("<tr></tr>").append($("<td></td>").text("z")).append($("<td></td>").text(json.SevenPar.Dz)));
		$table.append($("<tr></tr>").append($("<td></td>").text("a")).append($("<td></td>").text(json.SevenPar.Rx)));
		$table.append($("<tr></tr>").append($("<td></td>").text("b")).append($("<td></td>").text(json.SevenPar.Ry)));
		$table.append($("<tr></tr>").append($("<td></td>").text("c")).append($("<td></td>").text(json.SevenPar.Rz)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_scale").text())).append($("<td></td>").text(json.SevenPar.Dk)));
	
		$table.append($("<tr></tr>").append($("<th></th>").text($langXml.find("ids_four_par").text()).prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_is_use").text())).append($("<td></td>").text(json.FourPar.Use)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_translate_north").text())).append($("<td></td>").text(json.FourPar.Cx)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_translate_east").text())).append($("<td></td>").text(json.FourPar.Cy)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_rotate").text())).append($("<td></td>").text(json.FourPar.Ca)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_scale").text())).append($("<td></td>").text(json.FourPar.Ck)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_original_north").text())).append($("<td></td>").text(json.FourPar.Orgx)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_original_east").text())).append($("<td></td>").text(json.FourPar.Orgy)));
	
		$table.append($("<tr></tr>").append($("<th></th>").text($langXml.find("ids_ver_control_par").text()).prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_is_use").text())).append($("<td></td>").text(json.HeightFittingPar.Use)));
		$table.append($("<tr></tr>").append($("<td></td>").text("A0")).append($("<td></td>").text(json.HeightFittingPar.A0)));
		$table.append($("<tr></tr>").append($("<td></td>").text("A1")).append($("<td></td>").text(json.HeightFittingPar.A1)));
		$table.append($("<tr></tr>").append($("<td></td>").text("A2")).append($("<td></td>").text(json.HeightFittingPar.A2)));
		$table.append($("<tr></tr>").append($("<td></td>").text("A3")).append($("<td></td>").text(json.HeightFittingPar.A3)));
		$table.append($("<tr></tr>").append($("<td></td>").text("A4")).append($("<td></td>").text(json.HeightFittingPar.A4)));
		$table.append($("<tr></tr>").append($("<td></td>").text("A5")).append($("<td></td>").text(json.HeightFittingPar.A5)));
		$table.append($("<tr></tr>").append($("<td></td>").text("X0")).append($("<td></td>").text(json.HeightFittingPar.X0)));
		$table.append($("<tr></tr>").append($("<td></td>").text("Y0")).append($("<td></td>").text(json.HeightFittingPar.Y0)));

		$table.append($("<tr></tr>").append($("<th></th>").text($langXml.find("ids_vertical_par").text()).prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_is_use").text())).append($("<td></td>").text(json.VerticalPar.Use)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_north_slope").text())).append($("<td></td>").text(json.VerticalPar.NorthSlope)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_east_slope").text())).append($("<td></td>").text(json.VerticalPar.EastSlope)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_original_north").text())).append($("<td></td>").text(json.VerticalPar.Orgx)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_original_east").text())).append($("<td></td>").text(json.VerticalPar.Orgy)));
		
		$table.append($("<tr></tr>").append($("<th></th>").text($langXml.find("ids_local_offset").text()).prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_is_use").text())).append($("<td></td>").text(json.CorrectPar.Use)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_north_slope").text())).append($("<td></td>").text(json.CorrectPar.Dx)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_east_slope").text())).append($("<td></td>").text(json.CorrectPar.Dy)));
		$table.append($("<tr></tr>").append($("<td></td>").text($langXml.find("ids_original_north").text())).append($("<td></td>").text(json.CorrectPar.Dh)));
		
		$("body").append($table);
		
	}
})