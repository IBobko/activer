<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style type="text/css">
    .video_menu {
        margin: 0;
        padding: 0;
        overflow: hidden;
    }

    .video_menu li {
        font-size: 18px;
        text-transform: uppercase;
        font-weight: bold;
        color: #3f51b3;
        float: left;
        list-style: none;
        margin: 0 50px 20px 0;
    }
</style>
<a href="<c:url value="/videos"/>" class="std-button btn btn-default" style="float:left"><span
        class="glyphicon glyphicon-arrow-left"></span>&nbsp;Назад</a>

<ul style="margin-top:36px;margin-left:130px" class="video_menu">
    <li>Добавление видео</li>
</ul>


<form:form commandName="videoForm" method="post">
    <form:textarea path="body"/>
    <form:textarea path="description"/>
    <input type="submit"/>
</form:form>