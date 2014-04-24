<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<style type="text/css" media="screen">

.mutualfriend {	
	padding: 15px 0px 15px 0px;
	margin:  15px 0px 15px 0px;
	background-color: whitesmoke;
	overflow: auto;
	height: 100%;
}

.mutualfriend-pic {
	float: left;
	padding: 10px;
}

.mutualfriend-actions{
	float: left;
	padding: 10px;
	text-align: center;
	margin:  0px;
}

.mutualfriend-clear {
	content: ".";
    display: block;
    height: 0;
    clear: both;
    visibility: hidden;
}
.mutualfriend-fullname {
	font-size: 16px;
	font-weight: bold;
	padding: 5px;	
}
</style>

    <div class="modal-header">
         <h4 class="modal-title" id="myModalLabel"> <s:text name="global.mutualfriends"/> </h4>
    </div>
	<div class="mutualfriends">
		<s:iterator value="mutualFriendsList" var="mf">
			<s:set var="mutualFriendId" value="%{#mf.friendId}" />
				<%
				  int mutualFriendId = (Integer) pageContext.getAttribute("mutualFriendId");
				%>
			<div class="mutualfriend" id="mutualfriend_<%=mutualFriendId %>">				
				<div class="mutualfriend-pic">
					<a href="/facebook/profile?fref=<s:property value="#mf.friendId" />">
						<img width="60px" src="image?userId=<s:property value="#mf.friendId" />" />
					</a>
				</div>
				<div class="mutualfriend-actions">
					<div class="mutualfriend-fullname">
						<a href="/facebook/profile?fref=<s:property value="#mf.friendId" />">
							<span> 
								<s:property	value="#mf.firstName" /> 
								<s:property	value="#mf.lastName" />
							</span>
						</a>
					</div>
				</div>

			</div>
			<div class="mutualfriend-clear"></div>

		</s:iterator>
	</div>	
	<div class="modal-footer">
    	<button id="CloseMutualFriend" type="button" class="btn btn-default"  data-dismiss="modal"><s:text name="global.close"/></button>
    </div>
    
    <script>
	$(document).ready(function(){
		  $("#CloseMutualFriend").click(function(){
        	$("#mutualFriends").popover('destroy');
        	this.shown = false;
		  });
	});
    </script>
    

