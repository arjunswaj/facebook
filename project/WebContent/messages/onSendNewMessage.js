/**
 * When the send button for a new message is clicked
 */
$(document).on("click", "#send-button", function(event) {
	event.preventDefault();

	var recipients = $("input#recipients-text").val();
	var posting = $.post("send.action", {
		"newMessage.text" : $("#new-message-textarea").val(),
		"recipients" : recipients
	});

	posting.done(function(response) {

		// generate the header for the selected conversation thread
		selectedConversationThreadHeaderDiv.init(response.selectedConversation); 

		// generate selected conversation thread
		selectedConversationThreadDiv.init(response.selectedConversationThread);

		// generated the reply box
		replyBoxDiv.init(response.selectedConversation);

		// Now replace the entire right pane
		var right = '<div id="right-div">';
		right += selectedConversationThreadHeaderDiv.get();
		right += selectedConversationThreadDiv.get();
		right += replyBoxDiv.get();
		right += '</div>';
		$('#right-div').replaceWith(right);
		
		// Reload the conversations in the left pane
		var posting = $.post('loadConversations.action', {});
		posting.done(function(response) {
			conversationsDiv.init(response.conversations);
			$("#conversations-div").replaceWith(conversationsDiv.get());
		});

	});
});