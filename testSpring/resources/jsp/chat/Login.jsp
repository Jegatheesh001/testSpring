<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<STYLE type="text/css">
</STYLE>
<script src="resources/jsp/chat/GlobalChart.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<s:form name="loginForm">
		<table>
			<tr>
				<td colspan="2">Login</td>
			</tr>
			<tr>
				<td>User Name</td>
				<td><s:textfield id="userName" name="user.loginName" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><s:password id="userPassword" name="user.userPassword" /></td>
			</tr>
			<tr>
				<td colspan="2"></td>
				<s:submit value="login" onclick="return validateUserLogin();" />
			</tr>
			<tr>
		</table>
		<span id="loginStatus"></span>
	</s:form>
</body>
</html>
