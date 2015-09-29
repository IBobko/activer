<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>

<div>

    <% if (request.getAttribute("ie") != null)
    { %>
    <div class="alert alert-danger">
        <ul>
            <c:forEach items="${ie.errors}" var="error">
                <li>${error}</li>
            </c:forEach>
        </ul>
    </div>
    <% } %>
    <% if (request.getAttribute("success") != null)
    { %>
    <div class="alert alert-success">
        Изменения успешно сохранены
    </div>
    <% } %>
    <form:form method="post" commandName="changeProfileForm" enctype="multipart/form-data">
        <profile:change_form/>
        <div class="form-group">
            <label for="facePhoto">Фотография профиля</label>
            <form:input type="file" class="form-control" id="facePhoto" placeholder="Подтвердите пароль" path="facePhoto"/>
        </div>

        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Change"/>
        </div>
    </form:form>
    <a href="<c:url value="/profile/"/>">Вернуться</a>
</div>