<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contacts</title>
</head>
<body>
<s:iterator value="latestMessages">
<li>
<s:property value="sender" />
<s:property value="senderFirstName"/>
<s:property value="senderLastName"/>
<s:property value="text"/>
<s:property value="sentAt"/>
</li>
</s:iterator>
</body>
</html>