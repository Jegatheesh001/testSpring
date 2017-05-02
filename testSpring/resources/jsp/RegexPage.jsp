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
	$(function() {
		//validateEmail();
	});
	function validateEmail() {
		userinput = $("#email").val();
		color = "red";
		var pattern = /^\b[A-Z0-9._%-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b$/i
		if (userinput == "") {
			content = "<span style='color:"+color+"'>" + "Empty..." + "</span>";
			$("#emailAlert").html(content);
		} else if (pattern.test(userinput)) {
			color = "green";
			content = "<span style='color:"+color+"'>" + "Proceed"
					+ "</span>";
			$("#emailAlert").html(content);
		} else if (!pattern.test(userinput)) {
			content = "<span style='color:"+color+"'>" + "Not Valid"
					+ "</span>";
			$("#emailAlert").html(content);
		}
	}
</script>
</head>
<body>
	<a class="heading" onclick='$("#emailDiv").toggle();'>Mail
		Validation</a> :
	<div id="emailDiv" style="display: none;">
		<br /> <input id="email" type="text" class="inputtypes"
			onkeyup="validateEmail();" /> <span id="emailAlert"></span>
	</div>
</body>
</html>
