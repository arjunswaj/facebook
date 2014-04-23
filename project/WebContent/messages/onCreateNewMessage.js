
/**
 * When you want to create a new message
 */
$(document).on("click", "#new-message-button", function(event) {
	event.preventDefault();
	
	// TODO: move this to divRenderer
	var newMessageHeaderDiv = '<div id="new-message-header-div">New Message</div>';
	var newMessageSuggestionsAreaDiv = '<div id="new-message-suggestions-area-div"></div>';
	var newMessageToDiv 
		= '<div id="new-message-to-div">\
				<span id="to-span">To:</span>\
				<span id="recipients-span"><input type="text" id="recipients-text" placeholder="Name"/></span>\
			</div>';
	var newMessageBoxDiv
		= '<div>\
				<div>\
					<textarea id="new-message-textarea" cols="96" rows="5" placeholder="Write a message..." />\
				</div>\
				<div>\
					<input id="send-button" type="submit" value="Send" />\
				</div>\
			</div>';
	
	// Now replace the entire right pane
	var rightDiv = '<div id="right-div">';
	rightDiv += newMessageHeaderDiv + newMessageToDiv  + newMessageSuggestionsAreaDiv + newMessageBoxDiv;
	rightDiv += '</div>';
	$('#right-div').replaceWith(rightDiv);

	// Pre-populate friends list for "To:" suggestions 
	var posting = $.post("newMessage.action");
	posting.done(function(response){
		// Pre-process response before passing to make it more friendly to tokenInput
		var friends = [];
		$.each(response.friends, function(index, friend){
			friends.push({ id : friend.userId, firstName : friend.firstName, lastName : friend.lastName, searchIn : friend.firstName + " " + friend.lastName + " " + friend.email});
		});
		
		// Now pre-populate
		$("#recipients-text").tokenInput(friends, {
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
			hintText: "Type in a name or email-id to start searching..."
			});
	});
});