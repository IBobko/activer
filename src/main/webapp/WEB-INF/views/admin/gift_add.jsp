<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<a href="<c:url value="/admin/gifts"/>" class="std-button btn btn-default"><span
        class="glyphicon glyphicon-arrow-left"></span>&nbsp;Назад</a>

<h3 class="title">Добавление подарка</h3>


<div style="margin: 30px 0">

    <c:url value="/admin/gifts/upload" var="postUrl"/>

    <form:form method="post" action="${postUrl}" commandName="giftAddForm" enctype="multipart/form-data">
        <form:hidden path="id"/>
        <c:if test="${not empty giftAddForm.id}">

            <img src="${staticFiles}/${giftAddForm.fileName}.jpg"/>

        </c:if>
        <div style="margin: 15px 0">
            <form:input id="choosePhoto" type="file" path="photo" class="fileChoice"/>
            <a href="#" class="std-button btn btn-default">
                <span class="glyphicon glyphicon-pencil"></span>&nbsp;Выбрать фото\
            </a>
        </div>

        <form:select path="category">
            <form:option value=""></form:option>
            <c:forEach items="${categories}" var="category">
                <form:option value="${category.id}">${category.name}</form:option>
            </c:forEach>
        </form:select>

        <form:textarea type="text" path="description"/><br/>

        <form:checkbox path="enabled"/> Активно?

        <input type="submit" value="Загрузить Подарок" class="std-button btn btn-default"/>

    </form:form>

</div>

