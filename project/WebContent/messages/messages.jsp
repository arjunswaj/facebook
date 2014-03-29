<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Messages</title>
</head>
<script type="text/javascript">
	$.subscribe('beforeReply', function(event, data) {
		var statusfData = event.originalEvent.formData;
		if (!statusfData[0].value) {
			event.originalEvent.options.submit = false;
		}
	});

	$.subscribe('completeReply', function(event, data) {
		location.reload();
	});

	$.subscribe('errorStateReply', function(event, data) {
		alert('status: ' + event.originalEvent.status + '\n\nrequest status: '
				+ event.originalEvent.request.status);
	});
</script>

<body>
	<s:iterator value="messages">
		<b>
		<s:property value="senderFirstName" />
		<s:property value="senderLastName" />
		</b>
		<s:property value="sentAt" />
		<br>
		<s:property value="text" />
		<br>
		<br>
	</s:iterator>

	<s:form id="replyform" action="reply">
		<s:textarea name="reply" value="" cols="75" rows="5"
			placeholder="Write a reply" />
		<s:hidden name="from" value="%{recipient}" />
		<s:hidden name="to" value="%{sender}" />
		<sj:submit targets="replyResult" value="Reply" timeout="25000"
			indicator="replyIndicator" onBeforeTopics="beforeReply"
			onCompleteTopics="completeReply" onErrorTopics="errorStateReply"
			align="right" />
	</s:form>
</body>
</html>