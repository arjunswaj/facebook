
/**
 * When you want to create a new message
 */
$(document).on("click", ".new-message-button", function(event) {
	event.preventDefault();
	var newMessageHeader = '<div class="new-message">New Message</div>';
	//var newMessageTo = '<div class="new-message-to"> <textarea id="to" cols="75" rows="1" /></div>';
	var newMessageTo = '<div class="new-message-to"><input type="text" placeholder="Name" name="email" id="to" autocomplete="off" /><input type="text" id="fref" name="fref" hidden="true" /></div>';
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

	$("#to").autocomplete({
		source : function(request, response) {
			alert("gotcha");
			$.ajax({
				url : "getdata.jsp",
				type : "POST",
				dataType : "json",
				cache : false,
				data : {
					name : request.term
				},
				success : function(data) {

					response($.map(data, function(item) {

						return {

							label : item.name,
							value : item.userid
						};
					}));
				},
				error : function(error) {
					alert('error: ' + error);
				}
			});
		},
		minLength : 2,
		select : function(event, ui) {
			event.preventDefault();
			$(this).val(ui.item.label);
			$("#fref").val(ui.item.value);
		}
	});