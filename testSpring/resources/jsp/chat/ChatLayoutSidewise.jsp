<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>

<head>
<link href="resources/style/mystyle.css" rel="stylesheet"
	type="text/css" />

<title><tiles:insertAttribute name="title" ignore="true" /></title>

<style type="text/css">
table.layoutTable {
	text-align: left;
	width: 90%;
}

table.layoutTable tr {
	
}

table.layoutTable tr td.padding {
	
}

table.layoutTable tr td.header {
	background-color: grey;
	padding: 10px;
}

table.layoutTable tr td.footer {
	background-color: grey;
	padding: 10px;
}

table.layoutTable tr td.bodypart {
	border-left: 2px solid grey;
	border-right: 2px solid grey;
	vertical-align: top;
}

table.sideMenuLayout {
	
}

table.sideMenuLayout tr td.sideMenu {
	border-right: 2px solid grey;
	vertical-align: top;
}

table.sideMenuLayout tr td.body {
	vertical-align: top;
}
</style>

<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="resources/js/angular.min.js"></script>
<script type="text/javascript">
	function adjust() {
		$('.sideMenu').width($(".bodypart").width()*0.2)
		$('.bodypart').height($(window).height() - 130);
		$('.sideMenuLayout').height($(window).height() - 130);
	}
	setInterval(adjust, 1);
</script>
</head>

<body>

	<div align="center" style="">
		<table class="layoutTable">
			<tr>
				<td height="60" class="header"><tiles:insertAttribute
						name="header" /></td>
			</tr>
			<tr>
				<td width="100%" class="bodypart"><table class="sideMenuLayout">
						<tr>
							<td class="sideMenu"><tiles:insertAttribute name="sideMenu" /></td>
							<td class="body"><tiles:insertAttribute name="body" /></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td style="padding-top: 5px" class="footer"><tiles:insertAttribute
						name="footer" /></td>
			</tr>
		</table>
	</div>
</body>

</html>

