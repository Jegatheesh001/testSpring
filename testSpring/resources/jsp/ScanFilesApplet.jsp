<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload</title>
<script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
<script>
	var folderPath = "C:/Users/jegatheesh/Desktop/applets/";
	$(function() {
		scan = document.getElementById("scanner");
		scan.getFolderDetails(folderPath);
	});
	var results;
	function scanFiles() {
		// invoke public applet method
		var scan = document.getElementById("scanner");
		results = scan.getFolderDetails(folderPath);
		text = "";
		if (results == null) {
			text = "No files -> " + folderPath;
		} else {
			for (i = 0; i < results.length; i++) {
				if (results[i] != null) {
					text += results[i] + "<br>";
				}
			}
			$("#submit").show();
		}
		$("#display").html(text);
	}
	function submitFiles() {
		if (results != null) {
			for (i = 0; i < results.length; i++) {
				if (results[i] != null) {
					//document.files.image.value = tempfile;
				}
			}
		}
		document.files.action = "multipleFileUpload.action";
		document.files.submit();
	}
</script>
</head>
<body>

	<object id="scanner" type="application/x-java-applet" height="1"
		width="1" name="scannerApplet">
		<param name="code"
			value="com.medasuae.applets.ClientSystemFileScan.class" />
		<param name="archive"
			value="${pageContext.request.contextPath}/resources/applets/applets.jar" />
		failed to run code. No Java plug-in was found.
	</object>

	<input id="b" type="button" value="Get Info" onclick="scanFiles()" />
	<div id="display"></div>
	<s:form name="files">
		<s:submit id="submit" cssStyle="display:none;" value="submit"
			onclick="return submitFiles()" />
	</s:form>

</body>
</html>