<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facebook - <tiles:insertAttribute name="title"
		ignore="true" /></title>

<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">
<style type="text/css" media="screen">
.page-container {
	width: 100%;
}

.central-pane {
	margin: 0px;
	padding: 5px;
	font-family: Arial Narrow, sans-serif;
	width: 100%;
}

.clear {
	height: 0;
	font-size: 1px;
	margin: 0;
	padding: 0;
	line-height: 0;
	clear: both;
}

.link {
	padding: 10px 0px;
	font-style: italic;
}

.header1 {
	width: 10%;
	height: 10%;
}

.page-container1 {
	width: 10%;
	height: 10%;
}

body {
	background-color: #e9eaed;
}
</style>
</head>
<body>
	<div class="header">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="page-container">
		<div class="central-pane">
			<tiles:insertAttribute name="central-pane" />
		</div>
	</div>




	<div class="page-container">
		<tiles:insertAttribute name="profile_menu-pane" />
	</div>


	<div class="clear"></div>



	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<!--     <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.js"></script>

</body>
</html>