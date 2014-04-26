<%@ taglib prefix="s" uri="/struts-tags"%>
<style>
.lpcenter {
margin:auto;
width:70%;
}
</style>
<div class="lpcenter">
	<img align="left" width="50px" src="image?userId=<s:property value="#session['user'].userId" />" />
	<b><s:property value="#session['user'].firstName"/> <s:property value="#session['user'].lastName"/></b>
	<br>
	<a href="/facebook/about.action"><s:text name="global.editprofile"/></a>
	<br clear="all">
</div>
<hr>
<div class="lpcenter">
	  <a href="/facebook/newsfeeds"><h4 style="color:black;"><span class="glyphicon glyphicon-list-alt"></span>&nbsp;<s:text name="global.newsfeeds"/></h4></a>
	  <a href="inbox"><h4 style="color:black;"><span class="glyphicon glyphicon-envelope"></span>&nbsp;<s:text name="global.messages"/></h4></a>
	  <a href="/facebook/displayEvents"><h4 style="color:black;"><span class="glyphicon glyphicon-calendar"></span>&nbsp;<s:text name="global.events"/></h4></a>
</div>
