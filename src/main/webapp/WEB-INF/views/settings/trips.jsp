<%--suppress XmlDuplicatedId --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h4 style="color:#337ab7;font-weight:bold">Добавить путешествия</h4>

<form:form commandName="tripForm" method="post">
    <table>
        <tr>
            <td>Страна путешествия</td>
            <td>
                <form:select path="country">
                    <form:option value="">не указано</form:option>
                    <c:forEach items="${countries}" var="country">
                        <form:option value="${country.code}">${country.name}</form:option>
                    </c:forEach>
                </form:select>
            </td>
            <td><form:errors path="country"/> </td>
        </tr>
        <tr>
            <td>Город / Поселение</td>
            <td><form:input path="city"/></td>
            <td><form:errors path="city"/> </td>
        </tr>
        <tr>
            <td>Год путешествия</td>
            <td><form:input path="year"/></td>
            <td><form:errors path="year"/> </td>
        </tr>
        <tr>
            <td colspan="=2"><input type="submit" value="Сохранить"/></td>
            <td></td>
        </tr>
    </table>
</form:form>
<br/><br/>
<h4 style="color:#337ab7;font-weight:bold">Мои путешествия</h4>




<div class="interests">
    <ul class="nav nav-pills">
        <c:forEach items="${trips}" var="trip">
            <li><a href="#">${trip.country}</a><a href="<c:url value="/settings/trips/remove?trip=${trip.id}"/> "><span class="glyphicon glyphicon-remove-circle"></span></a></li>
        </c:forEach>
    </ul>
</div>
