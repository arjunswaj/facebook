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
	<s:iterator value="birthdayPeopleToday" var="u">
		<s:if test='user.getUserId().equals(#u.getUserId())'>
			* Your birthday is today. Happy Birthday.<br />
		</s:if>
		<s:else>
			* <a href='profile?fref=<s:property value="#u.getUserId()" />'><s:property value="#u.getFirstName()" /> <s:property value="#u.getLastName()" /></a>&apos;s birthday is today<br />
		</s:else>
	</s:iterator>
	<br />
	<s:iterator value="eventsToday" var="inv">
		* <a href='displayEvent?eventId=<s:property value="#inv.getEventId()" />'><s:property value="#inv.getEventName()" /></a>
		at
		<s:property value="#inv.getEventTime()" />
	</s:iterator>
</body>
</html>