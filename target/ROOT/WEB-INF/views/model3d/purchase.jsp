<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>Order # ${order.id}</h3>

<table class="table">
<tr>
	<td><strong>Name of order:</strong></td>
	<td>${model3d.name}</td>
</tr>
<tr>
	<td><strong>Price:</strong></td>
	<td>${model3d.price}</td>
</tr>
</table>
<div><a href="${robokassa.link}" class="btn btn-primary">Buy with robokassa</a></div>
