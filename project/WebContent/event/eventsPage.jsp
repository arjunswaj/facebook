<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Events</title>
</head>
<body>

	<div style="float: left; font-size: large;">
		<b>Events</b>
	</div>
	
	<div style="margin-left: 70%;">
		<a href="createEventPage.jsp"><input type="button" value="+ Create Event" /></a>
	</div>
	
	<hr />
	
	<div>
		<h4><b>Upcoming Events</b></h4>
		<br />
		<s:iterator value="invitationListMap" var="entry">
			<div style="background-color: #f0f1f2; height: 30px;">
				<b>&nbsp;<s:property value="#entry.getKey()" /></b>
			</div>
			<br />
			<s:iterator value="#entry.getValue()" var="invitation">
			<div style="margin-left: 80px; height: 100px;">
				<div style="float: left;">
					<s:property value="#invitation.getEventTime()" />
				</div>
				<div style="float: left; margin-left: 10px;">
					<img height="90px" width="90px" src='image?userId=<s:property value="#invitation.getInviterId()" />' />
				</div>
				<div style="margin-left: 160px;">
					<a href='event?eventId=<s:property value="#invitation.getEventId()" />'><s:property value="#invitation.getEventName()" /><br /></a>
					<s:property value="#invitation.getEventPlace()" /><br />
					<s:property value="#invitation.getInviterName()" /> invited you<br />
					<s:if test='#invitation.getConfirmation().equals("pending")'>
						<a href='confirm?caller=eventsPage&eventId=<s:property value="#invitation.getEventId()" />&confirmation=join'><input type="button" value="Join" /></a>
						<a href='confirm?caller=eventsPage&eventId=<s:property value="#invitation.getEventId()" />&confirmation=maybe'><input type="button" value="Maybe" /></a>
						<a href='confirm?caller=eventsPage&eventId=<s:property value="#invitation.getEventId()" />&confirmation=nope'><input type="button" value="Decline" /></a>
					</s:if>
					<s:else>
						<form id='<s:property value="#invitation.getEventId()" />' method="post" action="confirm">
							<input type="hidden" id='caller' name="caller" value="eventsPage" />
							<input type="hidden" id='eventId' name="eventId" value='<s:property value="#invitation.getEventId()" />' />
							
							<s:if test='#invitation.getConfirmation().equals("join")'>
							<select id="confirmation" name="confirmation" onchange="document.getElementById('<s:property value="#invitation.getEventId()" />').submit();">
								<option value="join" selected="selected">Going</option>
								<option value="maybe">Maybe</option>
								<option value="nope">Not Going</option>
							</select>
							</s:if>
							<s:elseif test='#invitation.getConfirmation().equals("maybe")'>
							<select id="confirmation" name="confirmation" onchange="document.getElementById('<s:property value="#invitation.getEventId()" />').submit();">
								<option value="join">Going</option>
								<option value="maybe" selected="selected">Maybe</option>
								<option value="nope">Not Going</option>
							</select>
							</s:elseif>
							<s:else>
								You declined.
							</s:else>
						</form>
					</s:else>
				</div>
				<br />
			</div>
			</s:iterator>
			<br /><br />
		</s:iterator>
	</div>
</body>
</html>