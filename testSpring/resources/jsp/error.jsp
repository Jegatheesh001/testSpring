<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<link href="resources/styles/mystyle.css" rel="stylesheet" type="text/css">

<script type="text/javascript">
$(function() {  
	document.getElementById('bdy').style.backgroundSize = ""+($(window).width()+50)+"px "+($(window).height()+50)+"px"; 
	$('#marquee').height($(window).height()-230);  
	$('#not').width($(window).width()/2.25); 
	$('#not').height($(window).height()-170);   
	var w=$(window).width()-($('#not').width()*1.75); 
	if(w>350){ w=350+(w-350)/3;	}
	if(w<350){ w=350;}
	document.getElementById('logo').style.width = w ; 
	document.getElementById('logo').style.height = document.getElementById('logo').style.width-1750; 
	$('#im').width($(window).width()-$('#not').width()-130); 
}); 
</script>
</head>

<body id="bdy"  style="background-repeat: no-repeat;">
<s:form action="adminlogin" method="post" theme="simple">

<table>
		<tr>
			<td style="text-align: center; font-weight: bold; vertical-align: middle; padding-top: 200px;">
			
			<font color="blue"> Session Expired!</font><br>
			Try to Re-Login From <a href="/testSpring" class="a_linlkistno"><font color="blue"> Here</font></a> Or Contact Your System Administrator.
    		</td>
		</tr>
</table>

</s:form>
</body>
 
</html>

