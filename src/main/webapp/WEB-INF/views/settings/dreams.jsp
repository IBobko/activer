<%--@elvariable id="staticImages" type="java.lang.String"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="<c:url value="/resources/cropper.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/cropper.js"/>"></script>

<style type="text/css">
    [dream-id] {
        float: left;
        cursor: pointer;
    }
</style>

<h4 style="color:#337ab7;font-weight:bold">Добавить мечту</h4>

<c:url value="/settings/dreams/upload" var="uploadUrl"/>
<form:form modelAttribute="dreamForm" action="${uploadUrl}" enctype="multipart/form-data">
    <form:input path="id" type="hidden"/>
    <form:input type="hidden" path="removePhoto" value="false"/>
    <table>
        <tr>
            <td style="padding:30px">
                <div id="preview" style="text-align: center;position:absolute;width:150px">
                    <div style="position:absolute;width:145px;display:none;z-index:100" class="removing">
                        <div style="float: right;font-size:16px;"><a href="#" title="Удалить" class="text-danger" ><span class=" fa fa-remove"></span></a></div>
                    </div>
                    <div class="noImage" style="overflow:hidden;background-color:#acb5e9;padding-top:23px;height:150px;">
                        Загрузить изображение<span class="fa fa-photo" style="font-size: 50px"></span>
                    </div>
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
        $('#text').text($(e).find('.dream-description').text());
        $('#id').val($(e).attr('dream-id'));
        var choosePhoto = $("#choosePhoto");
        choosePhoto.replaceWith(choosePhoto.clone());
        initialChangePhoto();
        setPhoto($(e).find("img")[0]);
    };

    var setPhoto = function (img) {
        var preview = $('#preview');
        preview.find("img").remove();
        if (img != undefined) {
            var width = 0;
            var height = 0;
            var r = 0;

            if (img.naturalWidth > 150) {
                r = img.naturalWidth / img.naturalHeight;
                width = 150;
                height = width / r;
            }

            if (height > 150) {
                r = img.naturalHeight / img.naturalWidth;
                height = 150;
                width = height / r;
            }
            var canvas = document.createElement('canvas');
            var ctx = canvas.getContext('2d');
            canvas.width = width;
            canvas.height = height;
            ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
            var previewImage = new Image();
            previewImage.src = canvas.toDataURL();
            preview.find(".noImage").css("display", "none");
            preview.find(".removing").css("display", "block");
            $('[name="removePhoto"]').val(false);
            preview.append(previewImage);
        } else {
            preview.find("div").css("display", "block");
            preview.find(".removing").css("display", "none");
        }
    };

    $(".removing a").click(function(){
        var preview = $('#preview');
        preview.find("div").css("display", "block");
        preview.find(".removing").css("display", "none");
        preview.find("img").remove();
        $('[name="removePhoto"]').val(true);
        var choosePhoto = $("#choosePhoto");
        choosePhoto.replaceWith(choosePhoto.clone());
        initialChangePhoto();
    });

    function initialChangePhoto() {
        $("#choosePhoto").change(function () {
            $('[name="removePhoto"]').val(false);
            var fileData = $(this).prop('files')[0];
            var reader = new FileReader();
            reader.onload = function (frEvent) {
                var img = new Image();
                img.src = frEvent.target.result;
                setPhoto(img);
            };
            reader.readAsDataURL(fileData);
        });
    }
    initialChangePhoto();
</script>

<c:forEach items="${dreams}" var="dream">
    <div dream-id="${dream.id}" style="width:250px;margin: 10px;font-weight:normal;border: #acb5e9 1px dashed;padding:5px" onclick="edit(this)">
        <div style="position:absolute;width:230px;">
            <div style="float: right;font-size:16px;"><a href="<c:url value="/settings/dreams/remove?dream=${dream.id}"/>" title="Удалить" class="text-danger" ><span class=" fa fa-remove"></span></a></div>
        </div>
        <div style="margin:auto;text-align:center;width:150px;height:150px;">
            <c:choose>
                <c:when test="${dream.photo != null && dream.photo != ''}">
                    <img crossOrigin="anonymous" style="max-width:150px;max-height:150px;" src="${staticImages}/${dream.photo}">
                </c:when>
                <c:otherwise>
                    <div style="overflow:hidden;background-color:#acb5e9;padding-top:50px;height:150px;">
                        <span class="fa fa-photo" style="font-size: 50px"></span>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <div style="margin-top:20px" class="dream-description">${dream.text}</div>
    </div>
</c:forEach>
