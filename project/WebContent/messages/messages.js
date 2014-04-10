/**
 * 
 */
var div = document.getElementById("selectedConversationThread");
div.scrollTop = div.scrollHeight;

/*
 * When you click on any conversation thread
 */
$(document).on("click", ".latest-conversation", function(event) {
	event.preventDefault();
	var anchor = $(this);
	var url = anchor[0].href.split('?')[0];
	var otherUser = anchor[0].href.split('=')[1];
	var posting = $.post(url, {"selectedLatestConversation.otherUser" : otherUser});
	
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
					<div>\
						<textarea id="reply" name="replyMsg.text" cols="75" rows="5" placeholder="Write a reply" />\
						<input type="hidden" id="replyMsg_recipient" name="replyMsg.recipient" value="' + otherUser + '" />\
						<input class="reply-button" type="submit" value="Reply" />\
					</div>\
				</form>\
			</div>';
		
		// Now replace the entire right pane
		var right = '<div id="right" class="right">';
		right += selectedConversationThreadHeader + selectedConversationThread + replyBox;
		right += '</div>';
		$('#right').replaceWith(right);

	});
});

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
		var newReply = '<div class="message"> \
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
	});
	
	/*
	 * Reload the latest Conversations
	 */
	var posting = $.post('latestConversations.action', {});
	posting.done(function(response) {
		var latestConversations = '<div id="latestConversations" class="latest-conversations">';
		$.each(response.latestConversations, function(index, latestConversation) {
			latestConversations += '<div>\
										<a class="latest-conversation" href="selectedConversationThread.action?otherUser=' + latestConversation.otherUser + '">'
										+ latestConversation.otherUserFirstName + ' ' + latestConversation.otherUserLastName + ' ' + latestConversation.latestMessage + ' ' + latestConversation.sentAt +										
										'</a>\
									</div>';
		});
		latestConversations += '</div>';
		
		$("#latestConversations").replaceWith(latestConversations);
	});
	div.scrollTop = div.scrollHeight;
});

/**
 * When you want to send a new message
 */
$(document).on("click", ".new-message-button", function(event) {
	alert("+ New message");
});