<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript">
jQuery(function($){
	// function for loading categories
	var that = this;
	
	this.prev = null;
	
	window.prev = this.prev;
	
	
	this.changeCategorySelect = function() {
		var id = $(this).val();
		if (id == 0) return;
		
		that.prev = $("[name='category']");
		that.prev.removeAttr('name');
		$(this).attr('name','category');
		$(this).removeAttr('doNotSelected');
		
		var select = "<ul class='tree'>\n<li>\n<select class='form-control' doNotSelected></select><a href='#' class='remove-category' style='float:right;top: -30px;position: relative;left: 44px;margin-bottom:-14px'><span style='color:red;opacity:0.5' class='glyphicon glyphicon-remove'></span></a></li>\n</ul>";
		$.get("<c:url value="/category/ajax/get-categories/"/>" + id,
			function(data){
				if (data.length == 0) return;
				var options = "<option value='0'>select category</option>\n";
				for (index in data) {
					options += "<option value='"+data[index].id+"'>"+data[index].name+"</option>\n";
				}
	
				that.prev = $("[name='category']");

				that.prev.attr('readonly','readonly');

				$('#category-tree li:last').append(select);
				
				$("[doNotSelected]").change(that.changeCategorySelect).append(options);
				$(".remove-category").click(function(){
					var parent = $(this).parent().parent();
					parent.parent().find('select').removeAttr('disabled');
					parent.parent().find('select').attr('name','category');
					parent.remove();
					return false;
				});
				
				
			}, "json");
	};
	$("[name='category']").change(this.changeCategorySelect);
});
</script>
<h1>Upload your own model</h1>
<hr/>
<form method="post" enctype='multipart/form-data'>
	<div style="width:400px">

	<c:if test="${errors!=null}">
	<div class='alert alert-danger'>
		<ul>
			<c:forEach items="${errors}" var="error">
				<li>${error}</li>
			</c:forEach>
		</ul>
	</div>
	</c:if>
	<div class="form-group" id="category-tree">
		<label for="category-id">Categories</label>
		<ul style='padding-left:0px'>
			<li>
				<select name='category' id='category-id' class='form-control'>
					<option value='0'>select category</option>
					<c:forEach var="category" items="${categories}">
					<option value='${category.id}'>${category.name}</option>
					</c:forEach>
	  			</select>
	  		</li>
	  	</ul>
	</div>
	<div class="form-group">
	  <label for="name-id">Name of model</label>
	  <input type="text" id="name-id" class="form-control" placeholder="Enter name" name="name">
	</div>
	<div class="form-group">
	  <label for="description-id">Description</label>
	  <textarea id="description-id" class="form-control" placeholder="Enter description" name="description" style="height:100px"></textarea>
	</div>
	<div class="form-group">
	  <label for="price-id">Price</label>
	  <input type="text" id="price-id" class="form-control" name="price" value="0.0">
	</div>
	<div class="form-group">
	  <label for="file-id">Model file</label>
	  <input type="file" id="file-id" class="form-control" name="file">
	</div>
	<div class="form-group">
	  <label for="image-id">Image  <small>(PNG, JPEG)</small></label>
	  <input type="file" id="image-id" class="form-control" name="image">
	</div>
	<div class="form-group">
	  <label for="marks-id">Marks <small>(e.g. decoration,cover,...)</small></label>
	  <input type="text" id="marks-id" class="form-control" name="marks">
	</div>
	<input type="submit" value="Upload" class="btn btn-primary">
	</div> 
</form>