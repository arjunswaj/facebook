/**
 * Object literals used for rendering
 */
var selectedConversationThreadHeaderDiv = {
		otherParticipants : [],
		
		init : function(otherParticipants) {
			selectedConversationThreadHeaderDiv.otherParticipants = otherParticipants; 
		},
		
		get : function() {
			var div = '<div id="selected-conversation-thread-header-div">\
							<div id="other-participants-div">';
			$.each(selectedConversationThreadHeaderDiv.otherParticipants, function(index, otherParticipant){
				div += otherParticipant.firstName + ' ' + otherParticipant.lastName + ', ';
			});
			div +=		'</div>\
							<div id="new-message-button-div">\
								<div>\
									<input id="new-message-button-submit" type="submit" value="+ New Message" />\
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
			var div = '<div id="selected-conversation-thread-div"> ';
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
		var div = '<div class="message-div"> \
						<div class="message-photo-div"> \
							<a href="profile?fref=' + messageDiv.message.sender + '">\
								<img width="32" height="32"	src="image?userId='	+ messageDiv.message.sender + '" /> \
							</a>\
						</div> \
						<div class="message-header-div"> \
							<strong> \
								<a href="profile?fref=' + messageDiv.message.sender + '">' + messageDiv.message.senderFirstName	+ ' ' + messageDiv.message.senderLastName + '</a>' + 
							'</strong>\
							<span class="message-date-div">' 
								+ jQuery.timeago(messageDiv.message.sentAt) +
							'</span>\
						</div> \
						<div class="message-text-div">'
							+ messageDiv.message.text	+ '\
						</div> \
					</div>';
		return div;
	}
};

var replyBoxDiv = {
		
		selectedConversation : {},
		
		init : function(selectedConversation) {
			replyBoxDiv.selectedConversation = selectedConversation;
		},
		
		get : function() {
			if (replyBoxDiv.selectedConversation.otherParticipants.length == 1 && replyBoxDiv.selectedConversation.allFriends == false) {
				return '<div id="reply-box-div" align="center">You cannot reply to this conversation</div>';
			}
			var div = 
			'<div id="reply-box-div">\
				<textarea id="reply-textarea" name="replyMsg.text" value="" cols="96" rows="5" placeholder="Write a reply..." />\
				<input type="hidden" id="conversation-hidden" name="replyMsg.conversation" value="' + replyBoxDiv.selectedConversation.id + '" />\
				<input id="reply-button" type="submit" value="Reply" />\
			</div>';
			return div;
		}
};

var conversationDiv = {
		conversation : {},
		
		init : function(conversation) {
			conversationDiv.conversation = conversation; 
		},
		
		get : function() {
			var div
				= '<div class="conversation-div">\
					<a href="loadSelectedConversationThread.action?selectedConversation.id=' + conversationDiv.conversation.id + '"></a>\
						<div class="conversation-photo-div">\
								<img width="50" height="50" src="image?userId='  + conversationDiv.conversation.otherParticipants[0].userId + '"/>\
						</div>\
						<div class="conversation-details-div">\
							<div class="conversation-participants-div">';
			$.each(conversationDiv.conversation.otherParticipants, function(index, otherParticipant) {
				div += otherParticipant.firstName + ' ' + otherParticipant.lastName + ', ';
			});
			div += '		</div>\
							<div class="conversation-date-div">'
								+ jQuery.timeago(conversationDiv.conversation.sentAt) +
							'</div>\
							<div class="conversation-text-div">'
								+ conversationDiv.conversation.latestMessageText + 
							'</div>\
						</div>\
				   </div>';
			return div;
		}
};

var conversationsDiv = {
		conversations : {},
		
		init : function(conversations) {
			conversationsDiv.conversations = conversations;
		},
		
		get : function() {
			var div = '<div id="conversations-div">';
			$.each(conversationsDiv.conversations, function(index, conversation) {
				conversationDiv.init(conversation);
				div	+= conversationDiv.get();
			});
			div += '</div>';
			return div;
		}
};

var toFieldSearchResultsFormatterDiv = {
		friend : {},
		
		init : function(friend) {
			toFieldSearchResultsFormatterDiv.friend = friend;
		},
		
		get : function() {
			var div = "<li>" + toFieldSearchResultsFormatterDiv.friend.firstName + " " + toFieldSearchResultsFormatterDiv.friend.lastName + "</li>";
			return div;
		}
};

var tokenFormatterDiv = {
		name : {},
		
		init : function(name) {
			tokenFormatterDiv = name;
		},
		
		get : function() {
			var div = "<li><strong>" + tokenFormatterDiv.name.firstName + " " + tokenFormatterDiv.name.lastName + "</strong></li>";
			return div;
		}
};