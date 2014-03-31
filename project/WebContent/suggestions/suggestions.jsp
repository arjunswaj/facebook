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
	<script type="text/javascript">
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
		
	</script>
	<h4 align="center">PEOPLE YOU MAY KNOW</h4>
	<div id="suggestions">
		<s:iterator value="friendSuggestionsList">
			<div align="center" >	
				<div >
					<img width="80px" src="image?userId=<s:property value="friendId" />" />
				</div>
				<div >
					<b><s:property value="firstName" /></b>
					<b><s:property value="lastName" /></b>
					<br><a href="javascript:getMutualFriends(<s:property value="userId"/>,<s:property value="friendId"/>)">Mutual Friends</a>
					<br><button type="button" class="btn btn-default"> + Add Friend</button>	
				</div>
			</div>
			<hr>
		</s:iterator>
	</div>
</body>
</html>