<%--suppress ELValidationInJSP --%>
<%--suppress XmlDuplicatedId --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style type="text/css">
    td {
        padding: 4px;
    }

    #page-content-wrapper {
        font-weight: normal;
    }
</style>

<link href="<c:url value="/resources/cropper.css"/>" rel="stylesheet">
<script src="<c:url value="/resources/cropper.js"/>"></script>


<script type="text/javascript">
    $(function () {
        var $image = $('#renderImage');
        $('#modal').on('show.bs.modal', function () {
            $image.cropper({
                aspectRatio:1,

                built: function () {
                    //$image.cropper('setCanvasData', canvasData);
                    $image.cropper('setCropBoxData', {
                        width:200,
                        height:200
                    });
                }
            });
        }).on('hide.bs.modal', function () {

            var canvasData = $image.cropper('getCroppedCanvas',{
                width: 250,
                height: 200
            });

            var canvasDataForRender = $image.cropper('getCroppedCanvas',{
                width: 150,
                height: 120
            });

            $image.cropper('destroy');

            canvasData.toBlob(function (blob) {
                var formData = new FormData();
                formData.append('croppedImage', blob);
                $.ajax('<c:url value="/settings/croppedSaveImage"/>', {
                    method: "POST",
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function () {
                        $('#image').html(canvasDataForRender);
                    },
                    error: function () {
                        console.log('Upload error');
                        console.log('%c Oh my heavens! ', 'background: #222; color: #bada55');
                    }
                });
            });

            $('#modal').modal('show');
        });
    });

</script>

<!-- Modal -->
<div class="modal fade" id="modal" aria-labelledby="modalLabel" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modalLabel">Выберите область</h4>
            </div>
            <div class="modal-body" style="height:500px;width:600px">
                <img id="renderImage" style="display:none" src="${staticFiles}/UJi9sX.jpg.jpg" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Выбрать</button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="gallery" aria-labelledby="modalLabel" role="dialog" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Выберите фотографию</h4>
            </div>
            <div class="modal-body" style="height:500px;overflow-y: scroll">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>




