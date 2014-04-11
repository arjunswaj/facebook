<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Create New Event</title>
	<link rel="stylesheet" href="../css/jquery-ui.css">
	<script src="../js/jquery-1.10.2.js"></script>
	<script src="../js/jquery-ui.js"></script>
	<script>
		$(function() {
		$( "#datepicker" ).datepicker({dateFormat: 'yy-mm-dd', minDate: 0});
		});
	</script>
	<script type="text/javascript">
		function validateAndSubmit()
		{
			if(document.getElementById("nm").value=="")
				alert("Name is empty.");
			else if(document.getElementById("dc").value=="")
				alert("Details are missing.");
			else if(document.getElementById("wh").value=="")
				alert("Place is empty.");
			else if(document.getElementById("datepicker").value=="")
				alert("Date is empty.");
			else if(document.getElementById("tm").value=="")
				alert("Time is empty.");
			else
			{
				document.getElementById("tm").value+=":00";
				document.getElementById("f1").submit();
			}
		}
	</script>
</head>
<body style="background-color: #c5c5df;">
	<form action="/facebook/createEvent" method="post" id='f1'>
		<table style="margin-left: 25%; margin-top: 150px; width: 50%; border: thin; border-style: solid; background-color: #ffffff;">
			<tbody>
				<tr>
					<td colspan="3" style="background-color: #45619d; color: white; height: 35px;"><b>Create New Event</b></td>
				</tr>
				<tr>
					<td>Name: </td>
					<td colspan="2"><input id='nm' type="text" name="eventName" style="width: 98%;"></td>
				</tr>
				<tr>
					<td>Details: </td>
					<td colspan="2"><textarea id='dc' rows="4" name="eventDescription" style="width: 98%;"></textarea></td>
				</tr>
				<tr>
					<td>Where: </td>
					<td colspan="2"><input id='wh' type="text" name="eventPlace" style="width: 98%;"></td>
				</tr>
				<tr>
					<td>When: </td>
					<td><input type="text" name="eventDate" style="width: 96%;" id='datepicker' /></td>
					<td><select id='tm' name="eventTime" style="width: 96%;" ></select></td>
				</tr>
				<tr>
					<td colspan="3" align="right">
						<input style="background-color: #45619d; color: white;" type="button" value="Create" onclick='validateAndSubmit()' />
						<a style="text-decoration: none;" href="/facebook/displayEvents"><input type="button" value="Cancel" /></a>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	<script type="text/javascript">
		var hh="";
		var mm="";
		for(var h=0; h<24; h++)
			for(var m=0; m<60; m+=5)
			{
				hh=("0"+h).substring(("0"+h).length-2);
				mm=("0"+m).substring(("0"+m).length-2);
				document.getElementById("tm").innerHTML+="<option>"+hh+":"+mm+"</option>";
			}
	</script>
</body>
</html>