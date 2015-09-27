<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<h1>Search</h1>
<hr/>
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
Integer pages = (Integer)request.getAttribute("pages");
for ( int i = 0; i < pages; i++){ 
	request.setAttribute("search_i", i); %>
	<c:url value="/model3d/search/" var="pageUri">
	  <c:param name="s" value="${search_s}" />
	  <c:param name="priceFrom" value="${search_priceFrom}" />
	  <c:param name="priceTo" value="${search_priceTo}" />
	  <c:param name="page" value="${search_i}" />
	</c:url>
	<li><a href='${pageUri}'><%=(i+1) %></a></li>
<%} %>
	<li><a href="#">&raquo;</a></li>
</ul>