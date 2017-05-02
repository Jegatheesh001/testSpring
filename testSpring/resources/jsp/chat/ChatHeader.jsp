<%@ taglib prefix="s" uri="/struts-tags"%>
<div>
	<div align="right">
		Hi,
		<s:property value="user.userName" />
	</div>
	<div style="text-align: center;">
		<span style="color: blue;">Chat Website</span>
		<div align="right">
			<a href="chatBase.action">Home</a>&nbsp;||&nbsp;<a
				href="chatLogout.action">logout</a>
		</div>
	</div>
</div>