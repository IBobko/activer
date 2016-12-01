<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/profile" prefix="profile" %>

<style type="text/css">
    .modal {
        font-weight: normal;
    }

    .datingBlock {
        padding: 30px;
    }
</style>

<div class="modal fade" id="pleaseWaitingWindow">
    <div class="modal-dialog modal-sm">
        <div class="modal-content" style="padding: 30px">
            <img src="<c:url value="/resources/img/progress.gif"/>" style="margin: 0 30px"/>
            <div style="margin-top:30px;">Подождите, пока сервер подберет вам собеседника.</div>
        </div>
    </div>
</div>

<h3 style="color: #3F51B5;font-weight:bold;">Знакомства</h3>
<div>Для того, чтобы начать знакомиться с новыми людьми просто
    выберите режим ниже. Каждый режим обладает своими особенностями,
    поэтому рекомендуем ознакомиться с описанием.
</div>

<div class="datingBlock">
    <img src="<c:url value="/resources/img/flirt.jpg"/>" style="float: left;margin: 0 30px"/>
    <h4 style="color: #3F51B5;font-weight:bold;">Флирт</h4>
    У вас есть 5 минут, чтобы познакомиться с собеседником и узнать его поближе.
    Проверьте свое обаяние на собеседнике.
    <br/><br/>
    <button id="searchFlirt" class="btn btn-default std-button"><span class="glyphicon glyphicon-ok"></span> Выбрать</button>

    <script type="text/javascript">
        $('#searchFlirt').click(function () {
            $('#pleaseWaitingWindow').modal('show');
            $.get("<c:url value="/dating/search/flirt"/>", function (data) {
                document.location = "<c:url value="/dating/flirt"/>" + "?id=" + data.trim();
            });
        });
    </script>
</div>

<div class="datingBlock">
    <img src="<c:url value="/resources/img/dispute.jpg"/>" style="float: left;margin: 0 30px"/>
    <h4 style="color: #3F51B5;font-weight:bold;">Споры</h4>
    В начале диалога мы даем вам тему и обозначаем вашу позицию относительно проблемы. У вас есть 7 минут, чтобы
    доказать собеседнику, что вы правы на все 100%.
    <br/><br/>
    <button id="searchDispute" class="btn btn-default std-button"><span class="glyphicon glyphicon-ok"></span> Выбрать</button>
    <script type="text/javascript">
        $('#searchDispute').click(function () {
            $('#pleaseWaitingWindow').modal('show');
            $.get("<c:url value="/dating/search"/>", function (data) {
                document.location = "<c:url value="/dating/dispute"/>" + "?id=" + data.trim();
            });
        });
    </script>
</div>

<div class="datingBlock">
    <img src="<c:url value="/resources/img/top.jpg"/>" style="float: left;margin: 0 30px"/>
    <h4 style="color: #3F51B5;font-weight:bold;">TOP LINE</h4>
    Хотите больше знакомств и друзей? Просто нажмите на кнопку ниже и попадите в TOP LINE.
    <br/><br/>
    <a class="btn btn-default std-button" href="<c:url value="/top-line"/>"><span class="glyphicon glyphicon-ok"></span> Выбрать</a>
</div>

<ul class="list-inline">
<c:forEach var="topLine" items="${topLines}">
    <li>
        <a href="<c:url value="/profile/id${topLine.account.id}"/>">

            <img title="<c:out value="${topLine.message}"/>" style="width:100px;" src="${staticImages}/${topLine.account.photo60x60}"/>
        </a>
    </li>
</c:forEach>
</ul>


