<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.cover_pic {
	position: relative;
	margin: 10px;
	width: 100%;
	z-index:-1;
}
.profile_pic {
	position: relative;
	margin: 10px;
	top:-100px;
	left: 50px;
}
.friends_status {
	position: relative;
	top:-200px;
	left: 750px;
	z-index: 1;
	width: 200px;
	height: 30px;
}
</style>
</head>
<body>
	<script>
		function addfriend() {
			document.getElementById("friendstatus_form").action = 'addfriend?fref=<s:property value="%{fref}"/>';
		}
		function blockfriend() {
			document.getElementById("friendstatus_form").action = 'blockfriend?fref=<s:property value="%{fref}"/>';
		}

		function confirmRequest() {
			document.getElementById("friendstatus_form").action = 'confirmRequest?fref=<s:property value="%{fref}"/>';
		}

		function rejectRequest() {
			document.getElementById("friendstatus_form").action = 'rejectRequest?fref=<s:property value="%{fref}"/>';
		}

	</script>
	
	<div class="container">
		<img class="cover_pic" height="350px" src="image?picType=cover&userId=<s:property value="fref" />" />
		<img class="profile_pic" width="120px" height="140px" src="image?userId=<s:property value="fref" />" />
	</div>
	
	<s:form id="friendstatus_form" method="post">
		<s:set name="checkFriend" value="requestStatus" />
		<!-- Button for Pending status -->
		<s:if test="%{#checkFriend=='pending'}">
			<input class="friends_status" type="button" value="+1 Friend Request Sent" disabled="disabled">
		</s:if>
		<!-- Button for Accepted status -->
		<s:elseif test="%{#checkFriend=='accepted'}">
			<%-- <s:checkbox name="friends" cssClass="friends_checkbox" checked="checked" disabled="true"></s:checkbox> --%>
			<input class="friends_status" type="button" value="Friends" disabled="disabled">
			<input class="friends_status" type="submit" value="Block" onclick="blockfriend();">
		</s:elseif>
		<!-- Button for Confirm/Reject status -->
		<s:elseif test="%{#checkFriend=='confirm_request'}">
			<input class="friends_status" type="submit" value="Confirm" onclick="confirmRequest();">
			<input class="friends_status" type="submit" value="Reject" onclick="rejectRequest();">
		</s:elseif>
		<!-- Button for Adding a friend -->
		<s:elseif test="%{#checkFriend=='add_friend'}">
			<input class="friends_status" type="submit" value="+1 Add Friend" onclick="addfriend();" />
		</s:elseif>
	</s:form>
</body>
</html>