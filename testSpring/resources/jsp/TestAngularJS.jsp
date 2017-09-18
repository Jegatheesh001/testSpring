<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Angular JS</title>
<script>
	$(function() {
	});
	var app = angular.module('myApp', []);
	app.controller('myCtrl', function($scope) {
		$scope.name = "Jegatheesh M";
		$scope.firstName = "Jegatheesh";
		$scope.lastName = "Mageswaran";
		$scope.records = [ {
			"Name" : "Alfreds Futterkiste",
			"Country" : "Germany"
		}, {
			"Name" : "Berglunds snabbköp",
			"Country" : "Sweden"
		}, {
			"Name" : "Centro comercial Moctezuma",
			"Country" : "Mexico"
		}, {
			"Name" : "Ernst Handel",
			"Country" : "Austria"
		} ]
	});
</script>
</head>
<body>
	<div data-ng-app="myApp" data-ng-controller="myCtrl">
		<p>
			Name : <input type="text" data-ng-model="name">
		</p>
		<h1>Hello {{name}}</h1>
		<p>
			The name is <span data-ng-bind="name"></span>
		</p>
		<p>-------------------------------------</p>
		First Name: <input type="text" data-ng-model="firstName"><br>
		Last Name: <input type="text" data-ng-model="lastName"><br>
		<br> Full Name: {{firstName + " " + lastName}}
		<p>-------------------------------------</p>
		<p data-ng-init="myCol='lightblue'">
			Color : <input type="text" data-ng-model="myCol">
		</p>
		<input style="background-color: {{myCol}}" />
		<p>-------------------------------------</p>
		<table>
			<tr data-ng-repeat="x in records">
				<td>{{x.Name}}</td>
				<td>{{x.Country}}</td>
			</tr>
		</table>
		<p>-------------------------------------</p>
		<p data-ng-init="price=18.5;quantity=1;">
			Quantity: <input type="number" step="1" data-ng-model="quantity"/> Costs: <input
				type="number" step="0.01" data-ng-model="price"/> Total: {{ quantity * price }}
		</p>
		<p>-------------------------------------</p>
		<!--directive:date-picker-->
		<input type="text" placeholder="Date Picker" class="date-picker"/>
		<p>-------------------------------------</p>
	</div>
</body>
</html>