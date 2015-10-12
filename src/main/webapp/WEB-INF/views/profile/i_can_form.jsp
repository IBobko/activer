<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Я могу</h3>

<form:form method="post" commandName="iCanForm">
    <form:hidden path="id"/>
    <div>Заголовок <form:input path="title" cssClass="form-control"/></div>
    <div>Описание <form:textarea path="description" cssClass="form-control"/></div>
    <div>Тэги <form:input cssClass="form-control" path="marks"/></div>
    <div><form:button type="submit" class="btn btn-default">Сохранить</form:button></div>
</form:form>
