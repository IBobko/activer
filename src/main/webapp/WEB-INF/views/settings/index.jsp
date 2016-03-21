<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Фото загружаем ЗДЕСЬ</h1>
<form method="post" action="<c:url value="/settings/uploadphoto"/>" enctype="multipart/form-data">
        <input type="file" name="photo">
        <input type="submit" value="Загрузить фото">
</form>

