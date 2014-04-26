<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<sj:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>About</title>
<link href="css/bootstrap.css" rel="stylesheet">
<style type="text/css" media="screen">
table {
	border: none;
	border-collapse: collapse;
}

td {
	border: none;
}

th {
	border: none;
}

p {
	background-color: #eeeff4;
}

p.padding {
	padding-top: 25px;
	padding-bottom: 25px;
	padding-right: 50px;
	padding-left: 40px;
	border-color: #eeeff4;
}

button.padding {
	float: right;
}

p.padding {
	padding-top: 25px;
	padding-bottom: 25px;
	padding-right: 50px;
	padding-left: 40px;
	border-color: #eeeff4;
}

b.r {
	font-size: 25px;
	padding-right: 800px;
}

div.box1 {
	padding-top: 30px;
	padding-left: 40px;
}

td.a {
	color: blue;
	font-size: 14px;
}

td.b {
	color: grey;
	padding-left: 0px;
	text-align: left;
	font-size: 12px;
}

b.a {
	color: #3b5999;
	font-size: 16px;
}

b.b {
	color: grey;
	padding-left: 0px;
	text-align: left;
	font-size: 14px;
}

b.c {
	color: black;
	padding-left: 0px;
	text-align: left;
	font-size: 12px;
}

td.c {
	color: black;
	padding-left: 0px;
	text-align: left;
	font-size: 12px;
}

th.p {
	padding-bottom: 12px;
	font-size: 20px;
}

td.n {
	padding-left: 4px;
	text-align: left;
}

img.loc {
	height: 80px;
	width: 80px;
}

img.wne {
	height: 40px;
	width: 40px;
}

b.q {
	font-size: 25px;
	padding-right: 800px;
}

label.labelcolor {
	color: grey;
}

div.padding {
	padding-top: 30px;
	padding-bottom: 35px;
	padding-right: 50px;
	padding-left: 40px;
	border-color: #eeeff4;
	border-style: solid;
	background-color: #eeeff4;
	border-width: 3px
}

b.new {
	font-size: 16px;
	color: grey;
}

table.tablenew {
	padding-top: 0px;
}
</style>
</head>
<body>

	<div class="row" style="background-color: #f7f7f7">
		<div class="col-md-1" align="left"></div>
		<div class="col-md-10" align="left" style="background-color: white">
			<div class="padding">
				<table>
					<tr>
						<td><b class="q">Edit Information</b></td>

					</tr>
				</table>

			</div>


			<div class="col-md-6" align="left" style="background-color: #FFFFFF">
				<br /> <br />
				<div id="workinformation" class="workform-group">
					<form id="workinfo" action="submitabout" method="post">
						<input type="text" id="test" class="form-control" name="wrkplace"
							placeholder="Add Your Workplace here"> <br /> <input
							type="text" class="form-control" name="role"
							placeholder="Add Your role here"> <br /> <label
							class="labelcolor">Joining Date</label> <br />
						<sj:datepicker id="1" name="joindate" displayFormat="yy-mm-dd" />
						<br /> <br /> <label class="labelcolor">Last working Date</label>
						<br />
						<sj:datepicker id="2" name="leftdate" displayFormat="yy-mm-dd" />
						<br /> <br />
						<button type="submit" class="btn btn-small btn-primary">
							Add <span class="fa fa-arrow-right"></span>
						</button>
					</form>
				</div>

				<br /> <br />

				<div id="collegeinformation" class="collegeform-group">
					<form id="collegeinfo" action="submitabout" method="post">
						<input type="text" class="form-control" name="collegename"
							placeholder="Add Your college here"> <br /> <input
							type="text" class="form-control" name="degree"
							placeholder="Add Your degree here"> <br /> <label
							class="labelcolor">College Joining Date</label> <br />
						<sj:datepicker id="3" name="collegejoindate"
							displayFormat="yy-mm-dd" />
						<br /> <br /> <label class="labelcolor">College Leaving
							Date</label> <br />
						<sj:datepicker id="4" name="collegeleftdate"
							displayFormat="yy-mm-dd" />
						<br /> <br />
						<button type="submit" class="btn btn-small btn-primary">
							Add <span class="fa fa-arrow-right"></span>
						</button>
					</form>
					<br />
				</div>

				<br /> <br />

				<div id="schoolinformation" class="schoolform-group">
					<form id="schoolinfo" action="submitabout" method="post">
						<input type="text" class="form-control" name="schoolname"
							placeholder="Add Your school here"> <br /> <input
							type="text" class="form-control" name="standard"
							placeholder="what did you study there?"> <br /> <label
							class="labelcolor">School Joining Date</label> <br />
						<sj:datepicker id="5" name="schooljoindate"
							displayFormat="yy-mm-dd" />
						<br /> <br /> <label class="labelcolor">School Left Date</label> <br />
						<sj:datepicker id="6" name="schoolleftdate"
							displayFormat="yy-mm-dd" />
						<br /> <br />
						<button type="submit" class="btn btn-small btn-primary">
							Add <span class="fa fa-arrow-right"></span>
						</button>
					</form>
					<br />
				</div>

				<br /> <br />
				<table>
					<tr>
						<th class="p">Relationship</th>
					</tr>
				</table>
				<div id="relation" class="relationgroup">

					<h5>
						<form id="relationships" action="submitabout" method="post">
							<b class="new">Add You Relationship</b> &nbsp
							<s:select id="rel"
								list="#{'Single':'Single', 'Committed':'Committed', 'Married':'Married', 'Divorced':'Divorced', 'Engaged':'Engaged'}"
								name="relationship" value="%{userInfo.relationship}" />
							<br /> <br />
							<button type="submit" class="btn btn-small btn-primary">
								Save <span class="fa fa-arrow-right"></span>
							</button>
						</form>
					</h5>
				</div>
			</div>
			<div class="col-md-1" align="left"></div>
			<div class="col-md-4" align="left">


				<br />
				<table class="tablenew">
					<tr>
						<th class="p">Places Lived</th>
					</tr>
					<tr>
				</table>
				<div id="location" class="locationgroup">
					<form id="locations" action="submitabout" method="post">
						<label class="labelcolor">Native Place</label> <br />
						<s:textfield id="ht" name="hometown" size="30"
							cssStyle="height:35px" value="%{userInfo.nativeplace}"></s:textfield>
						<br /> <br /> <label class="labelcolor">Current City</label> <br />
						<s:textfield id="cc" name="currentcity" size="30"
							cssStyle="height:35px" value="%{userInfo.place}"></s:textfield>

						<br /> <br />
						<button type="submit" class="btn btn-small btn-primary">
							Save <span class="fa fa-arrow-right"></span>
						</button>
					</form>
				</div>
				<br />
				<table>
					<tr>
						<th class="p">Basic Information</th>
					</tr>
				</table>
				<br />
				<div id="other" class="othergroup">
					<form id="others" action="submitabout" method="post">
						<b class="new">Birth Date</b> &nbsp
						<sj:datepicker id="7" name="birthday" displayFormat="yy-mm-dd"
							value="%{userInfo.dob}" />

						<br /> <br />
						<h5>
							<b class="new">Gender</b>&nbsp &nbsp &nbsp &nbsp
							<s:select
								list="#{'Male':'Male', 'Female':'Female', 'Other':'Other'}"
								name="gender" value="%{userInfo.gender}" id="gen" />
						</h5>
						<button type="submit" class="btn btn-small btn-primary">
							Save <span class="fa fa-arrow-right"></span>
						</button>
					</form>
				</div>

			</div>
		</div>


	</div>


