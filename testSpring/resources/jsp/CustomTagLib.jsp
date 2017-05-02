<%@taglib prefix="m" uri="/WEB-INF/taglib/myTag.tld"%>
<html>
<head>
<title>JSP Custom Taglib example: Substr function</title>
</head>
<body>
	SUBSTR(GOODMORNING, 1, 6) is
	<font color="blue"> <m:substring input="GOODMORNING" start="1"
			end="6" /></font>
	<br> Encryption of GOODMORNING is
	<font color="blue"><m:encrypt value="GOODMORNING" /> </font><br>
	<a href="decryptString.action?inputString=<m:encrypt value="GOODMORNING" />&inputInteger=100">Decrypt</a>
</body>
</html>