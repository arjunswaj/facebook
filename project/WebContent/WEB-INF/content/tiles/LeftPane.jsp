<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
.lpcenter {
margin:auto;
width:70%;
}
</style>
<div class="lpcenter">
	<img width="50px" src="image?userId=<s:property value="#session['user'].userId" />" />
	<br>
	<b><s:property value="#session['user'].firstName"/> <s:property value="#session['user'].lastName"/></b>
	<br>
	<a href="#">Edit Profile</a>
</div>

<div class="lpcenter">

	<ul class="nav nav-stacked">
	  <li><a href="/facebook/newsfeeds"><h4 style="color:black;"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;&nbsp;News Feeds</h4></a></li>
	  <li><a href="messages.action"><h4 style="color:black;"><span class="glyphicon glyphicon-envelope"></span>&nbsp;&nbsp;Messages</h4></a></li>
	  <li><a href="/facebook/event/displayEvents"><h4 style="color:black;"><span class="glyphicon glyphicon-calendar"></span>&nbsp;&nbsp;Events</h4></a></li>
	</ul>

</div>
