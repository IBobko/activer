<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="overflow: hidden">
    <img src="<c:url value="/resources/img/flirt.jpg"/>" style="float: left;margin: 0 30px"/>
    <h4 style="color: #3F51B5;font-weight:bold;">Флирт</h4>
    Вы выбрали "Флирт". У вас есть 5 минут, чтобы познакомиться с собеседником и узнать его поближе.
</div>

<br/><br/><br/>

<div style="overflow: hidden">
    <div style="width:300px;float:left">

        <img src="http://onoffline.ru/static/upload/files/${flirtData.opponentAvatar}.jpg"
             style="width:117px;float:left;margin:10px"/>
        <h4 style="color: #3F51B5;font-weight:bold;">${flirtData.opponentFirstName} ${flirtData.opponentLastName}</h4>
        <div style="margin:10px 0;font-weight: normal">Ваш собеседник</div>
        ${flirtData.age} лет
        <br/>
        <br/>
        <button class="std-button btn btn-default"><span class="glyphicon glyphicon-ok"></span> Добавить в друзья
        </button>
        <button class="std-button btn btn-default"><span class="glyphicon glyphicon-gift"></span></button>

        <h4 style="color: #3F51B5;font-weight:bold;">Интересы</h4>
        <ul>
            <c:forEach items="${flirtData.interests}" var="interest">
                <li>${interest}</li>
            </c:forEach>
        </ul>
    </div>

    <div style="margin-left:300px;">
        <div id="timer">5:00</div>
        <div style="overflow-y: scroll;border:#b6beec 3px solid;height:400px;" id="flirtMessageDialog">
            <div style="overflow: hidden">
                <img src="<c:url value="/resources/img/man.jpg"/>" style="float:left;margin:10px;width:50px;"/>
                Иван<br/><span
                    style="font-weight: normal">Привет, хотел тебя сразу спросить как ты относишься к ремарку</span><span
                    style="float:right">12:10</span>
            </div>

            <div style="overflow: hidden">
                <img src="<c:url value="/resources/img/woman.jpg"/>" style="float:left;margin:10px;width:50px;"/>
                Мария<br/><span style="font-weight: normal">Здравствуй, мне очень нравятся ее романы. Она чудесная женщина.</span><span
                    style="float:right">12:11</span>
            </div>
            <div style="overflow: hidden">
                <img src="<c:url value="/resources/img/man.jpg"/>" style="float:left;margin:10px;width:50px;"/>
                Иван<br/><span style="font-weight: normal">Хочешь я раскрою тебе страшную тайну?</span><span
                    style="float:right">12:11</span>
            </div>
            <div style="overflow: hidden">
                <img src="<c:url value="/resources/img/woman.jpg"/>" style="float:left;margin:10px;width:50px;"/>
                Мария<br/><span style="font-weight: normal">Что это мужчина? Я знаю.:) Просто проверяла тебя</span><span
                    style="float:right">12:12</span>
            </div>
            <div style="overflow: hidden">
                <img src="<c:url value="/resources/img/man.jpg"/>" style="float:left;margin:10px;width:50px;"/>
                Иван<br/><span style="font-weight: normal">Хитро, а ты не такая простая</span><span style="float:right">12:12</span>
            </div>
        </div>
        <div style="margin-top:20px">
            <form id="flirtForm">
                <input type="submit" style="float:right;margin-left:15px" class="std-button btn btn-default"
                       value="Отправить"/>
                <div style="overflow: hidden">
                    <input name="flirtMessage" type="text" class="form-control"/>&nbsp;
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    window.ACTIVER.Global.handlers["FLIRT_MESSAGE"] = function (data) {
        var message = $('#flirtMessageTemplate').html();
        var dateObj = new Date(data.date);
        message = message.replace("#message", data.message);
        message = message.replace("#name", data.from.firstName + " " + data.from.lastName);
        message = message.replace("#avatar", data.from.photo60x60 + ".jpg");
        message = message.replace("#time", dateObj.getHours() + ":" + dateObj.getMinutes());
        $('#flirtMessageDialog').append(message);
        var objDiv = document.getElementById("flirtMessageDialog");
        objDiv.scrollTop = objDiv.scrollHeight;
    };
    $('#flirtForm').submit(function () {
        var data = window.ACTIVER.Global.message;
        var input = $('[name="flirtMessage"]');
        data.to = ${flirtData.id};
        data.message = input.val();
        data.type = "FLIRT_MESSAGE";
        input.val('');
        window.ACTIVER.Global.submit(data);
        return false;
    });
    var dateObj = new Date();
    function timer() {
        setTimeout(function () {
            var date = new Date();
            var diff = new Date(date.getTime() - dateObj.getTime());

            var t = new Date(300000 - diff.getTime());

            var sec = t.getSeconds() < 10 ? "0" + t.getSeconds() : t.getSeconds();

            $('#timer').html(t.getMinutes() + ":" + sec);

            timer();
        }, 1000);
    }
    timer();
</script>

<div style="display:none" id="flirtMessageTemplate">
    <div style="overflow: hidden">
        <img src="http://onoffline.ru/static/upload/files/#avatar" style="float:left;margin:10px;width:50px;"/>
        #name<br/><span style="font-weight: normal">#message</span><span
            style="float:right">#time</span>
    </div>
</div>