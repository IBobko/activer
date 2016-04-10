<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- Info panel -->
<div class="container-fluid info-panel">
    <div class="row">
        <ul class="nav nav-pills">
            <li><a href="#">20 подарков</a></li>
            <li><a href="#">364 друга</a></li>
            <li><a href="#">70 фото</a></li>
            <li><a href="#">${profile.interests.size()} интересов</a></li>
        </ul>
    </div>
</div>
<!-- /Info panel -->

<!-- About me -->
<div class="container-fluid about-me">
    <div class="row">
        <p class="status-line"><c:if test="${profile.online}">online</c:if><c:if
                test="${!profile.online}">offline</c:if></p>
    </div>
    <div class="row">
        <div class="media">
            <div class="media-left media-heading">
                <a href="#">
                    <img class="media-object" src="http://todo100.ru:18080/static/upload/files/${photo}.png">
                </a>
            </div>
            <div class="media-body">
                <a href="javascript:window.ACTIVER.Global.submit({action:'add-to-friend','id':1});">Добавить в
                    друзья</a>
                <h4 class="media-heading">${profile.firstName}&nbsp;${profile.lastName}'27</h4>
                <table class="table">
                    <tr>
                        <td>Дата рождения:</td>
                        <td>${profile.birthDate}&nbsp;
                            <!--<a href="#" class="zodiac">
                                <span class="fa fa-venus"></span>
                                Весы
                            </a>-->
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
                                    не указано
                                </c:when>
                                <c:when test="${profile.maritalStatus == 2}">
                                    не указано
                                </c:when>
                                <c:when test="${profile.maritalStatus == 3}">
                                    не указано
                                </c:when>
                                <c:when test="${profile.maritalStatus == 4}">
                                    не указано
                                </c:when>
                                <c:when test="${profile.maritalStatus == 5}">
                                    не указано
                                </c:when>
                                <c:when test="${profile.maritalStatus == 6}">
                                    не указано
                                </c:when>
                                <c:otherwise>
                                    не указано
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td>Дети:</td>
                        <td>не сделано</td>
                    </tr>
                </table>
            </div>
            <div class="media-right hidden-sm hidden-xs">
                <a href="#">
                    <img class="media-object" src="<c:url value="/resources/img/premium.png"/>">
                </a>
            </div>
        </div>
    </div>
</div>
<!-- /About me -->

<!-- Photos -->
<div class="container-fluid photos">
    <div class="row">
        <p class="status-line">Фотографии - 364 <a class="pull-right" href="#">все фото</a></p>
    </div>
    <div class="row">
        <div class="text-justify">
            <div class="gallery">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
                <img class="img-responsive" src="<c:url value="/resources/img/image-not-found.jpg"/>">
            </div>
            <button class="btn btn-default upload-photo">Загрузить фото</button>
        </div>
    </div>
</div>
<!-- /Photos -->

<!-- Interests -->
<div class="container-fluid interests">
    <div class="row">
        <p class="status-line">Мои интересы - ${profile.interests.size()}</p>
    </div>
    <div class="row text-justify">
        <ul class="nav nav-pills">
            <c:forEach items="${profile.interests}" var="interest">
                <li><a href="#">${interest.name}</a></li>
            </c:forEach>
            <li>
                <button class="btn btn-default" onclick="document.location='<c:url value="/settings/interests"/>'">+ Добавить</button>
            </li>
        </ul>

    </div>
</div>
<!-- /Interests -->

<!-- Travels -->
<div class="container-fluid travels">
    <div class="row">
        <p class="status-line">Мои путешествия - ${profile.trips.size()} <a class="pull-right" href="<c:url value="/settings/trips"/>">добавить</a></p>
    </div>
    <div class="row">
        <div class="col-xs-6 col-lg-offset-1">
            <img class="img-responsive" src="<c:url value="/resources/img/map.png"/>">
        </div>
        <div class="col-lg-5 col-xs-6">
            <ul class="list-unstyled">
                <c:forEach items="${profile.trips}" var="trip">
                    <li><a href="#">${trip.country} <span class="hidden-xs hidden-sm">${trip.year}</span></a></li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<!-- /Travels -->

<!-- Dreams -->
<div class="container-fluid dreams">
    <div class="row">
        <p class="status-line">Мои мечты - ${profile.dreams.size()} <a class="pull-right" href="#">добавить</a></p>
    </div>
    <div class="row">

        <c:forEach items="${profile.dreams}" var="dream">
            <div class="col-xs-4">
                <div class="media">
                    <div class="media-left media-middle">
                        <a href="#">
                            <img class="media-object " src="http://todo100.ru:18080/static/upload/files/${dream.photo}.png">
                        </a>
                    </div>
                    <div class="media-body media-middle hidden-sm hidden-xs">
                        <a href="#">${dream.text}</a>
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>
</div>
<!-- /Dreams -->

<!-- Thoughts -->

<div style="display:none" id="wall-template">
    <li class="media">
        <div class="media-body">
            <h4 class="media-heading">%{sender-name} <span> - %{date}</span></h4>
            <p>%{text}</p>
        </div>
    </li>
</div>

<div class="container-fluid thoughts">
    <div class="row">
        <p class="status-line">Мои мысли - 782 <a class="pull-right" href="#">все мысли</a></p>
    </div>
    <div class="row">
        <ul class="media-list" id="profile-wall">
            <c:forEach items="${wall}" var="item">
                <li class="media">
                    <div class="media-body">
                        <h4 class="media-heading">${item.sender.firstName} ${item.sender.lastName}<span> - ${item.date}</span></h4>
                        <p>${item.text}</p>
                    </div>
                </li>
            </c:forEach>


            <form class="add-thought" id="wall">
                <div class="form-group">
                    <textarea class="form-control" id="wall-text" placeholder="Есть мысли?" maxlength="140" rows="2"></textarea>
                            <span class="pull-right">

                            </span>
                    <button type="submit" class="btn btn-default pull-right "><span class="fa fa-pencil"></span>
                        Опубликовать
                    </button>
                </div>
            </form>
            <script type="text/javascript">
                $('#wall').submit(function(){
                    var data = {
                        id: ${profile.id},
                        text: $('#wall-text').val()
                    };
                    $.post('<c:url value="/wall/publish"/>',data,function(response){
                        var data = JSON.parse(response);
                        var template = $('#wall-template').html();
                        template = template.replace("%{sender-name}",data.sender.firstName + " " + data.sender.lastName);
                        template = template.replace("%{text}",data.text);
                        template = template.replace("%{date}",data.date);
                        $('#profile-wall').prepend(template);
                    });
                    return false;
                });
            </script>
        </ul>
    </div>
</div>
<!-- /Thoughts -->

