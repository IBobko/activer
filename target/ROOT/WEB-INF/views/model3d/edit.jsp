<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<h1>Edit model</h1>
<form method="post">
	<div class='form-group'>
		<label>Name</label>
		<input type="text" name='' value='${model3d.name}' class='form-control' />
	</div>
	
	<div class='form-group'>
		<label>Description</label>
		<textarea name='' class='form-control'>${model3d.description}</textarea>
	</div>
	
	<img src="<c:url value="${picturePath}/${model3d.pictures[0].path}"/>"/>

	<div class='form-group'>
		<label>Price</label>
		<input type="text" name='' value='${model3d.price}' class='form-control' />
	</div>

	<div class='form-group'>
		<label>Marks</label>
		<input type="text" name='' value='${model3d.price}' class='form-control' />
	</div>
	
	<div><strong>Marks: </strong>
	<c:forEach var="mark" items="${model3d.marks}">
		${mark.name}
	</c:forEach>
	</div>
	
	<br/>
	
	<div><input type='submit' class="btn btn-primary" value="Save"/></div>
</form>