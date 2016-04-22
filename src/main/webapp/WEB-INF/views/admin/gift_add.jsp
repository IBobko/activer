<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="<c:url value="/admin/gifts"/>" class="std-button btn btn-default"><span
        class="glyphicon glyphicon-arrow-left"></span>&nbsp;Назад</a>

<h3 class="title">Добавление подарка</h3>
Здесь вы можете добавить новый подарок

<form method="post" action="<c:url value="/admin/gifts/upload"/>" enctype="multipart/form-data">
    <input type="file" name="photo"/>
    <input type="text" name="name"/>
    <input type="submit" value="Загрузить Подарок"/>
</form>