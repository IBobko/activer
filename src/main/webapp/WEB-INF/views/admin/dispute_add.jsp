<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<a href="<c:url value="/admin/dispute"/>" class="std-button btn btn-default"><span
        class="glyphicon glyphicon-arrow-left"></span>&nbsp;Назад</a>

<h3 class="title">Добавление темы</h3>
Здесь вы можете добавить новую темы для обсуждения

<c:url var="formUrl" value="/admin/dispute/upload"/>

<form:form method="post" commandName="disputeThemeForm" action="${formUrl}" enctype="multipart/form-data">
    Тема
    <form:input path="name" type="text" name="name"/>
    Позиция 1
    <form:input path="position1" type="text"/>
    Позиция 2
    <form:input path="position2" type="text"/>
    <input type="submit" value="Добавить тему"/>
</form:form>
