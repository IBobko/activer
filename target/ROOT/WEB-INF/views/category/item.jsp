<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<tiles:useAttribute name="categories"/>
<ul class="tree">
	<c:forEach items="${categories}" var="category">
		<li>
		<a href="<c:url value="/category/${category.id}"/>">${category.name}</a>
			<c:if test="${fn:length(category.children) > 0}">
				<tiles:insertTemplate template="/WEB-INF/views/category/item.jsp">
					<tiles:putAttribute name="categories" value="${category.children}"/>
				</tiles:insertTemplate>
			</c:if>
		</li>
	</c:forEach>
</ul>