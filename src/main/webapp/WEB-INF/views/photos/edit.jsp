<%--suppress HtmlUnknownAttribute --%>
<%--@elvariable id="staticFiles" type="java.lang.String"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style type="text/css">
    .btn-default-hover {
        color: #333;
        background-color: #e6e6e6;
        border-color: #adadad;
        cursor: pointer;
    }

    table td {
        padding: 10px 20px 10px 0;
        vertical-align: top;
    }
</style>

<script type="text/javascript">
    $(function () {
        $('#choosePhoto').mousemove(function () {
            $('#choosePhotoButton').addClass("btn-default-hover");
        }).mouseout(function () {
            $('#choosePhotoButton').removeClass("btn-default-hover");
        });

    })

</script>

<a href="<c:url value="/photos"/>" class="std-button btn btn-default"><span
        class="glyphicon glyphicon-arrow-left"></span>&nbsp;Назад</a>

<form:form modelAttribute="photoAlbumForm" method="post">
    <form:hidden path="id"/>
<div style="margin-top:20px">
    <div style="width:300px;height: 150px;overflow: hidden;float:left;text-align: center">
        <form:hidden path="photoId"/>
        <c:if test="${empty photoAlbumForm.photoId}">
            <img id="albumPhoto" src="<c:url value="/resources/img/notselected.png"/>"
                 style="width:200px;height:150px"/>
        </c:if>

        <c:if test="${not empty photoAlbumForm.photoId}">
            <img id="albumPhoto" src="${staticFiles}/${photoAlbumForm.photoName}." style="width:200px;height:150px"/>
        </c:if>

        <img id="albumPhoto" src="<c:url value="/resources/img/notselected.png"/>" style="width:200px;height:150px"/>
    </div>
    <div style="margin-left:300px">
        <h3 class="title">Редактирование альбома</h3>
        <div>Выберите фото, чтобы сделать его обложкой альбома.</div>
        <br/>

        <a data-toggle="modal" data-target="#photosOfAlbum" class="std-button btn btn-default"><span class="fa fa-plus"></span>&nbsp;Выбрать фото</a>
        &nbsp;&nbsp;&nbsp;
        <c:if test="${photoAlbumForm.id != null}">
            <a href="<c:url value="/photos/delete/${photoAlbumForm.id}"/>"
               class="std-button btn btn-default-hover"><span
                    class="glyphicon glyphicon-remove"></span>&nbsp;Удалить альбом</a>
        </c:if>
        <br/><br/><br/>
        <h3 class="title">Основная информация</h3>
            <table>
                <tr>
                    <td>Название альбома</td>
                    <td><form:input path="name" type="text" class="form-control"/></td>
                </tr>
                <tr>
                    <td>Описание альбома</td>
                    <td><form:textarea path="description" class="form-control"
                                       style="width:500px;height:200px"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button type="submit" class="std-button btn btn-default"><span
                                class="glyphicon glyphicon-ok"></span>&nbsp;Сохранить
                        </button>
                        &nbsp;&nbsp;&nbsp;
                        <a href="<c:url value="/photos"/>" type="submit" class="std-button btn btn-default-hover"><span
                                class="glyphicon glyphicon-remove"></span>&nbsp;Отменить
                        </a>
                    </td>
                </tr>
            </table>
    </div>
</div>
</form:form>




<!-- Modal -->
<div class="modal fade" id="photosOfAlbum" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Фотографии альбома</h4>
            </div>
            <div class="modal-body" style="overflow: hidden">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>
<div style="display:none" id="photoItem">
    <div photo-id="#id" onclick="choosePhoto(this)" style="margin:10px;float:left">
        <img src="${staticFiles}/#photo." style="border:3px solid #b5bdec;width:200px;height:100px">
    </div>
</div>

<script type="text/javascript">

    function choosePhoto(obj) {
        $('#albumPhoto').attr('src', $(obj).find('img').attr('src'));
        $('[name="photoId"]').val(obj.getAttribute("photo-id"));з
        $('#photosOfAlbum').modal('hide');
    }

    $.get("<c:url value="/photos/ajax/?album=${photoAlbumForm.id}"/>", {}, function (response) {
        var photoWindow = $('#photosOfAlbum').find('.modal-body');
        photoWindow.html('');
        var template = $('#photoItem').html();
        for (var index in response) {
            if (response.hasOwnProperty(index)) {
                var line = template;
                line = line.replace("#id", response[index].id);
                line = line.replace("#photo", response[index].middlePath);
                photoWindow.append(line);
            }
        }
    });

</script>