</body>

<script>
	$(document).on(
			"submit",
			"#workinfo",
			function(event) {

				$('.workform-group').removeClass('has-error'); // remove the error class
				$('.help-block').remove(); // remove the error text
				$('.alert-success').remove();

				/* stop form from submitting normally */
				event.preventDefault();

				/* get some values from elements on the page: */
				var formData = {
					'wrkplace' : $('input[name=wrkplace]').val(),
					'role' : $('input[name=role]').val(),
					'joindate' : $('input[name=joindate]').val(),
					'leftdate' : $('input[name=leftdate]').val(),
				};

				if (formData.wrkplace.length == 0) {
					$('#workinformation').addClass('has-error'); // add the error class to show red input
					$('#workinformation').append(
							'<div class="help-block">' + "workplace required"
									+ '</div>');
				}
				var status = "true";
				if (formData.joindate.length == 0
						&& formData.leftdate.length != 0) {
					$('#workinformation').addClass('has-error'); // add the error class to show red input
					$('#workinformation').append(
							'<div class="help-block">' + "Joindate required"
									+ '</div>');
					status = "false";

				}

				if (formData.wrkplace.length != 0 && status == "true") {

					$.ajax({
						type : 'POST', // define the type of HTTP verb we want to use (POST for our form)
						url : 'submitabout', // the url where we want to POST
						data : formData, // our data object
						dataType : 'json', // what type of data do we expect back from the server
						encode : true
					});
					$("#workinformation input").val('');
					$('#workinformation').append(
							'<div class="alert alert-success">' + "Success"
									+ '</div>');
				}

			});

	$(document).on(
			"submit",
			"#schoolinfo",
			function(event) {

				$('.schoolform-group').removeClass('has-error'); // remove the error class
				$('.help-block').remove(); // remove the error text
				$('.alert-success').remove();

				/* stop form from submitting normally */
				event.preventDefault();

				/* get some values from elements on the page: */
				var formData = {
					'schoolname' : $('input[name=schoolname]').val(),
					'standard' : $('input[name=standard]').val(),
					'schooljoindate' : $('input[name=schooljoindate]').val(),
					'schoolleftdate' : $('input[name=schoolleftdate]').val(),
				};

				if (formData.schoolname.length == 0) {
					$('#schoolinformation').addClass('has-error'); // add the error class to show red input
					$('#schoolinformation').append(
							'<div class="help-block">' + "schoolname required"
									+ '</div>');
				}
				var status = "true";
				if (formData.schooljoindate.length == 0
						&& formData.schoolleftdate.length != 0) {
					$('#schoolinformation').addClass('has-error'); // add the error class to show red input
					$('#schoolinformation').append(
							'<div class="help-block">' + "Join date required"
									+ '</div>');
					status = "false";
				}

				if (formData.schoolname.length != 0 && status == "true") {
					$.ajax({
						type : 'POST', // define the type of HTTP verb we want to use (POST for our form)
						url : 'submitabout', // the url where we want to POST
						data : formData, // our data object
						dataType : 'json', // what type of data do we expect back from the server
						encode : true
					});
					$("#schoolinformation input").val('');
					$('#schoolinformation').append(
							'<div class="alert alert-success">' + "Success"
									+ '</div>');
				}

			});

	$(document).on(
			"submit",
			"#collegeinfo",
			function(event) {

				$('.collegeform-group').removeClass('has-error'); // remove the error class
				$('.help-block').remove(); // remove the error text
				$('.alert-success').remove();

				/* stop form from submitting normally */
				event.preventDefault();

				/* get some values from elements on the page: */
				var formData = {
					'collegename' : $('input[name=collegename]').val(),
					'degree' : $('input[name=degree]').val(),
					'collegejoindate' : $('input[name=collegejoindate]').val(),
					'collegeleftdate' : $('input[name=collegeleftdate]').val(),
				};

				if (formData.collegename.length == 0) {
					$('#collegeinformation').addClass('has-error'); // add the error class to show red input
					$('#collegeinformation').append(
							'<div class="help-block">' + "collegename required"
									+ '</div>');
				}
				var status = "true";
				if (formData.collegejoindate.length == 0
						&& formData.collegeleftdate.length != 0) {
					$('#collegeinformation').addClass('has-error'); // add the error class to show red input
					$('#collegeinformation').append(
							'<div class="help-block">' + "Join date required"
									+ '</div>');
					status = "false";
				}

				if (formData.collegename.length != 0 && status == "true") {
					$.ajax({
						type : 'POST', // define the type of HTTP verb we want to use (POST for our form)
						url : 'submitabout', // the url where we want to POST
						data : formData, // our data object
						dataType : 'json', // what type of data do we expect back from the server
						encode : true
					});
					$("#collegeinformation input").val('');
					$('#collegeinformation').append(
							'<div class="alert alert-success">' + "Success"
									+ '</div>');
				}

			});

	$(document).on(
			"submit",
			"#relationships",
			function(event) {

				$('locationgroup').removeClass('has-error'); // remove the error class
				$('.help-block').remove(); // remove the error text
				$('.alert-success').remove();

				/* stop form from submitting normally */
				event.preventDefault();
				/* get some values from elements on the page: */
				var formData = {
					'relationship' : $("#rel option:selected").text()
				};

				alert(formData.relationship);
				$.ajax({
					type : 'POST', // define the type of HTTP verb we want to use (POST for our form)
					url : 'submitabout', // the url where we want to POST
					data : formData, // our data object
					dataType : 'json', // what type of data do we expect back from the server
					encode : true
				});

				$('#relationships').append(
						'<div class="alert alert-success">' + "Success"
								+ '</div>');

			});

	$(document).on(
			"submit",
			"#locations",
			function(event) {

				$('locationgroup').removeClass('has-error'); // remove the error class
				$('.help-block').remove(); // remove the error text
				$('.alert-success').remove();

				/* stop form from submitting normally */
				event.preventDefault();
				/* get some values from elements on the page: */
				var formData = {
					'hometown' : $("#ht").val(),
					'currentcity' : $("#cc").val()
				};

				$.ajax({
					type : 'POST', // define the type of HTTP verb we want to use (POST for our form)
					url : 'submitabout', // the url where we want to POST
					data : formData, // our data object
					dataType : 'json', // what type of data do we expect back from the server
					encode : true
				});

				$('#locations').append(
						'<div class="alert alert-success">' + "Success"
								+ '</div>');

			});

	$(document).on(
			"submit",
			"#others",
			function(event) {

				$('othergroup').removeClass('has-error'); // remove the error class
				$('.help-block').remove(); // remove the error text
				$('.alert-success').remove();

				/* stop form from submitting normally */
				event.preventDefault();
				/* get some values from elements on the page: */
				var formData = {
					'birthday' : $('input[name=birthday]').val(),
					'gender' : $("#gen option:selected").text()
				};

				$.ajax({
					type : 'POST', // define the type of HTTP verb we want to use (POST for our form)
					url : 'submitabout', // the url where we want to POST
					data : formData, // our data object
					dataType : 'json', // what type of data do we expect back from the server
					encode : true
				});

				$('#others').append(
						'<div class="alert alert-success">' + "Success"
								+ '</div>');

			});
</script>
</html>