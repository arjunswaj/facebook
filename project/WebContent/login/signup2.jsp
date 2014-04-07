<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facebook_signup</title>
<script>
function validate()
{
var x=document.forms["myForm"]["secret_question"].value;
var y=document.forms["myForm"]["secret_answer"].value;
if (!x)
	{
	alert("secret_question cannot be null");
	return false;
	}
if (!y)
{
alert("secret_answer cannot be null");
return false;
}	
}
</script>
</head>
<body bgcolor="#3b5998">

	<a style="color: #ffffff;" href="login.jsp"><h1 align="middle">Facebook</h1></a>
	<div style="color: #ffffff;">
		<h1>Sign Up</h1>
		<h4>It’s free and always will be.</h4>

		<s:form action="signup2" name="myForm" onsubmit="validate()" enctype="multipart/form-data"
			autocomplete="off">

			<s:textfield key="phone_number" label="Phone number" value="9876"/>
			<s:textfield key="secret_question" label="Enter the Secret Question" value="asdf"/>
			<s:textfield key="secret_answer" label="Enter the Secret Answer" value="asdf"/>
			<s:submit value="add" />
		</s:form>
	</div>
</body>
</html>