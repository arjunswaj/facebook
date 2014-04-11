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
	<s:if test="%{getFriendSuggestionsList().isEmpty()}">
	</s:if>
	<s:else>
	<div id="suggestions">
		<h5 style="color:gray";" align="center">PEOPLE YOU MAY KNOW</h5>
		<s:iterator value="friendSuggestionsList" var="fsl">
			<s:set var="friendId" value="%{friendId}"/>
			<%
			  int friendId = (Integer) pageContext.getAttribute("friendId");						    
			%>
			<form id="suggFriendForm_<%=friendId %>" class="suggFriendForm" action="addSuggestedFriend" method="post">
			<div class="suggFriendForm" align="center">
				<img width="80px" src="image?userId=<s:property value="friendId" />" />
				<div><a href="profile?fref=<s:property value="friendId" />" >
					<b style="color:#45619d;"><s:property value="firstName" />&nbsp;<s:property value="lastName" /></b>
					</a>
					<br>
					<a style="color:gray;" href="javascript:getMutualFriends(<s:property value="userId"/>,<s:property value="friendId"/>)">Mutual Friends</a>
					<br>
					<input type="submit" class="btn btn-default" id="AddFriend" value="+ Add Friend"/>
<%-- 					<s:submit  align="center" id="AddFriend" value="+ Add Friend" /> --%>
					<s:hidden name="friendId" value="%{friendId}" />
				</div>
			</div>
			</form>
			<hr>
		</s:iterator>
	</div>
	</s:else>
</body>
<%-- <script src="js/jquery-1.10.2.js"></script> --%>
	<script type="text/javascript">	
	$(".suggFriendForm").submit(function(event) {

		/* stop form from submitting normally */
		event.preventDefault();

		/* get some values from elements on the page: */
		var form = $(this); 
		var url = form[0].action;
		var friendId = form[0][1].value;

		/* Send the data using post */
		var posting = $.post(url, {
			"friendId" : friendId			
		});
		
		posting.done(function(addSuggFriendData) {
			if (addSuggFriendData.status == true) {
				form[0][0].value = "Friend Request Sent";
				form[0][0].disabled = true;
			}
		});
	});
		
		
	function getMutualFriends(userId, friendId) {
		var w = 300;
		var h = 600;
		var l = (screen.width/2) - (w/2);
		var t = (screen.height/2) - (h/2);
		if (userId != null && friendId != null) {
			var url="mutualfriends?userId="+userId+"+&friendId="+friendId;
			window.open(url,"_blank",'directories=no, location=no,resizable=no, titlebar=no, status=no, width = ' + w +', height =' + h + ',top = ' + t +',left = ' + l);
		}
	}
	
	$(document).ready(function(){
		  $("#addfriend").click(function(){
		   	$(this).html('Friend Request Sent');
		  });
	});
		
	</script>
</html>