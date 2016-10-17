<%--suppress XmlDuplicatedId --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<link href="<c:url value="/resources/css/highlight.css"/>" rel="stylesheet">
<link href="<c:url value="/resources/css/bootstrap-switch.css"/>" rel="stylesheet">
<link href="http://getbootstrap.com/assets/css/docs.min.css" rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap-switch.js"/>"></script>

<script src="<c:url value="/resources/js/highlight.js"/>"></script>

<style type="text/css">
    .private {
        font-weight: normal;
    }
</style>

<div class="private">

<h3 style="color:#337ab7;font-weight:bold">Приватность</h3>
Функции данного раздела доступны только премиум пользователям
<p>
    <form:form method="post" modelAttribute="privateForm">
        <form:checkbox path="showOnline" id="switch-state"
                    /> не показывать другим пользователям, что я on-line
        <br/>
        <form:checkbox path="showPremium" id="switch-state"
                    /> не показывать другим пользователям, что я Premium
        <br/>
        <br/>
        <input class="btn btn-default std-button" type="submit" value="Сохранить"/>
    </form:form>
</p>

</div>
<script src="<c:url value="/resources/js/main.js"/>"></script>

<%--Вы активировали Premium аккаунт 15.03.2016. Если вы хотите узнать о подробностях и возможностях Premium--%>
<%--аккаунта, пожалуйста нажмите на кнопку ниже.--%>
<%--<br/>--%>
<%--<button>Кнопка ниже</button>--%>