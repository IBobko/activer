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
</style>


<script src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"/>"></script>

<script type="text/javascript">
    jQuery.noConflict();
    jQuery(document).ready(function () {

        jQuery(".yoxview").yoxview(
                {
                    backgroundColor: '#000000',
                    backgroundOpacity: 0.8,
                    lang: 'ru'
                });
        if (document.location.hash != "") {
            var result = document.location.hash.match(/(\S+)=(\d+)/);
            $(".yoxview li a#photo-" + result[2] + " img").click();
        }

    });
</script>

<script type="text/javascript" src="<c:url value="/resources/yoxview/yoxview-init.js"/>"></script>


<a href="<c:url value="/photos"/>" class="std-button btn btn-default" style="float:left"><span
        class="fa fa-arrow-left"></span>&nbsp;Назад</a>

<div style="float:right">
    <input type="file" id="addingPhoto" style="cursor:pointer;position:absolute;height:34px;opacity: 0;width:165px"/>
    <a href="#" class="std-button btn btn-default"><span class="fa fa-pencil"></span>&nbsp;Добавить фото</a>
</div>

<script type="text/javascript">
    $('#addingPhoto').change(function (event) {
        var files = event.target.files;
        var data = new FormData();

        data.append("file", files[0]);
        data.append("album", ${album.id});

        $.ajax({
            url: '<c:url value="/photos/upload"/>',
            type: 'POST',
            data: data,
            cache: false,
            dataType: 'json',
            processData: false, // Don't process the files
            contentType: false, // Set content type to false as jQuery will tell the server its a query string request
            success: function (data) {
                $("#photos").append("<li><a href=\"${staticFiles}/" + data["originalPath"] + ".jpg\"><img photo-id=\"" + data["id"] + "\" style=\"width:200px; height:130px\" src=\"${staticFiles}/" + data["middlePath"] + "${photo.middlePath.trim()}.jpg\" alt=\"${photo.description}\"></a></li>");

                jQuery(".yoxview").yoxview(
                        {
                            backgroundColor: '#000000',
                            backgroundOpacity: 0.8,
                            lang: 'ru',
                        });
            },
            error: function (jqXHR, textStatus, errorThrown) {
                // Handle errors here
                console.log('ERRORS: ' + textStatus);
                // STOP LOADING SPINNER
            }
        });
    });
</script>

<ul style="margin-top:36px;margin-left:130px" class="photo_menu">
    <li>${album.name} (${photos.size()})</li>
</ul>

<ul id="photos" class="thumbnails yoxview">
    <c:forEach items="${photos}" var="photo">
        <li>
            <a id="photo-${photo.id}" href="${staticFiles}/${photo.path.trim()}.jpg"><img photo-id="${photo.id}"
                                                                                          style="width:200px; height:130px"
                                                                                          src="${staticFiles}/${photo.middlePath.trim()}.jpg"
                                                                                          alt="${photo.description}"
                                                                                          title="${photo.description}"/></a>
        </li>
    </c:forEach>
</ul>

<script type="text/javascript">
    function deletePhoto() {
        var data = {
            photoId: window.currentImage
        };
        $.get("<c:url value="/photos/delete"/>", data, function (data) {
            jQuery.yoxview.next();
            $('.yoxview').find("[photo-id='" + data + "']").closest("li").remove();

        });

    }
</script>