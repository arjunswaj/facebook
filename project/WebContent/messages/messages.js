/**
 * 
 */
var div = document.getElementById("conversationThread");
div.scrollTop = div.scrollHeight;

$('.conversation')
		.click(
				function(event) {
					event.preventDefault();
					var anchor = $(this);
					var url = anchor[0].href;
					var getting = $.get(url);
					getting
							.done(function(responses) {
								var messageThread = '<div id="conversationThread" class="conversation-thread"> ';
								$
										.each(
												responses.conversation,
												function(index, message) {
													messageThread += '			<div class="message"> \
					<div class="message-photo"> \
						<img width="100%" height="100%" \
							src="image?userId='
															+ message.sender
															+ '" /> \
					</div> \
					<div class="message-text"> \
						<b> '
															+ message.senderFirstName
															+ ' '
															+ message.senderLastName
															+ ' \
						</b> \
						'
															+ message.sentAt
															+ ' \
					</div> \
					<div class="message-text">'
															+ message.text
															+ '\
					</div> \
				</div>';
												});
								messageThread.append += '</div>';
								$("#conversationThread").replaceWith(messageThread);
								div.scrollTop = div.scrollHeight;
							});
				});

$("#replyForm")
		.submit(
				function(event) {

					event.preventDefault();

					var form = $(this);
					var url = form[0].action;
					var reply = form[0][0].value;
					var to = form[0][1].value;

					var posting = $.post(url, {
						"replyMsg.text" : reply,
						"replyMsg.recipient" : to
					});

					posting
							.done(function(response) {
								$("#conversationThread")
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
								div.scrollTop = div.scrollHeight;
							});
				});