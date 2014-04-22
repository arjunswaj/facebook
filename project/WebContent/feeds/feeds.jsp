<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>News Feeds</title>
<link rel="stylesheet" type="text/css" href="feeds/feeds.css" />
</head>
<body>
	<div id="Overlay" name="Overlay" class="overlay" onclick="funcClose(this);">		
	</div>	
	<s:set name="loggedInUser" value="userId" />
	<div id="status" class="status-container">

		<form id="statusform" action="statusupdate" class="status-form">
			<div>
				<s:textarea name="status" cols="75" rows="5" id="statusUpdate"
					placeholder="What's on your mind?" style="width: 95%;" />
			</div>
			<div class="status-action">
				<s:submit value="Post" />
			</div>
		</form>
	</div>
	<div id="feeds">
		<s:iterator value="newsFeeds" var="feeds">
			<div id='status_<s:property value="#feeds.postId"/>' class="feed-container">
				<div class="left-status">
					<a href="/facebook/profile?fref=<s:property value="fromUserId" />">
						<img width="80px"
							src="image?userId=<s:property value="fromUserId" />" />
					</a>
				</div>
				<div class="right-status">
					<s:if test="#feeds.postType == 'status'">
						<div class="status-description">
							<a href="/facebook/profile?fref=<s:property value="fromUserId" />">
								<span class="fullname"> <s:property
										value="fromUserFirstName" /> <s:property
										value="fromUserLastName" />
								</span> 
							</a> updated the status 
						</div>
						<div class="status-options">							
							<span> 
								<s:if test="#loggedInUser==#feeds.toUserId">
									<div class="btn-group">
										<button type="button" class="btn dropdown-toggle status-options-button"
											data-toggle="dropdown">
										</button>
										<ul class="dropdown-menu dropdown-menu-right">
											<li>
												<form id='<s:property value="#feeds.postId"/>'
														class="delete_post" method="post">
													<input type="submit" value="Delete..." />
												</form>
											</li>
											<li class="divider"></li>
											<li>
												<form id='<s:property value="#feeds.postId"/>'
														class="edit_post" method="post">
													<input type="submit" value="Edit..." />
												</form>	
											</li>
										</ul>
									</div> 																										
								</s:if>
							</span>
						</div>
						<div class="clear"></div>
					</s:if>
					<s:elseif test="#feeds.postType == 'wallpost'">
						<div>
							<div class="status-description">
								<a href="/facebook/profile?fref=<s:property value="fromUserId" />">
									<span class="fullname"> <s:property
											value="fromUserFirstName" /> <s:property
											value="fromUserLastName" />
									</span> 
								</a>
								posted on
								<a href="/facebook/profile?fref=<s:property value="toUserId" />"> 
									<span class="fullname"> <s:property
											value="toUserFirstName" /> <s:property value="toUserLastName" />
									</span>
								</a>'s Wall
							</div>
							<div class="status-options"> 
								<span> 							
									<s:if test="#loggedInUser==#feeds.toUserId">
										<div class="btn-group">
											<button type="button" class="btn dropdown-toggle status-options-button"
												data-toggle="dropdown">
											</button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li>
													<form id='<s:property value="#feeds.postId"/>'
														class="delete_post" method="post">
														<input type="submit" value="Delete..." />
													</form>
												</li>												
											</ul>
										</div> 											
									</s:if> 
									<s:elseif test="#loggedInUser==#feeds.fromUserId">
										<div class="btn-group">
											<button type="button" class="btn dropdown-toggle status-options-button"
												data-toggle="dropdown">
											</button>
											<ul class="dropdown-menu dropdown-menu-right">
												<li>
													<form id='<s:property value="#feeds.postId"/>'
														class="delete_post" method="post">
														<input type="submit" value="Delete..." />
													</form>
												</li>
												<li class="divider"></li>
												<li>
													<form id='<s:property value="#feeds.postId"/>'
														class="edit_post" method="post">
														<input type="submit" value="Edit..." />
													</form>	
												</li>												
											</ul>
										</div> 																				
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
							<div id='comment_<s:property value="#comments.commentId"/>'>
								<div class="left-comment">
									<a href="/facebook/profile?fref=<s:property value="#comments.commenterUserId" />">
										<img width="40px"
											src="image?userId=<s:property value="#comments.commenterUserId" />" />
									</a>
								</div>
								<div class="right-comment">
									<div class="comment-description">
										<div class="comment-post">
											<a href="/facebook/profile?fref=<s:property value="#comments.commenterUserId" />">
												<span class="fullname"> <s:property
													value="#comments.commenterFirstName" /> <s:property
													value="#comments.commenterLastName" />
												</span>
											</a> 
												<span id='comment_text_<s:property value="#comments.commentId"/>' class="comment-text"> <s:property
													value="#comments.commentText" />
												</span>
										</div>
									</div>
									<div class="comment-options"> 
										<span> 							
											<s:if test="#loggedInUser==#feeds.toUserId && #loggedInUser!=#comments.commenterUserId">
												<div class="btn-group">
													<button type="button" class="btn dropdown-toggle comment-options-button"
														data-toggle="dropdown">
													</button>
													<ul class="dropdown-menu dropdown-menu-right">
														<li>
															<form id='<s:property value="#comments.commentId"/>'
																class="delete_comment" method="post">
																<input type="submit" value="Delete..." />
															</form>
														</li>																									
													</ul>
												</div> 											
											</s:if> 
											<s:elseif test="#loggedInUser==#comments.commenterUserId">
												<div class="btn-group">
													<button type="button" class="btn dropdown-toggle comment-options-button"
														data-toggle="dropdown">
													</button>
													<ul class="dropdown-menu dropdown-menu-right">
														<li>
															<form id='<s:property value="#comments.commentId"/>'
																class="delete_comment" method="post">
																<input type="submit" value="Delete..." />
															</form>
														</li>
														<li class="divider"></li>
														<li>
															<form id='<s:property value="#comments.commentId"/>'
																class="edit_comment" method="post">
																<input type="submit" value="Edit..." />
															</form>	
														</li>												
													</ul>
												</div> 																				
											</s:elseif>								
										</span>
									</div>	
									<div class="timestamp">
										<s:property value="#comments.commentTime" />
										<s:if test="#comments.haveILiked == true">
											<span id='comment_liked_<s:property value="#comments.commentId"/>' class="liked-comment">Liked</span>
										</s:if>
										<s:elseif test="#comments.haveILiked == false">
											<span id='comment_like_<s:property value="#comments.commentId"/>' class="like-comment">Like</span>
										</s:elseif>
										<span id='comment_likers_<s:property value="#comments.commentId"/>' class="people-who-like-comment">
											<s:property value="#comments.likeCount" /> likes
										</span>
									</div>
								</div>
								<div class="clear"></div>
							</div>
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
<script src="feeds/feeds.js"></script>
</html>