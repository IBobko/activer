<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

Здесь вы можете добавить новую фотографию

<form method="post" action="<c:url value="/photos/upload"/>" enctype="multipart/form-data">
    <input type="file" name="photo">
    <input type="submit" value="Загрузить фото">
</form>