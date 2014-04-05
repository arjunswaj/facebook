<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Messages</title>
<link rel="stylesheet" type="text/css" href="messages/messages.css" />
</head>
<script type="text/javascript">
	$.subscribe('beforeReply', function(event, data) {
		var statusfData = event.originalEvent.formData;
		if (!statusfData[0].value) {
			event.originalEvent.options.submit = false;
		}
	});

	$.subscribe('completeReply', function(event, data) {
		location.reload();
	});

	$.subscribe('errorStateReply', function(event, data) {
		alert('status: ' + event.originalEvent.status + '\n\nrequest status: '
				+ event.originalEvent.request.status);
	});
</script>

<body>
	<div id="container" style="width: 100%">
		<div id="latestmessages" class="latest-messages">
			List of latest messages
			<s:iterator value="latestMessages">
				<li><a
					href="messages.action?withUser=<s:property value="otherUser" />" onClick="a_onClick()">
						<s:property value="senderFirstName" /> <s:property
							value="senderLastName" /> <s:property value="text" /> <s:property
							value="sentAt" />
				</a></li>
			</s:iterator>
		</div>
		<div id="messagethread" class="message-thread">
			<s:iterator value="messages">
				<div id="message" class="message">
					<div id="messageThreadPhoto" class="message-photo">
						<img width="100%" height="100%"
							src="image?userId=<s:property value="sender" />" />
					</div>
					<div style="width: 90% float: left">
						<b> <s:property value="senderFirstName" /> <s:property
								value="senderLastName" />
						</b>
						<s:property value="sentAt" />
						<br>
					</div>
					<div style="width: 90% float: left">
						<s:property value="text" />
						<br> <br>
					</div>
				</div>
			</s:iterator>
		</div>
		<s:form id="replyform" action="reply">
			<s:textarea name="reply" value="" cols="50" rows="5"
				placeholder="Write a reply" />
			<s:hidden name="to" value="%{withUser}" />
			<sj:submit targets="replyResult" value="Reply" timeout="25000"
				indicator="replyIndicator" onBeforeTopics="beforeReply"
				onCompleteTopics="completeReply" onErrorTopics="errorStateReply"
				align="right" />
		</s:form>
	</div>
</body>
</html>