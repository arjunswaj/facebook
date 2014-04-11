/**
 * Object literals used for rendering
 */

var selectedConversationThreadHeaderDiv = {
		firstName : "",
		lastName : "",
		
		init : function(userName) {
			selectedConversationThreadHeaderDiv.firstName = userName.firstName;
			selectedConversationThreadHeaderDiv.lastName = userName.lastName;
		},
		
		get : function() {
			var div = '<div id="selectedConversationThreadHeader" class="selected-conversation-thread-header">\
							<div class="conversation-with">'
							+ selectedConversationThreadHeaderDiv.firstName + ' ' + selectedConversationThreadHeaderDiv.lastName + 
							'</div>\
							<div class="new-message-button">\
								<div>\
									<input class="new-message-button" type="submit" value="+ New Message" />\
								</div>\
							</div>\
						</div>';
			
			return div;
		}
};

var selectedConversationThreadDiv = {
		conversationThread : [],
		
		init : function(conversationThread) {
			selectedConversationThreadDiv.conversationThread = conversationThread;
		},
		
		get : function() {
			var div = '<div id="selectedConversationThread" class="selected-conversation-thread"> ';
			$.each(selectedConversationThreadDiv.conversationThread, function(index, message) {
				messageDiv.init(message);
				div += messageDiv.get();
			});
			div += '</div>';
			return div;
		}
};

var messageDiv = {
	message : "",
	
	init: function(message) {
		messageDiv.message = message;
	},

	get : function() {
		var div = '<div class="message"> \
						<div class="message-photo"> \
							<img width="100%" height="100%"	src="image?userId='	+ messageDiv.message.sender + '" /> \
						</div> \
						<div class="message-header"> \
							<b> ' + messageDiv.message.senderFirstName	+ ' ' + messageDiv.message.senderLastName + '</b>'	+ messageDiv.message.sentAt +
						'</div> \
						<div class="message-text">'
							+ messageDiv.message.text	+ '\
						</div> \
					</div>';
		return div;
	}
};

var replyBoxDiv = {
		
		to : 0,
		
		init : function(to) {
			replyBoxDiv.to = to;
		},
		
		get : function() {
			var div = 
			'<div id="replyBox" class="reply-box">\
				<form id="replyForm" action="reply">\
					<textarea id="reply" name="replyMsg.text" cols="75" rows="5" placeholder="Write a reply..." />\
					<input type="hidden" id="replyMsg_recipient" name="replyMsg.recipient" value="' + replyBoxDiv.to + '" />\
					<input class="reply-button" type="submit" value="Reply" />\
				</form>\
			</div>';
			return div;
		}
};

var latestConversationDiv = {
		latestConversation : {},
		
		init : function(latestConversation) {
			latestConversationDiv.latestConversation = latestConversation; 
		},
		
		get : function() {
			var div
				= '<div>\
						<a class="latest-conversation" href="selectedConversationThread.action?otherUser=' + latestConversationDiv.latestConversation.otherUser + '">'
						+ latestConversationDiv.latestConversation.otherUserFirstName + ' ' + latestConversationDiv.latestConversation.otherUserLastName + ' ' + latestConversationDiv.latestConversation.latestMessage + ' ' + latestConversationDiv.latestConversation.sentAt +
						'</a>\
				   </div>';
			return div;
		}
};

var latestConversationsDiv = {
		latestConversations : {},
		
		init : function(latestConversations) {
			latestConversationsDiv.latestConversations = latestConversations;
		},
		
		get : function() {
			var div = '<div id="latestConversations" class="latest-conversations">';
			$.each(latestConversationsDiv.latestConversations, function(index, latestConversation) {
				latestConversationDiv.init(latestConversation);
				div	+= latestConversationDiv.get();
			});
			div += '</div>';
			return div;
		}
};