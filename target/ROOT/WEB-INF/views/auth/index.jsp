<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Log in</h1>
<hr/>
<div style="width:350px;">
	<c:if test="${ie != null}">
	<div class="alert alert-danger">
		<ul>
		<c:forEach items="${ie.errors}" var="error">
			<li>${error}</li>
		</c:forEach>
		</ul>
	</div>
	</c:if>
	<form action="<c:url value="/j_spring_security_check"/>" method="post">
		<table cellpadding="5" cellspacing="0" style="width:100%;border:none">
			<tr>
				<td><label for="username">Username</label></td>
				<td><input type="text" name="j_username" value="" class='form-control'/></td>
			<tr>
				<td><label for="password">Password</label></td>
				<td><input type="password" name="j_password" value="" class='form-control'/></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="login" value="Log in" class='btn btn-default'/> <a href="<c:url value="/auth/forgot"/>" class="btn btn-default">Forgot password</a></td>
			</tr>
		</table>
	</form>
</div>

	
