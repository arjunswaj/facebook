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

<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
</head>

<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="" bgcolor="#3b5998">
	<img src="images/icon.jpg" />
	<a style="color: #ffffff;" href="login.jsp"><h1 align="middle">Facebook</h1></a>
	<div align="center">
		<s:form action="login" autocomplete="off">

			<s:textfield key="email" label="email" />
			<s:password key="password" label="Password" />

			<s:submit value="login" />
		</s:form>
	</div>


	<p>
	<div align="left">
		<img src="images/like.png" />
	</div>
</body>
</html>
