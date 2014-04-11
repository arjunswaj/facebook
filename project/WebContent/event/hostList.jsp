<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Guests</title>
<script type="text/javascript">
	function displayAppropriateList(readOnly, eventId)
	{
		var s=document.getElementById('listType').value;
		if(s=="Maybe")
			window.location="maybeList?readOnly="+readOnly+"&eventId="+eventId;
		else if(s=="Invited")
			window.location="invitedList?readOnly="+readOnly+"&eventId="+eventId;
		else if(s=="Going")
			window.location="goingList?readOnly="+readOnly+"&eventId="+eventId;
		else if(s=="All")
			window.location="allList?readOnly="+readOnly+"&eventId="+eventId;
		else if(s=="Declined")
			window.location="declinedList?readOnly="+readOnly+"&eventId="+eventId;
	}
</script>
</head>
<body style="background-color: #c5c5df;">
	<div style="margin-left: 25%; margin-top: 50px; width: 50%; border: thin; border-style: solid; background-color: #ffffff; height: 600px;">
		<table style="width: 100%">
			<tr style="background-color: #f5f6f7; height: 50px;">
				<td><b>Guests</b></td>
				<td align="right"><a style="text-decoration: none;" href='displayEvent?eventId=<s:property value="eventId" />'><input type="button" value="X" /></a></td>
			</tr>
			<tr style="background-color: #f0f1f2; height: 40px;">
				<td colspan="2">
					<select id='listType' onchange="displayAppropriateList('<s:property value="readOnly" />', '<s:property value="eventId" />')">
						<option selected="selected">*Host</option>
						<option>Going</option>
						<option>Maybe</option>
						<option>Invited</option>
						<option>All</option>
						<option>Declined</option>
					</select>
				</td>
			</tr>
		</table>
		
		<div style="overflow-y: scroll; height: 502px;">
			<table style="width: 96%; margin-left: 2%;">
				<tr>
					<td width="50px"><a style="text-decoration: none;" href='profile?fref=<s:property value="inviter.getUserId()" />'><img width="40px" height="40px" src='image?userId=<s:property value="inviter.getUserId()" />' /></a></td>
					<td><a style="text-decoration: none;" href='profile?fref=<s:property value="inviter.getUserId()" />'><s:property value='inviter.getFirstName()+" "+inviter.getLastName()' /></a></td>
					<td></td>
				</tr>
			</table>
			<hr />
			<s:iterator value="map" var="u">
			<table style="width: 96%; margin-left: 2%;">
				<tr>
					<td width="50px"><a style="text-decoration: none;" href='profile?fref=<s:property value="#u.key" />'><img width="40px" height="40px" src='image?userId=<s:property value="#u.key" />' /></a></td>
					<td><a style="text-decoration: none;" href='profile?fref=<s:property value="#u.key" />'><s:property value="#u.value" /></a></td>
					<td align="right">
						<s:if test='readOnly.equals("false")'>
						<a style="text-decoration: none;" href='deleteInvitation?listName=host&eventId=<s:property value="eventId" />&inviteeId=<s:property value="#u.key" />'><input type="button" value="X" /></a>
						</s:if>
					</td>
				</tr>
			</table>
			<hr />
			</s:iterator>
		</div>
	</div>
</body>
</html>