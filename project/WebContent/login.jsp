<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facebook_login</title>
<script type="text/css">

</script>
</head>
<body bgcolor="#3b5998">
<img src="/images/facebook_icon.jpg"/>
<a style="color:#ffffff;" href="login.jsp"><h1 align="middle">Facebook</h1></a>
	<div align="center">
		<s:form action="login" autocomplete="off">

			<s:textfield key="email" label="email" />
			<s:password key="password" label="Password" />

			<s:submit value="login"/>
		</s:form>
	</div>
</body>
</html>
