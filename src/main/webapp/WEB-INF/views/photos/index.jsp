<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style type="text/css">
    .photo_menu {
        margin:0;
        padding: 0;
        overflow: hidden;
    }
    .photo_menu li {
        font-size: 18px;
        text-transform: uppercase;
        font-weight: bold;
        color: #3f51b3;
        float:left;
        list-style: none;
        margin:0 50px 20px  0;
    }
</style>

<a class="std-button btn btn-default" style="float:right" href="<c:url value="/photos/edit"/>"><span class="glyphicon glyphicon-plus"></span> Добавить альбом</a>
<ul class="photo_menu">
    <li >Мои альбомы</li>
    <li>Все фотографии</li>
</ul>



<a href="<c:url value="/photos/add"/>">Добавить</a>