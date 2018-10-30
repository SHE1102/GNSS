$(function(){
	$.getJSON("../CoordinateDetail", function(json){
		showDetail(json);
	})
	
	function showDetail(json){
		
		var $table = $("<table></table>");
		
		$table.append($("<tr></tr>").append($("<th></th>").text("椭球参数").prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text("名称")).append($("<td></td>").text(json.EllipsoidPar.Name)));
		$table.append($("<tr></tr>").append($("<td></td>").text("长半轴")).append($("<td></td>").text(json.EllipsoidPar.Axis)));
		$table.append($("<tr></tr>").append($("<td></td>").text("扁率倒数")).append($("<td></td>").text(json.EllipsoidPar.FaltRate)));
		
		$table.append($("<tr></tr>").append($("<th></th>").text("投影参数").prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text("投影方式")).append($("<td></td>").text(json.ProjectPar.Type)));
		$table.append($("<tr></tr>").append($("<td></td>").text("中央子午线")).append($("<td></td>").text(json.ProjectPar.CenterMeridian)));
		$table.append($("<tr></tr>").append($("<td></td>").text("北加常数")).append($("<td></td>").text(json.ProjectPar.Tx)));
		$table.append($("<tr></tr>").append($("<td></td>").text("东加常数")).append($("<td></td>").text(json.ProjectPar.Ty)));
		$table.append($("<tr></tr>").append($("<td></td>").text("投影比例尺")).append($("<td></td>").text(json.ProjectPar.Tk)));
		$table.append($("<tr></tr>").append($("<td></td>").text("投影高")).append($("<td></td>").text(json.ProjectPar.ProjectHeight)));
		$table.append($("<tr></tr>").append($("<td></td>").text("基准纬度")).append($("<td></td>").text(json.ProjectPar.ReferenceLatitude)));
		
		$table.append($("<tr></tr>").append($("<th></th>").text("七参数").prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text("是否使用")).append($("<td></td>").text(json.SevenPar.Use)));
		$table.append($("<tr></tr>").append($("<td></td>").text("模型")).append($("<td></td>").text(json.SevenPar.Mode)));
		$table.append($("<tr></tr>").append($("<td></td>").text("x")).append($("<td></td>").text(json.SevenPar.Dx)));
		$table.append($("<tr></tr>").append($("<td></td>").text("y")).append($("<td></td>").text(json.SevenPar.Dy)));
		$table.append($("<tr></tr>").append($("<td></td>").text("z")).append($("<td></td>").text(json.SevenPar.Dz)));
		$table.append($("<tr></tr>").append($("<td></td>").text("a")).append($("<td></td>").text(json.SevenPar.Rx)));
		$table.append($("<tr></tr>").append($("<td></td>").text("b")).append($("<td></td>").text(json.SevenPar.Ry)));
		$table.append($("<tr></tr>").append($("<td></td>").text("c")).append($("<td></td>").text(json.SevenPar.Rz)));
		$table.append($("<tr></tr>").append($("<td></td>").text("比例尺")).append($("<td></td>").text(json.SevenPar.Dk)));
	
		$table.append($("<tr></tr>").append($("<th></th>").text("四参数").prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text("是否使用")).append($("<td></td>").text(json.FourPar.Use)));
		$table.append($("<tr></tr>").append($("<td></td>").text("北平移")).append($("<td></td>").text(json.FourPar.Cx)));
		$table.append($("<tr></tr>").append($("<td></td>").text("东平移")).append($("<td></td>").text(json.FourPar.Cy)));
		$table.append($("<tr></tr>").append($("<td></td>").text("旋转角")).append($("<td></td>").text(json.FourPar.Ca)));
		$table.append($("<tr></tr>").append($("<td></td>").text("比例尺")).append($("<td></td>").text(json.FourPar.Ck)));
		$table.append($("<tr></tr>").append($("<td></td>").text("北原点")).append($("<td></td>").text(json.FourPar.Orgx)));
		$table.append($("<tr></tr>").append($("<td></td>").text("东原点")).append($("<td></td>").text(json.FourPar.Orgy)));
	
		$table.append($("<tr></tr>").append($("<th></th>").text("高程拟合参数").prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text("是否使用")).append($("<td></td>").text(json.HeightFittingPar.Use)));
		$table.append($("<tr></tr>").append($("<td></td>").text("A0")).append($("<td></td>").text(json.HeightFittingPar.A0)));
		$table.append($("<tr></tr>").append($("<td></td>").text("A1")).append($("<td></td>").text(json.HeightFittingPar.A1)));
		$table.append($("<tr></tr>").append($("<td></td>").text("A2")).append($("<td></td>").text(json.HeightFittingPar.A2)));
		$table.append($("<tr></tr>").append($("<td></td>").text("A3")).append($("<td></td>").text(json.HeightFittingPar.A3)));
		$table.append($("<tr></tr>").append($("<td></td>").text("A4")).append($("<td></td>").text(json.HeightFittingPar.A4)));
		$table.append($("<tr></tr>").append($("<td></td>").text("A5")).append($("<td></td>").text(json.HeightFittingPar.A5)));
		$table.append($("<tr></tr>").append($("<td></td>").text("X0")).append($("<td></td>").text(json.HeightFittingPar.X0)));
		$table.append($("<tr></tr>").append($("<td></td>").text("Y0")).append($("<td></td>").text(json.HeightFittingPar.Y0)));
		
		$table.append($("<tr></tr>").append($("<th></th>").text("垂直平差参数").prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text("是否使用")).append($("<td></td>").text(json.VerticalPar.Use)));
		$table.append($("<tr></tr>").append($("<td></td>").text("北斜坡")).append($("<td></td>").text(json.VerticalPar.NorthSlope)));
		$table.append($("<tr></tr>").append($("<td></td>").text("东斜坡")).append($("<td></td>").text(json.VerticalPar.EastSlope)));
		$table.append($("<tr></tr>").append($("<td></td>").text("北原点")).append($("<td></td>").text(json.VerticalPar.Orgx)));
		$table.append($("<tr></tr>").append($("<td></td>").text("东原点")).append($("<td></td>").text(json.VerticalPar.Orgy)));
		
		$table.append($("<tr></tr>").append($("<th></th>").text("平移参数").prop("colspan","2")))
		$table.append($("<tr></tr>").append($("<td></td>").text("是否使用")).append($("<td></td>").text(json.CorrectPar.Use)));
		$table.append($("<tr></tr>").append($("<td></td>").text("北斜坡")).append($("<td></td>").text(json.CorrectPar.Dx)));
		$table.append($("<tr></tr>").append($("<td></td>").text("东斜坡")).append($("<td></td>").text(json.CorrectPar.Dy)));
		$table.append($("<tr></tr>").append($("<td></td>").text("北原点")).append($("<td></td>").text(json.CorrectPar.Dh)));
		
		$("body").append($table);
		
	}
})