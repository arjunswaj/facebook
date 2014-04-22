/**
 * When you reply
 */
$(document).on("submit", "#reply-form", function(event) {
	event.preventDefault();
	var form = $(this);
	var url = form[0].action;
	var reply = form[0][0].value;
	var conversation = form[0][1].value;

	/**
	 * Post reply
	 */
	var posting = $.post(url, {
		"replyMsg.text" : reply,
		"replyMsg.conversation" : conversation
	});
	posting.done(function(response) {
		messageDiv.init(response.replyMsg);
		$("#selected-conversation-thread-div").append(messageDiv.get());
		
		// scroll down the selected conversation thread
		$("#selected-conversation-thread-div").scrollTop($("#selected-conversation-thread-div")[0].scrollHeight);
	});
	
	/*
	 * Reload the latest Conversations
	 */
	var posting = $.post('loadConversations.action', {});
	posting.done(function(response) {
		conversationsDiv.init(response.conversations);
		$("#conversations-div").replaceWith(conversationsDiv.get());
	});
});