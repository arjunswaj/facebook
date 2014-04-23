<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Messages</title>
<link rel="stylesheet" type="text/css" href="js/loopj-jquery-tokeninput/styles/token-input.css" />
<link rel="stylesheet" type="text/css" href="js/loopj-jquery-tokeninput/styles/token-input-facebook.css" />
<link rel="stylesheet" type="text/css" href="messages/messages.css" />
</head>
<body>
	<div id="container-div">
	
		<div id="left-div">
			
			<!-- Inbox header -->
			<div id="inbox-header-div">
				<span id="inbox-heading-span">Inbox</span>
			</div>
			
			<!-- Latest conversations -->
			<div id="conversations-div">
				<s:iterator value="conversations">
					<div class="conversation-div">
						<input type=hidden value='<s:property value="id" />' />
						<div class="conversation-photo-div">
							<img width="50" height="50" src="image?userId=<s:property value="otherParticipants.get(0).userId" />" />
						</div>
						<div class="conversation-details-div">
							<div class="conversation-participants-div">
									<s:iterator value="otherParticipants">
											<s:property value="firstName" /> 
											<s:property value="lastName" />
											, 
									</s:iterator>
							</div>
							<div class="conversation-date-div">								
									<s:property value="sentAt" />
							</div>	
							<div class="conversation-text-div">
								<s:property value="latestMessageText" /> 
							</div>
						</div>	
					</div>
				</s:iterator>
			</div>
			
		</div>


		<div id="right-div">
		
			<!-- Selected conversation header -->
			<div id="selected-conversation-thread-header-div">
				<div id="other-participants-div">
					<s:iterator value="selectedConversation.otherParticipants">
						<s:property value="firstName" />
						<s:property value="lastName" />
						,
					</s:iterator>
				</div>
				<div id="action-buttons-div">
					<input type="button" id="delete-conversation-button" value="Delete Conversation"/>
					<input type="hidden" value='<s:property value="%{selectedConversation.id}"/>' />
					<input type="button" id="new-message-button" value="+ New Message"/>
				</div>
			</div>
			
			<!-- Selected conversation thread -->
			<div id="selected-conversation-thread-div">
				<s:iterator value="selectedConversationThread">
					<div class="message-div">
						<div class="message-photo-div">
							<a href="profile?fref=<s:property value="sender" />" >
								<img width="32" height="32" src="image?userId=<s:property value="sender" />" />
							</a>
						</div>
						<div class="message-header-div">
							<strong>
								<a href="profile?fref=<s:property value="sender" />" >
									<s:property value="senderFirstName" /> 
									<s:property	value="senderLastName" />
								</a>
							</strong>
							<span class="message-date-div">
								<s:property value="sentAt" />
							</span>
						</div>
						<div class="message-text-div">
							<s:property value="text" />
						</div>
					</div>
				</s:iterator>
			</div>

			<!-- Reply -->
			<s:if test="%{selectedConversation.otherParticipants.size != 1 or selectedConversation.allFriends}">
				<div id="reply-box-div">
					<s:textarea id="reply-textarea" name="replyMsg.text" value="" cols="96" rows="5" placeholder="Write a reply..." />
					<s:hidden id="conversation-hidden" name="replyMsg.conversation" value="%{selectedConversation.id}" />
					<s:submit id="reply-button" value="Reply"></s:submit>
				</div>
			</s:if>
			<s:else>
			<div id="reply-box-div" align="center">
				You cannot reply to this conversation
			</div>
			</s:else>

		</div>
	
	</div>
	<script src="messages/divRenderers.js"></script>
	<script src="messages/onConversationThreadClick.js"></script>
	<script src="messages/onCreateNewMessage.js"></script>
	<script src="messages/onReplySubmit.js"></script>
	<script src="messages/onSendNewMessage.js"></script>
	<script src="messages/onDeleteConversation.js"></script>
	<script src="js/loopj-jquery-tokeninput/src/jquery.tokeninput.js"></script>
	<script src="js/jquery.timeago.js" type="text/javascript"></script>
</body>
</html>