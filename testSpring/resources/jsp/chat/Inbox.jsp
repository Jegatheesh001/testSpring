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
<script src="resources/jsp/chat/PrivateChat.js"></script>
<script type="text/javascript">
	$(function() {
		$('#send').prop('disabled', true);
		$('#userName').width($('.inboxContent').width() * 0.9);
		$('#message').width($('.inboxContent').width() * 0.85);
	});
	window.load(function() {
		//moveToBottom("#inboxMessageContent");
	});
</script>
</head>
<body>
	<table style="width: 100%">
		<tr>
			<td class="inboxContent" width="70%">
				<div class="inboxHeader">
					<input id="userName" type="text" onkeyup="getUserNameList()" />&nbsp;<span
						style="color: brown; font-weight: bold; cursor: pointer;"
						title="New Chat" onclick="openNewChat();">+</span>&nbsp;&nbsp;&nbsp;<span
						style="color: green; font-weight: bold; cursor: pointer;"
						title="Refresh" onclick="getLatestInboxChat();">R</span>
				</div>
				<div id="inboxMessageContent"
					style="overflow: scroll; overflow-x: none;"></div>
				<div class="inboxFooter">
					<input id="message" type="text" onkeyup='saveInboxMessage(event);' />
					<input id="send" type="button" onclick='saveInboxMessage();'
						value='Send' />
				</div>
			</td>
			<td class="inboxUsers"></td>
		</tr>
	</table>
</body>
</html>
