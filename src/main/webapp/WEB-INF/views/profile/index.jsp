<%--suppress ALL --%>
<%--@elvariable id="staticImages" type="java.lang.String"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>

<link href="<c:url value="/resources/yoxview/yoxview.css"/>" rel="stylesheet"/>

<!-- Info panel -->
<div class="container-fluid info-panel">
    <div class="row">
        <ul class="nav nav-pills">
            <li><a class="text-color-dark" href="<c:url value="/gifts/id${profile.id}"/>">${profile.gifts.size()} подарков</a></li>
            <li><a class="text-color-dark" href="<c:url value="/friend/list/id${profile.id}"/>">${friends.friends.size()} друга</a></li>
            <li><a class="text-color-dark" href="<c:url value="/photos/?accountId=${profile.id}"/>">${photos.size()} фото</a></li>
            <li><a class="text-color-dark" href="<c:url value="/videos/?accountId=${profile.id}"/>">${profile.videos.size()} видео</a></li>
            <li><a class="text-color-dark" href="#interests">${profile.interests.size()} интересов</a></li>
        </ul>
    </div>
</div>
<!-- /Info panel -->

<!-- About me -->
<div class="container-fluid about-me">
    <div class="row">
        <p class="status-line">
            <c:if test="${profile.online}">online</c:if>
            <c:if test="${!profile.online}">offline</c:if>
        </p>
    </div>
    <div class="row">
        <c:if test="${profile.showPremium}">
            <div class="media-right hidden-sm hidden-xs" style="float:right">
                <a href="#">
                    <c:if test="${profile.status == 'ROLE_USER'}">
                        <img class="media-object" src="<c:url value="/resources/img/statuses/0.png"/>">
                    </c:if>
                    <c:if test="${profile.status == 'ROLE_PARTNER'}">
                        <img class="media-object" src="<c:url value="/resources/img/statuses/1.png"/>">
                    </c:if>
                    <c:if test="${profile.status == 'ROLE_CREATOR'}">
                        <img class="media-object" src="<c:url value="/resources/img/statuses/2.png"/>">
                    </c:if>
                </a>
            </div>
        </c:if>
        <div class="media">
            <div class="media-left media-heading yoxview">
                <a href="${staticImages}/${showingPhoto}">
                    <img alt="First" title="<c:if test="${profile.my}"><a href='<c:url value="/settings"/>'>Изменить</a></c:if>" class="media-object" src="${staticImages}/${photo}">
                </a>
            </div>
            <div class="media-body">

                <c:if test="${!profile.my}">
                    <c:if test="${profile.friend}">
                        <a href="<c:url value="/friend/delete/${profile.id}"/>">Убрать из друзей</a>
                    </c:if>

                    <c:if test="${!profile.friend}">
                        <a href="<c:url value="/friend/add/${profile.id}"/>">Добавить в друзья</a>
                    </c:if>
                </c:if>


                <h4 class="media-heading">${profile.firstName}&nbsp;${profile.lastName}<c:if test="${profile.age ne 0}">'${profile.age}</c:if></h4>
                <table class="table">
                    <tr>
                        <td width="200">Дата рождения:</td>
                        <td>${profile.birthDate}&nbsp;
                            <a href="#" class="zodiac">
                                <c:choose>
                                    <c:when test="${profile.zodiac == 1}">
                                        <span class="fa">&#9809;</span>
                                        Козерог
                                    </c:when>
                                    <c:when test="${profile.zodiac == 2}">
                                        <span class="fa">&#9810;</span>
                                        Водолей
                                    </c:when>
                                    <c:when test="${profile.zodiac == 3}">
                                        <span class="fa">&#9811;</span>
                                        Рыбы
                                    </c:when>
                                    <c:when test="${profile.zodiac == 4}">
                                        <span class="fa">&#9800;</span>
                                        Овен
                                    </c:when>
                                    <c:when test="${profile.zodiac == 5}">
                                        <span class="fa">&#9801;</span>
                                        Телец
                                    </c:when>
                                    <c:when test="${profile.zodiac == 6}">
                                        <span class="fa">&#9802;</span>
                                        Близнец
                                    </c:when>
                                    <c:when test="${profile.zodiac == 7}">
                                        <span class="fa">&#9803;</span>
                                        Рак
                                    </c:when>
                                    <c:when test="${profile.zodiac == 8}">
                                        <span class="fa">&#9804;</span>
                                        Лев
                                    </c:when>
                                    <c:when test="${profile.zodiac == 9}">
                                        <span class="fa">&#9805;</span>
                                        Дева
                                    </c:when>
                                    <c:when test="${profile.zodiac == 10}">
                                        <span class="fa">&#9806;</span>
                                        Весы
                                    </c:when>
                                    <c:when test="${profile.zodiac == 11}">
                                        <span class="fa">&#9807;</span>
                                        Скорпион
                                    </c:when>
                                    <c:when test="${profile.zodiac == 12}">
                                        <span class="fa">&#9808;</span>
                                        Стрелец
                                    </c:when>
                                </c:choose>

                            </a>
                        </td>
                    </tr>
                    <tr>
                        <td>Образование:</td>
                        <td>${profile.education.university} - Факультет ${profile.education.faculty}
                            ‘${profile.education.year}</td>
                    </tr>
                    <tr>
                        <td>Работа:</td>
                        <td>${profile.job.post}</td>
                    </tr>
                    <tr>
                        <td>Cемейное положение:</td>
                        <td>
                            <c:choose>
                                <c:when test="${profile.maritalStatus == 0}">
                                    не указано
                                </c:when>
                                <c:when test="${profile.maritalStatus == 1}">
                                    не женат / не замужем
                                </c:when>
                                <c:when test="${profile.maritalStatus == 2}">
                                    встречаюсь
                                </c:when>
                                <c:when test="${profile.maritalStatus == 3}">
                                    женат / замужем
                                </c:when>
                                <c:when test="${profile.maritalStatus == 4}">
                                    влюблен
                                </c:when>
                                <c:when test="${profile.maritalStatus == 5}">
                                    все сложно
                                </c:when>
                                <c:when test="${profile.maritalStatus == 6}">
                                    в активном поиске
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td>Дети:</td>
                        <td>${profile.children.name}&nbsp;${profile.children.year}</td>
                    </tr>
                </table>
            </div>

        </div>
    </div>

