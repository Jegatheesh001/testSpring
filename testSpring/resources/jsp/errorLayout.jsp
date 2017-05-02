<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<link href="/test/resources/styles/mystyle.css" rel="stylesheet"
	type="text/css">
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<style type="text/css">
table.layoutTable {
	text-align: left;
	width: 90%;
	margin: 0px auto;
	border: 2px solid grey;
}

table.layoutTable tr {
	
}

table.layoutTable tr td {
	padding: 10px;
}

table.layoutTable tr td.bodypart {
	vertical-align: top;
}
</style>

<script type="text/javascript"
	src="/aims/resources/js/jquery-1.4.3.min.js"></script>
<script>
	$(document).ready(function() {
		$('#bodypart').height($(window).height() - 115);
	});
</script>
</head>

<body>

	<div align="center" style="">
		<table cellpadding="0" cellspacing="0" class="layoutTable">
			<tr>
				<td height="60" colspan="3" class="header"><tiles:insertAttribute
						name="header" /></td>
			</tr>
			<tr>
				<td id="bodypart" class="bodypart" colspan="2"><tiles:insertAttribute
						name="body" />
			</tr>
			<tr>
				<td colspan="2"><tiles:insertAttribute name="footer" /></td>
			</tr>

		</table>
	</div>
</body>

</html>

