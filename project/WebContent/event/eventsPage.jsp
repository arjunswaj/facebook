<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Events</title>
	<script type="text/javascript">
		function getChildElementById(parentElement, childId)
		{
			for(var i=0; i<parentElement.childNodes.length; i++)
			{
				if(parentElement.childNodes[i].id==childId)
					return parentElement.childNodes[i];
			}
		}
		
		function loadXmlDoc(e)
		{
			var sel=getChildElementById(document.getElementById(e), 'content');
			var req=new XMLHttpRequest();
			req.onreadystatechange=
				function()
				{
					if(req.readyState==4 && req.status==200)
					{
						var s=req.responseText;
						if(sel.value=="join")
							s=s.replace(">Going"," selected='selected'>*Going");
						else if(sel.value=="maybe")
							s=s.replace(">Maybe"," selected='selected'>*Maybe");
						if(sel.value=="nope")
							s=s.replace(">Not Going"," selected='selected'>*Not Going");
						sel.innerHTML=s;
					}
				};
			req.open("POST", "confirm?eventId="+e+"&confirmation="+sel.value, true);
			req.send();
		}
		
		function loadXmlDoc2(e, c)
		{
			var div=document.getElementById(e);
			var s="<option value='join'>Going</option><option value='maybe'>Maybe</option><option value='nope'>Not Going</option>";
			div.innerHTML="<select id='content' onchange=\"loadXmlDoc('"+e+"')\">"+s+"</select>";
			getChildElementById(div, 'content').value=c;
			loadXmlDoc(e);
		}
		
		function tafocus(taId)
		{
			var ta=document.getElementById(taId);
			if(ta.value=="Write a birthday wish on his timeline...")
				ta.value="";
		}
		
		function tablur(taId)
		{
			var ta=document.getElementById(taId);
			if(ta.value=="")
				ta.value="Write a birthday wish on his timeline...";
		}
		
		function taCheckEnter(event)
		{
			if(event.keyCode==13)
			{
				//post using ajax
				alert("posting");
			}
		}
	</script>
