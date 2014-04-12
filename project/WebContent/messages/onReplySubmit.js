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
		messageDiv.init(response.replyMsg);
		$("#selectedConversationThread").append(messageDiv.get());
		
		// scroll down the selected conversation thread
		$("#selectedConversationThread").scrollTop($("#selectedConversationThread")[0].scrollHeight);
	});
	
	/*
	 * Reload the latest Conversations
	 */
	var posting = $.post('latestConversations.action', {});
	posting.done(function(response) {
		latestConversationsDiv.init(response.latestConversations);
		$("#latestConversations").replaceWith(latestConversationsDiv.get());
	});
});