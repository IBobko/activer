<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link href="/ROOT/resources/cropper.css" rel="stylesheet">
<script src="/ROOT/resources/cropper.js"></script>


<script type="text/javascript">
    $(function () {
        var $image = $('#image');

        var canvasData;

        $('#modal').on('show.bs.modal', function () {
            var cropBoxData =
            $image.cropper({

                // autoCropArea: 0.5,
//
                built: function () {
                    //$image.cropper('setCanvasData', canvasData);
                    $image.cropper('setCropBoxData', {
                        width:250,
                        height:200
                    });
                }
            });
        }).on('hidden.bs.modal', function () {
            cropBoxData = $image.cropper('getCropBoxData');
            data = $image.cropper('getData');
            canvasData = $image.cropper('getCroppedCanvas',{
                width: 250,
                height: 200
            });
            console.log(data);
            $('#preview').html(canvasData);
            $image.cropper('destroy');
        });
    });

</script>

<!-- Modal -->
<div class="modal fade" id="modal" aria-labelledby="modalLabel" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modalLabel">Crop the image</h4>
            </div>
            <div class="modal-body">
                <div>
                   <img id="image" style="position:absolute" src="/ROOT/resources/img/image.jpg" />
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <a onclick='alert($("#image").cropper("crop").getData());'>sssssssssssss</a>
            </div>
        </div>
    </div>
</div>


<a href="<c:url value="/photos"/>" class="std-button btn btn-default"><span
        class="glyphicon glyphicon-arrow-left"></span>&nbsp;Назад</a>

<h3 class="title">Добавление фотографии</h3>

<table>
    <tr>
        <td width="300" id="preview">

        </td>
        <td>Здесь вы можете добавить новую фотографию

            <c:url value="/photos/upload" var="action"/>

            <form:form method="post" enctype="multipart/form-data" commandName="photoForm" action="${action}">
                <form:hidden path="album"/>
                <form:input type="file" path="file"
                            cssStyle="cursor:pointer;position:absolute;left:350px;height:34px;opacity: 0;overflow: hidden;width:165px"/>
                <a href="#" class="std-button btn btn-default"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Выбрать
                    фото</a>

                <input type="submit" value="Загрузить фото"/>
            </form:form></td>
    </tr>
</table>



<script type="text/javascript">


    $('[name="file"]').change(function(){
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#image').attr('src',e.target.result);


            $('#modal').modal('show');
        };
        reader.readAsDataURL(this.files[0]);

    });




//    function readURL(input) {
//
//        if (input.files && input.files[0]) {
//
//
//            reader.onload = function (e) {
//                $("#preview").html("<img src='" + e.target.result + "'/>");
//            }
//
//            reader.readAsDataURL(input.files[0]);
//        }
//    }
//
//    $("[name='file']").change(function(){
//        readURL(this);
//    });

</script>