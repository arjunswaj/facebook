<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Facebook_signup</title>
</head>
<<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="" style="background-color: #EBEEF5;">
<div
		style="position: absolute; width: 100%; height: 85px; background-color: #3b5998; left: 0px; top: 0px">
		<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<a
			href='login.action'><img src="images/facebook-logo.jpg" alt="Facebook" width="200" height="65"/></a>
			 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			
		<span style="color:white; font-size: 20pt;position: absolute;  left: 1000px; top: 30px">User:<s:property value="#session['user'].firstName" /></span>
		 
	
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<div>
		<h1>Sign Up</h1>
		<h4>It’s free and always will be.</h4>
		<h2>Upload Profile Pic</h2>

		<s:actionerror />
		<s:form action="profilepicupload" method="post" enctype="multipart/form-data"
			autocomplete="off">

			<s:file key="profile" label="upload profile pic" />
			<s:submit value="Upload" />
		</s:form>
		<s:actionerror />
		<s:form action="coverpicupload" method="post" enctype="multipart/form-data"
			autocomplete="off">

			<s:file key="cover" label="upload cover pic" />
			<s:submit value="Upload" />
		</s:form>
		<br>
		<s:form action="thirdsignup" method="post" enctype="multipart/form-data"
			autocomplete="off">


			<s:submit value="PROCEED" />
		</s:form>
	</div>

</body>
</html>