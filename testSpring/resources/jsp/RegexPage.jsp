<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<link
	href="https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
	rel="stylesheet">
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<STYLE type="text/css">
</STYLE>
<script type="text/javascript">
	$(function() {
		//validateEmail();
		var availableTutorials = [ "ActionScript", "Boostrap", "C", "C++",
				"Java", "Android", "Spring", "Struts", "JSP", "Hibernate",
				"MySQL" ];
		$("#automplete").autocomplete({
			source : availableTutorials
		//source : "/eclinic/eclinic_jsp/registration/ListAllSymptoms.jsp"
		});
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
			content = "<span style='color:"+color+"'>" + "Proceed" + "</span>";
			$("#emailAlert").html(content);
		} else if (!pattern.test(userinput)) {
			content = "<span style='color:"+color+"'>" + "Not Valid"
					+ "</span>";
			$("#emailAlert").html(content);
		}
	}
	function postJSONArray() {
		var users = [ {
			"userId" : "1",
			"userName" : "Jegatheesh"
		}, {
			"userId" : "2",
			"userName" : "Indra"
		}, {
			"userId" : "3",
			"userName" : "Sree"
		}, {
			"userId" : "4",
			"userName" : "Baiju"
		} ];
		
		$.ajax({
			type : "POST",
			url : "postJSONArray.action",
			data : "users="+JSON.stringify(users),
			success : function(data) {
			},
			failure : function(errMsg) {
				alert(errMsg);
			}
		});
	}
</script>
</head>
<body>
	<a onclick='$("#emailDiv").toggle();' style="cursor: pointer;">Mail
		Validation</a>
	<div id="emailDiv" style="">
		<input id="email" type="text" class="inputtypes"
			onkeyup="validateEmail();" /> <span id="emailAlert"></span>
	</div>
	<br />
	<div class="ui-widget">
		<label for="tags" style="">Auto complete: </label> <input
			id="automplete">
	</div>
	<a onclick="postJSONArray();">Post JSON Array</a>
</body>
</html>
