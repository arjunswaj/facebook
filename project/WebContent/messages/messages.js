/**
 * 
 */
var div = document.getElementById("selectedConversationThread");
div.scrollTop = div.scrollHeight;

$(document).on("click", ".latest-conversation", function(event) {
	event.preventDefault();
	
	var anchor = $(this);
	var url = anchor[0].href.split('?')[0];
	var otherUser = anchor[0].href.split('=')[1];
	var posting = $.post(url, {"otherUser" : otherUser});
	
	posting.done(function(responses) {
		
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
		$("#selectedConversationThread").replaceWith(selectedConversationThread);
		
		var replyBox = 
			'<div id="replyBox" class="reply-box">\
				<form id="replyForm" action="reply">\
					<div>\
						<textarea id="reply" name="replyMsg.text" cols="75" rows="5" placeholder="Write a reply" />\
						<input type="hidden" id="replyMsg_recipient" name="replyMsg.recipient" value="' + otherUser + '" />\
						<input id="" type="submit" value="Reply" />\
					</div>\
				</form>\
			</div>';
		$("#replyBox").replaceWith(replyBox);
			
	});
});

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
	var asdf = $.post('latestConversations.action', {});
	asdf.done(function(response) {
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