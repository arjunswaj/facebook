<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
  <head>
    <sj:head />
  </head>
  <body>
    <s:url var="remoteurl1" action="viewFriends"/>
    <s:url var="remoteurl2" action="ajax2"/>
   <sj:tabbedpanel id="remotetabs" selectedTab="2" show="true" hide="'fade'" collapsible="true" sortable="true">
        <sj:tab id="tab1" href="%{remoteurl1}" label="Friends" />
        <sj:tab id="tab2" href="%{remoteurl2}" label="workinprogress"/>
    </sj:tabbedpanel>
  </body>
</html>