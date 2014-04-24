<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Facebook_login</title>
<script type="text/css">

</script>

<script>
function validateEmail()
{
var x=document.forms["myForm"]["email"].value;
var y=document.forms["myForm"]["password"].value;
var atpos=x.indexOf("@");
var dotpos=x.lastIndexOf(".");
if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
  {
  alert("Not a valid e-mail address");
  return false;
  }
if (!y)
	{
	alert("password cannot be null");
	return false;
	}
}
</script>

<script>
function validatesignup()
{
var x=document.forms["signupform"]["email"].value;
var y=document.forms["signupform"]["reemail"].value;
var p=document.forms["signupform"]["first_name"].value;
var q=document.forms["signupform"]["last_name"].value;
var r=document.forms["signupform"]["password"].value;
var a=document.forms["signupform"]["day"].value;
var b=document.forms["signupform"]["month"].value;
var c=document.forms["signupform"]["year"].value;


var atpos=x.indexOf("@");
var dotpos=x.lastIndexOf(".");

if (!p)
{
alert("first name cannot be null");
}
if (!q)
	{
	alert("last name cannot be null");
    }
if(!r)
  {
		alert("password cannot be null");
  }
if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length)
  {
  alert("Not a valid e-mail address");
  return false;
  }
if(x!=y)
{
alert("Email & Re-Email entered were not same");
return false;
}
if( a == -1 || b == -1 || c == -1)
	{
	alert(" Enter a valid Bithday date ");
	return false;
	}

else if(!leapYear(a,b,c))
{
 alert(" Enter a valid Bithday date because you have entered a leap year ");
return false;
}

}


