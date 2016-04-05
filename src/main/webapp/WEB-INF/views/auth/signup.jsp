<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>Регистрация</h1>
<hr/>
<div class="alert alert-info">Все поля обязательны для заполнения.</div>
<form:form method="post" commandName="registerForm">
    <div style="width:350px;">
            <%--@elvariable id="ie" type="ru.todo100.activer.util.InputError"--%>
        <c:if test="${ie != null}">
            <div class="alert alert-danger">
                <ul>

                    <c:forEach items="${ie.errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
            <%--@elvariable id="referAccount" type="ru.todo100.activer.model.AccountItem"--%>
        <c:if test="${referAccount != null}">
            Вы пришли сюда по ссылке ${referAccount}
            <form:input path="refer" type="hidden"/>
        </c:if>

        <div class="form-group">
            <form:label for="promoCode" path="promo">Промо-код</form:label>
            <form:errors path="promo"/>
            <form:input type="text" class="form-control" id="promoCode" placeholder="Код" path="promo"/>
        </div>
        <div class="form-group">
            <form:label for="firstNameInput" path="firstName">Имя</form:label>
            <form:errors path="firstName"/>
            <form:input type="text" class="form-control" id="firstNameInput" placeholder="Введите имя"
                        path="firstName"/>
        </div>
        <div class="form-group">
            <form:label for="lastNameInput" path="lastName">Фамилия</form:label>
            <form:errors path="lastName"/>
            <form:input type="text" class="form-control" id="lastNameInput" placeholder="Введите фамилию"
                        path="lastName"/>
        </div>
        <div class="form-group">
            <form:label for="emailInput" path="email">E-mail</form:label>
            <form:errors path="email"/>
            <form:input type="email" class="form-control" id="emailInput" placeholder="Введите email" path="email"/>
        </div>
        <br/>
    </div>
    <form:button type="submit" class="btn btn-default">Зарегистрироваться</form:button>
</form:form>
