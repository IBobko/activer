<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/profile" prefix="profile" %>

<style type="text/css">
    .datingBlock button {
        font-size: 10px !important;
        font-weight: bold !important;
        color: #fff !important;
        background-color: #2f40a0 !important;
        text-transform: uppercase;
        border-radius: 30px !important;
        border: none !important;
        padding: 10px 15px !important;
        margin: 10px 0 !important;
    }

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
    <br/>
    <button id="searchFlirt" class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Выбрать</button>

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
    <br/>
    <button id="searchDispute" class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Выбрать</button>
    <script type="text/javascript">
        $('#searchDispute').click(function () {
            $('#pleaseWaitingWindow').modal('show');
            $.get("<c:url value="/dating/search"/>", function (data) {
                document.location = "<c:url value="/dating/dispute"/>" + "?id=" + data.trim();
            });
        });
    </script>
</div>
<%--<div class="datingBlock">--%>
    <%--<img src="<c:url value="/resources/img/top.jpg"/>" style="float: left;margin: 0 30px"/>--%>
    <%--<h4 style="color: #3F51B5;font-weight:bold;">TOP LINE</h4>--%>
    <%--Хотите больше знакомтс и друзей? Просто нажмите на кнопку ниже и попадите в TOP LINE--%>
    <%--<br/>--%>
    <%--<button class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Выбрать</button>--%>
<%--</div>--%>

