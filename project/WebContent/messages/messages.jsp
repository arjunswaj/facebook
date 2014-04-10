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
	
		<div id="left" class="left">
			
			<!-- Inbox header -->
			<div id="inboxHeader" class="inbox-header">
				<span class="inbox">Inbox</span>
				<span class="unread">Unread</span>
			</div>
			
			<!-- Latest conversations -->
			<div id="latestConversations" class="latest-conversations">
				<s:iterator value="latestConversations">
					<div>
						<a class="latest-conversation" href="selectedConversationThread.action?otherUser=<s:property value="otherUser" />">
							<s:property value="otherUserFirstName" /> 
							<s:property value="otherUserLastName" /> 
							<s:property value="latestMessage" /> 
							<s:property value="sentAt" />
						</a>
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
					<form id="newMessageForm" action="reply">
						<div>
							<s:submit value="+ New Message"></s:submit>
						</div>
					</form>
				</div>
			</div>
			
			<!-- Selected conversation thread -->
			<div id="selectedConversationThread" class="selected-conversation-thread">
				<s:iterator value="selectedConversationThread">
					<div class="message">
						<div class="message-photo">
							<img width="100%" height="100%" src="image?userId=<s:property value="sender" />" />
						</div>
						<div class="message-header">
							<b> 
								<s:property value="senderFirstName" /> 
								<s:property	value="senderLastName" />
							</b>
							<s:property value="sentAt" />
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
					<div>
						<s:textarea id="reply" name="replyMsg.text" value="" cols="75" rows="5" placeholder="Write a reply" />
						<s:hidden name="replyMsg.recipient" value="%{selectedLatestConversation.getOtherUser()}" />
						<s:submit value="Reply"></s:submit>
					</div>
				</form>
			</div>

		</div>
	
	</div>
	<script src="messages/messages.js"></script>
</body>
</html>
