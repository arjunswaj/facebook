<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Event</title>
</head>
<body style="background-color: #E5E5E9;">
	<div style="background-color: #D2D2D8;">
		<br /><br /><br /><br /><br /><br /><br />
		<h2>&nbsp;<s:property value="eventName"/></h2>
		<br />
	</div>
	<div style="background-color: #FFFFFF; float: left; width: 520px;">
		<br />
		&nbsp;Hosted by <a style="color: blue;" href='profile?fref=<s:property value="inviter.getUserId()" />'><s:property value="inviter.getFirstName()"/> <s:property value="inviter.getLastName()"/></a>
		<br /><br />
	</div>
	<div style="background-color: #FFFFFF;">
		<br />
		<s:if test='user.getUserId()==inviter.getUserId()'>
			<a style="text-decoration: none;" href='invite?eventId=<s:property value="eventId"/>'><input type="button" value="Invite"></a>
			<a style="text-decoration: none;" href='editEvent?eventId=<s:property value="eventId"/>'><input type="button" value="Edit"></a>
		</s:if>
		<s:else>
			<s:if test='confirmationIfUserIsInvitee.equals("pending")'>
				<a style="text-decoration: none;" href='confirm?caller=eventPage&eventId=<s:property value="eventId" />&confirmation=join'><input type="button" value="Join" /></a>
				<a style="text-decoration: none;" href='confirm?caller=eventPage&eventId=<s:property value="eventId" />&confirmation=maybe'><input type="button" value="Maybe" /></a>
				<a style="text-decoration: none;" href='confirm?caller=eventPage&eventId=<s:property value="eventId" />&confirmation=nope'><input type="button" value="Decline" /></a>
			</s:if>
			<s:else>
				<form id='<s:property value="eventId" />' method="post" action="confirm">
					<input type="hidden" id='caller' name="caller" value="eventPage" />
					<input type="hidden" id='eventId' name="eventId" value='<s:property value="eventId" />' />
					
					<s:if test='confirmationIfUserIsInvitee.equals("join")'>
					<select id="confirmation" name="confirmation" onchange="document.getElementById('<s:property value="eventId" />').submit();">
						<option value="join" selected="selected">*Going</option>
						<option value="maybe">Maybe</option>
						<option value="nope">Not Going</option>
					</select>
					</s:if>
					<s:elseif test='confirmationIfUserIsInvitee.equals("maybe")'>
					<select id="confirmation" name="confirmation" onchange="document.getElementById('<s:property value="eventId" />').submit();">
						<option value="join">Going</option>
						<option value="maybe" selected="selected">*Maybe</option>
						<option value="nope">Not Going</option>
					</select>
					</s:elseif>
					<s:else>
					<select id="confirmation" name="confirmation" onchange="document.getElementById('<s:property value="eventId" />').submit();">
						<option value="join">Going</option>
						<option value="maybe">Maybe</option>
						<option value="nope" selected="selected">*Not Going</option>
					</select>
					</s:else>
				</form>
			</s:else>
		</s:else>
		<br /><br />
	</div>
	<br />
	<div style="float: left; width: 500px;">
		<div style="background-color: #FFFFFF;">
			<br />
			&nbsp;<s:property value="eventDate"/> at <s:property value="eventTime"/>
			<br/>
			<hr />
			&nbsp;<s:property value="eventPlace"/>
			<br /><br />
		</div>
		<br />
		<div style="background-color: #FFFFFF;">
			<br />
			&nbsp;<s:property value="eventDescription"/>
			<br /><br />
		</div>
	</div>
	<div style="margin-left: 510px;">
		<div style="background-color: #FFFFFF;">
		
			<s:if test='user.getUserId()==inviter.getUserId()'>
			<table width="100%">
				<thead><b>GUESTS</b></thead>
				<tbody>
					<tr>
						<td><a style="text-decoration: none; color: blue;" href='goingList?eventId=<s:property value="eventId"/>'><%=1+Integer.parseInt((String)request.getAttribute("going"))%></a></td>
						<td><a style="text-decoration: none; color: blue;" href='maybeList?eventId=<s:property value="eventId"/>'><s:property value="maybe"/></a></td>
						<td><a style="text-decoration: none; color: blue;" href='invitedList?eventId=<s:property value="eventId"/>'><s:property value="invited"/></a></td>
					</tr>
					<tr>
						<td><a style="text-decoration: none; color: blue;" href='goingList?eventId=<s:property value="eventId"/>'>going</a></td>
						<td><a style="text-decoration: none; color: blue;" href='maybeList?eventId=<s:property value="eventId"/>'>maybe</a></td>
						<td><a style="text-decoration: none; color: blue;" href='invitedList?eventId=<s:property value="eventId"/>'>invited</a></td>
					</tr>
				</tbody>
			</table>
			</s:if>
			<s:else>
			<table width="100%">
				<thead><b>GUESTS</b></thead>
				<tbody>
					<tr>
						<td><a style="text-decoration: none; color: blue;" href='goingList?readOnly=true&eventId=<s:property value="eventId"/>'><%=1+Integer.parseInt((String)request.getAttribute("going"))%></a></td>
						<td><a style="text-decoration: none; color: blue;" href='maybeList?readOnly=true&eventId=<s:property value="eventId"/>'><s:property value="maybe"/></a></td>
						<td><a style="text-decoration: none; color: blue;" href='invitedList?readOnly=true&eventId=<s:property value="eventId"/>'><s:property value="invited"/></a></td>
					</tr>
					<tr>
						<td><a style="text-decoration: none; color: blue;" href='goingList?readOnly=true&eventId=<s:property value="eventId"/>'>going</a></td>
						<td><a style="text-decoration: none; color: blue;" href='maybeList?readOnly=true&eventId=<s:property value="eventId"/>'>maybe</a></td>
						<td><a style="text-decoration: none; color: blue;" href='invitedList?readOnly=true&eventId=<s:property value="eventId"/>'>invited</a></td>
					</tr>
				</tbody>
			</table>
			</s:else>
			
		</div>
	</div>
</body>
</html>