</div>
<!-- /About me -->

<!-- Photos -->
<div class="container-fluid photos">
    <div class="row">
        <p class="status-line">Фотографии - ${photos.size()} <a onclick="document.location=this.href" class="pull-right"
                                                                href="<c:url value="/photos"/><c:if test="${!profile.my}">?accountId=${profile.id}</c:if>">все фото</a></p>
    </div>
    <div id="collapsePhoto" class="row panel-collapse collapse <c:if test="${photos.size() != 0}">in</c:if>">
        <div class="text-justify">
            <div class="gallery">
                <c:forEach items="${photos}" var="photo">
                    <a href="<c:url value="/photos/album${photo.albumId}?accountId=${profile.id}#rrr=${photo.id}"/>">
                        <img class="img-responsive" src="${staticImages}/${photo.smallPath}"/>
                    </a>
                </c:forEach>
            </div>
            <c:if test="${profile.my}">
                <button onclick="document.location='<c:url value="/photos"/>';" class="btn btn-default upload-photo">
                    Загрузить фото
                </button>
            </c:if>
        </div>
    </div>
</div>
<!-- /Photos -->

<!-- Interests -->
<div class="container-fluid interests">
    <div class="row">
        <p class="status-line"><a name="interests"></a> Мои интересы - ${profile.interests.size()}</p>
    </div>
    <div id="collapseInterests"
         class="row text-justify panel-collapse collapse <c:if test="${profile.interests.size() != 0}">in</c:if>">
        <ul class="nav nav-pills">
            <c:forEach items="${profile.interests}" var="interest">
                <li><a href="#">${interest.name}</a></li>
            </c:forEach>
            <c:if test="${profile.my}">
                <li>
                    <button class="btn btn-default" onclick="document.location='<c:url value="/settings/interests"/>'">+
                        Добавить
                    </button>
                </li>
            </c:if>
        </ul>

    </div>
</div>
<!-- /Interests -->
<style type="text/css">
    .worldmap {
        fill: white;
        stroke: black;
        stroke-width: 0.5px;
    }

    .worldmap:hover {
        fill: #e0e0e0;
        stroke: black;
        stroke-width: 0.5px;
    }

    .worldmap_1 {
        fill: #f08080;
        stroke: black;
        stroke-width: 0.5px;
    }

    .worldmap_1:hover {
        fill: #ff0000;
        stroke: black;
        stroke-width: 1px;
    }

    .worldmap_2 {
        fill: #8080f0;
        stroke: black;
        stroke-width: 0.5px;
    }

    .worldmap_2:hover {
        fill: #0000ff;
        stroke: black;
        stroke-width: 1px;
    }
</style>
<script src="<c:url value="/resources/js/worldmap.js"/>"></script>


<!-- Travels -->
<div class="container-fluid travels">
    <div class="row">
        <p class="status-line">Мои путешествия - ${profile.trips.size()} <a onclick="document.location=this.href" class="pull-right"
                                                                            href="<c:url value="/settings/trips"/>">добавить</a>
        </p>
    </div>
    <div class="row panel-collapse collapse <c:if test="${profile.trips.size() != 0}">in</c:if>" id="collapseTrips">
        <div id="worldmap" width="640" height="400" style="overflow:hidden;float:left"></div>
        <div class="col-lg-5 col-xs-6">
            <ul class="list-unstyled">
                <c:forEach items="${profile.trips}" var="trip">
                    <li style="width: 400px">${trip.country} - ${trip.city} - ${trip.year}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<!-- /Travels -->
<jsp:useBean id="worldMap" class="ru.todo100.activer.jsp.WorldMap"/>
<script type="text/javascript">

    var map = new WorldMap({
        element: 'worldmap',
        width: 640,
        height: 400,
        c: {${worldMap.generateCData(profile.trips)}}
    })

