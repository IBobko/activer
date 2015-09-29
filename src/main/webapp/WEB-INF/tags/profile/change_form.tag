<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
<div class="form-group">
    <label for="passwordInput">Пароль</label>
    <form:input type="text" class="form-control" id="passwordInput" placeholder="Введите пароль" path="password"/>
</div>
<div class="form-group">
    <label for="repeatPasswordInput">Подтверждение пароля</label>
    <form:input type="text" class="form-control" id="repeatPasswordInput" placeholder="Подтвердите пароль" path="repeatPassword"/>
</div>
