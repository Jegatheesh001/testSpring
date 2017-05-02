<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<STYLE type="text/css">
a.heading {
	color: black;
}

a.heading:HOVER {
	color: blue;
	cursor: pointer;
}
</STYLE>
<script type="text/javascript">
	var chatTab = '<s:property value="#session.chatTab"/>';
	$(function() {
		$(".message-header-container tr td").removeClass("selected")
		$(".message-header-container tr td[id='" + chatTab + "']").addClass(
				"selected");
	});
</script>
</head>
<body>
	<table class="message-header-container" style="width: 100%;">
		<tr>
			<td id="nm" onclick="newmessage();">New Message</td>
		</tr>
		<tr>
			<td id="i" onclick="openinbox()">Inbox</td>
		</tr>
		<tr>
			<td id="s" onclick="opensentitems()">Sent Items</td>
		</tr>
		<tr>
			<td>Drafts</td>
		</tr>
		<tr>
			<td>Trash</td>
		</tr>
	</table>
</body>
</html>
