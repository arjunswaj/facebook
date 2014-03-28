<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facebook_signup</title>
</head>
<body bgcolor="#3b5998">

	<a style="color: #ffffff;" href="login.jsp"><h1 align="middle">Facebook</h1></a>
	<div style="color: #ffffff;">
		<h1>Sign Up</h1>
		<h4>It’s free and always will be.</h4>

		<s:form action="signup" method="post" enctype="multipart/form-data"
			autocomplete="off">

			<s:textfield key="first_name" label="first name" />
			<s:textfield key="last_name" label="last name" />
			<s:textfield key="email" label="Email Id" />
			<s:textfield key="reemail" label="re-enter Email Id" />
			<s:password key="password" label="New Password" />
			<s:textfield key="phone_number" label="Phone number" />
			<s:textfield key="place" label="place" />
			<s:textfield key="secret_question" label="Enter the Secret Question" />
			<s:textfield key="secret_answer" label="Enter the Secret Answer" />
			<s:file name="current_profile_pic" label="upload profile pic" />
			<s:file name="current_cover_pic" label="upload cover pic" />
			<s:submit value="Sign Up" />
		</s:form>
	</div>
</body>
</html>