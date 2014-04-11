<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style>
.rpcenter {
	margin:auto;
	width:70%;
	background-color:#ffffff;
	border-style:solid;
	border-width:1px;
	border-color:#c1b9b9;
}
</style>
<div class="rpcenter">
	<s:action name="friendsuggestions" executeResult="true" />
</div>