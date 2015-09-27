<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<h1>Mark</h1>
<h3>${s}</h3>
<hr/>
<ul class="model3d">
<c:forEach var="model" items="${models}">
	<li>
		<tiles:insertTemplate template="/WEB-INF/views//model3d/model3dWidget.jsp">
			<tiles:putAttribute name="model3d" value="${model}"/>
		</tiles:insertTemplate>
	</li>
</c:forEach>
</ul>

