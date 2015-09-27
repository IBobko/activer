<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h3>Я могу</h3>

<form:form method="post" commandName="iCanForm">
    <form:hidden path="id"/>
    <table>
        <tr>
            <td>Заголовок:</td>
            <td><form:input path="title"/></td>
        </tr>
        <tr>
            <td>Описание:</td>
            <td><form:input path="description"/></td>
        </tr>
        <tr>
            <td>Тэги:</td>
            <td><form:input path="marks"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"/></td>
        </tr>
    </table>
</form:form>
