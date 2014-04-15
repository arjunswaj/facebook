<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Messages</title>
<link rel="stylesheet" type="text/css" href="messages/messages.css" />
</head>
<body>
	<div id="container" class="container">
	
		<div id="left" class="left">
			
			<!-- Inbox header -->
			<div id="inboxHeader" class="inbox-header">
				<span class="inbox">Inbox</span>
			</div>
			
			<!-- Latest conversations -->
			<div id="latestConversations" class="latest-conversations">
			</div>
			
		</div>


		<div id="right" class="right">
		
			<!-- Selected conversation header -->
			<div id="selectedConversationThreadHeader" class="selected-conversation-thread-header">
				<div class="conversation-with">
						No Conversation Selected
				</div>
				<div class="new-message-button">
					<div>
						<s:submit value="+ New Message"></s:submit>
					</div>
				</div>
			</div>
			
			<!-- Selected conversation thread -->
			<div id="selectedConversationThread" class="selected-conversation-thread">
			</div>

		</div>
	
	</div>
	<script src="messages/divRenderers.js"></script>
	<script src="messages/onConversationThreadClick.js"></script>
	<script src="messages/onCreateNewMessage.js"></script>
	<script src="messages/onReplySubmit.js"></script>
	<script src="messages/onSendNewMessage.js"></script>
</body>
</html>