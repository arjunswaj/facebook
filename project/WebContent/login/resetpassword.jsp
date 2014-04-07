<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facebook_Password_reset</title>
<script>
function validatepassword()
{
var x=document.forms["myForm"]["password"].value;
var y=document.forms["myForm"]["repassword"].value;
var z=document.forms["myForm"]["email"];

if(x!=y)
{
alert("password & Re-entered password were not same. so, please re-enter it");
}
else
	z.disabled=false;

}

</script>
</head>
<body bgcolor="#3b5998">

	<a style="color: #ffffff;"><h1 align="middle">Facebook</h1></a>
	<div style="color: #ffffff;">
		<h1>Sign Up</h1>
		<h4>It’s free and always will be.</h4>
<h1>Password Resetting</h1>
		
		<s:form action="pswdreset" name="myForm" method="post"
			onsubmit="return validatepassword();" autocomplete="off">
			<s:textfield name="email" label="For your email " disabled="true"/>
			<s:password key="password" label="Enter the New Password" />
			<s:password key="repassword" label="Re-Enter the Password" />
			<s:submit value="Change" />
		</s:form>
	</div>
</body>
</html>