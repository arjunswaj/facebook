<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
  <head>
    <sj:head />
  </head>
  <body>
    <s:url var="remoteurl1" action="timeline"/>
    <s:url var="remoteurl2" action="viewFriends"/>
    <s:url var="remoteurl3" action="about"/>
   <sj:tabbedpanel id="remotetabs" selectedTab="2" show="true" hide="'fade'" collapsible="true" sortable="true">
        <sj:tab id="tab1" href="%{remoteurl1}" label="TimeLine" />
        <sj:tab id="tab2" href="%{remoteurl2}" label="Friends"/>
        <sj:tab id="tab2" href="%{remoteurl3}" label="About"/>
    </sj:tabbedpanel>
  </body>
</html>