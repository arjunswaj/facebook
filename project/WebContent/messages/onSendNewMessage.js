/**
 * When the send button for a new message is clicked
 */
$(document).on("submit", "#sendForm", function(event) {
	
	event.preventDefault();

	var form = $(this);
	var url = form[0].action;
	var msg = form[0][0].value;
	var to = $("textarea#to").val();

	var posting = $.post(url, {
		"newMessage.text" : msg,
		"newMessage.recipient" : to
	});
	
	posting.done(function(response) {
		
		// generate the header for the selected conversation thread
		selectedConversationThreadHeaderDiv.init({
			firstName : response.selectedLatestConversation.otherUserFirstName,
			lastName : response.selectedLatestConversation.otherUserLastName
		}); 
		
		// generate selected conversation thread
		selectedConversationThreadDiv.init(response.selectedConversationThread);
		
		// generated the reply box
		replyBoxDiv.init(response.selectedLatestConversation.otherUser);
		
		// Now replace the entire right pane
		var right = '<div id="right" class="right">';
		right += selectedConversationThreadHeaderDiv.get() + selectedConversationThreadDiv.get() + replyBoxDiv.get();
		right += '</div>';
		$('#right').replaceWith(right);
		
		// Reload the latest conversations in the left pane
		var posting = $.post('latestConversations.action', {});
		posting.done(function(response) {
			latestConversationsDiv.init(response.latestConversations);
			$("#latestConversations").replaceWith(latestConversationsDiv.get());
		});
	});
});