<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	function toggle() {
		if ( document.getElementById("pwdEditBlock").style.display == 'none' ){
			document.getElementById("pwdViewBlock").style.display = 'none';
			document.getElementById("pwdEditBlock").style.display = 'table-row'; // set to table-row instead of an empty string
		 } else{
			document.getElementById("pwdEditBlock").style.display = 'none';
			document.getElementById("pwdViewBlock").style.display = 'inline';
		 }
	}
</script>
<div class="container">
	<div class="page-header" align="left">
		<h4><strong>General Account Settings</strong></h4>
	</div>
	<form action="saveSettings" method="post">
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
        	<div class="col-md-3"><strong>Password</strong></div>
        	<div class="col-md-3" id="pwdViewBlock">Click Edit to Change</div>
         	<div class="col-md-3" id="pwdEditBlock" style="display:none;">
        	<table>
        		<tr>
        			<td><input type="password"></td>
        			<td><input type="submit" value="Submit"></td>
        		</tr>
        	</table>
        	</div>
        	<div class="col-md-3"><a id="EditPassword" href="#" onClick="toggle();">Edit</a></div>
      	</div>
      	<hr>
      	<!--  
   		<div class="row" align="center">
        	<div class="col-md-3"><strong>Language</strong></div>
        	<div class="col-md-3">English</div>
        	<div class="col-md-3"><a href="#">Edit</a></div>
      	</div>
      	<hr>
      	-->
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