<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Upload</title>
<script src="resources/js/jquery-3.1.1.min.js"></script>
<script>
	function findCount(e) {
		var key = e.which;
		if (key == 13) // the enter key code
		{
			$("#showCount").html("0");
			$.ajax({
				url : "findCount.action?query=" + $("#query").val(),
				success : function(output) {
					$("#showCount").html(output);
				}
			});
		}
		return false;
	}

	function executeQuery(e) {
		var key = e.which;
		if (key == 13) // the enter key code
		{
			$("#showOutput").html("0");
			$.ajax({
				url : "executeQuery.action?query=" + $("#query1").val(),
				success : function(output) {
					$("#showOutput").html(output);
				}
			});
		}
		return false;
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
	count :
	<a href="findCount.action?query=eclinic.procedure_setup">ps</a>&nbsp;
	<form onsubmit="return false">
		<s:textfield id="query" name="query" value="eclinic.procedure_setup"
			onkeydown="findCount(event);" />
		count : <span id="showCount">0</span><br />
		<s:textarea id="query1" name="query1" rows="5" cols="70"
			value="select * from eclinic.procedure_setup"
			onkeydown="executeQuery(event);"></s:textarea>
		<span id="showOutput">0</span>
	</form>
</body>

</html>