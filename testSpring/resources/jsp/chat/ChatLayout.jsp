<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>
<link href="resources/style/mystyle.css" rel="stylesheet"
	type="text/css" />
<link rel="icon" href="resources/images/favicon.ico" type="image/x-icon">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<style type="text/css">
table.layoutTable {
	text-align: left;
	width: 90%;
	margin: 0px auto;
}

table.layoutTable tr {
	
}

table.layoutTable tr td {
	
}

table.layoutTable tr td.header {
	background-color: grey;
	padding: 10px;
}

table.layoutTable tr td.bodypart {
	border-left: 2px solid grey;
	border-right: 2px solid grey;
	vertical-align: top;
}

table.layoutTable tr td.footer {
	background-color: grey;
	padding: 10px;
}
</style>

<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="resources/js/angular.min.js"></script>
<script type="text/javascript" src="resources/js/jquery-ui.min.js"></script>
<link href="resources/js/jquery-ui.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	function adjust() {
		$('#bodypart').height($(window).height() - 150);
	}
	setInterval(adjust, 1);
</script>
</head>

<body>

	<div align="center" style="">
		<table cellpadding="0" cellspacing="0" class="layoutTable">
			<tr>
				<td height="60" class="header"><tiles:insertAttribute
						name="header" /></td>
			</tr>
			<tr>
				<td id="bodypart" class="bodypart"><tiles:insertAttribute
						name="body" /></td>
			</tr>
			<tr>
				<td style="padding-top: 5px" class="footer"><tiles:insertAttribute
						name="footer" /></td>
			</tr>
		</table>
	</div>
</body>

</html>

