/**
 * Delete conversation
 */
$(document).on("click", ".delete-message-button", function(event) {
	event.preventDefault();
	var messageId = $(this).next().val();
	var conversationId = $(this).next().next().val();

	// Remove the message
	$(this).parent().parent().remove();

	
	var posting = $.post("deleteMessage.action", {
		"message.id" : messageId
		});

	posting.done(function(response) {		
		if ($("#selected-conversation-thread-div").children().length == 0) {
			$("#other-participants-div").text("No conversation selected");
			$("#delete-conversation-button").remove();	
			$("#reply-box-div").remove();
			
			// Remove the selected conversation-div by traversing the conversations-div
			$.each($("#conversations-div").children(), function(index, conversationDiv) {
				if (conversationDiv.lastElementChild.lastElementChild.lastElementChild.value == conversationId) {
					conversationDiv.remove();
					return;
				}		
			});
		}
	});
});