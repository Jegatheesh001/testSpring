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
	<s:form name="registerForm">
		<table>
			<tr>
				<td colspan="2">Register</td>
			</tr>
			<tr>
				<td>User Name</td>
				<td><s:textfield id="userName" name="user.loginName"
						onblur="checkUserAvailability(this.value);" />&nbsp;<span
					id="userStatus" style=""></span></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><s:password id="userPassword" name="user.userPassword" /></td>
			</tr>
			<tr>
				<td>Repeat Password</td>
				<td></td>
				<s:password id="repeatPassword" name="user.repeatPassword" />
			</tr>
			<tr>
				<td colspan="2"></td>
				<s:submit action="registerUser" value="Register"
					onclick="return validateUser();" />
			</tr>
		</table>
	</s:form>
</body>
</html>
