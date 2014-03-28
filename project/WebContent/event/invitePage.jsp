<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="f1" action="sendInvites" method="post">
		<input type="hidden" name="eventId" value='<s:property value="eventId" />' />
		<input type="hidden" name="invitees" id="invitees" value="" />
		<table>
			<thead><b>Invite Friends</b></thead>
			<tbody>
				<s:iterator value="map" var="u">
					<tr>
						<td><input type="checkbox" name="cb" value='<s:property value="#u.key" />' /><s:property value="#u.value" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		<input type="button" value="Send" onclick="submitForm();" />
		<a href='event?eventId=<s:property value="eventId" />'><input type="button" value="Cancel" /></a>
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