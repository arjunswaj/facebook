<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Events</title>
	<script type="text/javascript">
		function loadXmlDoc(e)
		{
			var sel=document.getElementById(e).childNodes[1];
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
			div.innerHTML="<div></div><select onchange=\"loadXmlDoc('"+e+"')\">"+s+"</select>";
			div.childNodes[1].value=c;
			loadXmlDoc(e);
		}

	</script>
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
					
					<div id='<s:property value="#invitation.getEventId()" />'>
						<s:if test='#invitation.getConfirmation().equals("pending")==false'>
							<s:if test='#invitation.getConfirmation().equals("join")'>
							<select onchange="loadXmlDoc('<s:property value="#invitation.getEventId()" />')">
								<option value="join" selected="selected">*Going</option>
								<option value="maybe">Maybe</option>
								<option value="nope">Not Going</option>
							</select>
							</s:if>
							<s:elseif test='#invitation.getConfirmation().equals("maybe")'>
							<select onchange="loadXmlDoc('<s:property value="#invitation.getEventId()" />')">
								<option value="join">Going</option>
								<option value="maybe" selected="selected">*Maybe</option>
								<option value="nope">Not Going</option>
							</select>
							</s:elseif>
							<s:else>
								You declined.
							</s:else>
						</s:if>
						<s:else>
							<div>						
								<input type="button" value="Join" onclick="loadXmlDoc2('<s:property value="#invitation.getEventId()" />', 'join')" />
								<input type="button" value="Maybe" onclick="loadXmlDoc2('<s:property value="#invitation.getEventId()" />', 'maybe')" />
								<input type="button" value="Decline" onclick="loadXmlDoc2('<s:property value="#invitation.getEventId()" />', 'nope')" />
							</div>
						</s:else>
					</div>
				</div>
				<br />
			</div>
			</s:iterator>
			<br /><br />
		</s:iterator>
	</div>
</body>
</html>