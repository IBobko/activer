<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="mark" tagdir="/WEB-INF/tags/mark" %>

<div class="well" style="width:400px">
    <div><strong>Имя:</strong> ${account.firstName}</div>
    <div><strong>Фамилия:</strong> ${account.lastName}</div>
    <div><strong>Логин:</strong> ${account.username}</div>
    <div><strong>E-mail:</strong> ${account.email}</div>
</div>

<div><a href="<%=request.getContextPath()%>/profile/change">Редактировать профиль</a></div>

Я хочу | <a href="<c:url value="/profile/add_i_want" />">Добавить</a>

<mark:list markType="want" marks="${wants}"/>

Я могу | <a href="<c:url value="/profile/add_i_can" />">Добавить</a>

<mark:list markType="can" marks="${cans}"/>


