$(function(){
	var $langXml;
	$.post("../LangResource",function(xml){
		$langXml = $(xml);
	})
	
	$.getJSON("../record",function(json){
		paintRegisterGraph(json);
		paintDownloadGraph(json);
	})
	
	$.getJSON("../roverStatistic", function(json){
		paintRoverGraph(json);
		paintRoverMap(json);
	})
	
	function paintRegisterGraph(json){
		var $categories = new Array();
		var $data = new Array();//int 数组
		for(var i=0; i<json.array.length; i++){
			var $dayData = json.array[i];
			$categories.push($dayData.date);
			$data.push(Number($dayData.register));
		}
		
		var $chartWidth;
		if($categories.length == 0){
			$chartWidth = 1800;
		}
		
		var chart = {
		      type: 'column',
		      width:$chartWidth
		};
		
	    var title = {
	      text: $langXml.find("ids_register_customer").text()   
	   };
	   var subtitle = {
	      //text: '2018-10'
	   };
	   var xAxis = {
	      categories:$categories,
	      crosshair: true
	   };
	   var yAxis = {
		  min: 0,
	      title: {
	         text: $langXml.find("ids_register_count").text()
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
		   
	   var credits = {
	      enabled: false
	   };
	   var series =  [
	      {
	         name: 'Regitser',
	         data: $data
	      }
	   ];

	   var json = {};
	   json.chart = chart;
	   json.title = title;
	   json.subtitle = subtitle;
	   json.xAxis = xAxis;
	   json.yAxis = yAxis;
	   json.tooltip = tooltip;
	  // json.legend = legend;
	   json.series = series;
	   json.credits = credits;

	   $('#RegisterGraph').highcharts(json);
	}
	
	function paintDownloadGraph(json){
		var $categories = new Array();
		var $data1 = new Array();//int 数组
		var $data2 = new Array();//int 数组
		for(var i=0; i<json.array.length; i++){
			var $dayData = json.array[i];
			$categories.push($dayData.date);
			$data1.push(Number($dayData.rinex));
			$data2.push(Number($dayData.virtual));
		}
		
		var $chartWidth;
		if($categories.length == 0){
			$chartWidth = 1800;
		}
		
		var chart = {
		      type: 'column',
		      width:$chartWidth
		   };
		   var title = {
		      text: $langXml.find("ids_download_statistic").text()
		   };
		   var subtitle = {
		     // text: '2018-10'  
		   };
		   var xAxis = {
		      categories:$categories,
		      crosshair: true
		   };
		   var yAxis = {
		      min: 0,
		      title: {
		         text: $langXml.find("ids_count").text()         
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
		            data:$data1
		        },
		        {
		            name: 'Virtual',
		            data:$data2
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
	
	 function paintRoverGraph(json){
		 var $categories = new Array();
			var $data1 = new Array();//int 数组
			var $data2 = new Array();//int 数组
			for(var i=0; i<json.rover.length; i++){
				var $roverData = json.rover[i];
				$categories.push($roverData.date);
				$data1.push(Number($roverData.count));
				$data2.push(Number($roverData.onlineTime));
			}
			
			var $chartWidth;
			if($categories.length == 0){
				$chartWidth = 1800;
			}
			
			var chart = {
			      type: 'column',
			      width:$chartWidth
			};
			
		    var chart = {
			      zoomType: 'xy',
			      width:$chartWidth
			   };
			   var subtitle = {
			      text: ''   
			   };
			   var title = {
			      text:$langXml.find("ids_rover_statistic").text()  
			   };
			   var xAxis = {
			      categories: $categories,
			      crosshair: true
			   };
			   var yAxis= [{ // 第一条Y轴
			      labels: {
			         style: {
			            color: Highcharts.getOptions().colors[1]
			         }
			      },
			      title: {
			         text: $langXml.find("ids_rover_count").text(),  
			         style: {
			            color: Highcharts.getOptions().colors[1]
			         }
			      }
			   }, { // 第二条Y轴
			      title: {
			         text: $langXml.find("ids_rover_onlinetime").text(),  
			         style: {
			            color: Highcharts.getOptions().colors[0]
			         }
			      },
			      labels: {
			    	  format: '{value} min',
			         style: {
			            color: Highcharts.getOptions().colors[0]
			         }
			      },
			      opposite: true
			   }];
			   var tooltip = {
			      shared: true
			   };
			   var legend = {
			      layout: 'vertical',
			      align: 'left',
			      x: 100,
			      verticalAlign: 'top',
			      y: 40,
			      floating: true,
			      backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'
			   };
			   var series= [{
			         name: 'count',
			            type: 'column',
			            yAxis: 0,
			            data: $data1
			         }, {
			            name: 'onlineTime',
			            type: 'column',
			            yAxis: 1,
			            data: $data2,
			            tooltip: {
			                valueSuffix: 'min'
			           }
			        }
			   ];     
			      
			   var json = {};   
			   json.chart = chart;   
			   json.title = title;
			   json.subtitle = subtitle;      
			   json.xAxis = xAxis;
			   json.yAxis = yAxis;
			   json.tooltip = tooltip;  
			  // json.legend = legend;  
			   json.series = series;
			   $('#RoverGraph').highcharts(json);  
	 }
	 
	 
	var $lat=23;
	var $lon=113;
	var $zoom=5;
	
	var mymap = L.map('RoverMap').setView([$lat, $lon], $zoom);
	L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png?access_token={accessToken}', {
				    attribution: '',
				    minZoom:2,
				    maxZoom: 18,
				    id: 'mapbox.streets',
				    accessToken: 'your.mapbox.access.token'
				}).addTo(mymap);
	
	$.getJSON("../Config",{method:"read"}, function(json){
		$lat = json.centerLat;
		$lon = json.centerLon;
		$zoom = json.mapZoom;
		mymap.setView([$lat, $lon], $zoom);
	})
		
	 function paintRoverMap(json){
		 for(var i=0;i<json.position.length;i++){
				var position = json.position[i];
				
				if(position.latitude < 1e-6 && position.longitude < 1e-6){
					continue;
				}
				
				var $icon = L.icon({
			        iconUrl: '../img/blue.png',
			        iconSize: [32,32]
			    });
				
				var marker = L.marker([position.latitude, position.longitude],{
					icon: $icon
				}).addTo(mymap);
			}
	 }
   
})