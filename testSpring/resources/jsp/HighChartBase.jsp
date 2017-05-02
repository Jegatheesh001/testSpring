<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>High Chart</title>
<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
<script src="resources/js/highcharts.js"></script>
<script src="resources/js/exporting.js"></script>
<script>
	/**
	 * Create a global getSVG method that takes an array of charts as an
	 * argument
	 */
	Highcharts.getSVG = function(charts) {
		var svgArr = [], top = 0, width = 0;

		Highcharts
				.each(
						charts,
						function(chart) {
							var svg = chart.getSVG(),
							// Get width/height of SVG for export
							svgWidth = +svg
									.match(/^<svg[^>]*width\s*=\s*\"?(\d+)\"?[^>]*>/)[1], svgHeight = +svg
									.match(/^<svg[^>]*height\s*=\s*\"?(\d+)\"?[^>]*>/)[1];

							svg = svg.replace('<svg',
									'<g transform="translate(0,' + top + ')" ');
							svg = svg.replace('</svg>', '</g>');

							top += svgHeight;
							width = Math.max(width, svgWidth);

							svgArr.push(svg);
						});

		return '<svg height="' + top + '" width="' + width +
        '" version="1.1" xmlns="http://www.w3.org/2000/svg">'
				+ svgArr.join('') + '</svg>';
	};

	/**
	 * Create a global exportCharts method that takes an array of charts as an
	 * argument, and exporting options as the second argument
	 */
	Highcharts.exportCharts = function(charts, options) {

		// Merge the options
		options = Highcharts.merge(Highcharts.getOptions().exporting, options);

		// Post to export server
		Highcharts.post(options.url, {
			filename : options.filename || 'chart',
			type : options.type,
			width : options.width,
			svg : Highcharts.getSVG(charts)
		});
	};
	var chart1 = Highcharts.chart('container1', {

		chart : {
			height : 200
		},

		title : {
			text : 'First Chart'
		},

		credits : {
			enabled : false
		},

		xAxis : {
			categories : [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
					'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ]
		},

		series : [ {
			data : [ 29.9, 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5,
					216.4, 194.1, 95.6, 54.4 ],
			showInLegend : false
		} ],

		exporting : {
			enabled : false
		// hide button
		}

	});

	var chart2 = Highcharts.chart('container2', {

		chart : {
			type : 'column',
			height : 200
		},

		title : {
			text : 'Second Chart'
		},

		xAxis : {
			categories : [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
					'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ]
		},

		series : [ {
			data : [ 176.0, 135.6, 148.5, 216.4, 194.1, 95.6, 54.4, 29.9, 71.5,
					106.4, 129.2, 144.0 ],
			colorByPoint : true,
			showInLegend : false
		} ],

		exporting : {
			enabled : false
		// hide button
		}

	});
	$('#export-png').click(function() {
		Highcharts.exportCharts([ chart1, chart2 ]);
	});

	$('#export-pdf').click(function() {
		Highcharts.exportCharts([ chart1, chart2 ], {
			type : 'application/pdf'
		});
	});
</script>
<style type="text/css">
.container {
	max-width: 600px;
	min-width: 320px;
	margin: 0 auto;
}

#buttonrow {
	max-width: 600px;
	min-width: 320px;
	margin: 0 auto;
}
</style>
</head>

<body>
	<div id="container1" class="container"></div>
	<div id="container2" class="container"></div>
	<div id="buttonrow">
		<button id="export-png">Export to PNG</button>
		<button id="export-pdf">Export to PDF</button>
	</div>
</body>
</html>