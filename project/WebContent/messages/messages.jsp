<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Messages</title>
<link rel="stylesheet" type="text/css" href="messages/messages.css" />
<script src="js/jquery-1.9.1.js"></script>
</head>
<body>
	<div id="container" class="container">
		<div id="latestmessages" class="latest-messages">
			List of latest messages
			<s:iterator value="latestMessages">
				<li><a
					href="messages.action?withUser=<s:property value="otherUser" />"
					onClick="a_onClick()"> <s:property value="senderFirstName" />
						<s:property value="senderLastName" /> <s:property value="text" />
						<s:property value="sentAt" />
				</a></li>
			</s:iterator>
		</div>
		<div id="messageThread" class="message-thread">
			<s:iterator value="messages">
				<div class="message">
					<div class="message-photo">
						<img width="100%" height="100%"
							src="image?userId=<s:property value="sender" />" />
					</div>
					<div class="message-text">
						<b> <s:property value="senderFirstName" /> <s:property
								value="senderLastName" />
						</b>
						<s:property value="sentAt" />
					</div>
					<div class="message-text">
						<s:property value="text" />
					</div>
				</div>
			</s:iterator>
			
		</div>
		<div id="replyBox" class="reply-box">
			<form id="replyForm" action="reply">
				<div>
					<s:textarea id="reply" name="replyMsg.text" value="" cols="75" rows="5"
						placeholder="Write a reply" />
					<s:hidden name="replyMsg.recipient" value="%{withUser}" />
					<s:submit value="Reply"></s:submit>
				</div>
			</form>
		</div>
	</div>
	<script>
		var div = document.getElementById("messageThread");
		div.scrollTop = div.scrollHeight;

		$("#replyForm").submit(function(event) {

			event.preventDefault();

			var form = $(this);
			var url = $("#replyForm")[0].action;
			var reply = form[0][0].value;
			var to = form[0][1].value;

			var posting = $.post(url, {
				"replyMsg.text" : reply,
				"replyMsg.recipient" : to
			});

			posting.done(function(response) {
				$("#messageThread").append('\
						<div class="message"> \
							<div class="message-photo"> \
								<img width="100%" height="100%" src="image?userId=' + response.replyMsg.sender + '"/> \
							</div> \
							<div class="message-text">	\
								<b> ' + response.replyMsg.senderFirstName + " " + response.replyMsg.senderLastName + '</b>'
								+ " " + response.now + 
							'</div> \
							<div class="message-text">' 
								+ response.replyMsg.text + 
							'</div> \
						</div>');
				$("#reply").value = "";
			});
		});
		div.scrollTop = div.scrollHeight;
	</script>
</body>
</html>