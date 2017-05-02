/**
 * 
 */

var window_focus;

$(window).focus(function() {
	window_focus = true;
	refreshChat();
}).blur(function() {
	window_focus = false;
});

var send_request = true;
$('#chatMessageContainer')
		.on(
				'scroll',
				function() {
					if (send_request) {
						if ($(this).scrollTop() + $(this).innerHeight() >= $(this)[0].scrollHeight) {
							send_request = false;
							getOlderForumChat();
						}
					}
				});

function validateForumChat() {
	if ($("#chatMessage").val() == "") {
		$('#send').prop('disabled', true);
		return false;
	} else {
		$('#send').prop('disabled', false);
	}
	return true;
}

function saveForumChat(e) {
	if (validateForumChat()) {
		// On Enter
		if (e) {
			keyCode = e.keyCode;
		} else {
			keyCode = 13;
		}
		if (keyCode == 13) {
			chatId = $("#chatId").val();
			chatMessage = $("#chatMessage").val();
			$.ajax({
				url : "saveForumChat.action?chatMessage.chatMessage="
						+ chatMessage + "&chat.chatId=" + chatId,
				beforeSend : function() {
					$('#send').prop('disabled', true);
					$('#send').val('Posting...');
				},
				success : function(result) {
					$("#chatMessage").val("");
					$('#send').val('Post');
					getLatestForumChat();
					moveToTop("#chatMessageContainer");
				}
			});
		}
	}
}

function getAllForumChat() {
	chatId = $("#chatId").val();
	$.ajax({
		url : "getAllForumChat.action?chat.chatId=" + chatId,
		beforeSend : function() {
		},
		success : function(result) {
			$("#chatMessageContainer").html(result);
		}
	});
}

// Checking for New Messages Every 5 seconds
setInterval(refreshChat, 5000);

function refreshChat() {
	// Only when browser tab is open
	if (window_focus)
		getLatestForumChat();
}

function getLatestForumChat() {
	latestId = $("#maxId").val();
	chatId = $("#chatId").val();
	$.ajax({
		url : "getAllForumChat.action?chat.chatId=" + chatId + "&startValue="
				+ latestId + "&direction=1",
		beforeSend : function() {
		},
		success : function(result) {
			if (result != "")
				$("#maxId").remove();
			$("#chatMessageContainer").html(
					result + $("#chatMessageContainer").html());
		}
	});
}

function getOlderForumChat() {
	olderId = $("#minId").val();
	chatId = $("#chatId").val();
	if (olderId) {
		$.ajax({
			url : "getAllForumChat.action?chat.chatId=" + chatId
					+ "&startValue=" + olderId + "&direction=-1",
			beforeSend : function() {
				$('#loadMore').html('loading content..');
			},
			success : function(result) {
				$("#minId").remove();
				$("#chatMessageContainer").append(result);
				$('#loadMore').html('');
				if (result != "") {
					send_request = true;
				}
			}
		});
	}
}

function moveToTop(id) {
	$(id).scrollTop(0);
}

function moveToBottom(id) {
	$(id).scrollTop($(id).height());
}

function adjustForumChat() {
	$('.forumContent').height($(".bodypart").height());
	$('.chat-list-container').height(
			$(".forumContent").height() - $(".forumHeader").height())
}
adjustForumChat();