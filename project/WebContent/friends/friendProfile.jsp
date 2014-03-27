<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style>
img.cover_pic {
	position: absolute;
	left: 0px;
	top: 0px;
	z-index: 0;
}

img.profile_pic {
	position: absolute;
	left: 40px;
	top: 140px;
	z-index: 1;
}
</style>
</head>
<body>

	<div>
		<img class="cover_pic"
			src="image?userId=<s:property value="%{friendUserId}"/>&picType=cover"
			width="1000px" height="250px" />


	</div>
	<div>
		<img class="profile_pic" width="120px" height="140px"
			src="image?userId=<s:property value="%{friendUserId}"/>" />
	</div>

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<s:set name="checkFriend" value="requestStatus" />
	
	<s:property value="%{#requestStatus}"/>

	<s:if test="%{#checkFriend=='pending'}">
		<input type="button" value="+1 Friend Request Sent"
			disabled="disabled">
	</s:if>

	<s:elseif test="%{#checkFriend=='accepted'}">

		<input type="checkbox" name="vehicle" value="Bike" checked="checked"
			disabled="disabled">
		<input type="button" value="Friends" disabled="disabled">
	</s:elseif>

</body>
</html>