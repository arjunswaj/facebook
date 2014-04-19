<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
.rpcenter {
	width:70%;
	background-color:#ffffff;
	border-style:solid;
	border-width:1px;
	border-color:#c1b9b9;
	height:100%;
}
</style>

<div class="rpcenter">
	<hr />
	<s:action name="displayEventsToday" executeResult="true" />
	<hr />
</div>

<div class="rpcenter">
	<s:action name="friendsuggestions" executeResult="true" />
</div>