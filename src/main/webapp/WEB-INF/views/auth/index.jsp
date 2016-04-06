<%--suppress XmlPathReference --%>
<%@ page language="java" trimDirectiveWhitespaces="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style type="text/css">
    #entrance {
        margin: 100px auto 0;
        width: 370px;
        height: 310px;
    }

    label {
        display: block;
        padding-left: 15px;
        text-indent: -15px;
        color: #2aabd2;
    }

    input[type="checkbox"] {
        width: 13px;
        height: 13px;
        padding: 0;
        margin: 2px;
        vertical-align: bottom;
        position: relative;
        top: -1px;
        overflow: hidden;
    }

    button[type="submit"] {
        font-size: 10px;
        font-weight: bold;
        color: #fff;
        background-color: #2f40a0;
        text-transform: uppercase;
        border-radius: 30px;
        border: none;
        padding: 10px 15px;
    }
</style>


<div id="entrance">
    <h4 style="color: #3F51B5;font-weight:bold; text-align: center">Добро пожаловать</h4>
    <div style="text-align: center;font-weight:bold;">Для авторизации в сети пожалуйста заполните поля ниже:</div>
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
    <form action="<c:url value="/login"/>" method="post">
        <div style="padding:10px 0">
            <input type="text" placeholder="E-mail" name="username" id="username" class='form-control'/>
        </div>
        <div style="padding:10px 0">
            <input type="password" placeholder="Пароль" name="password" id="password" class='form-control'/>
        </div>
        <div style="padding:10px 0; text-align: center">
            <label><input type="checkbox" name="remember-me" title="Запомнить меня"/> Запомнить меня</label>
        </div>
        <div style="text-align: center">
            <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-log-in">&nbsp;</span>Войти
            </button>
        </div>
        <div style="padding:10px 0;text-align: center;">
            <a href="<c:url value="/auth/forgot"/>" style="color: #2aabd2">Забыли пароль</a>
        </div>
    </form>
</div>
