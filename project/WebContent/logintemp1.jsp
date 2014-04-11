<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
.alert-box {
	color: #555;
	font-family: Tahoma, Geneva, Arial, sans-serif;
	font-size: 18px;
	background: #ffecec url('images/error.png') no-repeat 10px 50%;
}

img {
	position: absolute;
	left: 0px;
	top: 100px;
	z-index: -1;
}

.label {
	color: #eeeff4;
	font-style: normal;
}
</style>
<head>
<script type="text/javascript">
function validateForm()
{
var x=document.forms["signUpForm"]["fname"].value;
var email=document.forms["signUpForm"]["email"].value;
var atpos=email.indexOf("@");
var dotpos=email.lastIndexOf(".");

if (x==null || x=="")
  {
  alert("Please provide your first name");
  return false;
  }
if( document.signUpForm.lname.value == "" )
{
  alert( "Please provide your last name!" );
  document.signUpForm.lname.focus() ;
  return false;
}

if( document.signUpForm.email.value == "" )
{
  alert( "Please provide your Email!" );
  document.signUpForm.email.focus() ;
  return false;
}
if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length)
  {
  alert("Not a valid e-mail address");
  return false;
  }

if( document.signUpForm.reEnteredEmail.value == "" )
{
  alert( "Please re-enter your email id!" );
  document.signUpForm.reEnteredEmail.focus();
  return false;
}
if( document.signUpForm.password.value == "" )
{
  alert( "Please provide your password!" );
  document.signUpForm.password.focus();
  return false;
}
if( document.signUpForm.day.value == "-1" )
{
  alert( "Please provide day of your date of birth!" );
  document.signUpForm.day.focus() ;
  return false;
}
if( document.signUpForm.month.value == "-1" )
{
  alert( "Please provide month of your date of birth!" );
  document.signUpForm.month.focus();
  return false;
}
if( document.signUpForm.year.value == "-1" )
{
  alert( "Please provide year of your date of birth!" );
  document.signUpForm.year.focus();
  return false;
}
return( true );
}
var pass_strength;

function IsEnoughLength(str,length)
{
	if ((str == null) || isNaN(length))
		return false;
	else if (str.length < length)
		return false;
	return true;
	
}

function HasMixedCase(passwd)
{
	if(passwd.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/))
		return true;
	else
		return false;
}

function HasNumeral(passwd)
{
	if(passwd.match(/[0-9]/))
		return true;
	else
		return false;
}

function HasSpecialChars(passwd)
{
	if(passwd.match(/.[!,@,#,$,%,^,&,*,?,_,~]/))
		return true;
	else
		return false;
}


function CheckPasswordStrength(pwd)
{
	if (IsEnoughLength(pwd,14) && HasMixedCase(pwd) && HasNumeral(pwd) && HasSpecialChars(pwd))
		pass_strength = "<b><font style='color:olive'>Very strong</font></b>";
	else if (IsEnoughLength(pwd,8) && HasMixedCase(pwd) && (HasNumeral(pwd) || HasSpecialChars(pwd)))
		pass_strength = "<b><font style='color:Blue'>Strong</font></b>";
	else if (IsEnoughLength(pwd,8) && HasNumeral(pwd))
		pass_strength = "<b><font style='color:Green'>Good</font></b>";
	else
		pass_strength = "<b><font style='color:red'>Weak</font></b>";

	document.getElementById('pwd_strength').innerHTML = pass_strength;
}
function matchEmail(){
	 var email1 = document.getElementById("email").value;
	    var reEnteredEmail = document.getElementById("reEnteredEmail").value;
	    if (email1 != reEnteredEmail) {
            document.getElementById("emailMatchError").innerHTML = "email does not match";
            return false;
        }
        else {
            document.getElementById("emailMatchError").innerHTML = "email matches";
            return true;
        }
	    
}

</script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="shortcut icon" href="Images\favicon.ico">
<title>Welcome to Facebook</title>
</head>

<body
	style="background-color: #EEEEEE; width: 100%; height: 100%; margin: 0; padding: 0">

	<div
		style="position: absolute; width: 700px; height: 100px; background-color: #3b5998; left: 0px">
		<form action="login" method="post">
			<br>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; <font color="white"
				size="6"><b> facebook </b></font>
	</div>
	<div
		style="position: absolute; width: 750px; height: 100px; background-color: #3b5998; left: 700px">
		<br> <label> <font color="white" size="2">Email or
				Phone</font>
		</label> &nbsp; &nbsp; &nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp; <label> <font color="white" size="2">Password
		</font></label><br> <input type="text" name="emailOrPhone" size="18">&nbsp;
		<input type="password" name="password" size="18">&nbsp; <input
			type="submit" value="Log in"><br>
		<s:checkbox name="keepMeLoggedIn" fieldValue="true" />
		<label class="label"><font size="2"> Keep me logged in</font></label>
		&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <a
			href="forgotPassword.jsp" style="text-decoration: none"> <font
			style="color: #eeeff4;" size="2"> Forgotten your password?</font></a>
		<s:if test="hasActionErrors()">
			<div class="alert-box">
				<s:actionerror />
			</div>
		</s:if>
		</form>
	</div>

	<img src="Images\fb4.png">

	<div id="content"
		style="position: absolute; background-color: #eeeff4; height: 1000px; width: 1500px; left: 700px; top: 120px">
		<h1>
			<b> Create an account</b>
		</h1>
		<h3>It's free and always will be.</h3>
		<form action="signUp" name="signUpForm"
			onsubmit="return validateForm()" method="post">

			<br> <br> <input type="text" name="fname"
				placeholder="First Name" size="22" style="height: 39px"> <input
				type="text" name="lname" placeholder="Last Name" size="21"
				style="height: 39px"><br>
			<br> <input type="text" name="email" id="email"
				placeholder="Your email address" size="50" style="height: 39px"><br>
			<br> <input type="text" name="reEnteredEmail"
				id="reEnteredEmail" onkeyup='matchEmail();'
				placeholder="Re-enter email address" size="50" style="height: 39px">

			<span id="emailMatchError"></span><br> <br> <input
				type="password" name="password"
				onkeyup='CheckPasswordStrength(this.value);'
				placeholder="New Password" size="50" style="height: 39px"><br>
			<div id='pwd_strength'></div>

			<br> <label for="birthday">Birthday</label> <br>
			<br> <select name="day">
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
			</select> <select name="month">
				<option value="-1">Month</option>
				<option value="Jan">Jan</option>
				<option value="Feb">Feb</option>
				<option value="Mar">Mar</option>
				<option value="Apr">Apr</option>
				<option value="May">May</option>
				<option value="Jun">Jun</option>
				<option value="Jul">Jul</option>
				<option value="Aug">Aug</option>
				<option value="Sept">Sept</option>
				<option value="Oct">Oct</option>
				<option value="Nov">Nov</option>
				<option value="Dec">Dec</option>

			</select> <select name="year">
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

			</select> <br>
			<br> <input type="radio" name="gender" id="female"
				value="female"> <label for="female">Female</label> <input
				type="radio" name="gender" id="male" value="male"> <label
				for="male">Male</label> <br>
			<br> <input type="image" src="Images\b1.png"
				alt="Create an account" width="260" height="65"><br>
		</form>
	</div>

</body>
</html>


