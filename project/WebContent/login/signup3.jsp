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
		<h2>Upload Profile Pic</h2>

		<s:actionerror />
		<s:form action="signup3.1" method="post" enctype="multipart/form-data"
			autocomplete="off">

			<s:file name="current_profile_pic" label="upload profile pic" />
			<s:submit value="Upload" />
		</s:form>
		<s:actionerror />
		<s:form action="signup3.2" method="post" enctype="multipart/form-data"
			autocomplete="off">

			<s:file name="current_profile_pic" label="upload cover pic" />
			<s:submit value="Upload" />
		</s:form>
		<br>
		<s:form action="signup3" method="post" enctype="multipart/form-data"
			autocomplete="off">


			<s:submit value="PROCEED" />
		</s:form>
	</div>
</body>
</html>