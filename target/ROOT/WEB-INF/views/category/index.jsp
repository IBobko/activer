<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<h2>Category</h2>
<ul class="model3d">
<c:forEach var="model3d" items="${model3ds}">
<li>
<tiles:insertTemplate template="/WEB-INF/views//model3d/model3dWidget.jsp">
	<tiles:putAttribute name="model3d" value="${model3d}"/>
</tiles:insertTemplate>
</li>
</c:forEach>
</ul>
<div style="clear:both"></div>

<ul class="pagination">
	<li><a href="#">&laquo;</a></li>
<%
Integer count = (Integer)request.getAttribute("count");
for ( int i = 0; i < count; i++){ 
	request.setAttribute("search_i", i); %>
	<c:url value="/category/${categoryId}/" var="pageUri">
	  <c:param name="page" value="${search_i}" />
	</c:url>
	<li><a href='${pageUri}'><%=(i+1) %></a></li>
<%} %>
	<li><a href="#">&raquo;</a></li>
</ul>