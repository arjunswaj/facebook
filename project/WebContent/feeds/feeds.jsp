<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sj:head jqueryui="true"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News Feeds</title>
<style type="text/css" media="screen">
.status-container {	
	width: 88%;
}
.feed-container {	
	width: 100%;
	padding: 15px 0px 15px 0px;
}

.left-status {
	float: left;
	width: 20%;
	font-family: Arial Narrow, sans-serif;
}

.right-status {
	float: left;
	width: 75%;
	font-family: Arial Narrow, sans-serif;
}

.left-comment {
	float: left;
	margin: 2px;
	padding: 5px;
	font-family: Arial Narrow, sans-serif;
}

.right-comment {
	float: left;
	margin: 2px;
	padding: 5px;
	font-family: Arial Narrow, sans-serif;
}

.timestamp {
	padding: 15px 0px;
	font-size: x-small;
}

.post {
	font-size: 18px;
	padding: 5px 0px;
	width: 88%;
	text-align: justify;
}

.comment-post {	
	padding: 3px 0px;
	width: 100%;
	text-align: left;
}

.fullname {
	font-size: 16px;
	font-weight: bold;
	padding: 5px 0px;
}
.comment-form {
	width: 90%;	
}
.comment-text {
	font-size: 15px;
	padding: 5px 0px;
	text-align: left;
}
.clear {
	height: 0;
	font-size: 1px;
	margin: 0;
	padding: 0;
	line-height: 0;
	clear: both;
}
</style>

<script type="text/javascript">	

	$.subscribe('beforeStatus', function(event, data) {
		var statusfData = event.originalEvent.formData;
		var statusForm = event.originalEvent.form[0];		
		if (!statusfData[0].value) {
			event.originalEvent.options.submit = false;
		}
	});

	$.subscribe('completeStatus', function(event, data) {
		location.reload();
	});

	$.subscribe('errorStateStatus', function(event, data) {
		alert('status: ' + event.originalEvent.status + '\n\nrequest status: '
				+ event.originalEvent.request.status);
	});
</script>


</head>
<body>	
	<h1>News Feeds</h1>
	<div id="status" class="status-container">

		<s:form id="statusform" action="statusupdate" theme="simple"
			cssClass="yform">
			<div>
				<s:textarea name="status" cols="75" rows="5"
					placeholder="What's on your mind?" />
				<s:hidden name="userId" value="%{userId}" />				
			</div>
			<div style="width: 100%; text-align: right;">
				<sj:submit targets="statusResult" value="Post" timeout="2500"
					indicator="statusIndicator" onBeforeTopics="beforeStatus"
					onCompleteTopics="completeStatus" onErrorTopics="errorStateStatus"
					align="right" />
			</div>
		</s:form>
	</div>
	<div id="feeds">
		<s:iterator value="newsFeeds" var="feeds">		
			<div class="feed-container">				
				<div class="left-status">
					<img width="80px"
						src="image?userId=<s:property value="fromUserId" />" />
				</div>
				<div class="right-status">
					<s:if test="#feeds.postType == 'status'">
						<div>
							<span class="fullname">
								<s:property value="fromUserFirstName" />
								<s:property value="fromUserLastName" />
							</span>
							updated his status
						</div>

					</s:if>
					<s:elseif test="#feeds.postType == 'wallpost'">
						<div>
							<span class="fullname">
								<s:property value="fromUserFirstName" />
								<s:property value="fromUserLastName" />
							</span>
							posted on
							<span class="fullname">
								<s:property value="toUserFirstName" />
								<s:property value="toUserLastName" />
							</span>'s Wall
						</div>
					</s:elseif>
					<div class="post">
						<s:property value="postText" />
					</div>
					<div class="timestamp">
						<s:property value="updatedTime" />
					</div>
					<div>
						<s:iterator value="#feeds.postComments" var="comments">
							<div class="left-comment">
								<img width="40px"
									src="image?userId=<s:property value="#comments.commenterUserId" />" />
							</div>
							<div class="right-comment">
								<div class="comment-post">
									<span class="fullname">
										<s:property value="#comments.commenterFirstName" /> <s:property value="#comments.commenterLastName" />
									</span>
									<span class="comment-text">
										<s:property value="#comments.commentText" />
									</span>
								</div>
								<div class="timestamp">
									<s:property value="#comments.commentTime" />
								</div>
							</div>
							<div class="clear"></div>
						</s:iterator>
						<s:form id="formevent_%{postId}" action="postcomment"
							theme="simple" cssClass="yform">
							<div class="comment-form">
								<div>
									<s:textarea name="comment" cols="57" rows="2"
										placeholder="Post Comment" />
									<s:hidden name="postId" value="%{postId}" />
									<s:hidden name="userId" value="%{userId}" />
								</div>
								<div style="width: 100%; text-align: right;">
									<sj:submit targets="result" value="Post" timeout="2500"
										indicator="indicator" onBeforeTopics="before"
										onCompleteTopics="complete" onErrorTopics="errorState"
										align="right" />
								</div>
							</div>
						</s:form>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</s:iterator>
	</div>
</body>
<script type="text/javascript">	

	$.subscribe('before', function(event, data) {
		var fData = event.originalEvent.formData;
		var form = event.originalEvent.form[0];
		if (!fData[0].value) {
			event.originalEvent.options.submit = false;
		}
	});

	$.subscribe('complete', function(event, data) {
		var commentData = JSON.parse(event.originalEvent.request.responseText);

		var pic = "<div class='left-comment'>" + "<img width='40px'"
				+ "src='image?userId=" + commentData.userId + "'/>" + "</div>";

		var commentDiv = "<div class='right-comment'>" + "<div class='post'>"
				+ "<span class='fullname'> " + commentData.fullname
				+ "</span> " + " <span class='comment-text'>"
				+ commentData.comment + "</span>" + "</div>"
				+ "<div class='timestamp'>" + commentData.now + "</div>"
				+ "</div>";
		$("#formevent_" + commentData.postId).prepend(pic + commentDiv);
		$("#formevent_" + commentData.postId)[0][0].value = "";
	});

	$.subscribe('errorState', function(event, data) {
		alert('status: ' + event.originalEvent.status + '\n\nrequest status: '
				+ event.originalEvent.request.status);
	});
</script>
</html>