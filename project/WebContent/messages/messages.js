/**
 * 
 */
var div = document.getElementById("messageThread");
div.scrollTop = div.scrollHeight;

$("#replyForm")
		.submit(
				function(event) {

					event.preventDefault();

					var form = $(this);
					var url = $("#replyForm")[0].action;
					var reply = form[0][0].value;
					var to = form[0][1].value;

					var posting = $.post(url, {
						"replyMsg.text" : reply,
						"replyMsg.recipient" : to
					});

					posting
							.done(function(response) {
								$("#messageThread")
										.append(
												'\
						<div class="message"> \
							<div class="message-photo"> \
								<img width="100%" height="100%" src="image?userId='
														+ response.replyMsg.sender
														+ '"/> \
							</div> \
							<div class="message-text">	\
								<b> '
														+ response.replyMsg.senderFirstName
														+ " "
														+ response.replyMsg.senderLastName
														+ '</b>'
														+ " "
														+ response.now
														+ '</div> \
							<div class="message-text">'
														+ response.replyMsg.text
														+ '</div> \
						</div>');
								$("#reply").value = "";
							});
				});
div.scrollTop = div.scrollHeight;