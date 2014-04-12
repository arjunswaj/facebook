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
				<s:iterator value="latestConversations">
					<div class="latest-conversation">
						<div class="latest-conversation-photo">
							<a href="profile?fref=<s:property value="otherUser" />" >
								<img width="50" height="50" src="image?userId=<s:property value="otherUser" />" />
							</a>
						</div>
						<div class="latest-conversation-header">
						</div>
						<div class="latest-conversation-text">
							<a class="latest-conversation" href="selectedConversationThread.action?otherUser=<s:property value="otherUser" />">
								<s:property value="otherUserFirstName" /> 
								<s:property value="otherUserLastName" /> 
								<s:property value="latestMessage" /> 
								<s:property value="sentAt" />
							</a>
						</div>
					</div>
				</s:iterator>
			</div>
			
		</div>


		<div id="right" class="right">
		
			<!-- Selected conversation header -->
			<div id="selectedConversationThreadHeader" class="selected-conversation-thread-header">
				<div class="conversation-with">
						<s:property value="latestConversations.get(0).getOtherUserFirstName()" />
						<s:property value="latestConversations.get(0).getOtherUserLastName()" />
				</div>
				<div class="new-message-button">
					<div>
						<s:submit value="+ New Message"></s:submit>
					</div>
				</div>
			</div>
			
			<!-- Selected conversation thread -->
			<div id="selectedConversationThread" class="selected-conversation-thread">
				<s:iterator value="selectedConversationThread">
					<div class="message">
						<div class="message-photo">
							<a href="profile?fref=<s:property value="sender" />" >
								<img width="32" height="32" src="image?userId=<s:property value="sender" />" />
							</a>
						</div>
						<div class="message-header">
							<strong>
								<a href="profile?fref=<s:property value="sender" />" >
									<s:property value="senderFirstName" /> 
									<s:property	value="senderLastName" />
								</a>
							</strong>
							<span class="message-header-date">
								<s:property value="sentAt" />
							</span>
						</div>
						<div class="message-text">
							<s:property value="text" />
						</div>
					</div>
				</s:iterator>
			</div>

			<!-- Reply -->
			<div id="replyBox" class="reply-box">
				<form id="replyForm" action="reply">
						<s:textarea id="reply" name="replyMsg.text" value="" cols="75" rows="5" placeholder="Write a reply..." />
						<s:hidden name="replyMsg.recipient" value="%{selectedLatestConversation.getOtherUser()}" />
						<s:submit class="reply-button" value="Reply"></s:submit>
				</form>
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