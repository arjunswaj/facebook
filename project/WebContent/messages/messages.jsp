<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Messages</title>
</head>
<body>
<s:iterator value="messages">
<li>
<s:property value="text"/>
<s:property value="sentAt"/>
</li>
</s:iterator>

<s:form action="reply">
<s:textarea name="reply"  value="" cols="75" rows="5" placeholder="Write a reply..."/>
<s:hidden name="from" value="%{recipient}" />
<s:hidden name="to" value="%{sender}"/>
<s:submit value="Reply"/>
</s:form>
</body>
</html>