<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facebook_signup</title>
<script>
	function validate() {
		var x = document.forms["myForm"]["secret_question"].value;
		var y = document.forms["myForm"]["secret_answer"].value;
		
		if (!x) {
			alert("secret_question cannot be null");
			return false;
		}
		if (!y) {
			alert("secret_answer cannot be null");
			return false;
		}
	}
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="" style="background-color: #EBEEF5;">

	<div
		style="position: absolute; width: 100%; height: 85px; background-color: #3b5998; left: 0px; top: 0px">
		<br> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<a
			href="login.jsp"><img src="images/facebook-logo.jpg"
			alt="Facebook" width="200" height="65" /></a> &nbsp; &nbsp; &nbsp; &nbsp;
		&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
		<s:property value="first_name" />
	</div>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<h2>Adding security details</h2>
	<br>
	<br>
	<s:form action="secondsignup" name="myForm" onsubmit="validate()"
		enctype="multipart/form-data" autocomplete="off">

		<s:textfield key="phone_number" label="Phone number" />
		<s:textfield key="secret_question" label="Enter the Secret Question" />
		<s:textfield key="secret_answer" label="Enter the Secret Answer" />
		<s:submit value="add" />
	</s:form>
	</div>
</body>
</html>