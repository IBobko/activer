<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<h1>Регистрация</h1>
<hr/>
<div class="alert alert-info">Все поля обязательны для заполнения.</div>
<form method="post">
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
        <div class="form-group">
            <label for="promoCode">Промо-код</label>
            <input type="text" class="form-control" id="promoCode" placeholder="Код" name="promoCode"/>
        </div>
        <div class="form-group">
            <label for="firstNameInput">Имя</label>
            <input type="text" class="form-control" id="firstNameInput" placeholder="Введите имя" name="firstName"/>
        </div>
        <div class="form-group">
            <label for="lastNameInput">Фамилия</label>
            <input type="text" class="form-control" id="lastNameInput" placeholder="Введите фамилию" name="lastName"/>
        </div>
        <div class="form-group">
            <label for="emailInput">Email</label>
            <input type="email" class="form-control" id="emailInput" placeholder="Введите email" name="email"/>
        </div>
        <br/>
    </div>
    <button type="submit" class="btn btn-default">Зарегистрироваться</button>
</form>