<table>
    <tr>
        <td valign="top" width="250">
            <div style="margin-top:44px;text-align: center" id="image">
                <img style="width:152px;height:152px;" src="${staticFiles}/${photo.photoAvatar}."/>
            </div>
        </td>
        <td>
            <h4 style="color:#337ab7;font-weight:bold">Фото профиля</h4>
            Данное фото будет отображаться на главной странице профиля.
            <br/>
            Максимальный размер фото 10Mb.
            <form method="post" id="photoForm" action="<c:url value="/settings/uploadphoto"/>" enctype="multipart/form-data">
                <input id="choosePhoto" name="photo" type="file"
                       style="cursor:pointer;position:absolute;height:34px;opacity: 0;overflow: hidden;width:165px">
                <div style="margin:20px 0">
                    <a id="choosePhotoButton" href="#" class="std-button btn btn-default"><span
                            class="fa fa-camera"></span>&nbsp;Выбрать
                        фото</a>

                    <a class="std-button btn btn-default" href="javascript:$('#gallery').modal('show');" type="submit">Выбрать из галереи</a>
                </div>
            </form>

            <script type="text/javascript">

                function selectPhotoFromGallery(e){
                    document.getElementById("renderImage").src = $(e).find('img').attr('src');
                    $('#modal').modal('show');
                    $('#gallery').modal('hide');
                }

                $('#gallery').on('show.bs.modal', function () {
                    var body = $("#gallery").find(".modal-body");
                    $.get("<c:url value="/photos/ajax2"/>",function(data){

                        for (var index in data) {
                            if (data.hasOwnProperty(index)) {
                                body.append("<div style='text-align:center' image-id='"+data[index].id+"' onclick='selectPhotoFromGallery(this)'><img style='width:200px' src='${staticFiles}/" + data[index].smallPath + ".'/></div>");
                                body.append("<br/>");
                            }
                        }
                    });
                });




                $("#choosePhoto").change(function () {

                    var formData = new FormData();
                    var fileData = $('#choosePhoto').prop('files')[0];
                    formData.append('photo', fileData);

                    var reader2 = new FileReader();

                    reader2.onload = function(frEvent) {
                        document.getElementById("renderImage").src = frEvent.target.result;
                        $('#modal').modal('show');
                    };
                    reader2.readAsDataURL(fileData);

                    $.ajax({
                        xhr: function()
                        {
                            var xhr = new window.XMLHttpRequest();
                            //Upload progress
                            xhr.upload.addEventListener("progress", function(evt){
                                if (evt.lengthComputable) {
                                    var percentComplete = evt.loaded / evt.total;
                                    //Do something with upload progress
                                    console.log(percentComplete);
                                    console.log("Progress");
                                }
                            }, false);
                            return xhr;
                        },
                        data: formData,
                        url: '<c:url value="/settings/uploadphoto"/>',
                        type: 'POST',
                        contentType: false,
                        processData: false
                    }).done(function(data){
                        // Здесь возможно что-то нужно
                    }).fail(function(){

                    });
                });
            </script>
            <div style="margin:40px 0">
                <h4 style="color:#337ab7;font-weight:bold">Основная информация</h4>
                <form:form method="post" modelAttribute="mainInfoForm">
                    <table>
                        <tr>
                            <td width="300">
                                Дата рождения
                            </td>
                            <td>
                                <div style="width:202px" class="input-group date form_date col-md-5" data-date=""
                                     data-date-format="dd/MM/yyyy" data-link-format="yyyy-MM-dd">
                                    <form:input type="text" path="birthDate" cssClass="form-control"
                                                cssStyle="background-color: white" readonly="true"/>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span
                                        class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                            </td>
                            <td>
                                <form:errors path="birthDate"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Имя
                            </td>
                            <td>
                                <form:input cssClass="form-control" path="firstName"/>
                            </td>
                            <td>
                                <form:errors path="firstName"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Фамилия
                            </td>
                            <td>
                                <form:input cssClass="form-control" path="lastName"/>
                            </td>
                            <td>
                                <form:errors path="lastName"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Пол
                            </td>
                            <td>
                                <form:select path="sex" cssClass="form-control">
                                    <form:option value="">не указано</form:option>
                                    <form:option value="0">мужской</form:option>
                                    <form:option value="1">женский</form:option>
                                </form:select>
                            </td>
                            <td>
                                <form:errors path="sex"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Семейное положение
                            </td>
                            <td>
                                <form:select path="maritalStatus" cssClass="form-control">
                                    <form:option value="">не выбрано</form:option>
                                    <form:option value="0">не женат/не замужем</form:option>
                                    <form:option value="1">встречаюсь</form:option>
                                    <form:option value="2">помолвлен</form:option>
                                    <form:option value="3">женат/замужем</form:option>
                                    <form:option value="4">влюблён</form:option>
                                    <form:option value="5">все сложно</form:option>
                                    <form:option value="6">в активном поиске</form:option>
                                </form:select>
                            </td>
                            <td>
                                <form:errors path="maritalStatus"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" style="text-align: center">
                                <input type="submit"
                                       class="std-button btn btn-default"
                                       value="Сохранить"/></td>
                            <td></td>
                        </tr>
                    </table>
                </form:form>
            </div>

            <div style="margin: 30px 0">
            <c:url var="advancedPost" value="/settings/advancedPost"/>
            <form:form method="post" action="${advancedPost}" modelAttribute="childrenEducationJobForm">
                <h4 style="color:#337ab7;font-weight:bold">Образование</h4>

                <table>
                    <tr>
                        <td width="300">
                            Страна
                        </td>
                        <td>
                            <form:select path="educationForm.country" cssClass="form-control">
                                <form:option value="">не указано</form:option>
                                <c:forEach items="${countries}" var="country">
                                    <form:option value="${country.code}">${country.name}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Город
                        </td>
                        <td>
                            <form:input path="educationForm.city" cssClass="form-control"/>
                        </td>
                        <td>
                            <form:errors path="educationForm.city"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Университет / Институт
                        </td>
                        <td>
                            <form:input path="educationForm.university" cssClass="form-control"/>
                        </td>
                        <td>
                            <form:errors path="educationForm.university"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Факультет
                        </td>
                        <td>
                            <form:input path="educationForm.faculty" cssClass="form-control"/>
                        </td>
                        <td>
                            <form:errors path="educationForm.faculty"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Год окончания
                        </td>
                        <td>
                            <form:input path="educationForm.year" cssClass="form-control"   />
                        </td>
                        <td>
                            <form:errors path="educationForm.year"/>
                        </td>
                    </tr>

                </table>
            </div>
            <div style="margin: 30px 0">
                <h4 style="color:#337ab7;font-weight:bold">Работа</h4>

                <table>
                    <tr>
                        <td width="300">
                            Место работы
                        </td>
                        <td>
                            <form:input path="jobForm.work" cssClass="form-control"/>
                        </td>
                        <td>
                            <form:errors path="jobForm.work"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Город
                        </td>
                        <td>
                            <form:input path="jobForm.city" cssClass="form-control"/>
                        </td>
                        <td>
                            <form:errors path="jobForm.city"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Должность
                        </td>
                        <td>
                            <form:input path="jobForm.post" cssClass="form-control"/>
                        </td>
                        <td>
                            <form:errors path="jobForm.post"/>
                        </td>
                    </tr>

                </table>
            <div style="margin: 30px 0">
                <h4 style="color:#337ab7;font-weight:bold">Дети</h4>
                <table>
                    <tr>
                        <td width="300">
                            Имя
                        </td>
                        <td>
                            <form:input path="childrenForm.name" cssClass="form-control"/>
                        </td>
                        <td>
                            <form:errors path="childrenForm.name"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Год рождения
                        </td>
                        <td>
                            <form:input path="childrenForm.year" cssClass="form-control"/>
                        </td>
                        <td>
                            <form:errors path="childrenForm.year"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"><input type="submit" value="Сохранить" class="std-button btn btn-default"/></td>
                    </tr>
                </table>
            </form:form>
            </div>

            <h4 style="color:#337ab7;font-weight:bold">Привязка к PayPal</h4>
            <form:form method="post" modelAttribute="payPalForm">
                <table>
                    <tr>
                        <td width="300">
                            PayPal account
                        </td>
                        <td>
                            <form:input type="text" path="login" cssClass="form-control"/>
                        </td>
                        <td>
                            <form:errors path="login"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"><input type="submit" class="std-button btn btn-default" value="Привязать"/></td>
                        <td></td>
                    </tr>
                </table>
            </form:form>
        </td>
    </tr>

</table>