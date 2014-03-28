<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="createEvent">
		<table>
			<thead><b>Create New Event</b></thead>
			<tbody>
				<tr>
					<td>Name: </td>
					<td colspan="2"><input type="text" name="eventName" size="50"></td>
				</tr>
				<tr>
					<td>Description: </td>
					<td colspan="2"><textarea rows="4" cols="50" name="eventDescription"></textarea></td>
				</tr>
				<tr>
					<td>Where: </td>
					<td colspan="2"><input type="text" name="eventPlace" size="50"></td>
				</tr>
				<tr>
					<td>When: </td>
					<td><input type="text" name="eventDate" size="22"></td>
					<td><input type="text" name="eventTime" size="21"></td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Create">
						<a href="displayEvents"><input type="button" value="Cancel"></a>
					</td>
					
					<td colspan="2"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>