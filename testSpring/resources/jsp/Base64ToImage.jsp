<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Base64 To Image</title>
<script src="resources/js/jquery-3.1.1.min.js"></script>
<link rel="stylesheet" href="resources/js/signature/signature-pad.css" />
<style type="text/css">
</style>
<script>
	function clearSign() {
		signaturePad.clear();
	}
	function saveImage() {
		var fileName = $("#fileName").val() + ".png";
		var fullPath = $("#path").val() + fileName;
		$.ajax({
			url : "createImageFromBase64.action",
			data : {
				fileName : fullPath,
				base64Data : signaturePad.toDataURL()
			},
			beforeSend : function() {
			},
			success : function(result) {
				$("#imageDiv").show();
				$("#image").attr("src", fileName + "?t=" + new Date().getTime());// Remove Cache in browser
			}
		});
	}
	function getImage() {
		var fileName = $("#fileName").val() + ".png";
		var fullPath = $("#path").val() + fileName;
		$.ajax({
			url : "getBase64FromImage.action",
			data : {
				fileName : fullPath
			},
			beforeSend : function() {
			},
			success : function(result) {
				signaturePad.fromDataURL(result);
			}
		});
	}
</script>
</head>
<body>
	<%
		String path = new File("").getAbsolutePath();
	%>
	<input id="path" type="hidden" value="<%=path%>/webapps/testSpring/">
	<div style="margin: 50px;">
		<div style="padding: 20px; background-color: gray;">
			File Name : <input id="fileName" type="text" value="sign"><span style="color:white;">.png</span> <br />
			<br />
			<div id="signature-pad" class="m-signature-pad">
				&nbsp;
				<div class="m-signature-pad--body">
					<canvas></canvas>
				</div>
			</div>
			<br /> <input onclick="clearSign()" type="button" value="Clear">
			<input onclick="saveImage()" type="button" value="Save Image">
			<br /> <br />
			<div id="imageDiv"
				style="padding: 10px; width: 300px; background-color: white; display: none;">
				<img id="image" src="" />
			</div>
		</div>
	</div>
</body>
<script src="resources/js/signature/signature_pad.js"></script>
<script src="resources/js/signature/signature_app.js"></script>
<script>
	getImage();
</script>
</html>