/**
 * When you reply
 */
$(document).on("submit", "#replyForm", function(event) {
	event.preventDefault();
	var form = $(this);
	var url = form[0].action;
	var reply = form[0][0].value;
	var to = form[0][1].value;

	/**
	 * Post reply
	 */
	var posting = $.post(url, {
		"replyMsg.text" : reply,
		"replyMsg.recipient" : to
	});
	posting.done(function(response) {
		var newReply 
			= '<div class="message"> \
					<div class="message-photo"> \
						<img width="100%" height="100%" src="image?userId='	+ response.replyMsg.sender + '"/> \
					</div> \
					<div class="message-header"> \
						<b> ' + response.replyMsg.senderFirstName + " "	+ response.replyMsg.senderLastName + '</b> ' + response.now + 
					'</div> \
					<div class="message-text">'
						+ response.replyMsg.text + 
					'</div> \
				</div>';
								
		$("#selectedConversationThread").append(newReply);
		
		// scroll down the selected conversation thread
		$("#selectedConversationThread").scrollTop($("#selectedConversationThread")[0].scrollHeight);
	});
	
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