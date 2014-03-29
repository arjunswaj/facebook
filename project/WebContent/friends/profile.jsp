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
	top: 130px;
	z-index: 1;
}

input.friends_checkbox {
	position: absolute;
	left: 520px;
	top: 265px;
	z-index: 0;
	width: 150px;
	height: 30px;
}

input.friends_status {
	position: absolute;
	left: 620px;
	top: 270px;
	z-index: 1;
	width: 150px;
	height: 30px;
}

input.RejectButton {
	position: absolute;
	left: 900px;
	top: 270px;
	z-index: 1;
	width: 150px;
	height: 30px;
}
</style>
</head>
<body>


	<script>
		function addfriend() {

			document.getElementById("friendstatus_form").action = 'addfriend?friendUserId=<s:property value="%{friendUserId}"/>';

		}

		function confirmRequest() {

			document.getElementById("friendstatus_form").action = 'confirmRequest?friendUserId=<s:property value="%{friendUserId}"/>';

		}

		function rejectRequest() {

			document.getElementById("friendstatus_form").action = 'rejectRequest?friendUserId=<s:property value="%{friendUserId}"/>';
		}
	</script>

	<div>
		<img class="cover_pic"
			src="image?userId=<s:property value="%{friendUserId}"/>&picType=cover"
			width="1000px" height="250px" />


	</div>
	<div>
		<img class="profile_pic" width="120px" height="140px"
			src="image?userId=<s:property value="%{friendUserId}"/>" />
	</div>


	<s:form id="friendstatus_form" method="post">



		<s:hidden name="loggedInUserId" value="%{loggedInUserId}"></s:hidden>

		<s:set name="checkFriend" value="requestStatus" />
		<s:if test="%{#checkFriend=='pending'}">
			<input class="friends_status" type="button"
				value="+1 Friend Request Sent" disabled="disabled">
		</s:if>

		<s:elseif test="%{#checkFriend=='accepted'}">

			<s:checkbox name="friends" cssClass="friends_checkbox"
				checked="checked" disabled="true"></s:checkbox>
			<input class="friends_status" type="button" value="Friends"
				size="200" disabled="disabled">
		</s:elseif>
		<s:elseif test="%{#checkFriend=='confirm_request'}">

			<s:submit cssClass="friends_status" value="confirm"
				onclick="confirmRequest(); return true"></s:submit>

			<s:submit cssClass="RejectButton" value="Reject"
				onclick="rejectRequest(); return true"></s:submit>
		</s:elseif>
		<s:elseif test="%{#checkFriend=='add_friend'}">
			<s:submit cssClass="friends_status" value="+1 Add Friend"
				onclick="addfriend(); return true" />
		</s:elseif>
	</s:form>
</body>
</html>