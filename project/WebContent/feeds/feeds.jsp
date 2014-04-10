<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News Feeds</title>
<style type="text/css" media="screen">
.status-container {
	width: 88%;
}

.feed-container {
	width: 100%;
	padding: 15px 0px 15px 0px;
}

.left-status {
	float: left;
	width: 20%;
	font-family: Arial Narrow, sans-serif;
}

.right-status {
	float: left;
	width: 75%;
	font-family: Arial Narrow, sans-serif;
}

.left-comment {
	float: left;
	margin: 2px;
	padding: 5px;
	font-family: Arial Narrow, sans-serif;
}

.right-comment {
	float: left;
	margin: 2px;
	padding: 5px;
	width: 80%;
	font-family: Arial Narrow, sans-serif;
}

.timestamp {
	padding: 15px 0px;
	font-size: 12px;
}

.post {
	font-size: 18px;
	padding: 5px 0px;
	width: 88%;
	text-align: justify;
}

.comment-post {
	padding: 3px 0px;
	width: 100%;
	text-align: left;
}

.delete_post {
	padding: 2px;	
	text-align: left;
	float:left;
	width:auto;
}

.edit_post {
	padding: 2px;	
	text-align: left;
	float:left;
	width:auto;
}

.fullname {
	font-size: 16px;
	font-weight: bold;
	padding: 5px 0px;
}

.comment-form {
	width: 90%;
}

.comment-text {
	font-size: 15px;
	padding: 5px 0px;
	text-align: left;
}

.clear {
	height: 0;
	font-size: 1px;
	margin: 0;
	padding: 0;
	line-height: 0;
	clear: both;
}
.status-description {
	float:left; 
	width:70%;
}

.status-options {
	float:left; 
	width:30%;
}
</style>

