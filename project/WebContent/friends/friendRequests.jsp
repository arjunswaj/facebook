<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css" media="screen">

.stranger {	
	padding: 15px 0px 15px 0px;
	margin:  15px 0px 15px 0px;
	background-color: whitesmoke;
	overflow: auto;
	height: 100%;
}

.stranger-pic {
	float: left;
	padding: 10px;
}

.stranger-actions {
	float: left;
	padding: 10px;
	text-align: center;
	margin:  0px;
}

.clear {
	content: ".";
    display: block;
    height: 0;
    clear: both;
    visibility: hidden;
}

.accept-request {
	float: left;
	width: 50%;
}
.reject-request {
	float: left;
	width: 50%;
}

.fullname {
	font-size: 16px;
	font-weight: bold;
	padding: 5px;	
}
</style>
	<div class="strangers">
		<s:iterator value="strangers" var="stranger">
			<s:set var="strangerId" value="%{#stranger.userId}" />
				<%
				  int strangerId = (Integer) pageContext.getAttribute("strangerId");
				%>
			<div class="stranger" id="stranger_<%=strangerId %>">				
				<div class="stranger-pic">
					<a href="/facebook/profile?fref=<s:property value="#stranger.userId" />">
						<img width="60px" src="image?userId=<s:property value="#stranger.userId" />" />
					</a>
				</div>
				<div class="stranger-actions">
					<div class="fullname">
						<a href="/facebook/profile?fref=<s:property value="#stranger.userId" />">
							<span> 
								<s:property	value="#stranger.firstName" /> 
								<s:property	value="#stranger.lastName" />
							</span>
						</a>
					</div>
					<div>
						<form id="accept_request_<%=strangerId%>" class="accept-request"
							action="confirmfriend" method="post">
							<input type ="hidden" name="friendId" id="friendId" value="<%=strangerId%>"/>
							<input type ="submit" value="<s:text name="global.accept"/>" />
						</form>
						<form id="reject_request_<%=strangerId%>" class="reject-request"
							action="rejectfriend" method="post">
							<input type ="hidden" name="friendId" id="friendId" value="<%=strangerId%>"/>
							<input type ="submit" value="<s:text name="global.reject"/>" />
						</form>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</s:iterator>
	</div>	


