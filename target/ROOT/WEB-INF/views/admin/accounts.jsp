<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <h1>Accounts</h1>
<hr/>    
<table class="table">
	<tr><th>#</th><th>Username</th><th>Password</th><th>E-mail</th><th>Roles</th></tr>
	<c:forEach var="account" items="${accounts}">
		<tr><td>${account.id}</td><td>${account.username}</td><td>${account.password}</td><td>${account.email}</td>
		<td>
		<c:forEach var="authority" items="${account.authoritys}" varStatus="loop">
			<i>${authority.role}<c:if test="${!loop.last}"><br/></c:if></i>
		</c:forEach>
		</td></tr>
	</c:forEach>	
</table>
