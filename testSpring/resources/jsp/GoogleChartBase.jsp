<html>
<head>
<!--Load the AJAX API-->
<script type="text/javascript" src="resources/js/googlecharts/loader.js"></script>
<script type="text/javascript">
	var loading = '<img src="resources/images/load.gif" />';
	drawGoogleChart('corechart', drawSeriesChart);

	function drawGoogleChart(chart, method) {

		google.charts.load("current", {
			packages : [ chart ]
		});
		google.charts.setOnLoadCallback(method);

	}

	function drawCalendarChart() {
		var dataTable = new google.visualization.DataTable();
		dataTable.addColumn({
			type : 'date',
			id : 'Date'
		});
		dataTable.addColumn({
			type : 'number',
			id : 'Won/Loss'
		});
		dataTable.addRows([ [ new Date(2012, 3, 13), 37032 ],
				[ new Date(2012, 3, 14), 38024 ],
				[ new Date(2012, 3, 15), 38024 ],
				[ new Date(2012, 3, 16), 38108 ],
				[ new Date(2012, 3, 17), 38229 ],
				// Many rows omitted for brevity.
				[ new Date(2013, 9, 4), 38177 ],
				[ new Date(2013, 9, 5), 38705 ],
				[ new Date(2013, 9, 12), 38210 ],
				[ new Date(2013, 9, 13), 38029 ],
				[ new Date(2013, 9, 19), 38823 ],
				[ new Date(2013, 9, 23), 38345 ],
				[ new Date(2013, 9, 24), 38436 ],
				[ new Date(2013, 9, 30), 38447 ] ]);

		var chart = new google.visualization.Calendar(document
				.getElementById('calendar_basic'));

		var options = {
			title : "Red Sox Attendance",
			height : 350,
		};

		chart.draw(dataTable, options);
	}
	function drawBarChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Year', 'Sales', 'Expenses', 'Profit' ],
				[ '2014', 1000, 400, 200 ], [ '2015', 1170, 460, 250 ],
				[ '2016', 660, 1120, 300 ], [ '2017', 1030, 540, 350 ] ]);

		var options = {
			chart : {
				title : 'Company Performance',
				subtitle : 'Sales, Expenses, and Profit: 2014-2017',
			},
			bars : 'horizontal' // Required for Material Bar Charts.
		};

		var chart = new google.charts.Bar(document
				.getElementById('calendar_basic'));

		chart.draw(data, options);
	}
	function drawSeriesChart() {

		var data = google.visualization.arrayToDataTable([
				[ 'ID', 'Life Expectancy', 'Fertility Rate', 'Region',
						'Population' ],
				[ 'CAN', 80.66, 1.67, 'North America', 33739900 ],
				[ 'DEU', 79.84, 1.36, 'Europe', 81902307 ],
				[ 'DNK', 78.6, 1.84, 'Europe', 5523095 ],
				[ 'EGY', 72.73, 2.78, 'Middle East', 79716203 ],
				[ 'GBR', 80.05, 2, 'Europe', 61801570 ],
				[ 'IRN', 72.49, 1.7, 'Middle East', 73137148 ],
				[ 'IRQ', 68.09, 4.77, 'Middle East', 31090763 ],
				[ 'ISR', 81.55, 2.96, 'Middle East', 7485600 ],
				[ 'RUS', 68.6, 1.54, 'Europe', 141850000 ],
				[ 'USA', 78.09, 2.05, 'North America', 307007000 ] ]);

		var options = {
			title : 'Correlation between life expectancy, fertility rate '
					+ 'and population of some world countries (2010)',
			hAxis : {
				title : 'Life Expectancy'
			},
			vAxis : {
				title : 'Fertility Rate'
			},
			bubble : {
				textStyle : {
					fontSize : 11
				}
			}
		};

		var chart = new google.visualization.BubbleChart(document
				.getElementById('calendar_basic'));
		chart.draw(data, options);
	}
	function drawAreaChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Year', 'Sales', 'Expenses' ], [ '2013', 1000, 400 ],
				[ '2014', 1170, 460 ], [ '2015', 660, 1120 ],
				[ '2016', 1030, 540 ] ]);

		var options = {
			title : 'Company Performance',
			hAxis : {
				title : 'Year',
				titleTextStyle : {
					color : '#333'
				}
			},
			vAxis : {
				minValue : 0
			}
		};

		var chart = new google.visualization.AreaChart(document
				.getElementById('calendar_basic'));
		chart.draw(data, options);
	}
	function drawCandlestickChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Mon', 20, 28, 38, 45 ], [ 'Tue', 31, 38, 55, 66 ],
				[ 'Wed', 50, 55, 77, 80 ], [ 'Thu', 77, 77, 66, 50 ],
				[ 'Fri', 68, 66, 22, 15 ]
		// Treat first row as data as well.
		], true);

		var options = {
			legend : 'none'
		};

		var chart = new google.visualization.CandlestickChart(document
				.getElementById('calendar_basic'));

		chart.draw(data, options);
	}
	function drawColumnChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Year', 'Sales', 'Expenses', 'Profit' ],
				[ '2014', 1000, 400, 200 ], [ '2015', 1170, 460, 250 ],
				[ '2016', 660, 1120, 300 ], [ '2017', 1030, 540, 350 ] ]);

		var options = {
			chart : {
				title : 'Company Performance',
				subtitle : 'Sales, Expenses, and Profit: 2014-2017',
			}
		};

		var chart = new google.charts.Bar(document
				.getElementById('calendar_basic'));

		chart.draw(data, options);
	}
	function drawComboChart() {
		// Some raw data (not necessarily accurate)
		var data = google.visualization.arrayToDataTable([
				[ 'Month', 'Bolivia', 'Ecuador', 'Madagascar',
						'Papua New Guinea', 'Rwanda', 'Average' ],
				[ '2004/05', 165, 938, 522, 998, 450, 614.6 ],
				[ '2005/06', 135, 1120, 599, 1268, 288, 682 ],
				[ '2006/07', 157, 1167, 587, 807, 397, 623 ],
				[ '2007/08', 139, 1110, 615, 968, 215, 609.4 ],
				[ '2008/09', 136, 691, 629, 1026, 366, 569.6 ] ]);

		var options = {
			title : 'Monthly Coffee Production by Country',
			vAxis : {
				title : 'Cups'
			},
			hAxis : {
				title : 'Month'
			},
			seriesType : 'bars',
			series : {
				5 : {
					type : 'line'
				}
			}
		};

		var chart = new google.visualization.ComboChart(document
				.getElementById('calendar_basic'));
		chart.draw(data, options);
	}
</script>
</head>
<body>
	<div style="color: blue;">
		<a style="cursor: pointer;"
			onclick="drawGoogleChart('calendar',drawCalendarChart);">Calendar
			Chart</a>&nbsp;|| <a style="cursor: pointer;"
			onclick="drawGoogleChart('bar',drawBarChart);">Bar Chart</a>&nbsp;||
		<a style="cursor: pointer;"
			onclick="drawGoogleChart('corechart',drawAreaChart);"> Area Chart</a>&nbsp;||
		<a style="cursor: pointer;"
			onclick="drawGoogleChart('corechart',drawSeriesChart);">Series
			Chart</a>&nbsp;|| <a style="cursor: pointer;"
			onclick="drawGoogleChart('corechart',drawCandlestickChart);">Candle
			Stick Chart</a>&nbsp;|| <a style="cursor: pointer;"
			onclick="drawGoogleChart('bar',drawColumnChart);">Column Chart</a>&nbsp;||
		<a style="cursor: pointer;"
			onclick="drawGoogleChart('bar',drawComboChart);">Combo Chart</a>&nbsp;||
	</div>
	<div id="calendar_basic" style="width: 1000px; height: 350px;"></div>
</body>
</html>