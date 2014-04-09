<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Invite Friends</title>
</head>
<body style="background-color: #c5c5df;">
	<form id="f1" action="sendInvites" method="post">
		<input type="hidden" name="caller" value='<s:property value="caller" />' />
		<input type="hidden" name="eventId" value='<s:property value="eventId" />' />
		<input type="hidden" name="invitees" id="invitees" value="" />
		<table style="margin-left: 25%; margin-top: 150px; width: 50%; border: thin; border-style: solid; background-color: #ffffff;">
			<colgroup>
				<col width="10px"/>
				<col width="50px"/>
				<col width="90%"/>
				<col />
			</colgroup>
			<tbody>
				<tr style="background-color: #f5f6f7; height: 60px;">
					<td></td>
					<td colspan="2"><h3>Invite Friends</h3></td>
					<td align="right">
						<s:if test='caller.equals("eventsPage")'>
							<a style="text-decoration: none;" href='displayEvents'><input type="button" value="X" /></a>
						</s:if>
						<s:else>
							<a style="text-decoration: none;" href='event?eventId=<s:property value="eventId" />'><input type="button" value="X" /></a>
						</s:else>
					</td>
				</tr>
				<s:iterator value="map" var="u">
				<tr>
					<td valign="middle"><input type="checkbox" name="cb" value='<s:property value="#u.key" />' /></td>
					<td><img width="40px" height="40px" src='image?userId=<s:property value="#u.key" />' /></td>
					<td colspan="2"><s:property value="#u.value" /></td>
				</tr>
				</s:iterator>
				<tr>
					<td colspan="4" align="right">
						<input style="background-color: #45619d; color: white;" type="button" value="Send" onclick="submitForm();" />
						<s:if test='caller.equals("eventsPage")'>
							<a style="text-decoration: none;" href='displayEvents'><input type="button" value="Cancel" /></a>
						</s:if>
						<s:else>
							<a style="text-decoration: none;" href='event?eventId=<s:property value="eventId" />'><input type="button" value="Cancel" /></a>
						</s:else>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
		var cbArray=document.getElementsByName("cb");
		for(var i=0; i<cbArray.length; i++)
			if(cbArray[i].value<0)
				cbArray[i].disabled="disabled";
		
		function submitForm()
		{
			var cbArray=document.getElementsByName("cb");
			document.getElementById("invitees").value="";
			for(var i=0; i<cbArray.length; i++)
				if(cbArray[i].checked)
				{
					document.getElementById("invitees").value+=cbArray[i].value+"|";
					cbArray[i].checked=false;
				}
			document.getElementById("f1").submit();
		}
	</script>
</body>
</html>