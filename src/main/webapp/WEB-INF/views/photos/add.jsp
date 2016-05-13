<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<a href="<c:url value="/photos"/>" class="std-button btn btn-default"><span
        class="glyphicon glyphicon-arrow-left"></span>&nbsp;Назад</a>

<h3 class="title">Добавление фотографии</h3>
Здесь вы можете добавить новую фотографию

<c:url value="/photos/upload" var="action"/>

<form:form method="post" enctype="multipart/form-data" commandName="photoForm" action="${action}">
    <form:hidden path="album"/>
    <form:input type="file" path="file"
                cssStyle="cursor:pointer;position:absolute;left:350px;height:34px;opacity: 0;overflow: hidden;width:165px"/>
    <a href="#" class="std-button btn btn-default"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Выбрать
        фото</a>

    <input type="submit" value="Загрузить фото"/>
</form:form>
