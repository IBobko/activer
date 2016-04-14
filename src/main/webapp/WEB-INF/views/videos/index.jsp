<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
<a class="std-button btn btn-default" style="float:right" href="<c:url value="/videos/edit"/>"><span
        class="glyphicon glyphicon-plus"></span> Добавить видео</a>
<ul style="margin-top:36px;" class="video_menu">
    <li>Мои видеозаписи (5 видео)</li>
</ul>

<ul id="albums">
    <c:forEach items="${videos}" var="video">
        <li>${video.body}<br/>
            <pre>${video.description}</pre>
                <%--<div onmousemove="albumIn(${photo.id})" style="height: 202px;" class="photoWindow">--%>
                <%--<img src="http://onoffline.ru/static/upload/files/${photo.middlePath}.jpg" style="width:200px;height:150px"/>--%>
                <%--</div>--%>
        </li>
    </c:forEach>
</ul>
