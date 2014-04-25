/**
 * 
 */
$(document).ready( function() {
	
	var posting = $.post('loadConversations.action', {});
	posting.done(function(response) {
		conversationsDiv.init(response.conversations);
		$("#conversations-div").replaceWith(conversationsDiv.get());
	});

	// Then render the left div and replace it
});