<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/profile" prefix="profile" %>

<style type="text/css">
    #dating button {
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

<div style="margin:30px" id="dating">
    <div>
        <img src="<c:url value="/resources/img/flirt.jpg"/>" style="float: left;margin: 0 30px"/>
        <h4 style="color: #3F51B5;font-weight:bold;">Флирт</h4>
        У вас есть 5 минут, чтобы познакомиться с собеседником и узнать его поближе.
        Проверьте свое обаяние на собеседнике.
        <br/>
        <button id="searchFlirt" class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Выбрать</button>
        <span style="font-weight: normal">Вы учавствовали 58 раз</span>


        <div id="templatePost" style="display:none">
            <profile:post message="${templatePost}"/>
        </div>

        <script type="text/javascript">
            $('#searchFlirt').click(function () {
                $.get("<c:url value="/dating/search"/>", function (data) {
                    $('#meetingWindow').append("<div>Собеседник найден, пишите!</div>");
                    dialog = data.trim();
                });

            });
        </script>
    </div>

    <br/><br/><br/>


    <div>
        <img src="<c:url value="/resources/img/dispute.jpg"/>" style="float: left;margin: 0 30px"/>
        <h4 style="color: #3F51B5;font-weight:bold;">Споры</h4>
        В начале диалога мы даем вам тему и обозначаем вашу позицию относительно проблемы. У вас есть 7 минут, чтобы
        доказать собеседнику, что вы правы на все 100%.
        <br/>
        <button id="searchDispute" class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Выбрать</button>
        <span style="font-weight: normal">Вы учавствовали 58 раз</span>
        <script type="text/javascript">
            $('#searchDispute').click(function () {
                $('#pleaseWaitingWindow').modal('show');
                $.get("<c:url value="/dating/search"/>", function (data) {
                    document.location = "<c:url value="/dating/dispute"/>" + "?id=" + data;
                });
            });
        </script>
    </div>

    <br/><br/><br/>
    <div>
        <img src="<c:url value="/resources/img/top.jpg"/>" style="float: left;margin: 0 30px"/>
        <h4 style="color: #3F51B5;font-weight:bold;">TOP LINE</h4>
        Хотите больше знакомтс и друзей? Просто нажмите на кнопку ниже и попадите в TOP LINE
        <br/>

        <button id="111" class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Выбрать</button>
        <span style="font-weight: normal">Вы учавствовали 58 раз</span>


        <div id="templatePost1" style="display:none">
            <profile:post message="${templatePost}"/>
        </div>

    </div>


    <div style="width:400px; height:400px;" id="meetingWindow">

    </div>
    <form id="dialogForm">
        <input id="text" type="text"><input type="submit" value="Послать сообщение несчастному"/>
    </form>


    <script type="text/javascript">
        var dialog = <c:if test="${dialog == null}">0</c:if><c:if test="${dialog != null}">${dialog}</c:if>;
        window.ACTIVER.Global.onPRIVATE_MESSAGE = function (data) {

            var post = $('#templatePost').html();
            post = post.replace("%text%", data.message);
            console.log(post);
            $('#meetingWindow').append(post);
            $('#meetingWindow').scrollTop($('#meetingWindow').height());
        };
        $('#dialogForm').submit(function () {
            var data = window.ACTIVER.Global.message;
            data.to = dialog;
            data.message = $('#text').val();
            data.type = "DATING";
            $('#text').val('');
            window.ACTIVER.Global.submit(data);
            return false;
        });
        $('#meetingWindow').scrollTop($('#meetingWindow').height());
    </script>

</div>