<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: #E5E5E9;">
	<div style="background-color: #D2D2D8;">
		<br /><br /><br /><br /><br /><br /><br />
		<b><s:property value="eventName"/></b>
		<br /><br />
	</div>
	<div style="background-color: #FFFFFF; float: left; width: 520px;">
		<br />
		Hosted by <s:property value="user.getFirstName()"/> <s:property value="user.getLastName()"/>
		<br /><br />
	</div>
	<div style="background-color: #FFFFFF;">
		<br />
		<a href='invite?eventId=<s:property value="eventId"/>'><input type="button" value="Invite"></a>
		<a href='editEvent?eventId=<s:property value="eventId"/>'><input type="button" value="Edit"></a>
		<br /><br />
	</div>
	<br />
	<div style="float: left; width: 500px;">
		<div style="background-color: #FFFFFF;">
			<br />
			<s:property value="eventDate"/> at <s:property value="eventTime"/>
			<br/>
			<hr />
			<s:property value="eventPlace"/>
			<br /><br />
		</div>
		<br />
		<div style="background-color: #FFFFFF;">
			<br />
			<s:property value="eventDescription"/>
			<br /><br />
		</div>
	</div>
	<div style="margin-left: 510px;">
		<div style="background-color: #FFFFFF;">
			<table width="100%">
				<thead><b>GUESTS</b></thead>
				<tbody>
					<tr>
						<td><a href='goingList?eventId=<s:property value="eventId"/>'><s:property value="going"/></a></td>
						<td><a href='maybeList?eventId=<s:property value="eventId"/>'><s:property value="maybe"/></a></td>
						<td><a href='invitedList?eventId=<s:property value="eventId"/>'><s:property value="invited"/></a></td>
					</tr>
					<tr>
						<td><a href='goingList?eventId=<s:property value="eventId"/>'>going</a></td>
						<td><a href='maybeList?eventId=<s:property value="eventId"/>'>maybe</a></td>
						<td><a href='invitedList?eventId=<s:property value="eventId"/>'>invited</a></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>