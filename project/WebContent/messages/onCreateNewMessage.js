
/**
 * When you want to create a new message
 */
$(document).on("click", ".new-message-button", function(event) {
	event.preventDefault();
	var newMessageHeader = '<div class="new-message-header">New Message</div>';
	var newMessageSuggestions = '<div class="new-message-to-suggestions"></div>';
	var newMessageTo 
		= '<div class="new-message-to">\
				<span class="to-label">To:</span>\
					<input type="text" class="to-names" id="to" placeholder="ID as of now... :-("/>\
			</div>';
	var newMessageBox 
		= '<div>\
				<form id="sendForm" action="send">\
					<div>\
					<textarea class="new-message-box" id="new" cols="96" rows="5" placeholder="Write a message..." />\
					</div>\
					<div>\
					<input class="send-button" type="submit" value="Send" />\
					</div>\
				</form>\
			</div>';
	
	// Now replace the entire right pane
	var right = '<div id="right" class="right">';
	right += newMessageHeader + newMessageTo + newMessageSuggestions + newMessageBox;
	right += '</div>';
	$('#right').replaceWith(right);
});