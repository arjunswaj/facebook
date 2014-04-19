
/**
 * When you want to create a new message
 */
$(document).on("click", ".new-message-button", function(event) {
	event.preventDefault();
	
	// TODO: move this to divRenderer
	var newMessageHeaderDiv = '<div class="new-message-header">New Message</div>';
	var newMessageSuggestionsDiv = '<div class="new-message-to-suggestions"></div>';
	var newMessageToDiv 
		= '<div class="new-message-to">\
				<span class="to-label">To:</span>\
				<input type="text" class="to-names" id="to" placeholder="Name"/>\
			</div>';
	var newMessageBoxDiv
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
	var rightDiv = '<div id="right" class="right">';
	rightDiv += newMessageHeaderDiv + newMessageToDiv + newMessageSuggestionsDiv + newMessageBoxDiv;
	rightDiv += '</div>';
	$('#right').replaceWith(rightDiv);

	// Pre-populate friends list for "To:" suggestions 
	var posting = $.post("newMessage.action");
	posting.done(function(response){
		// Pre-process response before passing to make it more friendly to tokenInput
		var friends = [];
		$.each(response.friends, function(index, friend){
			friends.push({ id : friend.userId, firstName : friend.firstName, lastName : friend.lastName, searchIn : friend.firstName + " " + friend.lastName + " " + friend.email});
		});
		
		// Now pre-populate
		$("#to").tokenInput(friends, {
			propertyToSearch: "searchIn",
			theme: "facebook",
			preventDuplicates: true,
			resultsFormatter: function(item){ 
				toFieldSearchResultsFormatterDiv.init({firstName : item.firstName, lastName : item.lastName, id : item.id}); 
				return toFieldSearchResultsFormatterDiv.get();
			},
			tokenFormatter: function(item) { 
				//tokenFormatterDiv.init({firstName : item.firstName, lastName : item.lastName}); 
				//return tokenFormatterDiv.get(); // TODO: why is this not working?
				return "<li><strong>" + item.firstName + " " + item.lastName + "</strong></li>"; 
			}, 
			hintText: "Type in a name to start searching...",
			tokenLimit: "1" // TODO: remove this after proper implementation of conversation
			});
	});
});