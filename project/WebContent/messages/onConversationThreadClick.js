/*
 * When you click on any conversation thread
 */
$(document).on("click", ".conversation-div", function(event) {
	event.preventDefault();
	
	// Change color of selected conversation
	$(this).parent().find(".conversation-div").css('background-color', 'white');
	$(this).css('background-color', 'teal');
	
	// load selected conversation
	var selectedConversation = $(this).children("input").val();
	var posting = $.post("loadSelectedConversationThread.action", {"selectedConversation.id" : selectedConversation});
	
	posting.done(function(response) {

		// generate the header for the selected conversation thread
		selectedConversationThreadHeaderDiv.init(response.selectedConversation); 

		// generate selected conversation thread
		selectedConversationThreadDiv.init(response.selectedConversationThread);
		
		// generated the reply box
		replyBoxDiv.init(response.selectedConversation);
		
		// Now replace the entire right pane
		var right = '<div id="right-div">';
		right += selectedConversationThreadHeaderDiv.get() + selectedConversationThreadDiv.get();
		right += replyBoxDiv.get();
		right += '</div>';
		$('#right-div').replaceWith(right);
		
		// scroll down the selected conversation thread
		//$("#selected-conversation-thread-div").scrollTop($("#selected-conversation-thread.div")[0].scrollHeight);
	});
});