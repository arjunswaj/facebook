/**
 * When you reply
 */
$(document).on("click", "#reply-button", function(event) {
	event.preventDefault();

	/**
	 * Post reply
	 */
	var posting = $.post("reply.action", {
		"replyMsg.text" : $("#reply-textarea").val(),
		"replyMsg.conversation" : $("#conversation-hidden").val()
	});
	posting.done(function(response) {
		
		$("#reply-textarea").val(""); // clear reply box
		$(".conversation-div:first-of-type").css('background-color', 'teal'); // highlight the first conversation
		
		
		messageDiv.init(response.replyMsg);
		$("#selected-conversation-thread-div").append(messageDiv.get());
		
		// scroll down the selected conversation thread
		$("#selected-conversation-thread-div").scrollTop($("#selected-conversation-thread-div")[0].scrollHeight);
	});
	
	/*
	 * Reload the conversations
	 */
	var posting = $.post('loadConversations.action', {});
	posting.done(function(response) {
		conversationsDiv.init(response.conversations);
		$("#conversations-div").replaceWith(conversationsDiv.get());
	});
});