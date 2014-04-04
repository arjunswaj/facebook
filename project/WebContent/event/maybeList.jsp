<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Guests</title>
</head>
<body style="background-color: #c5c5df;">
	<div style="margin-left: 25%; margin-top: 50px; width: 50%; border: thin; border-style: solid; background-color: #ffffff; height: 600px;">
		<table style="width: 100%">
			<tr style="background-color: #f5f6f7; height: 60px;">
				<td><h3>Guests (maybe)</h3></td>
				<td align="right"><a href='event?eventId=<s:property value="eventId" />'><input type="button" value="X" /></a></td>
			</tr>
		</table>
		<div style="overflow-y: scroll; height: 536px;">
			<s:iterator value="map" var="u">
			<table style="width: 96%; margin-left: 2%;">
				<tr>
					<td width="50px"><img width="40px" height="40px" src='image?userId=<s:property value="#u.key" />' /></td>
					<td><s:property value="#u.value" /></td>
					<td align="right"><a href='deleteInvitation?listName=maybe&eventId=<s:property value="eventId" />&inviteeId=<s:property value="#u.key" />'><input type="button" value="X" /></a></td>
				</tr>
			</table>
			<hr />
			</s:iterator>
		</div>
	</div>
</body>
</html>