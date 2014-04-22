<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE>
<html>
<head>
<title>Auto Complete in JSP Java</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<style>
	.popover {
		 width: 500px; 
		 height: 350px;
		 overflow: auto;
	 }
</style>
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
<script type="text/javascript">

$(document).on('click', '.accept-request',
		function(event) {

			event.preventDefault();			
			var form = $(this);
			var url = form[0].action;
			var friendId = form[0][0].value;		
			
			var posting = $.post(url, {
				"friendId" : friendId
			});
				
			posting.done(function(data) {
				if (data) {
					$("div[id='stranger_" + friendId + "']").remove();
				}
			});
});

$(document).on('click', '.reject-request',
		function(event) {
			event.preventDefault();			
			var form = $(this);
			var url = form[0].action;
			var friendId = form[0][0].value;				
			var posting = $.post(url, {
					"friendId" : friendId
			});
				
			posting.done(function(data) {
				if (data) {
						$("div[id='stranger_" + friendId + "']").remove();
				}
			});
});
</script>
<script>
	function gotoProfile() {

		document.getElementById("facebookHeader").action = "/facebook/profile";
		document.getElementById("facebookHeader").submit();
	}
	function gotoNewsFeeds() {

		document.getElementById("facebookHeader").action = "/facebook/newsfeeds";
		document.getElementById("facebookHeader").submit();
	}
	function gotoMessages() {

		document.getElementById("facebookHeader").action = "/facebook/inbox";
		document.getElementById("facebookHeader").submit();
	}
	function search() {

		document.getElementById("facebookHeader").action = "search";
		document.getElementById("facebookHeader").submit();
	}

	function logoutEvent() {
		document.getElementById("facebookHeader").action = "logout";
		document.getElementById("facebookHeader").submit();

	}
	
	$(function() {
		$("#names").autocomplete({
			source : function(request, response) {
				$.ajax({
					url : "getdata.jsp",
					type : "POST",
					dataType : "json",
					cache : false,
					data : {
						name : request.term
					},
					success : function(data) {

						response($.map(data, function(item) {

							return {

								label : item.name,
								value : item.userid
							}
						}));
					},
					error : function(error) {
						alert('error: ' + error);
					}
				});
			},
			minLength : 2,
			select : function(event, ui) {
				event.preventDefault();
				$(this).val(ui.item.label);
				$("#fref").val(ui.item.value);
			}
		});
	});
		
	$(document).on("click", "#friendReq", function(e) {
        if (!this.shown) {              
	        $.ajax({
	            url: '/facebook/friendrequests',
	            data: {},
	            dataType: 'html',
	            success: function(html) {
	            	$("#friendReq").popover({
	                    title: 'Friends Requests',
	                    content: html,
	                    placement: 'bottom',
	                    html: true                    
	                }).popover('show');
	            }
	        });
	        this.shown = true;
        } else {
        	$("#friendReq").popover('destroy');
        	this.shown = false;
        }
    });	
	
</script>
</head>

<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
	<div class="navbar navbar-default">
		<!-- facebook icon -->
		<a class="navbar-brand" href="#"><img width="35px"
			src="/facebook/images/icon.jpg"></a>

		<form class="navbar-form" id="facebookHeader" method="post">
			<!-- All elements in a single row -->
			<div class="row">

				<div class="col-lg-6">
					<div class="input-group">
						<input type="text" class="form-control"
							placeholder="Search for people, places and things" name="email"
							id="names" autocomplete="off" /> <input type="text" id="fref"
							name="fref" hidden="true" /> <span class="input-group-btn">
							<button class="btn btn-default" type="button" onclick="search();">
								<span class="glyphicon glyphicon-search"></span>
							</button>
						</span>
					</div>
					<!-- end of div  input-group -->
				</div>
				<!-- end of col-lg-6 -->


				<div class="btn-group">
					<!-- Profile Button -->
					<button type="button" id="profile" class="btn btn-primary"
						onclick="gotoProfile();">
						<b><s:property value="#session['user'].firstName" /></b>
					</button>
					<!-- Home Button -->
					<button type="button" id="home" class="btn btn-primary"
						onclick="gotoNewsFeeds();">
						<b>Home</b>
					</button>
					<!-- My Friends Request -->
					<a id="friendReq" class="btn btn-primary" rel="popover" >
						<span class="glyphicon glyphicon-user"></span>						
					</a>
					
					<!-- Messages button -->
					<button type="button" id="messages" class="btn btn-primary"
						onclick="gotoMessages();">
						<span class="glyphicon glyphicon-comment"></span>
					</button>
					
					<!--  
					<button type="button" id="logout" class="btn btn-primary"
						onclick="logoutEvent();">
						<b>logout</b>
					</button>
				
				-->
					</div>
					<div class="btn-group">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="accountSettings">Settings</a></li>
						<li class="divider"></li>
						<li><a href="logout">Logout</a></li>
					</ul>
				</div> 



			</div>
		</form>
	</div>
	</div>
</body>
</html>


