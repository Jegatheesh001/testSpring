<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HTML Dragger</title>
<link rel="icon" href="resources/images/favicon.ico" type="image/x-icon">
<style type="text/css">
.tags {
	padding: 5px;
	width: 95%;
	background-color: gray;
	margin: 2px;
	cursor: pointer;
}

.dropSource {
	padding: 10px;
	width: 200px;
	height: 500px;
	border: 1px solid #aaaaaa;
}

.dropTarget {
	float: left;
	padding: 10px;
	width: 500px;
	height: 500px;
	border: 1px solid #aaaaaa;
}
</style>

<style type="text/css">
.custom-source-menu, .custom-target-menu {
	display: none;
	z-index: 1000;
	position: absolute;
	overflow: hidden;
	border: 1px solid #CCC;
	white-space: nowrap;
	font-family: sans-serif;
	background: #FFF;
	color: #333;
	border-radius: 5px;
	padding: 0;
}

/* Each of the items in the list */
.custom-source-menu li, .custom-target-menu li {
	padding: 8px 12px;
	cursor: pointer;
	list-style-type: none;
	transition: all .3s ease;
}

.custom-source-menu li:hover, .custom-target-menu li:hover {
	background-color: #DEF;
}
</style>
<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="resources/js/angular.min.js"></script>
<script type="text/javascript" src="resources/jsp/HTMLDragger.js"></script>
<script type="text/javascript" src="resources/jsp/ContextMenu.js"></script>
<script>
	$(function() {
	});
</script>
</head>
<body>
	<div>
		<ul class='custom-target-menu'>
			<li data-action="copy">Copy To ClipBoard</li>
		</ul>
		<ul class='custom-source-menu'>
			<li data-action="add">Add</li>
		</ul>
	</div>
	<table>
		<tr>
			<td>
				<div class="dropSource">
					<div style="cursor: pointer; float: right;" onclick="back();">Back</div>
					<br />
					<div class="tags" ondragstart="dragStart(event)"
						ondragend="dragEnd(event)" draggable="true" id="break">Break
						Line</div>
					<div class="tags" ondragstart="dragStart(event)"
						ondragend="dragEnd(event)" draggable="true" id="tab">Tab
						Space</div>
					<div class="tags" ondragstart="dragStart(event)"
						ondragend="dragEnd(event)" draggable="true" id="text">Text</div>
					<div class="tags" ondragstart="dragStart(event)"
						ondragend="dragEnd(event)" draggable="true" id="textField">TextField</div>
				</div>
			</td>
			<td><div class="dropTarget" ondrop="drop(event)"
					ondragover="allowDrop(event)"></div></td>
		</tr>
		<tr>
			<td colspan="2"><div id="status"></div></td>
		</tr>
	</table>
</body>
</html>