<%--@elvariable id="staticFiles" type=""--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="<c:url value="/resources/cropper.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/cropper.js"/>"></script>

<style type="text/css">
    [dream-id] {
        float: left;
    }

    [dream-id] a {
        visibility: hidden;
    }

    [dream-id]:hover a {
        visibility: visible;
    }
</style>

<h4 style="color:#337ab7;font-weight:bold">Добавить мечту</h4>

<c:url value="/settings/dreams/upload" var="uploadUrl"/>
<form:form modelAttribute="dreamForm" action="${uploadUrl}" enctype="multipart/form-data">
    <form:input path="id" type="hidden"/>
    <table>
        <tr>
            <td style="padding:30px">
                <div id="preview" style="overflow:hidden;background-color:#acb5e9;padding-top:23px;position:absolute;height:150px;width:150px;text-align: center">
                    Загрузить изображение<span class="fa fa-photo" style="font-size: 50px"></span>
                </div>
                <form:input path="photo" id="choosePhoto" type="file" style="opacity:0;height:150px;width:150px"/>
            </td>
            <td><form:textarea path="text" cssStyle="width:550px;height:100px" cssClass="form-control"/>
                <br/>
                <a onclick="$('#dreamForm').submit()" class="std-button btn btn-default">Сохранить</a>
            </td>
        </tr>

    </table>
</form:form>


<h4 style="color:#337ab7;font-weight:bold">Мои мечты</h4>

<script type="text/javascript">
    var edit = function (e) {
        $('#text').text($(e).find('span').text());
        $('#id').val($(e).attr('dream-id'));
        setPhoto($(e).find("img")[0]);
    };

    var setPhoto = function(img) {
        var width = 0;
        var height = 0;
        var r = 0;

        if (img.naturalWidth > 150) {
            r = img.naturalWidth/img.naturalHeight;
            width = 150;
            height = width * r;
        }

        if (height > 150) {
            r = img.naturalHeight/img.naturalWidth;
            height = 150;
            width = height * r;
        }
        var oc = document.createElement('canvas');
        var canvas = oc.getContext('2d');
        canvas.width = width;
        canvas.height = height;
        canvas.drawImage(img, 0, 0, height,width);

        var previewImage = new Image();
        previewImage.src = oc.toDataURL();
        previewImage.style.marginTop = "-23px";

        $("#preview").html(previewImage);
    };

    $("#choosePhoto").change(function () {
        var fileData = $(this).prop('files')[0];
        var reader = new FileReader();
        reader.onload = function (frEvent) {
            var img = new Image();
            img.src = frEvent.target.result;
            setPhoto(img);
        };
        reader.readAsDataURL(fileData);
    });
</script>

<c:forEach items="${dreams}" var="dream">
    <div dream-id="${dream.id}" onclick="edit(this)">
        <img crossOrigin="anonymous" style="width:100px;float:left" class="media-object" src="${staticFiles}/${dream.photo}.">
        <span>${dream.text}</span>
        <a class="std-button btn btn-default"
           href="<c:url value="/settings/dreams/remove?dream=${dream.id}"/>">Удалить</a>
    </div>
</c:forEach>
