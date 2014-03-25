<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News Feeds</title>
</head>
<body>
	<h1>News Feeds</h1>
	<table border="1" style="margin: 0px auto">
		<thead>
			<tr>
				<td>From</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>To</td>
				<td>First Name</td>
				<td>Last Name</td>
				<td>Post</td>
				<td>Type</td>
				<td>Time</td>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="newsFeeds" status="feeds">
				<tr>
					<td><s:property value="fromUserId" /></td>
					<td><s:property value="fromUserFirstName" /></td>
					<td><s:property value="fromUserLastName" /></td>
					<td><s:property value="toUserId" /></td>
					<td><s:property value="toUserFirstName" /></td>
					<td><s:property value="toUserLastName" /></td>
					<td><s:property value="postText" /></td>
					<td><s:property value="postType" /></td>
					<td><s:property value="updatedTime" /></td>
				</tr>

			</s:iterator>
		</tbody>
	</table>
</body>
</html>