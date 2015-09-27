<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<h1>Sign up</h1>
<hr/>
<div class="alert alert-info">All fields are required.</div>
<form method="post">
<div style="width:350px;">
	<c:if test="${ie != null}">
	<div class="alert alert-danger">
		<ul>
		<c:forEach items="${ie.errors}" var="error">
			<li>${error}</li>
		</c:forEach>
		</ul>
	</div>
	</c:if>		
	<tiles:insertTemplate template="/WEB-INF/views/auth/form.jsp">
		<tiles:putAttribute name="account" value="${account}"/>
	</tiles:insertTemplate>
	<br/>
</div>
<button type="submit" class="btn btn-default">Sign up</button>
</form>
