<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
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
		<s:hidden name="mails.cc" />
		<s:hidden name="mails.bcc" />
		<table class="listing">
			<tr>
				<th colspan="2" style="text-align: center;">Send Mail</th>
			</tr>
			<tr>
				<td width="20%" colspan="1">To</td>
				<td colspan="1"><s:textfield name="mails.mailto"
						cssClass="inputtypes" /></td>
			</tr>
			<tr>
				<td>Subject</td>
				<td><s:textfield name="mails.subject" cssClass="inputtypes" /></td>
			</tr>
			<tr>
				<td>Content</td>
				<td><s:textarea id="editor" rows="3" name="mails.details" /></td>
			</tr>
			<tr>
				<td colspan="2"><div align="center">
						<s:submit action="sendMail" value="Send Mail" />
					</div></td>
			</tr>
		</table>
	</s:form>
</body>
</html>
