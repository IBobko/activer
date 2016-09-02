<%--suppress XmlDuplicatedId --%>
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
        console.log($(e).find("img"));
        $('#text').text($(e).find('span').text());
        $('#id').val($(e).attr('dream-id'));




        setPhoto($(e).find("img")[0]);


    };


    var setPhoto = function(img2) {
        console.log(img2.src);
        var img = new Image();
        img.src = img2.src;

        var initialData = $(img).cropper('getCanvasData');

        var width = 0;
        var height = 0;
        var r = 0;

        if (initialData.naturalWidth > 150) {
            r = initialData.naturalWidth/initialData.naturalHeight;
            width = 150;
            height = width * r;
        }

        if (initialData.naturalHeight > 150) {
            r = initialData.naturalWidth/initialData.naturalHeight;
            height = 150;
            width = height * r;
        }
        var t = $(img).cropper('getCroppedCanvas',{
            width:width,
            height:height
        });


        t[0].style.marginTop = "-23px";

        $("#preview").html(t[0]);


    };


    $("#choosePhoto").change(function () {
        var fileData = $(this).prop('files')[0];
        var reader = new FileReader();

        reader.onload = function (frEvent) {
            var img = new Image();
            img.style.marginTop = "-23px";
            img.src = frEvent.target.result;

            var initialData = $(img).cropper('getImageData');

            var width = 0;
            var height = 0;
            var r = 0;

            if (initialData.naturalWidth > 150) {
                r = initialData.naturalWidth/initialData.naturalHeight;
                width = 150;
                height = width * r;
            }

            if (initialData.naturalHeight > 150) {
                r = initialData.naturalWidth/initialData.naturalHeight;
                height = 150;
                width = height * r;
            }
            var t = $(img).cropper('getCroppedCanvas',{
                width:width,
                height:height
            });

            t.style.marginTop = "-23px";

            $("#preview").html(t);
        };
        reader.readAsDataURL(fileData);
    });


</script>

<c:forEach items="${dreams}" var="dream">
    <div dream-id="${dream.id}" onclick="edit(this)">
        <img style="width:100px;float:left" class="media-object" src="${staticFiles}/${dream.photo}.">
        <span>${dream.text}</span>
        <a class="std-button btn btn-default"
           href="<c:url value="/settings/dreams/remove?dream=${dream.id}"/>">Удалить</a>
    </div>
</c:forEach>
