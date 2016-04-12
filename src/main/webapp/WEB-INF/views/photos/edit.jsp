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

    table td{
        padding:10px 20px 10px 0px;
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


<div style="margin-top:20px">
    <div style="width:300px;height: 150px;overflow: hidden;float:left;text-align: center">
        <img src="<c:url value="/resources/img/notselected.png"/>" style="height:150px"/>
    </div>
    <div style="margin-left:300px">
        <h3 class="title">Редактирование альбома</h3>
        <div>Выберите фото, чтобы сделать его обложкой альбома.<br/>Максимальный размер фото 10MB.</div>
        <br/>
        <input id="choosePhoto" type="file"
               style="cursor:pointer;position:absolute;left:350px;height:34px;opacity: 0;overflow: hidden;width:165px">
        <a id="choosePhotoButton" href="<c:url value="/photos"/>" class="std-button btn btn-default"><span
                class="glyphicon glyphicon-pencil"></span>&nbsp;Выбрать фото</a>
        &nbsp;&nbsp;&nbsp;
        <c:if test="${photoAlbumForm.id != null}">
            <a href="<c:url value="/photos/delete/${photoAlbumForm.id}"/>"
               class="std-button btn btn-default-hover"><span
                    class="glyphicon glyphicon-remove"></span>&nbsp;Удалить альбом</a>
        </c:if>
        <br/><br/><br/>
        <h3 class="title">Основная информация</h3>
        <form:form commandName="photoAlbumForm" method="post">
            <table>
                <tr>
                    <td>Название альбома</td>
                    <td><form:input path="name" type="text" class="form-control"/></td>
                </tr>
                <tr>
                    <td>Описание альбома</td>
                    <td><form:textarea path="description" class="form-control"
                                       style="width:500px;height:200px"></form:textarea></td>
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
        </form:form>
    </div>
</div>

