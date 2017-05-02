<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload</title>
<script src="../js/jquery-3.1.1.min.js"></script>
<script>
	function selectAll() {
		checkboxes = document.getElementsByName("setupIds");
		if (document.getElementById("selectall").checked) {
			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = true;
			}
		} else {
			for (var i = 0, n = checkboxes.length; i < n; i++) {
				checkboxes[i].checked = false;
			}
		}
	}
</script>
<style type="text/css">
.new_cpt {
	color: green;
}

.same_price {
	color: red;
}
</style>
</head>
<body>
	<div style="margin: 50px;">
		<s:form action="portLotCode" method="POST"
			enctype="multipart/form-data" name="portingBase">
			<s:hidden name="officeId" />
			<table style="padding: 10px; width: 400px; border: 1px solid black;">
				<tr>
					<td colspan="2"><s:textfield label="Sheet Number"
							name="sheetNo" maxlength="3" size="3" /></td>
				</tr>
				<tr>
					<td colspan="2"><s:textfield label="Start Line"
							name="startLine" maxlength="3" size="3" /></td>
				</tr>
				<tr>
					<td colspan="2"><s:file label="File" name="imageFile" /></td>
				</tr>
				<tr>
					<td><s:select label="Type" name="type"
							list="#{'1':'Normal', '2':'Network'}" /></td>
				</tr>
				<tr>
					<td align="left" colspan="2" style="text-align: center;"><s:submit
							value="Upload" /></td>
				</tr>
			</table>
			<s:if test='priceList!=null && priceList.size>0'>
				<table style="margin-top: 10px;">
					<tr>
						<th rowspan="2" width="2%"><input type="checkbox"
							id="selectall" name="selectall" onclick="selectAll()" /></th>
						<th rowspan="2" width="10%">CPT Code</th>
						<th rowspan="2">Description</th>
						<th width="20%" colspan="3">New Price</th>
						<th width="20%" colspan="3">Old Price</th>
						<th rowspan="2" width="10%"></th>
					</tr>
					<tr>
						<th>Prediscount amount</th>
						<th>Corporate Discount</th>
						<th>Insurer amount</th>
						<th>Prediscount amount</th>
						<th>Corporate Discount</th>
						<th>Insurer amount</th>
					</tr>
					<s:iterator value="priceList">
						<tr
							class="<s:if test='status=="N"'>new_cpt</s:if><s:elseif test='priceStatus=="Y"'>same_price</s:elseif>">
							<td><input type="checkbox"
								<s:if test='priceStatus=="Y"&&status=="O"'>name="setupIds1" disabled="true"</s:if>
								<s:else>name="setupIds" </s:else>
								value="<s:property value="id" />" /></td>
							<td><s:property value="cptCode" /></td>
							<td><s:property value="description" /></td>
							<td><s:property value="prediscAmt" /></td>
							<td><s:property value="corpDisc" /></td>
							<td><s:property value="insurarAmount" /></td>
							<td><s:property value="OldPrediscAmt" /></td>
							<td><s:property value="OldCorpDisc" /></td>
							<td><s:property value="OldInsurarAmount" /></td>
							<td><s:property value="overAllStatus" /></td>
						</tr>
					</s:iterator>
					<tr>
						<td colspan="10" style="text-align: center;"><s:submit
								action="portData" value="Port Data" /></td>
					</tr>
				</table>
			</s:if>
		</s:form>
	</div>
</body>

</html>