</script>


<!-- Dreams -->
<div class="container-fluid dreams">
    <div class="row">
        <p class="status-line">Мои мечты - ${profile.dreams.size()} <a onclick="document.location=this.href" class="pull-right" href="<c:url value="/settings/dreams"/>">добавить</a></p>
    </div>
    <div class="row">
        <c:forEach items="${profile.dreams}" var="dream">
            <div class="col-xs-4">
                <div class="media">
                    <div class="media-left media-middle">
                        <a href="#">
                            <img class="media-object" src="${staticImages}/${dream.photo}">
                        </a>
                    </div>
                    <div class="media-body media-middle hidden-sm hidden-xs">
                        <a class="text-color-dark" href="#">${dream.text}</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<!-- /Dreams -->

<!-- Thoughts -->

<div class="container-fluid thoughts">
    <div class="row">
        <p class="status-line">Мои мысли - ${wall.size()}</p>

        <c:if test="${profile.my}">
            <form class="add-thought" id="wall">
                <div class="form-group" style="overflow: hidden">
                    <button type="submit" class="btn btn-default pull-right" style="margin-top:0px;margin-left: 10px;float: right;"><span class="fa fa-pencil"></span> Опубликовать</button>
                    <div style="float:right">
                        <input id="choosePhoto" name="photo" type="file"
                           style="cursor:pointer;position:absolute;height:34px;opacity: 0;overflow: hidden;width:165px">
                        <a href="#" class="std-button btn btn-default"><span class="fa fa-camera"></span>&nbsp;Прикрепить фото</a>
                    </div>
                    <textarea  style="width: calc(100% - 340px);" class="form-control" id="wall-text" placeholder="Есть мысли?" maxlength="140" rows="2"></textarea>
                </div>
                <img src="#" style="max-width:200px; max-height:200px;display: none;" id="renderImage"/>
            </form>
        </c:if>

        <div id="profile-wall">
            <textarea style="display:none" id="wall-template">
                <profile:wall/>
            </textarea>

            <c:forEach items="${wall}" var="item">
                <profile:wall post="${item}"/>
            </c:forEach>

            <script type="text/javascript">
                var m = new window.ACTIVER.Dialog.Messages('#wall-template',"<c:url value="/wall/publish"/>",function(result){
                    $('#profile-wall').prepend(result);
                    jQuery(".yoxview").yoxview( {
                                backgroundColor: '#000000',
                                backgroundOpacity: 0.8,
                                lang: 'ru',
                            });
                });

                var renderImageForPublish = document.getElementById("renderImage");
                var formData = new FormData();
                $('#choosePhoto').change(function(){
                    var fileData = $('#choosePhoto').prop('files')[0];
                    formData.set('photo', fileData);
                    var reader = new FileReader();
                    reader.onload = function(frEvent) {
                        renderImageForPublish.style.display = 'inline';
                        renderImageForPublish.src = frEvent.target.result;
                    };
                    reader.readAsDataURL(fileData);
                });

                $('#wall').submit(function () {
                    $wallText = $('#wall-text');
                    formData.set("id",${profile.id});
                    formData.set("text",$wallText.val());
                    m.submit(formData);
                    // cleaning
                    $wallText.val("");
                    renderImageForPublish.style.display = 'none';
                    formData = new FormData();
                    return false;
                });
            </script>
        </div>
    </div>
</div>
<!-- /Thoughts -->


<script src="<c:url value="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"/>"></script>
<script type="text/javascript">
    jQuery.noConflict();
    jQuery(document).ready(function () {
        jQuery(".yoxview").yoxview({
            backgroundColor: '#000000',
            backgroundOpacity: 0.8,
            lang: 'ru',
        });
    });
</script>
<script type="text/javascript" src="<c:url value="/resources/yoxview/yoxview-init.js"/>"></script>

<script type="text/javascript">
    var loaded = 1;
    var end = false;
    var already_downloading = false;
    var lastScrollTop = 0;
    $(document).scroll(function (e) {
        var st = $(this).scrollTop();
        if (st < lastScrollTop){
            return;
        }
        lastScrollTop = st;

        if (end) return;
        if (already_downloading) return;
        var scroll = $(this).scrollTop() + $(window).height();
        if (scroll > $(this).height() - 200) {
            already_downloading = true;
            $.ajax({
                url: "<c:url value="/wall/ajax${profile.id}"/>",
                data: {
                    page: loaded
                }
            }).done(function (data) {
                already_downloading = false;
                if (data.elements.length == 0){
                    end = true;
                };

                for (var index in data.elements) {
                    $('#profile-wall').append(m.getHtmlPost(data.elements[index]));
                }
                loaded++;
            });
        }
    });

    function deleteWallPost(id) {
        $.get("<c:url value="/wall/remove/"/>" + id,function(response){
            $("[wall-id='"+response+"']").remove();
        })

    }

</script>

<style type="text/css">
    .wallRemove {
    <c:if test="${currentProfileData.id != profile.id}">display:none</c:if>
    }
</style>