</head>
<body>

	<div style="float: left; font-size: large;">
		<b>Events</b>
	</div>
	
	<div style="margin-left: 70%;">
		<a style="text-decoration: none;" href="event/createEventPage.jsp"><input type="button" value="+ Create Event" /></a>
	</div>
	
	<hr />
	
	<div>
		<h4><b>Upcoming Events</b></h4>
		<br />
		<s:iterator value="invitationListMap" var="entry">
			<div style="background-color: #f0f1f2; height: 30px;">
				<s:if test='dateTodayString.equals(#entry.getKey())'>
					<b>&nbsp;Today</b>
				</s:if>
				<s:else>
					<b>&nbsp;<s:property value="#entry.getKey()" /></b>
				</s:else>
			</div>
			<br />
			<s:iterator value="#entry.getValue()" var="invitation">
				<s:if test="user.getUserId()!=#invitation.getInviterId()">
					<div style="margin-left: 80px; height: 110px;">
						<div style="float: left;">
							<s:property value="#invitation.getEventTime()" />
						</div>
						<div style="float: left; margin-left: 10px;">
							<a style="text-decoration: none;" href='displayEvent?eventId=<s:property value="#invitation.getEventId()" />'><img height="90px" width="90px" src='image?userId=<s:property value="#invitation.getInviterId()" />' /></a>
						</div>
						<div style="margin-left: 160px;">
							<a style="text-decoration: none;" href='displayEvent?eventId=<s:property value="#invitation.getEventId()" />'><s:property value="#invitation.getEventName()" /><br /></a>
							<s:property value="#invitation.getEventPlace()" /><br />
							<s:property value="#invitation.getInviterName()" /> invited you<br />
							
							<div id='<s:property value="#invitation.getEventId()" />'>
								<s:if test='#invitation.getConfirmation().equals("pending")'>
									<div>
										<input type="button" value="Join" onclick="loadXmlDoc2('<s:property value="#invitation.getEventId()" />', 'join')" />
										<input type="button" value="Maybe" onclick="loadXmlDoc2('<s:property value="#invitation.getEventId()" />', 'maybe')" />
										<input type="button" value="Decline" onclick="loadXmlDoc2('<s:property value="#invitation.getEventId()" />', 'nope')" />
									</div>
								</s:if>
								<s:else>
									<s:if test='#invitation.getConfirmation().equals("join")'>
									<select id='content' onchange="loadXmlDoc('<s:property value="#invitation.getEventId()" />')">
										<option value="join" selected="selected">*Going</option>
										<option value="maybe">Maybe</option>
										<option value="nope">Not Going</option>
									</select>
									</s:if>
									<s:elseif test='#invitation.getConfirmation().equals("maybe")'>
									<select id='content' onchange="loadXmlDoc('<s:property value="#invitation.getEventId()" />')">
										<option value="join">Going</option>
										<option value="maybe" selected="selected">*Maybe</option>
										<option value="nope">Not Going</option>
									</select>
									</s:elseif>
									<s:else>
										You declined.
									</s:else>							
								</s:else>
							</div>
						</div>
					</div>
				</s:if>
				<s:else>
					<div style="margin-left: 80px; height: 130px;">
						<div style="float: left;">
							<s:property value="#invitation.getEventTime()" />
						</div>
						<div style="float: left; margin-left: 10px;">
							<a style="text-decoration: none;" href='displayEvent?eventId=<s:property value="#invitation.getEventId()" />'><img height="110px" width="110px" src='image?userId=<s:property value="#invitation.getInviterId()" />' /></a>
						</div>
						<div style="margin-left: 180px;">
							<a style="text-decoration: none;" href='displayEvent?eventId=<s:property value="#invitation.getEventId()" />'><s:property value="#invitation.getEventName()" /><br /></a>
							<s:property value="#invitation.getEventPlace()" /><br />
							<s:property value="#invitation.getInviterName()" /> hosting<br />
							<a style="text-decoration: none;" href='invite?eventId=<s:property value="#invitation.getEventId()" />&caller=eventsPage'><input type="button" value="Invite Friends"></a>
						</div>
					</div>
				</s:else>
				<br />
			</s:iterator>
			
			<s:if test="birthdayPeopleListMap.get(#entry.getKey())!=null">
				<s:if test="#entry.getValue().size()>0">
					<hr /><br />
				</s:if>
				<div style="margin-left: 80px;">
					<div style="float: left;">
						Birthdays
					</div>
					
					<s:if test='dateTodayString.equals(#entry.getKey())'>
						<% int i=0, cols=1, rowHeight=80; %>
						<div style="margin-left: 80px;">
						<table>
						<s:iterator value='birthdayPeopleListMap.get(#entry.getKey())' var="u">
							<%if(i++%cols==0){%><tr height="<%=rowHeight%>px"><%}%>
							<td style="padding-right: 5px;">
								<a href='profile?fref=<s:property value="#u.getUserId()" />' title='<s:property value="#u.getFirstName()" /> <s:property value="#u.getLastName()" />&apos;s birthday'><img height="70px" width="70px" src='image?userId=<s:property value="#u.getUserId()" />' /></a>
							</td>
							<td>
								<s:if test='#u.getUserId()==user.getUserId()'>
									Happy Birthday!
								</s:if>
								<s:else>
									<a href='profile?fref=<s:property value="#u.getUserId()" />' title='<s:property value="#u.getFirstName()" /> <s:property value="#u.getLastName()" />&apos;s birthday'><s:property value="#u.getFirstName()" /> <s:property value="#u.getLastName()" /></a><br />
									<!-- <input type="text" value="Write a birthday wish on his timeline..." onkeypress="taCheckEnter(event)" size="30" id='ta<s:property value="#u.getUserId()" />' onfocus="tafocus('ta<s:property value="#u.getUserId()" />')" onblur="tablur('ta<s:property value="#u.getUserId()" />')" /> -->
								</s:else>
							</td>
							<%if(i%cols==0){%></tr><%}%>
						</s:iterator>
						</table>
						</div>
					</s:if>
					<s:else>
						<% int i=0, cols=5, rowHeight=80; %>
						<div style="margin-left: 80px;">
						<table>
						<s:iterator value='birthdayPeopleListMap.get(#entry.getKey())' var="u">
							<%if(i++%cols==0){%><tr height="<%=rowHeight%>px"><%}%>
							<td style="padding-right: 5px;">
								<a href='profile?fref=<s:property value="#u.getUserId()" />' title='<s:property value="#u.getFirstName()" /> <s:property value="#u.getLastName()" />&apos;s birthday'><img height="70px" width="70px" src='image?userId=<s:property value="#u.getUserId()" />' /></a>
							</td>
							<%if(i%cols==0){%></tr><%}%>
						</s:iterator>
						</table>
						</div>
					</s:else>
				</div>
				<br /><br />
			</s:if>
			
			<br /><br />
		</s:iterator>
	</div>
</body>
</html>