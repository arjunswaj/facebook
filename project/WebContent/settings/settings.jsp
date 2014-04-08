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
        	<div class="col-md-3">Click Edit to Change</div>
        	<div class="col-md-3"><a href="#">Edit</a></div>
      	</div>
      	<hr>
   		<div class="row" align="center">
        	<div class="col-md-3"><strong>Language</strong></div>
        	<div class="col-md-3">English</div>
        	<div class="col-md-3"><a href="#">Edit</a></div>
      	</div>
      	<hr>
      	<h4>List of Blocked Users</h4>
	</form>

</div>
</body>
</html>