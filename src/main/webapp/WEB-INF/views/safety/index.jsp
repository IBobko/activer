<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<h3 style="color:#337ab7;font-weight:bold">Смена пароля</h3>
<form:form method="post">
    <table>
        <tr>
            <td>
                Введите старый пароль
            </td>
            <td>
                <input type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                Ваш новый пароль
            </td>
            <td>
                <input type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                Подтвердите новый пароль
            </td>
            <td>
                <input type="text"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"/></td>
        </tr>
    </table>
</form:form>
<h3 style="color:#337ab7;font-weight:bold">E-mail</h3>
<form:form method="post">
    <table>
        <tr>
            <td>
                Текущий E-mail
            </td>
            <td>
                your current email
            </td>
        </tr>
        <tr>
            <td>
                Новый E-mail
            </td>
            <td>
                <input type="text"/>
            </td>
        </tr>
        <tr>
            <td>
                Введите пароль
            </td>
            <td>
                <input type="text"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"/></td>
        </tr>
    </table>
</form:form>








