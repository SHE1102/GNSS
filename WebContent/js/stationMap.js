$(function(){
	
	var $lat=23;
	var $lon=113;
	var $zoom=5;
	
	var mymap = L.map('OSM').setView([$lat, $lon], $zoom);
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
	
	$.getJSON("../Station", {userType: "Tourist"},function(json){
		showMarker(json);
	})
	
	function showMarker(json){
		for(var i=0;i<json.station.length;i++){
			var station = json.station[i];
			
			var $htmlcontext = "<img src='../img/blue.png'/><span style='background-color:#999;color:white;'>"+station.id+"</span>";
			var $icon = L.divIcon({
				className:'iconDiv',
				html:$htmlcontext,
				iconSize: [32, 32]
			})
			var marker = L.marker([station.latitude, station.longitude],{
				icon: $icon
			}).addTo(mymap);
			
			marker.bindPopup(station.name).closePopup();
			marker.on("click",function(){	
				this.closePopup();
				var $stationName = this.getPopup().getContent();
				var $href = "../Monograph?name="+$stationName;
				window.open($href, '_blank');
			});
			
		}
	}
	
})