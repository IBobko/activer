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
        list-style: none;
        margin: 0 50px 20px 0;
    }
</style>
<c:if test="${profile.my}">
    <a class="std-button btn btn-default" style="float:right" href="<c:url value="/videos/edit"/>"><span
            class="glyphicon glyphicon-plus"></span> Добавить видео</a>
</c:if>
<ul style="margin-top:36px;" class="video_menu">
    <li>Видеозаписи</li>
</ul>

<c:forEach items="${profile.videos}" var="video">
    <div>
        <c:if test="${profile.my}">
            <a href="<c:url value="/videos/remove/?id=${video.id}"/>">Удалить</a><br/></c:if>
            ${video.body}<br/>
            ${video.description}
        <hr/>
    </div>
</c:forEach>

