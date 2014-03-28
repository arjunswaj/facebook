<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Friend Suggestions</title>
</head>
<body>
	<h1>People you may know</h1>
	<div id="suggestions">
		<s:iterator value="friendSuggestionsList">
			<div>
				<img width="80px" src="image?userId=<s:property value="friendId" />" />
			</div>		
			<div align=left>
				<span>
						<s:property value="firstName" />
						<s:property value="lastName" /><br>
						<a href="#">Add friend</a>
				</span>
			</div>
		</s:iterator>
	</div>
</body>
</html>