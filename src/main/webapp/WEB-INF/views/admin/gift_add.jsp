<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<a href="<c:url value="/admin/gifts"/>" class="std-button btn btn-default"><span
        class="glyphicon glyphicon-arrow-left"></span>&nbsp;Назад</a>

<h3 class="title">Добавление подарка</h3>

<style type="text/css">
    table td {
        padding: 10px;
    }
</style>

<div style="margin: 30px 0">

    <c:url value="/admin/gifts/upload" var="postUrl"/>

    <form:form method="post" action="${postUrl}" modelAttribute="giftAddForm" enctype="multipart/form-data">
        <form:hidden path="id"/>
        <c:if test="${not empty giftAddForm.id}">

            <img src="${staticFiles}/${giftAddForm.fileName}.jpg"/>

        </c:if>
        <div style="margin: 15px 0">
            <form:input id="choosePhoto" type="file" path="photo" class="fileChoice"/>
            <a href="#" class="std-button btn btn-default">
                <span class="glyphicon glyphicon-pencil"></span>&nbsp;Выбрать фото
            </a>
        </div>


        <table>
            <tr>
                <td valign="top">Категория</td>
                <td>
                    <form:select cssClass="form-control" path="category">
                        <form:option value=""></form:option>
                        <c:forEach items="${categories}" var="category">
                            <form:option value="${category.id}">${category.name}</form:option>
                        </c:forEach>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td valign="top">Описание</td>
                <td><form:textarea cssStyle="width:600px;height:150px;" cssClass="form-control" path="description"/></td>
            </tr>
            <tr>
                <td valign="top">Цена</td>
                <td><form:input type="text" cssClass="form-control" path="cost"/></td>
            </tr>
            <tr>
                <td>Активно?</td>
                <td><form:checkbox path="enabled" /></td>
            </tr>
        </table>


        <input type="submit" value="Загрузить Подарок" class="std-button btn btn-default"/>

    </form:form>

</div>

