<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style type="text/css">
    .photo_menu {
        margin: 0;
        padding: 0;
        overflow: hidden;
    }

    .photo_menu li {
        font-size: 18px;
        text-transform: uppercase;
        font-weight: bold;
        color: #3f51b3;
        float: left;
        list-style: none;
        margin: 0 50px 20px 0;
    }
</style>
<a href="<c:url value="/photos"/>" class="std-button btn btn-default" style="float:left"><span
        class="glyphicon glyphicon-arrow-left"></span>&nbsp;Назад</a>

<a class="std-button btn btn-default" style="float:right" href="<c:url value="/photos/add?album=${album}"/>"><span
        class="glyphicon glyphicon-plus"></span> Добавить фото</a>
<ul style="margin-top:36px;margin-left:130px" class="photo_menu">
    <li>Название альбома</li>
</ul>

<ul id="albums">
    <c:forEach items="${photos}" var="photo">
        <li onclick='goToAlbum(${photo.id})' id="album${photo.id}">
            <div onmousemove="albumIn(${photo.id})" style="height: 202px;" class="photoWindow">
                <img src="http://onoffline.ru/static/upload/files/${photo.middlePath}.jpg" style="width:200px;height:150px"/>
            </div>
        </li>
    </c:forEach>
</ul>
