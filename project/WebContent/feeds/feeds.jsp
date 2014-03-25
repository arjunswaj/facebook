<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News Feeds</title>
<style type="text/css" media="screen">
.container {
	margin: 0 50px 0px 50px;
	width: 900px;
}

.left-status {
	float: left;
	margin: 2px;
	padding: 5px;
	font-family: Arial Narrow, sans-serif;
}

.right-status {
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
	font-size: large;
	padding: 5px 0px;
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
</head>
<body>
	<h1>News Feeds</h1>
	<div>
		<s:iterator value="newsFeeds" var="feeds">
			<div class="container">
				<s:if test="#feeds.postType == 'status'">
					<div class="left-status">
						<img width="80px"
							src="image?userId=<s:property value="fromUserId" />" />
					</div>
					<div class="right-status">
						<div>
							<s:property value="fromUserFirstName" />
							<s:property value="fromUserLastName" />
							updated his status
						</div>
						<div class="post">
							<s:property value="postText" />
						</div>
						<div class="timestamp">
							<s:property value="updatedTime" />
						</div>
					</div>
				</s:if>
				<s:elseif test="#feeds.postType == 'wallpost'">
					<div class="left-status">
						<img width="80px"
							src="image?userId=<s:property value="fromUserId" />" />
					</div>
					<div class="right-status">
						<div>
							<s:property value="fromUserFirstName" />
							<s:property value="fromUserLastName" />
							posted on
							<s:property value="toUserFirstName" />
							<s:property value="toUserLastName" />'s Wall
						</div>
						<div class="post">
							<s:property value="postText" />
						</div>
						<div class="timestamp">
							<s:property value="updatedTime" />
						</div>
					</div>
				</s:elseif>
			</div>
			<div class="clear"></div>
		</s:iterator>
	</div>
</body>
</html>