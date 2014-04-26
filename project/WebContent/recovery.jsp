<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>password recovery</title>
</head>
<body bgcolor="#3b5998">

	<div style="color: #ffffff;">
		<h1>password recovery</h1>
		<s:form action="myrecoveryAction" id="form1"
			enctype="multipart/form-data" autocomplete="off" method="post">

			<s:textfield key="email" id="email" label="Enter your Email ID"
				onchange="question()" />
			<s:textfield key="secret_question" label="Secret Question" id="qs" readonly="true"></s:textfield>

			<s:textfield key="secret_answer" label="Enter the Secret Answer" />
			<s:submit value="add" />
		</s:form>
	</div>
	<script type="text/javascript">
		function question() {
			document.form1.action = 'getquestion.action';
			document.form1.submit();
		}
	</script>
</body>
</html>