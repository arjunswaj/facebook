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

.left-likers {
	float: left;
	margin: 2px;	
	font-family: Arial Narrow, sans-serif;
}

.right-likers {
	float: left;
	margin: 2px;
	padding: 5px;
	width: 80%;
	font-family: Arial Narrow, sans-serif;
	top: 0; left: 0; bottom: 0; right: 0;
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
	float: left;
	width: auto;
}

.edit_post {
	padding: 2px;
	text-align: left;
	float: left;
	width: auto;
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
	float: left;
	width: 70%;
}

.status-options {
	float: left;
	width: 30%;
}

.like {
	color: #428bca;
	text-decoration: none;
}

.like:hover {
	color: #428bca;
	text-decoration: none;
	text-decoration: underline;
	cursor: pointer;
}

.liked {
	color: #428bca;
	text-decoration: none;
}

.liked:hover {
	color: #428bca;
	text-decoration: none;
	text-decoration: underline;
	cursor: pointer;
}

.people-who-like {
	color: #428bca;
	text-decoration: none;
}

.people-who-like:hover {
	color: #428bca;
	text-decoration: none;
	text-decoration: underline;
	cursor: pointer;
}

.overlay {
	background-color: rgba(204, 204, 204, 0.5);
	color: #333;
	position: absolute;
	width: 100%;
	z-index: 400;
	height: 100%;
	top: 0px;
	display: none;
}

.show,.show2 {
	width: 50px;
	height: 50px;
	left: 20%;
	top: 30%;
	background-color: #9C9C9C;
	position: absolute;
}

.show2 {
	left: 60%;
	top: 40%;
}

.Absolute-Center {
  width: 90%;
  height: auto;
  overflow: auto;
  margin: auto;
  position: absolute;
  background-color: rgba(204, 204, 204, 0.8);
  top: 0; left: 0; bottom: 0; right: 0;
}

.like-title {
	text-align: center;
	font-size: 18px;
	font-weight: bold;
	padding: 10 px;
}
.likers {
	padding: 10 px;
}
</style>

</head>
<body>
	<div id="Overlay" name="Overlay" class="overlay" onclick="funcClose(this);">		
	</div>
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
							<div class="status-description">
								<span class="fullname"> <s:property
										value="fromUserFirstName" /> <s:property
										value="fromUserLastName" />
								</span> posted on <span class="fullname"> <s:property
										value="toUserFirstName" /> <s:property value="toUserLastName" />
								</span>'s Wall
							</div>
							<div class="status-options"> 
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
							</div>						
							<div class="clear"></div>
						</div>
					</s:elseif>
					<div id='post_<s:property value="#feeds.postId"/>' class="post">
						<s:property value="postText" />
					</div>
					<div class="timestamp">
						<s:property value="updatedTime" />
						<s:if test="#feeds.haveILiked == true">
							<span id='liked_<s:property value="#feeds.postId"/>' class="liked">Liked</span>
						</s:if>
						<s:elseif test="#feeds.haveILiked == false">
							<span id='like_<s:property value="#feeds.postId"/>' class="like">Like</span>
						</s:elseif>
						<span id='post_likers_<s:property value="#feeds.postId"/>' class="people-who-like">
							<s:property value="#feeds.likeCount" />	likes
						</span>
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
										<span id='<s:property value="#comments.commentId"/>' class="liked-comment">Liked</span>
									</s:if>
									<s:elseif test="#comments.haveILiked == false">
										<span id='<s:property value="#comments.commentId"/>' class="like-comment">Like</span>
									</s:elseif>
									<span id='comment_likers_<s:property value="#comments.commentId"/>' class="people-who-like-comment">
										<s:property value="#comments.likeCount" /> likes
									</span>
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
<%-- <script src="js/jquery-1.10.2.js"></script> --%>
<script type="text/javascript">

	function funcShow(_btn) {
	    var ov = $("#Overlay");
	    var pos = $(_btn).offset();
	    var doc = $(document);
	    ov.css({
	        left: pos.left + 'px',
	        top: pos.top + 'px',
	        width: 0,
	        height: 0
	    })
	    .show()
	    .animate({
	        left: pos.left + 'px',
	        top: pos.right + 'px',
	        width: '30%',
	        height: '60%'
	        }, "slow");
	    
	}
	
	function funcClose() {
	   $("#Overlay").hide("slow");
	}

	$(document).on("click", ".like",
			function(event) {		
		var postId = $(this)[0].id.split("_")[1];		
		$.ajax({
            url:  '/facebook/likepost',
            type: 'POST', 
            dataType: 'json',
            data: {
            	"postId" : postId
            },
            success: function(likeData) {
            	$("#post_likers_"+likeData.postId)[0].innerHTML = likeData.likersCount + " likes";
            	$("#like_"+likeData.postId)[0].innerHTML = "Liked";
            	$("#like_"+likeData.postId)[0].className = "liked";
            	$("#like_"+likeData.postId)[0].id = "liked_"+likeData.postId;
            }
        });
	});

	$(document).on("click", ".liked",
			function(event) {
		var postId = $(this)[0].id.split("_")[1];		
		$.ajax({
            url:  '/facebook/unlikepost',
            type: 'POST', 
            dataType: 'json',
            data: {
            	"postId" : postId
            },
            success: function(likeData) {
            	$("#post_likers_"+likeData.postId)[0].innerHTML = likeData.likersCount + " likes";
            	$("#liked_"+likeData.postId)[0].innerHTML = "Like";
            	$("#liked_"+likeData.postId)[0].className = "like";
            	$("#liked_"+likeData.postId)[0].id = "like_"+likeData.postId;
            }
        });
	});
	
	$(document).on("click", ".people-who-like",
			function(event) {
		var postId = $(this)[0].id.split("_")[2];		
		$.ajax({
            url:  '/facebook/postlikers',
            type: 'POST', 
            dataType: 'json',
            data: {
            	"postId" : postId
            },
            success: function(likeData) {
            	var html = [];
            	var postLikers = likeData.likers;
            	$("#post_likers_"+likeData.postId)[0].innerHTML = postLikers.length + " likes";
            	html.push("<div class=\"Absolute-Center\">");
	            	html.push("<span class=\"like-title\">");
		            	if (postLikers.length > 0) {            	
		            		html.push("This post has been liked by following people");				       
		            	} else {
		            		html.push("No one has liked this post yet.");
		            	}
	            	html.push("</span>");
	            	html.push("<div class=\"likers\">");
	            	for (var index = 0; index < postLikers.length; index += 1) {
	            		var user = postLikers[index];
	            		html.push("<div>");
	            			html.push("<div class=\"left-likers\">");
	            				html.push("<img width=\"60px\" src=\"image?userId="+user.userId+"\" />");
	            			html.push("</div>");
	            			html.push("<div class=\"right-likers\">");
		            			html.push("<span>");
		            				html.push(user.firstName);
		            			html.push("</span>");
		            			html.push("<span>");
		        					html.push(user.lastName);
		        				html.push("</span>");
	            			html.push("</div>");
	            			html.push("<div class=\"clear\"></div> ");
	            		html.push("</div>");
	            	}
	            	html.push("</div>");
	            html.push("</div>");
            	$("#Overlay")[0].innerHTML = html.join(" ");
            	funcShow($(event)[0].target);
            }
        });		
			
	});
	
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
										html.push("<span id='like_"+commentData.postId+"' class=\"like\">Like</span>");																												
										html.push("<span id='post_likers_"+commentData.postId+"' class=\"people-who-like\">0 likes</span>");										
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