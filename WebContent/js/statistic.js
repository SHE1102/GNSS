$(function(){
	
	paintRegisterGraph();
	paintDownloadGraph();
	
	function paintRegisterGraph(){
	   var title = {
	      text: '注册用户'   
	   };
	   var subtitle = {
	      text: '2018-10'
	   };
	   var xAxis = {
	      categories: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12', '13']
	   };
	   var yAxis = {
	      title: {
	         text: '注册数量'
	      },
	      plotLines: [{
	         value: 0,
	         width: 1,
	         color: '#808080'
	      }]
	   };   

	   var tooltip = {
	      //valueSuffix: '\xB0C'
	   }

	   var legend = {
	      layout: 'vertical',
	      align: 'right',
	      verticalAlign: 'middle',
	      borderWidth: 0
	   };

	   var $str = "3, 4, 5, 8, 11, 15, 17, 16, 14, 10, 6, 0, 0";
	   var $strArr = $str.split(",");
	   var $data = new Array();//int 数组
	   for(var i=0; i<$strArr.length; i++){
		   $data.push(Number($strArr[i]));
	   }
		   
	   var series =  [
	      {
	         name: 'Regitser',
	         data: $data
	      }
	   ];

	   var json = {};

	   json.title = title;
	   json.subtitle = subtitle;
	   json.xAxis = xAxis;
	   json.yAxis = yAxis;
	   json.tooltip = tooltip;
	   json.legend = legend;
	   json.series = series;

	   $('#RegisterGraph').highcharts(json);
	}
	
	function paintDownloadGraph(){
		var chart = {
		      type: 'column'
		   };
		   var title = {
		      text: '下载数量统计'   
		   };
		   var subtitle = {
		      text: '2018-10'  
		   };
		   var xAxis = {
		      categories: ['01', '02', '03', '04', '05', '06','07', '08', '09', '10', '11', '12', '13'],
		      crosshair: true
		   };
		   var yAxis = {
		      min: 0,
		      title: {
		         text: '计数'         
		      }      
		   };
		   var tooltip = {
		      headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		      pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		         '<td style="padding:0"><b>{point.y:.f} </b></td></tr>',
		      footerFormat: '</table>',
		      shared: true,
		      useHTML: true
		   };
		   var plotOptions = {
		      column: {
		         pointPadding: 0.2,
		         borderWidth: 0
		      }
		   };
		   var credits = {
		      enabled: false
		   };
		   
		   var series= [
		        {
		            name: 'Rinex',
		            data: [49, 71, 106, 129, 144, 176, 135, 148, 216, 194, 95, 54]
		        },
		        {
		            name: 'Virtual',
		            data: [83, 78, 98, 93, 106, 84, 105, 104, 91, 83, 106, 92]
		        }
		   ];
		      
		   var json = {};
		   json.chart = chart;
		   json.title = title;
		   json.subtitle = subtitle;
		   json.tooltip = tooltip;
		   json.xAxis = xAxis;
		   json.yAxis = yAxis;
		   json.series = series;
		   json.plotOptions = plotOptions;
		   json.credits = credits;
		   $('#DownloadGraph').highcharts(json);
	}
})