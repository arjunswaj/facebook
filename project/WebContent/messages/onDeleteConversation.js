/**
 * Delete conversation
 */
$(document).on("click", "#delete-conversation-button", function(event) {
	event.preventDefault();

	var selectedConversationId = $(this).next().val();
	
	var posting = $.post("deleteConversation.action", {
		"selectedConversation.id" : selectedConversationId
		});

	posting.done(function(response) {
		// Remove the selected conversation-div by traversing the conversations-div
		$.each($("#conversations-div").children(), function(index, conversationDiv) {
			if (conversationDiv.firstChild.nextSibling.value == selectedConversationId) {
				conversationDiv.remove();
				return;
			}
		});
		
		// Modify the selected-conversation-thread-header-div
		$("#other-participants-div").empty();
		$("#other-participants-div").append("No conversation selected");
		$("#delete-conversation-button").remove();
		
		// Empty the deleted thread
		$("#selected-conversation-thread-div").empty();
	});
});