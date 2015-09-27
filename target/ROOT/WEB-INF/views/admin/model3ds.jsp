<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Models</h1>
<table class="table">
	<tr><th>#</th><th>Model name</th><th>Author</th><th>Edit</th><th>Delete</th></tr>
	<c:forEach var="model3d" items="${model3ds}">
		<tr>
			<td>${model3d.id}</td>
			<td><a href="<%=request.getContextPath()%>/model3d/show/${model3d.id}">${model3d.name}</a></td>
			<td>${model3d.account.username}</td>
			<td><a href="<%=request.getContextPath()%>/model3d/edit/${model3d.id}"><span style="color:green;opacity:0.5" class='glyphicon glyphicon-pencil'></span></a></td>
			
			<td><a href="<c:url value="/model3d/remove/${model3d.id}"/>"><span style="color:red;opacity:0.5" class="glyphicon glyphicon-remove"></span></a></td>
		</tr>
	</c:forEach>	
</table>