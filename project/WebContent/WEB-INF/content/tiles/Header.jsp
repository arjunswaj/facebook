<!DOCTYPE>
<html>
<head>
<title>Auto Complete in JSP Java</title>
<link rel="stylesheet" href="css/jquery-ui.css">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/jquery-ui.js"></script>
<script>
	function search() {

		document.getElementById("facebookHeader").action = "search";
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

<body>

	<div class="navbar navbar-default">
		<div class="container-fluid">
			<a class="navbar-brand" href="#"><img width="35px"
				src="/facebook/images/icon.jpg"></a>
			<form class="navbar-form" id="facebookHeader" method="post">
				<div class="row">
					<div class="col-lg-6">
						<div class="input-group">
							<input type="text" class="form-control"
								placeholder="Search for people, places and things" name="email"
								id="names" /> <span class="input-group-btn">
								<button class="btn btn-default" type="button"
									onclick="search();">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</div>
					<div class="btn-group">
						<button type="button" class="btn btn-primary">
							<b>User</b>
						</button>
						<button type="button" class="btn btn-primary">
							<b>Home</b>
						</button>
						<button type="button" class="btn btn-primary">
							<span class="glyphicon glyphicon-user"></span>
						</button>
						<button type="button" class="btn btn-primary">
							<span class="glyphicon glyphicon-comment"></span>
						</button>
					</div>

					<div class="btn-group">
						<button type="button" class="btn btn-primary dropdown-toggle"
							data-toggle="dropdown">
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="#">Settings</a></li>
							<li class="divider"></li>
							<li><a href="#">Logout</a></li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</div>
</body>
</html>


