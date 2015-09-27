<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Category edit</h1>
<hr/>
<form method="post">
	<input type="hidden" name="id" value="${category.id}"/>
	<input type="text" name="name" value="${category.name}" class="form-control"/>
	<br/><br/>
	<input type="submit" value="Save" class="btn btn-default"/>
</form>