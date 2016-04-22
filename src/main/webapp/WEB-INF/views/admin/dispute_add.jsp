<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<a href="<c:url value="/admin/dispute"/>" class="std-button btn btn-default"><span
        class="glyphicon glyphicon-arrow-left"></span>&nbsp;Назад</a>

<h3 class="title">Добавление темы</h3>
Здесь вы можете добавить новую темы для обсуждения

<form method="post" action="<c:url value="/admin/dispute/upload"/>" enctype="multipart/form-data">
    Тема
    <input type="text" name="name"/>
    Позиция 1
    <input type="text" name="position1"/>
    Позиция 2
    <input type="text" name="position12"/>
    <input type="submit" value="Добавить тему"/>
</form>