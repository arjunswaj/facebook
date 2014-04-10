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
		
		// scroll down the selected conversation thread
		$("#selectedConversationThread").scrollTop($("#selectedConversationThread")[0].scrollHeight);
	});
});