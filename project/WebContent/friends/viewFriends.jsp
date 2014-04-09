<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>viewfriends</title>
<style>

.friends {
	position: relative;
	margin: 20px;
	top: 20px;
	left: 50px;
	-moz-column-count:3; /* Firefox */
     column-count:3;
	
}

div.img {
	margin: 5px;
	padding: 5px;
	border: 1px solid #0000ff;
	height: auto;
	width: auto;
	float: left;
	text-align: center;
	-moz-column-count:3;
	/* Firefox */
    column-count:3;
	
}
	
}
/*opacity*/
img {
	opacity: 1.0;
	filter: alpha(opacity = 100);
	/* For IE8 and earlier */
}

img:hover {
	opacity: 0.7;
	filter: alpha(opacity = 100);
	/* For IE8 and earlier */
}

div.img img {
	display: inline;
	margin: 3px;
	border: 1px solid #ffffff;
}

div.desc {
	text-align: center;
	font-weight: normal;
	text-shadow: 5px 5px 5px #FF0000;
	color:#0000FF;
	width: 120px;
	margin: 5px;
}
h1
{
color:blue;
text-align:center;
}

</style>
</head>
<body>
<div class="friends">
<s:iterator value="friendsList" var="a">
<div class="img">
 <a href='profile?fref=<s:property value="%{userId}"/>'> <img align="left" width="120px" height="130px" 
					src="image?userId=<s:property value="%{userId}"/>" alt="klematis"></a>
 <div class="desc"><a href='profile?fref=<s:property value="%{userId}"/>'><s:property value="#a.firstName" /></a></div>
</div>
		
</s:iterator>
	
</div>
</body>
</html>



