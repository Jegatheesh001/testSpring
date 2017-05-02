<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Google Maps</title>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCvlLsmL1nm9BPeqIdstGINFX3mlEt78T8"></script>
<script>
	function loadRoadMap() {
		var mapOptions = {
			center : new google.maps.LatLng(17.609993, 83.221436),
			zoom : 9,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		};

		var map = new google.maps.Map(document.getElementById("sample"), mapOptions);
	}

	function loadSatelliteMap() {
		var mapOptions = {
			center : new google.maps.LatLng(8.2359285, 77.1916497),
			zoom : 12,
			mapTypeId : google.maps.MapTypeId.SATELLITE
		};

		var map = new google.maps.Map(document.getElementById("sample"), mapOptions);
	}

	function loadHybridMap() {
		var mapOptions = {
			center : new google.maps.LatLng(8.2359285, 77.1916497),
			zoom : 12,
			mapTypeId : google.maps.MapTypeId.Hybrid
		};

		var map = new google.maps.Map(document.getElementById("sample"), mapOptions);
	}

	function loadTERRAINMap() {
		var mapOptions = {
			center : new google.maps.LatLng(8.2359285, 77.1916497),
			zoom : 12,
			mapTypeId : google.maps.MapTypeId.TERRAIN
		};

		var map = new google.maps.Map(document.getElementById("sample"), mapOptions);
	}

	function loadMap() {
		var mapOptions = {
			center : new google.maps.LatLng(8.2359285, 77.1916497),
			zoom : 12,
			mapTypeControl : true,

			mapTypeControlOptions : {
				style : google.maps.MapTypeControlStyle.DROPDOWN_MENU,
				position : google.maps.ControlPosition.TOP_CENTER,

				mapTypeIds : [
					google.maps.MapTypeId.ROADMAP,
					google.maps.MapTypeId.SATELLITE,
					google.maps.MapTypeId.TERRAIN
				]
			},
			zoomControl : true,

			zoomControlOptions : {
				style : google.maps.ZoomControlStyle.SMALL,
				position : google.maps.ControlPosition.RIGHT_TOP
			}
		}

		var map = new google.maps.Map(document.getElementById("sample"), mapOptions);
		var marker = new google.maps.Marker({
			position : new google.maps.LatLng(8.2359285, 77.1916497),
			map : map
		});

	}

	google.maps.event.addDomListener(window, 'load', loadMap);
</script>

</head>

<body>
	<div id="sample" style="width: 580px; height: 400px;"></div>
</body>
</html>