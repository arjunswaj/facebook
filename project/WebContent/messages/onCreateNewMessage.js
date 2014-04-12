
/**
 * When you want to create a new message
 */
$(document).on("click", ".new-message-button", function(event) {
	event.preventDefault();
	var newMessageHeader = '<div class="new-message-header">New Message</div>';
	var newMessageTo 
		= '<div class="new-message-to">\
				<span class="to-label">To:</span>\
					<input type="text" class="to-names" id="to" placeholder="ID as of now... :-("/>\
			</div>';
	var newMessageBox 
		= '<div class="new-message-box">\
				<form id="sendForm" action="send">\
					<textarea id="new" cols="75" rows="5" placeholder="Write a message..." />\
					<input class="send-button" type="submit" value="Send" />\
				</form>\
			</div>';
	
	// Now replace the entire right pane
	var right = '<div id="right" class="right">';
	right += newMessageHeader + newMessageTo + newMessageBox;
	right += '</div>';
	$('#right').replaceWith(right);
});