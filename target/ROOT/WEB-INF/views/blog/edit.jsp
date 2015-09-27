<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<h1>Blog</h1>

<form method="post">
	<div class='form-group'>
		<label for="title-id">Title</label>
		<input type='text' id='title-id' name='title' class='form-control'>
	</div>
	
	<div class='form-group'>
		<label for="text-id">Text</label>
		<textarea name='text' class='form-control' style='height:300px;'></textarea>	
	</div>
	
	<div class='form-group'>
		<input type='submit' value='Submit' class='btn btn-default'/>	
	</div>
</form>
