<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/resources/yoxview/yoxview.css"/>" rel="stylesheet"/>
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

    #photos li {
        float: left;
        margin: 10px;
        list-style: none;
    }

    #yoxview_popupWrap {
        z-index: 100000;
    }

    .yoxview_popupBarPanel {
        z-index: 100000;
    }
</style>


<script src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"/>"></script>

<script type="text/javascript">
    jQuery.noConflict();
    jQuery(document).ready(function () {

        jQuery(".yoxview").yoxview(
                {
                    backgroundColor: '#000000',
                    backgroundOpacity: 0.8,
                    lang: 'ru',
                });

    });
</script>


<script type="text/javascript" src="<c:url value="/resources/yoxview/yoxview-init.js"/>"></script>

<a href="<c:url value="/photos"/>" class="std-button btn btn-default" style="float:left"><span
        class="fa fa-arrow-left"></span>&nbsp;Назад</a>

<a class="std-button btn btn-default" style="float:right" href="<c:url value="/photos/add?album=${album}"/>"><span
        class="glyphicon glyphicon-plus"></span> Добавить фото</a>
<ul style="margin-top:36px;margin-left:130px" class="photo_menu">
    <li>${album.name} (${photos.size()})</li>
</ul>

<ul id="photos" class="thumbnails yoxview">
    <c:forEach items="${photos}" var="photo">
        <li>
            <a href="${staticFiles}/${photo.path.trim()}.jpg"><img style="width:200px; height:130px"
                                                                   src="${staticFiles}/${photo.middlePath.trim()}.jpg"
                                                                   alt="${photo.description}"
                                                                   title="${photo.description}"/></a>
        </li>
    </c:forEach>
</ul>
