<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload</title>
<script src="../js/jquery-3.1.1.min.js"></script>
<script>
	function createNew() {
		var table = document.getElementById("myTable");
		var count = table.rows.length;
		var row = table.insertRow(count);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		cell1.innerHTML = "File:";
		cell2.innerHTML = "<input type='File' name='image'/>"
	}
</script>
</head>
<body>
	<div style="width: 200px; border: 1px; margin: 50px;">
		<s:form action="multipleFileUpload" method="POST"
			enctype="multipart/form-data">
			<table id="myTable">
				<tr>
					<td><s:file label="File" name="image" />&nbsp;&nbsp;<span
						style="cursor: pointer; color: green; font-weight: bold;"
						onclick="createNew()" title="Add New">[+]</span></td>
				</tr>
				<tr>
					<td><s:file label="File" name="image" /></td>
				</tr>
			</table>
			<tr>
				<td style="text-align: center;"><s:submit
						cssClass="btn btn-primary" value="Upload" /></td>
			</tr>
		</s:form>
	</div>

	<s:if test="imageDescription!=null">
		<s:iterator value="imageDescription">
			<s:property />
			<br />
		</s:iterator>
	</s:if>
</body>

</html>