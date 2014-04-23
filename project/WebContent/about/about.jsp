<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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

div.padding {
	padding-top: 15px;
	padding-bottom: 35px;
	padding-right: 50px;
	padding-left: 40px;
	border-color: #eeeff4;
	border-style: solid;
	background-color: #eeeff4;
	border-width: 3px
}

b.q {
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
	padding-bottom: 18px;
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
</style>
</head>
<body>
	<div class="row" style="background-color: #f7f7f7">
		<div class="col-md-1" align="left"></div>
		<div class="col-md-10" align="left" style="background-color: white">
			<div class="padding">
				<table>
					<tr>
						<td><b class="q">About</b></td>
						<td><s:form action="editabout" method="post">
								<s:submit type="button" label="Edit" align="right" />
							</s:form>
						</td>
					</tr>
				</table>

			</div>


			<div class="col-md-6" align="left" style="background-color: #FFFFFF">

				<div class="box1">
					<table cellspacing="2" cellpadding="5" border="0"
						bordercolor="white">
						<tr>
							<th class="p" colspan="2">Work and Education</th>
						</tr>

						<s:iterator value="organization_details" var="org" status="status">
							<tr>
								<td width="70px"><img src="about/job.png" class="wne"></img></td>
								<td><b class="a"><s:property value="#org.name" /></b><br />
								<b class="b"><s:property value="#org.position" /></b></td>
							</tr>

						</s:iterator>
						<s:iterator value="institute_details" var="ins" status="status">
							<tr>
								<td><img src="about/school.png" class="wne"></img></td>
								<td><b class="a"><s:property value="#ins.name" /></b><br />
								<b class="b"><s:property value="#ins.description" /></b></td>
							</tr>
						</s:iterator>
					</table>
					<br /> <br />
					<table>
						<tr>
							<th class="p">Relationship</th>
						</tr>
						<tr class="b">
							<td><img src="about/relationships.png" height="50px"
								width="50px" alt="test" align="middle">&nbsp&nbsp&nbsp&nbsp<b><s:property
										value="relationship" /></b></img></td>
						</tr>
					</table>
				</div>
			</div>
			<div class="col-md-4" align="left">
				<div class="box1">


					<table>
						<tr>
							<th class="p">Places Lived</th>
						</tr>
						<tr>
							<td><img src="about/location.jpeg" class="loc"></img></td>
							<td><b class="a"><s:property value="place" /></b><br />
							<b class="b">Current City</b></td>
						</tr>
						<tr>
							<td><img src="about/location.jpeg" class="loc"></img></td>
							<td><b class="a"><s:property value="nativePlace" /></b><br />
							<b class="b">Hometown</b></td>
						</tr>
					</table>
					<br /> <br />
					<table>
						<tr>
							<th class="p">Basic Information</th>
						</tr>
						<tr>
							<td><b class="b">Birth Date</b></td>
							<td><b class="c"><s:property value="date_month" /></b></td>
						</tr>
						<tr>
							<td><b class="b">Birth Year</b></td>
							<td class="c"><s:property value="year" /></td>
						</tr>
						<tr>
							<td><b class="b">Gender</b></td>
							<td><b class="c"><s:property value="gender" /></b></td>
						</tr>
						<tr>
							<td><b class="b">Relationship</b></td>
							<td><b class="c"><s:property value="relationship" /></b></td>
						</tr>

					</table>
				</div>
			</div>
			<div class="col-md-1" align="left"></div>
		</div>
	</div>


</body>
</html>