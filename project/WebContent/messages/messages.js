/**
 * 
 */
$("#replyForm").submit(function(event) {
	event.preventDefault();
			
	var form = $(this);
	var url = form[0].action;
	var reply = form[0][0].value;
			
	var posting = $.post(url, {
		"reply" : reply
	});
			
	posting.done(function(replyData) {
		var html[];
					
		html.push('<div id="messageThread" class="message-thread">');
		html.push('<s:iterator value="messages">');
		html.push('<div id="message" class="message">');
		html.push('<div id="messageThreadPhoto" class="message-photo">');
		html.push('<img width="100%" height="100%"');
		html.push('src="image?userId=<s:property value="sender" />" />');
		html.push('</div>');
		html.push('<div style="width: 90% float: left">');
		html.push('<b> <s:property value="senderFirstName" /> <s:property value="senderLastName" />');
		html.push('</b>');
		html.push('<s:property value="sentAt" />');
		html.push('<br>');
		html.push('</div>');
		html.push('<div style="width: 90% float: left">');
		html.push('<s:property value="text"/>');
		html.push('<br> <br>');
		html.push('</div>');
		html.push('</div>');
		html.push('</s:iterator>');
		html.push('</div>');
	});
});