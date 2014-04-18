/*
 * When you click on any conversation thread
 */
$(document).on("click", ".latest-conversation", function(event) {
	event.preventDefault();
	
	// Change color of selected conversation
	$(this).parent().find(".latest-conversation").css('background-color', 'white');
	$(this).css('background-color', 'teal');
	
	// load selected conversation
	var href = $(this).children("a:first")[0].href;
	var url = href.split('?')[0];
	var otherUser = href.split('=')[1];
	var posting = $.post(url, {"selectedLatestConversation.otherUser" : otherUser});
	
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
		
		// scroll down the selected conversation thread
		$("#selectedConversationThread").scrollTop($("#selectedConversationThread")[0].scrollHeight);
	});
});