function leapYear(day,month,year)
{
 if(((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
 {
	 if((month==2)&&((day==30)||(day==31)))
			 {
		 document.forms["signupform"]["day"].value=-1;
		 document.forms["signupform"]["month"].value=-1;
			return false;
			 }
 }
 else
	 {
	 if((month==2)&&((day==30)||(day==31)||(day==29)))
			 {
		 document.forms["signupform"]["day"].value=-1;
		 document.forms["signupform"]["month"].value=-1;
			return false;
			 }
	 }
 return true;
}


</script>
</head>

<body style="background-color: #EBEEF5;">

	<div
		style="position: absolute; width: 700px; height: 85px; background-color: #3b5998; left: 0px; top: 0px">
		<br>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
		&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <a href="login.jsp"><img
			src="images/facebook-logo.jpg" alt="Facebook" width="200" height="65" /></a>
	</div>

	<div
		style="position: absolute; width: 700px; height: 85px; background-color: #3b5998; left: 700px; top: 0px">
		<s:form action="login" name="myForm" autocomplete="off" method="post"
			onsubmit="return validateEmail();">
			<table cellspacing="0">
				<tr>
					<td><label for="email"><font color="white" size="2">Email
						</font></label></td>
					<td><label for="pass"><font color="white" size="2">Password</font></label></td>
				</tr>
				<tr>
					<td><input type="text" name="email" size="18" value="" /></td>
					<td><input type="password" name="password" size="18" /></td>
					<td><label><input value="Log In" tabindex="4"
							type="submit" id="u_0_n" /></label></td>
				</tr>
				<tr>
					<td><input id="persist_box" type="checkbox" name="persistent"
						value="1" tabindex="3" /><label for="persist_box"><font
							color="#C0C0C0" size="2">Keep me logged in</font></label>
					<td><a href="recovery.jsp"><font color="#C0C0C0" size="2">Forgot
								your password?</font></a></td>
				</tr>
			</table>
		</s:form>
	</div>

	<div
		style="position: absolute; left: 150px; top: 130px; color: #484848">
		<b>Facebook helps you connect and share with the<br> people
			in your life.
		</b>
	</div>
	<div style="position: absolute; left: 153px; top: 180px">
		<img alt="Connecting the world" src="images/signup.png">
	</div>

	<div style="position: absolute; width: 1500px; left: 700px; top: 120px">
		<h1>Sign Up</h1>
		It's free and always will be.
		<table>
			<form action="firstsignup" name="signupform"
				onsubmit="return validatesignup()" method="post">

				<br> <span style="color: red"><s:property
						value="notification" /></span> <br>
				<tr>
					<input type="text" name="first_name" placeholder="First Name"
						size="21" style="height: 35px">
				</tr>
				<tr>
					<input type="text" name="last_name" placeholder="Last Name"
						size="22" style="height: 35px">
					<br>
				</tr>
				<tr>
					<input type="text" name="email" placeholder="Your email address"
						size="50" style="height: 35px">
					<br>
				</tr>
				<tr>
					<input type="text" name="reemail" value=""
						placeholder="Re-enter email address" size="50"
						style="height: 35px">
					<br>
				</tr>
				<tr>
					<input type="password" name="password" placeholder="New Password"
						value="" size="50" style="height: 35px">
					<br>
				</tr>
				<tr>
					<input type="text" name="place" placeholder="Enter the place"
						size="50" style="height: 35px">
					<br>
				</tr>
				<tr>
					<br>
					<label>Birthday</label>
					<br>
					<select name="day">
						<option value="-1">Day</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option>
					</select>
					<select name="month">
						<option value="-1">Month</option>
						<option value="01">Jan</option>
						<option value="02">Feb</option>
						<option value="03">Mar</option>
						<option value="04">Apr</option>
						<option value="05">May</option>
						<option value="06">Jun</option>
						<option value="07">Jul</option>
						<option value="08">Aug</option>
						<option value="09">Sept</option>
						<option value="10">Oct</option>
						<option value="11">Nov</option>
						<option value="12">Dec</option>

					</select>
					<select name="year">
						<option value="-1">Year</option>
						<option value="2014">2014</option>
						<option value="2013">2013</option>
						<option value="2012">2012</option>
						<option value="2011">2011</option>
						<option value="2010">2010</option>
						<option value="2009">2009</option>
						<option value="2008">2008</option>
						<option value="2007">2007</option>
						<option value="2006">2006</option>
						<option value="2005">2005</option>
						<option value="2004">2004</option>
						<option value="2003">2003</option>
						<option value="2002">2002</option>
						<option value="2001">2001</option>
						<option value="2000">2000</option>
						<option value="1999">1999</option>
						<option value="1998">1998</option>
						<option value="1997">1997</option>
						<option value="1996">1996</option>
						<option value="1995">1995</option>
						<option value="1994">1994</option>
						<option value="1993">1993</option>
						<option value="1992">1992</option>
						<option value="1991">1991</option>
						<option value="1990">1990</option>
						<option value="1989">1989</option>
						<option value="1988">1988</option>
						<option value="1987">1987</option>
						<option value="1986">1986</option>
						<option value="1985">1985</option>
						<option value="1984">1984</option>
						<option value="1983">1983</option>
						<option value="1982">1982</option>
						<option value="1981">1981</option>
						<option value="1980">1980</option>
						<option value="1979">1979</option>
						<option value="1978">1978</option>
						<option value="1977">1977</option>
						<option value="1976">1976</option>
						<option value="1975">1975</option>
						<option value="1974">1974</option>
						<option value="1973">1973</option>
						<option value="1972">1972</option>
						<option value="1971">1971</option>
						<option value="1970">1970</option>
						<option value="1969">1969</option>
						<option value="1968">1968</option>
						<option value="1967">1967</option>
						<option value="1966">1966</option>
						<option value="1965">1965</option>
						<option value="1964">1964</option>
						<option value="1963">1963</option>
						<option value="1962">1962</option>
						<option value="1961">1961</option>
						<option value="1960">1960</option>
						<option value="1959">1959</option>
						<option value="1958">1958</option>
						<option value="1957">1957</option>
						<option value="1956">1956</option>
						<option value="1955">1955</option>
						<option value="1954">1954</option>
						<option value="1953">1953</option>
						<option value="1952">1952</option>
						<option value="1951">1951</option>
						<option value="1950">1950</option>
						<option value="1949">1949</option>
						<option value="1948">1948</option>
						<option value="1947">1947</option>
						<option value="1946">1946</option>
						<option value="1945">1945</option>
						<option value="1944">1944</option>
						<option value="1943">1943</option>
						<option value="1942">1942</option>
						<option value="1941">1941</option>
						<option value="1940">1940</option>
						<option value="1939">1939</option>
						<option value="1938">1938</option>
						<option value="1937">1937</option>
						<option value="1936">1936</option>
						<option value="1935">1935</option>
						<option value="1934">1934</option>
						<option value="1933">1933</option>
						<option value="1932">1932</option>
						<option value="1931">1931</option>
						<option value="1930">1930</option>
						<option value="1929">1929</option>
						<option value="1928">1928</option>
						<option value="1927">1927</option>
						<option value="1926">1926</option>
						<option value="1925">1925</option>
						<option value="1924">1924</option>
						<option value="1923">1923</option>
						<option value="1922">1922</option>
						<option value="1921">1921</option>
						<option value="1920">1920</option>
						<option value="1919">1919</option>
						<option value="1918">1918</option>
						<option value="1917">1917</option>
						<option value="1916">1916</option>
						<option value="1915">1915</option>
						<option value="1914">1914</option>
						<option value="1913">1913</option>
						<option value="1912">1912</option>
						<option value="1911">1911</option>
						<option value="1910">1910</option>
						<option value="1909">1909</option>
						<option value="1908">1908</option>
						<option value="1907">1907</option>
						<option value="1906">1906</option>
						<option value="1905">1905</option>
					</select>
					<br>
					<br>

				</tr>
				<tr>
					<input type="radio" name="gender" value="male" checked>Male
					<input type="radio" name="gender" value="female">Female
				<tr>
				</tr>
				<br> <br> <input type="submit" value="Sign Up"
					style="height: 45px; width: 160px; background-color: #5CAD5C; color: white">
				</tr>
				<br>
			</form>

		</table>
		<div align="left">
			<h6>
				Facebook's Replica Copyrights @ GROUP1 of OOAD-2014 IIITB<BR>
			</h6>
		</div>

	</div>

</body>
</html>
