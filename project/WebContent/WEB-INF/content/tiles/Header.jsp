<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE>
<html>
<head>
<title>Auto Complete in JSP Java</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
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

		document.getElementById("facebookHeader").action = "/facebook/listLatestMessages";
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
					data : {
						name : request.term
					},
					success : function(data) {

						response($.map(data, function(item) {
							return {
								label : item.name,
								value : item.value,
							}
						}));
					},
					error : function(error) {
						alert('error: ' + error);
					}
				});
			},
			minLength : 2
		});
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
							id="names" autocomplete="off" /> <span class="input-group-btn">
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
					<!-- My Friends Button -->
					<button type="button" id="friends" class="btn btn-primary">
						<span class="glyphicon glyphicon-user"></span>
					</button>
					<!-- Messages button -->
					<button type="button" id="messages" class="btn btn-primary"
						onclick="gotoMessages();">
						<span class="glyphicon glyphicon-comment"></span>
					</button>
					<button type="button" id="logout" class="btn btn-primary"
						onclick="logoutEvent();">
						<b>logout</b>
					</button>
				</div>

				<%-- 	<div class="btn-group">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown">
						<span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="#">Settings</a></li>
						<li class="divider"></li>
						<li><a href="#">Logout</a></li>
					</ul>
				</div> --%>



			</div>
		</form>
	</div>
	</div>
</body>
</html>


