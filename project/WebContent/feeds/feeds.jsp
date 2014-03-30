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
</style>

</head>
<body>	
	<h1>News Feeds</h1>
	<div id="status" class="status-container">

		<form id="statusform" action="statusupdate" class="status-form">
			<div>
				<s:textarea name="status" cols="75" rows="5" id ="statusUpdate"
					placeholder="What's on your mind?" style="width: 95%;"/>								
			</div>
			<div style="width: 95%; text-align: right;">
				<s:submit value="Post" />
			</div>
		</form>
	</div>
	<div id="feeds">
		<s:iterator value="newsFeeds" var="feeds">		
			<div class="feed-container">				
				<div class="left-status">
					<img width="80px"
						src="image?userId=<s:property value="fromUserId" />" />
				</div>
				<div class="right-status">
					<s:if test="#feeds.postType == 'status'">
						<div>
							<span class="fullname">
								<s:property value="fromUserFirstName" />
								<s:property value="fromUserLastName" />
							</span>
							updated the status
						</div>

					</s:if>
					<s:elseif test="#feeds.postType == 'wallpost'">
						<div>
							<span class="fullname">
								<s:property value="fromUserFirstName" />
								<s:property value="fromUserLastName" />
							</span>
							posted on
							<span class="fullname">
								<s:property value="toUserFirstName" />
								<s:property value="toUserLastName" />
							</span>'s Wall
						</div>
					</s:elseif>
					<div class="post">
						<s:property value="postText" />
					</div>
					<div class="timestamp">
						<s:property value="updatedTime" />
					</div>
					<div>
						<s:iterator value="#feeds.postComments" var="comments">
							<div class="left-comment">
								<img width="40px"
									src="image?userId=<s:property value="#comments.commenterUserId" />" />
							</div>
							<div class="right-comment">
								<div class="comment-post">
									<span class="fullname">
										<s:property value="#comments.commenterFirstName" /> <s:property value="#comments.commenterLastName" />
									</span>
									<span class="comment-text">
										<s:property value="#comments.commentText" />
									</span>
								</div>
								<div class="timestamp">
									<s:property value="#comments.commentTime" />
								</div>
							</div>
							<div class="clear"></div>
						</s:iterator>
						<s:set var="postId" value="%{postId}"/>
						<%
						  int postId = (Integer) pageContext.getAttribute("postId");						    
						%>
						<form id="commentForm_<%=postId %>" class="comment-form" action="postcomment" method="post">
							<div class="comment-form">
								<div>
									<s:textarea name="comment" cols="57" rows="2"
										placeholder="Post Comment" style="width: 95%;"/>
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


	$(".comment-form").submit(function(event) {

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

			var pic = "<div class='left-comment'>" + "<img width='40px'"
					+ "src='image?userId=" + commentData.userId + "'/>" + "</div>";

			var commentDiv = "<div class='right-comment'>"
					+ "<div class='comment-post'>" + "<span class='fullname'> "
					+ commentData.fullname + "</span> "
					+ " <span class='comment-text'>" + commentData.comment
					+ "</span>" + "</div>" + "<div class='timestamp'>"
					+ commentData.now + "</div>" + "</div>";			
			$("#commentForm_" + commentData.postId).prepend(pic + commentDiv);
			$("#commentForm_" + commentData.postId)[0][0].value = "";
		});
	});

	$("#statusform").submit(
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
					  html.push("<div id=\"feeds\"> ");
					  html.push("      <div class=\"feed-container\">         ");
					  html.push("        <div class=\"left-status\"> ");
					  html.push("          <img width=\"80px\" ");
					  html.push("            src=\"image?userId="+commentData.userId+"\" /> ");
					  html.push("        </div> ");
					  html.push("        <div class=\"right-status\"> ");					  
					  html.push("            <div> ");
					  html.push("              <span class=\"fullname\"> ");
					  html.push(commentData.fullName);					  
					  html.push("              </span> ");
					  html.push("              updated the status ");
					  html.push("            </div> ");
					  html.push(" ");					  
					  html.push("          <div class=\"post\"> ");
					  html.push(commentData.status); 
					  html.push("          </div> ");
					  html.push("          <div class=\"timestamp\"> ");
					  html.push(commentData.now); 
					  html.push("          </div> ");
					  /*
					  html.push("          <div> ");					   
					  html.push("            <form id=\"commentForm_"+commentData.postId+"\" class=\"comment-form\" action=\"postcomment\" method=\"post\"> ");
					  html.push("              <div class=\"comment-form\"> ");
					  html.push("                <div> ");
					  html.push("                  <textarea name=\"comment\" cols=\"57\" rows=\"2\" ");
					  html.push("                    placeholder=\"Post Comment\" style=\"width: 95%;\"></textarea> ");
					  html.push("                  <input type=\"hidden\" name=\"postId\" value=\""+commentData.postId+"\" />                   ");
					  html.push("                </div> ");
					  html.push("                <div style=\"width: 95%; text-align: right;\"> ");
					  html.push("                  <input type=\"submit\" value=\"Post\" /> ");
					  html.push("                </div> ");
					  html.push("              </div> ");
					  html.push("            </form> ");
					  html.push("          </div> ");
					  */
					  html.push("        </div> ");
					  html.push("      </div> ");
					  html.push("      <div class=\"clear\"></div> ");					   
					  html.push("  </div>");
					  					 
					$("#feeds").prepend(html.join(" "));
					$("#statusUpdate")[0].value = "";					
				});
			});
</script>
</html>