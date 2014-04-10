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
	
	posting.done(function(response){
		
	});
});