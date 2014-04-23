<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Messages</title>
<link rel="stylesheet" type="text/css" href="messages/messages.css" />
<link rel="stylesheet" type="text/css" href="js/loopj-jquery-tokeninput/styles/token-input.css" />
<link rel="stylesheet" type="text/css" href="js/loopj-jquery-tokeninput/styles/token-input-facebook.css" />
</head>
<body>
	<div id="container-div">
	
		<div id="left-div">
			
			<!-- Inbox header -->
			<div id="inbox-header-div">
				<span id="inbox-heading-span">Inbox</span>
			</div>
			
			<!-- Latest conversations -->
			<div id="conversations-div" style="text-align:center;">
			<strong>
				No conversations
			</strong>
			</div>
			
		</div>


		<div id="right-div">
		
			<!-- Selected conversation header -->
			<div id="selected-conversation-thread-header-div">
				<div id="other-participants-div">
						No Conversation Selected
				</div>
				<div id="new-message-button-div">
					<div>
						<s:submit id="new-message-button" value="+ New Message"></s:submit>
					</div>
				</div>
			</div>
			
			<!-- Selected conversation thread -->
			<div id="selected-conversation-thread-div">
			</div>

		</div>
	
	</div>
	<script src="messages/divRenderers.js"></script>
	<script src="messages/onConversationThreadClick.js"></script>
	<script src="messages/onCreateNewMessage.js"></script>
	<script src="messages/onReplySubmit.js"></script>
	<script src="messages/onSendNewMessage.js"></script>
	<script src="js/loopj-jquery-tokeninput/src/jquery.tokeninput.js"></script>
</body>
</html>