<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div>
    <img src="<c:url value="/resources/img/dispute.jpg"/>" style="float: left;margin: 0 30px"/>
    <h4 style="color: #3F51B5;font-weight:bold;">Знакомство "Спор"</h4>
    В начале диалога мы даем вам тему и обозначаем вашу позицию относительно проблемы. У вас есть 7 минут, чтобы доказать собеседнику, что вы правы на все 100%.
    <br/>
    <button id="searchDispute" class="btn btn-default"><span class="glyphicon glyphicon-ok"></span> Выбрать</button>
    <span style="font-weight: normal">Вы учавствовали 58 раз</span>


    <div id="templatePost1" style="display:none">
        <profile:post message="${templatePost}"/>
    </div>

    <script type="text/javascript">
        $('#searchDispute').click(function () {
            <%--$.get("<c:url value="/dating/search"/>", function (data) {--%>
            <%--$('#meetingWindow').append("<div>Собеседник найден, пишите!</div>");--%>
            <%--dialog = data.trim();--%>
            <%--});--%>
            $('#myModal').modal('show')
        });
    </script>
    <div style="text-align: center">
    <h3 style="font-weight: bold; color:orange">Кто круче Batman или Superman</h3>
        <table style="width:300px" align="center">
            <tr>
                <td>ИВАН РЕОН<br/>Защита Бэтмэна</td>
                <td><img src="<c:url value="/resources/img/man.jpg"/>" style="margin:10px"/></td>
                <td><img src="<c:url value="/resources/img/vs.jpg"/>" style="margin:10px"/></td>
                <td><img src="<c:url value="/resources/img/woman.jpg"/>" style="margin:10px;width:117px"/></td>
                <td>КЭТИ ПЕРРИ<br/>Защита супермена</td>
            </tr>
        </table>
        <center>3:27</center>

        <div style="border:#b6beec 3px solid;">

            <div style="overflow: hidden">
                <img src="<c:url value="/resources/img/man.jpg"/>" style="float:left;margin:10px;width:50px;"/>
                Иван<br/><span style="font-weight: normal">Привет, тут даже спорить не о чем, Бэтмен всегда был круче, чем этот гламурный брэнет в красных трусах</span><span style="float:right">12:10</span>
            </div>

            <div style="overflow: hidden">
                <img src="<c:url value="/resources/img/woman.jpg"/>" style="float:left;margin:10px;width:50px;"/>
                Мария<br/><span style="font-weight: normal">Аха, тогда скажи мне бесмертен и уязвим  лишь к криптониту, который не досттать нигде . А на  бэтса  в любой момент может упасть  бетонная плита  и до свидания</span><span style="float:right">12:10</span>
            </div>

            <div style="overflow: hidden">
                <img src="<c:url value="/resources/img/man.jpg"/>" style="float:left;margin:10px;width:50px;"/>
                Иван<br/><span style="font-weight: normal">Ты знаешь, что летучая мушь не так то прост</span><span style="float:right">12:10</span>
            </div>
        </div>


        </div>

    <br/>
    <input type="text" class="form-control" style="float:left; width:500px"/><button>отправить</button>
</div>