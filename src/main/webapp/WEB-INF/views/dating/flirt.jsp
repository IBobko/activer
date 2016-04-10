<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

<br/>
<br/>
<br/>



<div style="width:300px;float:left">

    <img src="<c:url value="/resources/img/man.jpg"/>" style="float:left;margin:10px"/>
    <h4 style="color: #3F51B5;font-weight:bold;">Иван РЕОН</h4>
    ваше собеседник<br/>
27 лет Весы<br/>
    Актер<br/>
    <br/>
    <br/>
    <button id="searchFlirt" class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Добавить в друзья</button>
    <button id="searchFlirt" class="btn btn-default"><span class="glyphicon glyphicon-gift"></span> </button>


    <h4 style="color: #3F51B5;font-weight:bold;">Интересы</h4>
    <ul>
        <li>Общественные работы</li>
        <li>Игра престолов</li>
        <li>Кофе</li>
        <li>Поэзия</li>
        <li>История</li>
        <li>небо</li>
        <li>Кофе</li>

    </ul>


</div>
<div style="margin-left:300px;">
    3:27
<div style="border:#b6beec 3px solid;height:400px;">
    <div style="overflow: hidden">
    <img src="<c:url value="/resources/img/man.jpg"/>" style="float:left;margin:10px;width:50px;"/>
    Иван<br/><span style="font-weight: normal">Привет, хотел тебя сразу спросить как ты относишься к ремарку</span><span style="float:right">12:10</span>
    </div>

    <div  style="overflow: hidden">
        <img src="<c:url value="/resources/img/woman.jpg"/>" style="float:left;margin:10px;width:50px;"/>
        Мария<br/><span style="font-weight: normal">Здравствуй, мне очень нравятся ее романы. Она чудесная женщина.</span><span style="float:right">12:11</span>
    </div>
    <div  style="overflow: hidden">
        <img src="<c:url value="/resources/img/man.jpg"/>" style="float:left;margin:10px;width:50px;"/>
        Иван<br/><span style="font-weight: normal">Хочешь я раскрою тебе страшную тайну?</span><span style="float:right">12:11</span>
    </div>
    <div  style="overflow: hidden">
        <img src="<c:url value="/resources/img/woman.jpg"/>" style="float:left;margin:10px;width:50px;"/>
        Мария<br/><span style="font-weight: normal">Что это мужчина? Я знаю.:) Просто проверяла тебя</span><span style="float:right">12:12</span>
    </div>
    <div  style="overflow: hidden">
        <img src="<c:url value="/resources/img/man.jpg"/>" style="float:left;margin:10px;width:50px;"/>
        Иван<br/><span style="font-weight: normal">Хитро, а ты не такая простая</span><span style="float:right">12:12</span>
    </div>
</div>
    <br/>
    <input type="text" class="form-control" style="float:left; width:500px"/><button>отправить</button>
</div>