<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Orders</h1>
<table class="table">
	<thead>
		<tr>
			<th>#</th>
			<th>Account</th>
			<th>Model</th>
			<th>Status</th>
		</tr>
	</thead>
	<c:forEach items="${orders}" var="order">
		<tr>
			<td>${order.id}</td>
			<td>${order.account.username}</td>
			<td><a href="<%=request.getContextPath() %>/model3d/show/${order.model3d.id}">${order.model3d.name}</a></td>
			<td>${order.statusText}</td>
		</tr>
	</c:forEach>
</table>