</head>
<body>
	<h1>News Feeds</h1>

	<s:set name="loggedInUser" value="userId" />
	<div id="status" class="status-container">

		<form id="statusform" action="statusupdate" class="status-form">
			<div>
				<s:textarea name="status" cols="75" rows="5" id="statusUpdate"
					placeholder="What's on your mind?" style="width: 95%;" />
			</div>
			<div style="width: 95%; text-align: right;">
				<s:submit value="Post" />
			</div>
		</form>
	</div>
	<div id="feeds">
		<s:iterator value="newsFeeds" var="feeds">
			<div id='<s:property value="#feeds.postId"/>' class="feed-container">
				<div class="left-status">
					<img width="80px"
						src="image?userId=<s:property value="fromUserId" />" />
				</div>
				<div class="right-status">
					<s:if test="#feeds.postType == 'status'">
						<div class="status-description">
							<span class="fullname"> <s:property
									value="fromUserFirstName" /> <s:property
									value="fromUserLastName" />
							</span> updated the status 
						</div>
						<div class="status-options">
							<span> 
								<s:if test="#loggedInUser==#feeds.toUserId">
									<form id='<s:property value="#feeds.postId"/>'
										class="delete_post" method="post">
										<input type="submit" value="delete" />
									</form>
									<form id='<s:property value="#feeds.postId"/>'
										class="edit_post" method="post">
										<input type="submit" value="edit" />
									</form>									
								</s:if>
							</span>
						</div>
						<div class="clear"></div>
					</s:if>
					<s:elseif test="#feeds.postType == 'wallpost'">
						<div>
							<span class="fullname"> <s:property
									value="fromUserFirstName" /> <s:property
									value="fromUserLastName" />
							</span> posted on <span class="fullname"> <s:property
									value="toUserFirstName" /> <s:property value="toUserLastName" />
							</span>'s Wall 
							<span> 
								<s:if test="#loggedInUser==#feeds.toUserId">
									<form id='<s:property value="#feeds.postId"/>'
										class="delete_post" method="post">
										<input type="submit" value="delete" />
									</form>
								</s:if> 
								<s:elseif test="#loggedInUser==#feeds.fromUserId">
									<form id='<s:property value="#feeds.postId"/>'
										class="delete_post" method="post">
										<input type="submit" value="delete" />
									</form>
									<form id='<s:property value="#feeds.postId"/>'
										class="edit_post" method="post">
										<input type="submit" value="edit" />
									</form>
								</s:elseif>								
							</span>
							<div class="clear"></div>
						</div>
					</s:elseif>
					<div id='post_<s:property value="#feeds.postId"/>' class="post">
						<s:property value="postText" />
					</div>
					<div class="timestamp">
						<s:property value="updatedTime" />
						<s:if test="#feeds.haveILiked == true">
							<span>Liked</span>
						</s:if>
						<s:elseif test="#feeds.haveILiked == false">
							<span>Like</span>
						</s:elseif>
						<s:property value="#feeds.likeCount" />
						likes
					</div>
					<div>
						<s:iterator value="#feeds.postComments" var="comments">
							<div class="left-comment">
								<img width="40px"
									src="image?userId=<s:property value="#comments.commenterUserId" />" />
							</div>
							<div class="right-comment">
								<div class="comment-post">
									<span class="fullname"> <s:property
											value="#comments.commenterFirstName" /> <s:property
											value="#comments.commenterLastName" />
									</span> <span class="comment-text"> <s:property
											value="#comments.commentText" />
									</span>
								</div>
								<div class="timestamp">
									<s:property value="#comments.commentTime" />
									<s:if test="#comments.haveILiked == true">
										<span>Liked</span>
									</s:if>
									<s:elseif test="#comments.haveILiked == false">
										<span>Like</span>
									</s:elseif>
									<s:property value="#comments.likeCount" />
									likes
								</div>
							</div>
							<div class="clear"></div>
						</s:iterator>
						<s:set var="postId" value="%{postId}" />
						<%
							int postId = (Integer) pageContext.getAttribute("postId");
						%>
						<form id="commentForm_<%=postId%>" class="comment-form"
							action="postcomment" method="post">
							<div class="comment-form">
								<div>
									<s:textarea name="comment" cols="57" rows="2"
										placeholder="Post Comment" style="width: 95%;" />
									<s:hidden name="postId" value="%{postId}" />
								</div>
								<div style="width: 95%; text-align: right;">
									<s:submit value="Post" />
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="clear"></div>
		</s:iterator>
	</div>
