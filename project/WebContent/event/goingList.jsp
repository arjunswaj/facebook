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
	<table>
		<thead><b>Guests (going)</b></thead>
		<tbody>
			<s:iterator value="map" var="u">
				<tr>
					<td><s:property value="#u.value" /></td>
					<td><a href='deleteInvitation?listName=going&eventId=<s:property value="eventId" />&inviteeId=<s:property value="#u.key" />'>X</a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<a href='event?eventId=<s:property value="eventId" />'><input type="button" value="Close" /></a>
</body>
</html>