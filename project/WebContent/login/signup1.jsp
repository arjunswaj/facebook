<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facebook_signup</title>
<script>
function validateEmail()
{
var x=document.forms["myForm"]["email"].value;
var y=document.forms["myForm"]["reemail"].value;
var p=document.forms["myForm"]["first_name"].value;
var q=document.forms["myForm"]["last_name"].value;
var r=document.forms["myForm"]["password"].value;

var atpos=x.indexOf("@");
var dotpos=x.lastIndexOf(".");
if (!p)
{
alert("first name cannot be null");
}
if (!q)
	{
	alert("last name cannot be null");
    }
if(!r)
  {
		alert("password cannot be null");
  }
if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
  {
  alert("Not a valid e-mail address");
  return false;
  }
if(x!=y)
{
alert("Email & Re-Email entered were not same");
return false;
}

}
<link rel="stylesheet" href="/css/jquery-ui.css"></link>
<script src="/js/jquery-1.10.2.js"></script>
<script src="/js/jquery-ui.js"></script>
<script>
$(function() {
$( "#datepicker" ).datepicker();
});
</script>

</head>
<body bgcolor="#3b5998">

	<a style="color: #ffffff;"><h1 align="middle">Facebook</h1></a>
	<div style="color: #ffffff;">
		<h1>Sign Up</h1>
		<h4>It’s free and always will be.</h4>

		<s:form action="firstsignup" name="myForm" method="post"
			onsubmit="return validateEmail();" autocomplete="off">

			<s:textfield key="first_name" label="First name" />
			<s:textfield key="last_name" label="Last name" />
			<s:textfield key="email" label="Email Id" />
			<s:textfield key="reemail" label="Re-enter Email Id" />
			<s:password key="password" label="New Password" />
			<s:textfield key="place" label="Place" />
			<s:submit value="Sign Up" />
		</s:form>
	</div>
</body>
</html>