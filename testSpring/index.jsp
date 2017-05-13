<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<style type="text/css">
#customCaptcha {
	-webkit-touch-callout: none; /* iOS Safari */
	-webkit-user-select: none; /* Safari */
	-khtml-user-select: none; /* Konqueror HTML */
	-moz-user-select: none; /* Firefox */
	-ms-user-select: none; /* Internet Explorer/Edge */
	user-select: none; /* Non-prefixed version, currently supported by Chrome and Opera */
}

#captcha {
	-webkit-transform: rotate(-10deg);
	-moz-transform: rotate(-10deg);
	float: left;
	color: red;
}
</style>
<script type="text/javascript">
	$(function() {
		// $("#userName").focus();
		var online = navigator.onLine;
		if (online) {
			// alert("Internet Connection Available");
		} else {
			$(".g-recaptcha").html(
					"No Internet Connection. Unable to load Captcha.");
		}
		var lang = '<s:property value="#session.lang" />';
		$('input:radio[name=lang][value=' + lang + ']').attr('checked', true);
	});
	function validate() {
		var userName = $("#userName").val();
		var password = $("#password").val();
		var response = grecaptcha.getResponse();

		if (userName == "") {
			alert($("#userNameAlert").text());
			return false;
		}
		if (password == "") {
			alert($("#passwordAlert").text());
			return false;
		}
		if (response.length == 0) {
			alert("Please Verify Captcha");
			// return false;
		}
	}
	function setUserName(name) {
		$("#userName").val(name);
		$("#userName").focus()
	}
	function changeLanguage(lang) {
		$.ajax({
			url : "showLoginPage.action?lang=" + lang,
			beforeSend : function() {
				$("#langStatus").html("Changing Language...");
			},
			success : function(result) {
				window.location.reload();
			}
		});
	}
</script>
<style type="text/css">
.login-block {
	border: 3px solid white;
	border-radius: 5px;
	padding: 15px 20px 10px 20px;
	border-color: gray;
	width: 300px;
}
</style>
</head>
<body>
	<%
		Cookie cookie = null;
		Cookie[] cookies = null;
		// Get an array of Cookies associated with this domain
		cookies = request.getCookies();
		int size = 0;
		int selected = 0;
		String[] names = null;
		String[] values = null;
		if (cookies != null) {
			size = cookies.length;
			names = new String[size];
			values = new String[size];
			for (int i = 0; i < size; i++) {
				cookie = cookies[i];
				if (cookie.getName().startsWith("userName-")) {
					selected++;
					names[selected - 1] = cookie.getName();
					values[selected - 1] = cookie.getValue();
				}
			}
		}
	%>
	<form action="loginAction.action">
		<div>
			<s:radio onchange="changeLanguage(this.value)" name="lang"
				list='#{"en":"English","ar":"Arabic"}'></s:radio>
			&nbsp;&nbsp;&nbsp;&nbsp; <span id="langStatus" style="color: red;"></span>
		</div>
		<span id="userNameAlert" style="visibility: hidden;"><s:property
				value="getText('global.alert.username')" /></span> <span
			id="passwordAlert" style="visibility: hidden;"><s:property
				value="getText('global.alert.password')" /></span>
		<div class="login-block">
			<table>
				<tr>
					<td style="width: 150px;"><s:property
							value="getText('global.username')" /></td>
					<td style="width: 150px;"><input id="userName" type="text"
						name="userName" autocomplete="off" /></td>
				</tr>
				<tr>
					<td><s:property value="getText('global.password')" /></td>
					<td><input id="password" type="password" name="password" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="g-recaptcha"
							style="min-height: 80px; margin-top: 10px; transform: scale(0.99); transform-origin: 0 0"
							data-sitekey="6LeGrB0UAAAAAJyes6TD6BYEiMGXCqQItm0cRfWl"></div>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;" colspan="2"><input
						type="submit" value="Log in" onclick="return validate();" /></td>
				</tr>
			</table>
		</div>
	</form>
	<%
		if (selected > 0) {
			out.println("<b>Existing Logins</b><br/>");
			for (int i = 0; i < selected; i++) {
				//out.print("Name : " + names[i] + ",  ");
				out.print("<span style='cursor:pointer;' onclick=setUserName('" + values[i] + "');>" + values[i]
						+ "</span><br/>");
			}
		} else {
			out.println("<b>No Existing Logins</b><br/>");
		}
	%>
</body>
</html>