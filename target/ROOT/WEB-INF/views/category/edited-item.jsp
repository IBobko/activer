<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<tiles:useAttribute name="categories"/>
<ol class="dd-list">
	<c:forEach items="${categories}" var="category">
		<li class="dd-item dd3-item" data-id="${category.id}">
		<div class="dd-handle dd3-handle">Drag</div>
		<div class="dd3-content"><a href="<c:url value="/category/edit/${category.id}"/>">${category.name}</a> <div style="float:right"><a href="<c:url value="/category/remove/${category.id}"/>"><span style="color:red;opacity:0.5" class="glyphicon glyphicon-remove"></span></a></div></div>
			<c:if test="${fn:length(category.children) > 0}">
				<tiles:insertTemplate template="/WEB-INF/views/category/edited-item.jsp">
					<tiles:putAttribute name="categories" value="${category.children}"/>
				</tiles:insertTemplate>
			</c:if>
		</li>
	</c:forEach>
</ol>
