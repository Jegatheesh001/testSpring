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

<script src="resources/jsp/chat/ForumChat.js"></script>
<script type="text/javascript">
	$(function() {
		getAllForumChat();
		$('#send').prop('disabled', true);
		$('#chatMessage').width($('.forumContent').width() * 0.6);
	});
</script>
</head>
<body>
	<div class="forumContent" style="width: 70%; text-align: left;">
		<div class="forumHeader" style="margin-bottom: 5px;">
			<b><s:property value="chat.chatName" /></b> Created By '
			<s:property value="chat.createdBy.userName" />
			'
			<s:hidden id="chatId" name="chat.chatId" />
			<input id="chatMessage" onkeyup='saveForumChat(event);' type="text"
				value="" /> <input id="send" type="button"
				onclick='saveForumChat();' value='Post' />&nbsp;&nbsp;&nbsp;&nbsp;<span
				id="loadMore" style="color: brown;"></span>
		</div>
		<div class="chat-list-container" id="chatMessageContainer"
			style="overflow: scroll; overflow-x: none;"></div>
	</div>
</body>
</html>
