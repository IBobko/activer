<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<h3>Execute mysql query from file</h3>

<form method="post" enctype='multipart/form-data'>
<div class='form-group'>
	<label for="file-id">File</label>
	<input type="file" name="sqlfile" class='form-control' id="file-id"/>
</div>
<div class='form-group'>
	<input type="submit" value="Послать" class='btn btn-primary'/>
</div>
</form>