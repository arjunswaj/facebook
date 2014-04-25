<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Locale" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>General Account Settings</title>
<style>
hr {
background-color:#b3acac;
border-width:0;
height:1px;
line-height:0;
width:100%;
}
body {background-color:#ffffff;} 
</style>
</head>
<body>
<script type="text/javascript">
	function unblockfriend(obj) {
		fref = obj.value;
		document.getElementById("unblockFriendForm_"+fref).action = 'unblockfriend?fref='+fref;
	}
</script>
<div class="container">
	<div class="page-header" align="left">
		<h4><strong>General Account Settings</strong></h4>
	</div>
	<form action="updateSettings" method="post">
		<hr>
		<div class="row" align="center">
        	<div class="col-md-3"><strong>Name</strong></div>
        	<div class="col-md-3"><s:property value="#session['user'].firstName" />&nbsp;<s:property value="#session['user'].lastName" /></div>
        	<div class="col-md-3"><a href="#"></a></div>
      	</div>
      	<hr>
 		<div class="row" align="center">
        	<div class="col-md-3"><strong>Email</strong></div>
        	<div class="col-md-3"><s:property value="#session['user'].email" /></div>
        	<div class="col-md-3"><a href="#"></a></div>
      	</div>
      	<hr>
      	<div class="row" align="center">
      	<div class="col-md-3"><strong>Language</strong></div>
      	<div class="col-md-3">
			<select name="localeCode" id="localeCode"> 
			    <option value="en_US">Select Your Preference</option> 
			    <option value="en_US">English (United States)</option> 
			    <option value="fr_FR">French (France)</option> 
			</select>
		</div>
		<div>
			<input style="color:black;" type="submit" value="Submit"></input>
			<a href="accountSettings" ><input style="color:black;"  type="button" value="Cancel"></input></a>
		</div>
		
		</div>
		<hr>
	</form>
	
	<h4>List of Blocked Users</h4>
	<hr>
	<div id="blockedfriends">
		<s:iterator value="blockedFriendsList" var="bfl">
		<s:set var="friendId" value="%{friendId}"/>
		<%
			int friendId = (Integer) pageContext.getAttribute("friendId");						    
		%>
		<form id="unblockFriendForm_<%=friendId %>" method="post">
			<b><s:property value="firstName" /></b>
			<b><s:property value="lastName" /></b>
			<input type="submit" value="Unblock" onclick="unblockfriend(friendId);"/>
			<s:hidden name="friendId" value="%{friendId}" />
		</form>
		<hr>
		</s:iterator>
	</div>
	
</div>

</body>
</html>