</body>
<script src="js/jquery-1.9.1.js"></script>
<script type="text/javascript">
	$(document).on("submit", ".comment-form",
			function(event) {

				/* stop form from submitting normally */
				event.preventDefault();

				/* get some values from elements on the page: */
				var form = $(this);
				var url = form[0].action;
				var comment = form[0][0].value;
				var postId = form[0][1].value;

				/* Send the data using post */
				var posting = $.post(url, {
					"comment" : comment,
					"postId" : postId
				});

				/* Put the results in the view */
				posting.done(function(commentData) {

					var html = [];
					html.push("<div class='left-comment'>");
						html.push("<img width='40px'" + "src='image?userId=" + commentData.userId + "'/>");
					html.push("</div>");

					html.push("<div class='right-comment'>");
						html.push("<div class='comment-post'>");
							html.push("<span class='fullname'> " + commentData.fullname + "</span>");
							html.push("<span class='comment-text'>" + commentData.comment + "</span>");
						html.push("</div>");
						html.push("<div class='timestamp'>");
							html.push(commentData.now + " <span>Like</span> <span>0 Likes</span>");
						html.push("</div>");
					html.push("</div>");					
					
					html.push("<div class=\"clear\"></div> ");
					html.push($("#commentForm_" + commentData.postId)[0].outerHTML);
					
					var parent = $("#commentForm_" + commentData.postId).parent();
					$("#commentForm_" + commentData.postId).remove();
					parent.append(html.join(" "));
					//$("#commentForm_" + commentData.postId)[0][0].value = "";
				});
			});

		$(document).on("submit", "#statusform",
					function(event) {

						/* stop form from submitting normally */
						event.preventDefault();

						/* get some values from elements on the page: */
						var form = $(this);
						var url = form[0].action;
						var status = form[0][0].value;

						/* Send the data using post */
						var posting = $.post(url, {
							"status" : status
						});

						/* Put the results in the view */
						posting.done(function(commentData) {
									var html = [];
									//html.push("<div id=\"feeds\"> ");
									html.push("<div id=\""+commentData.postId+"\" class=\"feed-container\">");
										html.push("<div class=\"left-status\">");
											html.push("<img width=\"80px\" ");
											html.push("src=\"image?userId="
												+ commentData.userId + "\" /> ");
										html.push("</div> ");
									html.push("<div class=\"right-status\">");
										html.push("<div>");
											html.push("<div class=\"status-description\">");
												html.push("<span class=\"fullname\"> ");
												html.push(commentData.fullName);
												html.push("</span> ");
												html.push("updated the status ");
												html.push(" ");
											html.push("</div> ");
											html.push("<div class=\"status-options\">");
												html.push("<span><form id='"+commentData.postId+"' class=\"delete_post\" method=\"post\">");
												html.push("<div align=\"right\"><input type=\"submit\"  value=\"delete\">");
												html.push("</div></form><form id='"+commentData.postId+"' class=\"edit_post\" method=\"post\">");
												html.push("<div align=\"right\"><input type=\"submit\"  value=\"edit\"></div></form></span>");
											html.push("</div> ");
											html.push("<div class=\"clear\"></div> ");
										html.push("</div> ");
									html.push("<div id='post_"+commentData.postId+"' class=\"post\"> ");
										html.push(commentData.status);
									html.push("</div>");
									html.push("<div class=\"timestamp\">");
										html.push(commentData.now);
										html.push("<span>Like</span> <span>0 Likes</span>");
									html.push("</div>");
									html.push("<div>");					   
										html.push("<form id=\"commentForm_"+commentData.postId+"\" class=\"comment-form\" action=\"postcomment\" method=\"post\"> ");
											html.push("<div class=\"comment-form\">");
												html.push("<div> ");
													html.push("<textarea name=\"comment\" cols=\"57\" rows=\"2\" ");
													html.push("placeholder=\"Post Comment\" style=\"width: 95%;\"></textarea> ");
													html.push("<input type=\"hidden\" name=\"postId\" value=\""+commentData.postId+"\" />");
												html.push("</div> ");
												html.push("<div style=\"width: 95%; text-align: right;\"> ");
													html.push("<input type=\"submit\" value=\"Post\" /> ");
												html.push("</div> ");
											html.push("</div> ");
										html.push("</form> ");
									html.push("</div> ");
									html.push("</div> ");
									html.push("</div> ");
									html.push("<div class=\"clear\"></div> ");
									//html.push("  </div>");

									$("#feeds").prepend(html.join(" "));
									$("#statusUpdate")[0].value = "";

								});
					});

		
	$(document).on("submit", ".delete_post",
		function(event) {

		event.preventDefault();
		var answer = confirm("Are you sure you want to delete this post ?");
		if (answer) {

			var url = "deletePostAction";
			var postidvalue = $(this).attr("id");

			var posting = $.post(url, {
				"postId" : postidvalue
			});

			posting.done(function(data) {

				if (data.value == "true") {

					$("div[id='" + postidvalue + "']").remove();
				}

			});

		}

		return false;
	});

	$(document).on("submit", ".edit_post", function(event) {

		event.preventDefault();

		var url = "getPostAction";
		var postidvalue = $(this).attr("id");

		var posting = $.post(url, {
			"postId" : postidvalue
		});

		posting.done(function(data) {

			var text = prompt("edit the post", data.postText);

			if (text != null) {
				
				

				var otherusrl = "editPostAction";
				
				var newPosting = $.post(otherusrl, {
					"postId" : data.postId,
					"postText" : text
				});
				
				newPosting.done(function(newdata) {

					if(newdata.value="true")
					document.getElementById("post_" + data.postId).innerHTML =text;

				});

				
			}

		});

	});
</script>
</html>