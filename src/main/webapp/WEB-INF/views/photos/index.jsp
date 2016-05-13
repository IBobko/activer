<%--@elvariable id="staticFiles" type="java.lang.String"--%>
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
    #albums {
        margin:0px;
        padding:0px;
    }

    #albums li {
        width: 350px;
        /*background-color: #2aabd2;*/
        list-style: none;
        text-align: center;
        border: 3px solid #e4e4e4;
        height:250px;
        float:left;
        margin-right:30px;
        cursor:pointer;
    }
</style>

<a class="std-button btn btn-default" style="float:right" href="<c:url value="/photos/edit"/>"><span
        class="glyphicon glyphicon-plus"></span> Добавить альбом</a>
<ul class="photo_menu">
    <li>Мои альбомы</li>
    <li>Все фотографии</li>
</ul>

<ul id="albums">
    <c:forEach items="${albums}" var="album">
        <li onclick='goToAlbum(${album.id})' id="album${album.id}"><div class="descriptionWindow" onmouseout="albumOut(${album.id})"style="position:absolute;height:202px;background-color:#2b669a;width:344px;opacity: 0.7;display:none">${album.description}</div>
            <a href="<c:url value="/photos/edit?id=${album.id}"/>"><span class="fa fa-cog"
                                                                         style="font-size:20px;float:right"></span></a>
            <div onmousemove="albumIn(${album.id})" style="height: 202px;" class="photoWindow">
                <c:if test="${empty album.cover}">
                    <span class="fa fa-camera-retro" style="margin-top:45px;font-size:120px"></span>
            </c:if>
                <c:if test="${not empty album.cover}">
                    <img src="${staticFiles}/${album.cover.middlePath}." style="width:344px;height:180px"/>
                </c:if>
            </div>

            <div style="overflow: hidden;margin:10px;text-align: left">
                <div style="float:right"><span style="font-size:22px;color: #92a0c3" class="fa fa-camera"></span>&nbsp;0
                </div>
                    ${album.name}</div>
        </li>
    </c:forEach>
</ul>

<script>

    function goToAlbum(id) {
        document.location = "<c:url value="/photos/album"/>" + id;
    }

    function albumIn(id){
        $('#album' + id).find('.descriptionWindow').show();
    }

    function albumOut(id){
        $('#album' + id).find('.descriptionWindow').hide();
    }
</script>