/**
 * When the send button for a new message is clicked
 */
$(document).on("submit", "#sendForm", function(event) {
	
	event.preventDefault();

	var form = $(this);
	var url = form[0].action;
	var msg = form[0][0].value;
	var to = $("textarea#to").val();

	var posting = $.post(url, {
		"newMessage.text" : msg,
		"newMessage.recipient" : to
	});
	
	posting.done(function(responses) {
		
		// generate the header for the selected conversation thread
		var selectedConversationThreadHeader =
			'<div id="selectedConversationThreadHeader" class="selected-conversation-thread-header">\
				<div class="conversation-with">'
						 + responses.selectedLatestConversation.otherUserFirstName + ' ' + responses.selectedLatestConversation.otherUserLastName +
				'</div>\
				<div class="new-message-button">\
					<div>\
					 	<input class="new-message-button" type="submit" value="+ New Message" />\
					</div>\
				</div>\
			</div>';
		
		// generate selected conversation thread
		var selectedConversationThread = '<div id="selectedConversationThread" class="selected-conversation-thread"> ';
		$.each(responses.selectedConversationThread, function(index, message) {
			selectedConversationThread += 
				'<div class="message"> \
					<div class="message-photo"> \
						<img width="100%" height="100%"	src="image?userId='	+ message.sender + '" /> \
					</div> \
					<div class="message-text"> \
						<b> ' + message.senderFirstName	+ ' ' + message.senderLastName + '</b>'	+ message.sentAt +
					'</div> \
					<div class="message-text">'
						+ message.text	+ '\
					</div> \
				</div>';
		});
		selectedConversationThread += '</div>';
		
		// generated the reply box
		var replyBox = 
			'<div id="replyBox" class="reply-box">\
				<form id="replyForm" action="reply">\
						<textarea id="reply" name="replyMsg.text" cols="75" rows="5" placeholder="Write a reply..." />\
						<input type="hidden" id="replyMsg_recipient" name="replyMsg.recipient" value="' + responses.selectedLatestConversation.otherUser + '" />\
						<input class="reply-button" type="submit" value="Reply" />\
				</form>\
			</div>';
		
		// Now replace the entire right pane
		var right = '<div id="right" class="right">';
		right += selectedConversationThreadHeader + selectedConversationThread + replyBox;
		right += '</div>';
		$('#right').replaceWith(right);
		
		/*
		 * Reload the latest Conversations
		 */
		var posting = $.post('latestConversations.action', {});
		posting.done(function(response) {
			var latestConversations = '<div id="latestConversations" class="latest-conversations">';
			$.each(response.latestConversations, function(index, latestConversation) {
				latestConversations 
					+= '<div>\
							<a class="latest-conversation" href="selectedConversationThread.action?otherUser=' + latestConversation.otherUser + '">'
							+ latestConversation.otherUserFirstName + ' ' + latestConversation.otherUserLastName + ' ' + latestConversation.latestMessage + ' ' + latestConversation.sentAt +
							'</a>\
						</div>';
			});
			latestConversations += '</div>';
			
			$("#latestConversations").replaceWith(latestConversations);
		});

	});
});