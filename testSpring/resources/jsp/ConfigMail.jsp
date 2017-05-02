<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%><html>
<head>
<STYLE type="text/css">
</STYLE>
<script type="text/javascript"
	src="/aims/resources/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript"
	src="/aims/resources/js/ckeditor/ckeditorModified.js"></script>
<script type="text/javascript">
	$(function() {
		CKEDITOR.replace('editor', {});
	});
</script>
</head>
<body>
	<s:form>
		<table class="listing">
			<tr>
				<td width="20%">Mail Id</td>
				<td><s:textfield name="mails.mailId" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><s:password name="mails.password" /></td>
			</tr>
			<tr>
				<td colspan="2"><div align="center">
						<s:submit action="openMail" value="Send Mail" />
					</div></td>
			</tr>
		</table>
	</s:form>
</body>
</html>
