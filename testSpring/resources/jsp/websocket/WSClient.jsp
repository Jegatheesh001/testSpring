<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebSocket Client</title>
<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	var wsocket;
	function connect() {
		wsocket = new WebSocket("ws://localhost:8080/testSpring/ws/messenger");
		// wsocket = new WebSocket("ws://192.168.0.147:9090");
		// wsocket = new WebSocket("wss://echo.websocket.org");
		wsocket.onopen = onOpen;
		wsocket.onmessage = function(evt) {
			onMessage(evt)
		};
		wsocket.onclose = function(evt) {
			onClose(evt)
		};
		wsocket.onerror = function(evt) {
			onError(evt)
		};
	}

	function onOpen(event) {
		// Display user friendly messages for the successful establishment of connection
		writeToScreen("Connection established");
		text = {
			"user" : $("#user").val(),
			"connect" : "yes"
		}
		wsocket.send(JSON.stringify(text));
	}

	function onMessage(evt) {
		// var msgObj = JSON.parse(evt.data);
		writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data
				+ '</span>');
	}

	function onClose(evt) {
		writeToScreen("DISCONNECTED");
	}

	function onError(evt) {
		writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
	}

	function pushMessage() {
		if ($("#message").val() == "") {
			$("#error").html("Enter Message");
			return false;
		} else {
			$("#error").html("");
		}
		text = {
			"user" : $("#user").val(),
			"msg" : $("#message").val()
		}
		writeToScreen("SENT: " + $("#message").val());
		wsocket.send(JSON.stringify(text));
	}

	function closeClient() {
		wsocket.close();
	}

	function writeToScreen(message) {
		console.log(message);
		var pre = document.createElement("p");
		pre.innerHTML = message;
		document.getElementById("contents").appendChild(pre);
	}
	window.addEventListener("load", connect, false);

	function connect1() {
		wsocket1 = new WebSocket("ws://localhost:8080/testSpring/ws/messenger/online");
		wsocket1.onmessage = function(evt) {
			onMessage1(evt);
		};
	}
	function onMessage1(evt) {
		var msgObj = JSON.parse(evt.data);
		$("#online").html('<span style="color: green;">Online: ' + msgObj.online
				+ '</span>');
	}
	connect1();
</script>

</head>

<body>
	<%
		String user = "";
		if (request.getParameter("u") == null
				|| (request.getParameter("u") != null && request.getParameter("u") == ""))
			user = "Jegatheesh";
		else
			user = request.getParameter("u");
	%>
	<table>
		<tr>
			<td>Hello <%=user%>,
			</td>
		</tr>
		<tr>
			<td><input id="user" value="<%=user%>" type="hidden" /> <input
				id="message" type="text" /> <input type="button" value="Push"
				onclick="pushMessage();" />&nbsp;&nbsp;&nbsp; <input type="button"
				value="Close" onclick="closeClient();" /></td>
		</tr>
		<tr>
			<td><label style="color: red;" id="error"></label></td>
		</tr>
		<tr>
			<td><label style="" id="online"></label></td>
		</tr>
		<tr>
			<td><label id="contents"></label></td>
		</tr>

	</table>

</body>

</html>