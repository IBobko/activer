<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div id="entrance" class="well">
    <c:if test="${ie != null}">
        <div class="alert alert-danger">
            <ul>
                <c:forEach items="${ie.errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <c:url value="/login" var="login"/>
    <form action="${login}" method="post">
        <table style="width:100%;border:none">
            <tr>
                <td><label for="username">Логин:</label></td>
                <td><input type="text" name="username" id="username" value="" class='form-control'/></td>
            <tr>
                <td><label for="password">Пароль</label></td>
                <td><input type="password" name="password" id="password" value="" class='form-control'/></td>
            </tr>
            <tr>
                <td>Remember Me:</td>
                <td><input type="checkbox" name="remember-me"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="login" value="Войти" class='btn btn-default'/></td>
            </tr>
            <tr>
                <td></td>
                <td style="text-align: center">
                    <a href="<c:url value="/auth/forgot"/>" class="btn btn-default">Забыли пароль</a>
                    <a href="<c:url value="/auth/signup"/>" class="btn btn-default">Регистрация</a>
                </td>
            </tr>
        </table>
    </form>
</div>