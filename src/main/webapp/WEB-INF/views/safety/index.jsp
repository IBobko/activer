<%--suppress XmlDuplicatedId --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3 style="color:#337ab7;font-weight:bold">Смена пароля</h3>
<form:form method="post" commandName="changePasswordForm">
    <table>
        <tr>
            <td>
                Введите старый пароль
            </td>
            <td>
                <form:input type="text" path="oldPassword"/>
            </td>
            <td>
                <form:errors path="oldPassword"/>
            </td>
        </tr>
        <tr>
            <td>
                Ваш новый пароль
            </td>
            <td>
                <form:input type="text" path="newPassword"/>
            </td>
            <td>
                <form:errors path="newPassword"/>
            </td>
        </tr>
        <tr>
            <td>
                Подтвердите новый пароль
            </td>
            <td>
                <form:input type="text" path="repeatedPassword"/>
            </td>
            <td>
                <form:errors path="repeatedPassword"/>
            </td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"/></td><td></td>
        </tr>
    </table>
</form:form>





