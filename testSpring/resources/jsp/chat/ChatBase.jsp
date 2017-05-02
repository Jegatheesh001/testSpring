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

table.sideMenuLayout {
	
}

table.sideMenuLayout tr td.sideMenu {
	border-right: 2px solid grey;
	vertical-align: top;
}

table.sideMenuLayout tr td.body {
	vertical-align: top;
}
</STYLE>
<script src="resources/jsp/chat/GlobalChart.js"></script>
<script type="text/javascript">
	var chatTab = '<s:property value="#session.chatTab"/>';
	$(function() {
		if ('<s:property value="#session.chatUser"/>' == "")
			login();
		else
			globalChat();
	});
</script>
</head>
<body>
	<table class="sideMenuLayout">
		<tr>
			<td class="sideMenu">
				<table class="message-header-container" style="width: 100%;">
					<s:if test="#session.chatUser==null">
						<tr>
							<td id="l" onclick="login();">Login</td>
						</tr>
						<tr>
							<td id="r" onclick="register();">Register</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td id="gc" onclick="globalChat();">Global Chart</td>
						</tr>
						<tr>
							<td id="i" onclick="openinbox()">Inbox</td>
						</tr>
						<tr>
							<td>Drafts</td>
						</tr>
						<tr>
							<td>Trash</td>
						</tr>
					</s:else>
				</table>
			</td>
			<td id="chatContainer" class="body"></td>
		</tr>
	</table>
</body>
</html>
