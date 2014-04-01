<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mutual Friends</title>
<style type="text/css" media="screen">
.pop-up {
	float: center;
	margin: 2px;
	padding: 5px;
	font-family: Arial Narrow, sans-serif;
	width: 90%;
}
</style>
</head>
<body>
	<div id="mutualfriends" class="pop-up">
		<b> <h4 align="center">Mutual Friends</h4>  </b>
		<s:iterator value="mutualFriendsList">
			<div align="center" >	
				<div >
				<img width="80px" class="img-thumbnail" src="image?userId=<s:property value="friendId" />" />
				</div>
				<div >
					<strong><s:property value="firstName" /></strong>
					<strong><s:property value="lastName" /></strong>
				</div>
			</div>
			<hr>
		</s:iterator>
	</div>
</body>
</html>