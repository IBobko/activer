<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<h1>Most popular</h1>
<hr/>
<div style="overflow:hidden">
<ul class="model3d">
<c:forEach var="model3d" items="${popularityModels}">
<li>
<tiles:insertTemplate template="/WEB-INF/views//model3d/model3dWidget.jsp">
	<tiles:putAttribute name="model3d" value="${model3d}"/>
</tiles:insertTemplate>
</li>
</c:forEach>
</ul>
<div style="clear:both"></div>
<h1>Most free popular</h1>
<hr/>
<ul class="model3d">
<c:forEach var="model3d" items="${popularityFreeModels}">
<li>
<tiles:insertTemplate template="/WEB-INF/views//model3d/model3dWidget.jsp">
	<tiles:putAttribute name="model3d" value="${model3d}"/>
</tiles:insertTemplate>
</li>
</c:forEach>
</ul>
</div>
