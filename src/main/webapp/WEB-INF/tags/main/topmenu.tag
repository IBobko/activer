<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Navbar -->
<nav class="navbar navbar-default navbar-static-top my-navbar-top">
    <ul class="nav navbar-nav">
        <li class="active"><a href="#">Новости</a></li>
        <li><a href="#">Знакомства</a></li>
        <li><a href="#">Подарки</a></li>
        <li><a href="<c:url value="/settings/"/>">Настройки</a></li>
        <button type="button" class="btn btn-default navbar-btn navbar-right">Выход</button>
    </ul>
</nav>