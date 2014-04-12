
/**
 * When you want to create a new message
 */
$(document).on("click", ".new-message-button", function(event) {
	event.preventDefault();
	var newMessageHeader = '<div class="new-message">New Message</div>';
	var newMessageTo = '<div class="new-message-to"> <input type="text" id="to" placeholder="Name"/></div>';
	var newMessageToSuggestions = '<div class="new-message-to-suggestions">Suggestions to come here</div>';
	var newMessageBox 
		= '<div class="new-message-box">\
				<form id="sendForm" action="send">\
					<textarea id="new" cols="75" rows="5" placeholder="Write a message..." />\
					<input class="send-button" type="submit" value="Send" />\
				</form>\
			</div>';
	
	// Now replace the entire right pane
	var right = '<div id="right" class="right">';
	right += newMessageHeader + newMessageTo + newMessageToSuggestions + newMessageBox;
	right += '</div>';
	$('#right').replaceWith(right);
});