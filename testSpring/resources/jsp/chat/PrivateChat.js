/**
 * For Inbox Communication
 */
var load = true;
var move_Bottom = false;

var chat = true;
var send_request = true;// older
var new_request = true;// newer

var window_focus;

$(window).focus(function() {
	window_focus = true;
	// refreshInboxChat();
}).blur(function() {
	window_focus = false;
});

// Checking for New Messages Every 5 seconds
setInterval(refreshInboxChat, 5000);

function refreshInboxChat() {
	// Only when browser tab is open
	var inboxUserId = $("#inboxUserId").val();
	if (window_focus) {
		getAllInboxUsers();
		if (chat && new_request)
			getLatestInboxChat();
	}
}

$('#inboxMessageContent').on('scroll', function() {
	if (send_request) {
		if ($(this).scrollTop() == 0) {
			send_request = false;
			getOlderInboxChat();
		}
	}
});

function getAllInboxUsers() {
	$.ajax({
		url : "getAllInboxUsers.action",
		beforeSend : function() {
		},
		success : function(result) {
			$(".inboxUsers").html(result);
		}
	});
}

function getUserName(userId) {
	$.ajax({
		url : "getUserName.action?inboxUserId=" + userId,
		beforeSend : function() {
		},
		success : function(result) {
			$('#userName').val(result);
		}
	});
}

function openInboxChat(inboxUserId) {
	if (inboxUserId) {
		getInboxChat(inboxUserId);
		$('#userName').prop('disabled', true);
		getUserName(inboxUserId);
	} else {
		getInboxChat();
	}
}

// First Result
function getInboxChat(inboxUserId) {
	chat = true;
	send_request = true;
	subQuery = "";
	if (inboxUserId) {
		subQuery = "inboxUserId=" + inboxUserId;
	}
	$.ajax({
		url : "getInboxChat.action?" + subQuery,
		beforeSend : function() {
		},
		success : function(result) {
			$("#inboxMessageContent").html(result);
			if (result != "" && !inboxUserId) {
				$('#userName').prop('disabled', true);
				getUserName($('#inboxUserId').val());
			}
			moveToBottom("#inboxMessageContent");
		}
	});
}

function getLatestInboxChat() {
	latestId = $("#maxId").val();
	inboxUserId = $('#inboxUserId').val();
	new_request = false;
	alert(inboxUserId + " " + latestId);
	if (latestId) {
		$.ajax({
			url : "getInboxChat.action?inboxUserId=" + inboxUserId
					+ "&startValue=" + latestId + "&direction=1",
			beforeSend : function() {
			},
			success : function(result) {
				$("#maxId").remove();
				$("#inboxMessageContent").html(
						$("#inboxMessageContent").html() + result);
				// For Saving
				if (move_Bottom) {
					moveToBottom("#inboxMessageContent");
					move_Bottom = false;
				}
				new_request = true;
			}
		});
	}
}

function getOlderInboxChat() {
	olderId = $("#minId").val();
	inboxUserId = $('#inboxUserId').val();
	if (olderId) {
		$.ajax({
			url : "getInboxChat.action?inboxUserId=" + inboxUserId
					+ "&startValue=" + olderId + "&direction=-1",
			beforeSend : function() {
			},
			success : function(result) {
				$("#minId").remove();
				$("#inboxMessageContent").html(
						result + $("#inboxMessageContent").html());
				if (result != "") {
					send_request = true;
				}
				$("#inboxMessageContent").scrollTop(30);
			}
		});
	}
}

function openNewChat() {
	chat = false;
	$.ajax({
		url : "newInboxChat.action",
		beforeSend : function() {
		},
		success : function(result) {
			$('#userName').val("");
			$('#userName').prop('disabled', false);
			$("#inboxMessageContent").html("");
		}
	});
}

function validateInboxChat() {
	if ($("#message").val() == "") {
		$('#send').prop('disabled', true);
		return false;
	} else {
		$('#send').prop('disabled', false);
	}
	return true;
}

function saveInboxMessage(e) {
	if (validateInboxChat()) {
		// On Enter
		if (e) {
			keyCode = e.keyCode;
		} else {
			keyCode = 13;
		}
		if (keyCode == 13) {
			inboxUserId = $("#inboxUserId").val();
			message = $("#message").val();
			$.ajax({
				url : "saveInboxMessage.action?inboxUserId=" + inboxUserId
						+ "&message.message=" + message,
				beforeSend : function() {
					$('#send').prop('disabled', true);
				},
				success : function(result) {
					$("#message").val("");
					getAllInboxUsers();
					move_Bottom = true;
					getLatestInboxChat();
				}
			});
		}
	}
}

function getUserNameList() {
	userName = $("#userName").val();
	$('#userName').autocomplete({
		source : function(request, response) {
			$.ajax({
				url : "getUserNameList.action?userName=" + userName,
				type : "GET",
				dataType : "json",
				delay : 500,
				success : function(data) {
					response($.map(data, function(item) {
						return {
							label : item.userName,
							id : item.userId,
							title : item.loginName
						};
					}));
				}
			});
		},
		minLength : 0,
		focus : function(event, ui) {
			$("#userName").val(ui.item.label);
			return false;
		},
		select : function(event, ui) {
			$("#inboxUserId").val(ui.item.id);
			openInboxChat(ui.item.id);
		}
	}).autocomplete("instance")._renderItem = function(ul, item) {
		return $("<li>").append(
				"<div>" + item.label + " [" + item.title + "]" + "</div>")
				.appendTo(ul);
	};
}

function moveToTop(id) {
	$(id).scrollTop(0);
}

function moveToBottom(id) {
	$(id).scrollTop($(id).height());
}

function getClientTimeZone() {
	var timezone = new Date().getTimezoneOffset() / 60 * (-1);
	return timezone;
}

function adjustInboxChat() {
	$('.inboxContent').height($(".bodypart").height());
	$('#inboxMessageContent')
			.height(
					$('.inboxContent').height()
							- ($('.inboxHeader').height() + $('.inboxFooter')
									.height()));
}

if (load) {
	getAllInboxUsers();
	openInboxChat();
	adjustInboxChat();
	load = false;
}
