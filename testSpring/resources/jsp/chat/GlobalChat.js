/**
 * 
 */

function newTab(tab) {
	$(".message-header-container tr td").removeClass("selected")
	$(".message-header-container tr td[id='" + tab + "']").addClass("selected");
}

function login() {
	newTab("l");
	$.ajax({
		url : "chatLogin.action",
		beforeSend : function() {
			$('#chatContainer').html('Loading...');
		},
		success : function(result) {
			$('#chatContainer').html(result);
		}
	});
}

function register() {
	newTab("r");
	$.ajax({
		url : "chatRegister.action",
		beforeSend : function() {
			$('#chatContainer').html('Loading...');
		},
		success : function(result) {
			$('#chatContainer').html(result);
		}
	});
}

function checkUserAvailability(userName) {
	if (userName.length > 3) {
		$.ajax({
			url : "checkUserAvailability.action?user.loginName=" + userName,
			beforeSend : function() {
				$('#userStatus').html('Checking...');
			},
			success : function(result) {
				if (result.response > 0) {
					response = "User Name Not Available";
					$('#userStatus').css("color", "red");
				} else {
					response = userName + " is Available";
					$('#userStatus').css("color", "green");
				}
				$('#userStatus').html(response);
			}
		});
	} else {
		$('#userStatus').html("User Name must have 4 characters");
		$('#userStatus').css("color", "red");
	}
}

function hexc(colorval) {
	var parts = colorval.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
	delete (parts[0]);
	for (var i = 1; i <= 3; ++i) {
		parts[i] = parseInt(parts[i]).toString(16);
		if (parts[i].length == 1)
			parts[i] = '0' + parts[i];
	}
	userStatusColor = '#' + parts.join('');
}

function validateUser() {
	if ($('#userName').val() == "") {
		alert("Enter User Name");
		return false;
	}
	if ($('#userPassword').val() == "") {
		alert("Enter Password");
		return false;
	}
	if ($('#repeatPassword').val() == "") {
		alert("Enter Password Again");
		return false;
	}
	if ($('#repeatPassword').val() != $('#userPassword').val()) {
		alert("Both Password Must Same");
		return false;
	}
	hexc($('#userStatus').css('color'));
	if (userStatusColor == "#ff0000") {
		alert("User Name Not Available");
		return false;
	}
	return true;
}

function validateUserLogin() {
	userName = $('#userName').val();
	userPassword = $('#userPassword').val();
	if (userName == "") {
		alert("Enter User Name");
		return false;
	}
	if (userPassword == "") {
		alert("Enter Password");
		return false;
	}
	var timeZone = getClientTimeZone();
	$.ajax({
		url : "loginCheck.action?timeZone=" + timeZone + "&user.loginName="
				+ userName + "&user.userPassword=" + userPassword,
		beforeSend : function() {
			$('#loginStatus').html('Checking...');
			response = "";
		},
		success : function(result) {
			if (result.response > 0) {
				window.location.reload("chatBase.action");
			} else {
				$('#loginStatus').css('color', 'red');
				response = "Login Failed...";
			}
			$('#loginStatus').html(response);
		}
	});
	return false;
}

function getClientTimeZone() {
	var timeZone = new Date().getTimezoneOffset() / 60 * (-1);
	return timeZone;
}

function newmessage() {
	newTab("nm");
	$.ajax({
		url : "chatBase.action",
		beforeSend : function() {
			$('#chatContainer').html('Loading...');
		},
		success : function(result) {
		}
	});
}

function savemessage() {
	newTab("nm");
	$.ajax({
		url : "action/messageAction.php?method=send-message&to=" + to
				+ "&message=" + message,
		beforeSend : function() {
			$('#send').prop('disabled', true);
			$('#send').val('Sending...');
		},
		success : function(result) {
			$("#newmessage").val("");
			$('#send').prop('disabled', false);
			$('#send').val('Send');
			openchat(to);
		}
	});
}

function openinbox() {
	newTab("i");
	$.ajax({
		url : "openInbox.action",
		beforeSend : function() {
			$('#chatContainer').html('Loading...');
		},
		success : function(result) {
			$('#chatContainer').html(result);
		}
	});
}

function opensentitems() {
	newTab("s");
	$.ajax({
		url : "chatBase.action",
		beforeSend : function() {
			$('#chatContainer').html('Loading...');
		},
		success : function(result) {
		}
	});
}

function globalChat() {
	newTab("gc");
	$.ajax({
		url : "openChat.action?chat.chatId=" + "1",
		beforeSend : function() {
			$('#chatContainer').html('Loading...');
		},
		success : function(result) {
			$('#chatContainer').html(result);
		}
	});
}

function chatRooms() {
	newTab("cr");// ChatRooms
}

function openChat(chatId) {
	newTab("cr");// ChatRooms
	$.ajax({
		url : "openChat.action?chat.chatId=" + chatId,
		beforeSend : function() {
			$('#chatContainer').html('Loading...');
		},
		success : function(result) {
			$('#chatContainer').html(result);
		}
	});
}

function adjust() {
	$('.sideMenu').width($(".bodypart").width() * 0.2)
	$('.body').width($(".bodypart").width() * 0.8)
	$('.sideMenuLayout').height($(window).height() - 130);
}

setInterval(adjust, 1);