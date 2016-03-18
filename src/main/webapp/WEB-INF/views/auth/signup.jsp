<%--suppress ELValidationInJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1>Регистрация</h1>
<hr/>
<div class="alert alert-info">Все поля обязательны для заполнения.</div>
<form:form method="post" commandName="registerForm">
    <div style="width:350px;">
        <c:if test="${ie != null}">
            <div class="alert alert-danger">
                <ul>
                    <c:forEach items="${ie.errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
        <c:if test="${referAccount != null}">
            Вы пришли сюда по ссылке ${referAccount}
            <form:input path="refer" type="hidden"/>
        </c:if>

        <div class="form-group">
            <label for="promoCode">Промо-код</label>
            <form:input type="text" class="form-control" id="promoCode" placeholder="Код" path="promo"/>
        </div>
        <div class="form-group">
            <label for="firstNameInput">Имя</label>
            <form:input type="text" class="form-control" id="firstNameInput" placeholder="Введите имя" path="firstName"/>
        </div>
        <div class="form-group">
            <label for="lastNameInput">Фамилия</label>
            <form:input type="text" class="form-control" id="lastNameInput" placeholder="Введите фамилию" path="lastName"/>
        </div>
        <div class="form-group">
            <label for="emailInput">Email</label>
            <form:input type="email" class="form-control" id="emailInput" placeholder="Введите email" path="email"/>
        </div>
        <br/>
    </div>
    <button type="submit" class="btn btn-default">Зарегистрироваться</button>
</form:form>
