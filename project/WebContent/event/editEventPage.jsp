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
	<form action="editEvent">
		<input type="hidden" name="eventId" value='<s:property value="eventId" />' />
		<table>
			<thead><b>Edit Event</b></thead>
			<tbody>
				<tr>
					<td>Name: </td>
					<td colspan="2"><input type="text" name="eventName" size="50" value='<s:property value="eventName" />'></td>
				</tr>
				<tr>
					<td>Description: </td>
					<td colspan="2"><textarea rows="4" cols="50" name="eventDescription"><s:property value="eventDescription" /></textarea></td>
				</tr>
				<tr>
					<td>Where: </td>
					<td colspan="2"><input type="text" name="eventPlace" size="50" value='<s:property value="eventPlace" />'></td>
				</tr>
				<tr>
					<td>When: </td>
					<td><input type="text" name="eventDate" size="22" value='<s:property value="eventDate" />'></td>
					<td><input type="text" name="eventTime" size="21" value='<s:property value="eventTime" />'></td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Done">
						<a href='event?eventId=<s:property value="eventId" />'><input type="button" value="Back" /></a>
					</td>
					<td colspan="2"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>