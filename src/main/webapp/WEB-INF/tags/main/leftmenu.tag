<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="sidebar-nav">
    <li class="active">
        <a href="<c:url value="/profile"/>">
            <span class="fa fa-user"></span>
            Моя страница
        </a>
    </li>
    <li>
        <a href="<c:url value="/friend"/>">
            <span class="fa fa-smile-o"></span>
            Друзья
        </a>
    </li>
    <li>
        <a href="<c:url value="/message/2"/>">
            <span class="fa fa-envelope-o"></span>
            Сообщения<span class="badge">12</span>
        </a>
    </li>
    <li>
        <a href="<c:url value="/photos/"/>">
            <span class="fa fa-photo"></span>
            Фотографии<span class="badge">3</span>
        </a>
    </li>
    <li>
        <a href="<c:url value="/videos/"/>">
            <span class="fa fa-play-circle"></span>
            Видео
        </a>
    </li>
</ul>
