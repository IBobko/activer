<%--suppress ALL --%><%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %><%@ taglib prefix="mark" tagdir="/WEB-INF/tags/mark" %><%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %><style>    h5 {        font-size: 14px;        margin-top: 10px;        margin-bottom: 10px;        font-family: inherit;        font-weight: 500;        line-height: 1.1;        color: inherit;    }</style><script type="text/javascript">    var stompClient = null;    function connect() {        var socket = new SockJS(window.ACTIVER.context_path + '/wall/${personOfPage.id}');        stompClient = Stomp.over(socket);        stompClient.connect({}, function (frame) {            stompClient.subscribe('/wall1/${personOfPage.id}', function (greeting) {                var post = JSON.parse(greeting.body);                if (post.text == null || post.text == undefined || post.text == "") {                    return;                }                console.log("START/ READ THIS!!!");                console.log(post);                console.log("TEMPLATE");                console.log();                var template = $("#wallTemplate").html();                console.log("TEMPLATE");                console.log(template);                template = template.replace(/%date%/gi,post.date);                template = template.replace(/%firstName%/gi,post.sender.firstName);                template = template.replace(/%lastName%/gi,post.sender.lastName);                template = template.replace(/%text%/gi,post.text);                template = template.replace(/%photo60x60%/gi,post.sender.photo60x60);                $('#wallDiv').prepend(template);            });        });    }    function disconnect() {        if (stompClient != null) {            stompClient.disconnect();        }    }    function sendName() {        var text = document.getElementById('messageForPost').value;        stompClient.send(window.ACTIVER.context_path +"/wall/" + ${personOfPage.id}, {}, JSON.stringify({'text': text}));    }    connect();    $(function(){        $('#mainPhoto').mouseover(function(){            console            $('#changePhoto').animate({                marginTop:"-40px",                padding:"10px 10px",                height:"40px",                opacity: 0.7            });            console.log("xxx");        });        $('#mainPhoto,#changePhoto').mouseout(function(e){            console.log(e);            if (e.relatedTarget.id != "changePhoto" && e.relatedTarget.id != "mainPhoto" && e.relatedTarget.tagName != "A") {                $('#changePhoto').animate({                    marginTop: "0px",                    padding:"0px 10px",                    height: "0px",                    opacity: 0                });            }        });        $('#myModal').on('shown.bs.modal', function () {            $('#photoCancel').unbind("click.one");            $('#photoCancel').bind("click.one",function(){                $.get("/photo/cancel");            });            $('#photoSave').unbind("click.one");            $('#photoSave').bind("click.one",function(){                $('#myModal').modal('hide')            });        });    });</script><link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/photo-upload.css"/>"><script type="text/javascript" src="<c:url value="/resources/js/activer.photo-upload.js"/>"></script><!-- Modal --><div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">    <div class="modal-dialog" style="width: 1000px" role="document">        <div class="modal-content">            <div class="modal-header">                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>                <h4 class="modal-title" id="myModalLabel">Загрузите свою фотографию</h4>            </div>            <div class="modal-body">                <div class="btn btn-default file-btn">                    <input type="file">                    <div>Выбрать</div>                </div>                <div style="clear: both"></div>                <div class="container">                    <div class="content">                        <div class="component">                            <div class="overlay">                                <div class="overlay-inner">                                </div>                            </div>                            <img class="resize-image">                        </div>                    </div>                </div>                <script src="<c:url value="resources/js/component.js"/>"></script>            </div>            <div class="modal-footer">                <button type="button" class="btn btn-default" id="photoCancel" data-dismiss="modal">Отменить</button>                <button type="button" class="btn btn-primary js-crop" id="photoSave">Сохранить&nbsp;<img class="icon-crop" src="<c:url value="/resources/img/crop.svg"/>"></button>            </div>        </div>    </div></div><div style="float:left;width:250px">    <div style="overflow:hidden">        <div style="float:left;margin-right:10px;background-color:#fff;border-radius:4px;">            <img id="mainPhoto" src="${profile.facePhotoUrl}" style="border-radius: 4px"/>            <div id="changePhoto" style="width:250px;opacity:0;text-align:center;position:absolute;color:white;background-color:black;padding:0 10px;height:0px"  data-toggle="modal" data-target="#myModal"><a href="#">Сменить фото</a></div>            <div style="font-weight:600;font-size:16px;text-align: center;line-height:2.2">                ${profile.firstName} ${profile.lastName}            </div>        </div>    </div>    <div>        <a href="<c:url value="/profile/change" />">Редактировать профиль</a>    </div>    <div>        <a href="<c:url value="/message/${account.id}" />">Отправить сообщение</a>    </div>    <div class="well" style="width:250px">        <h5>Друзья</h5>        <ul>            <c:forEach items="${profile.friends}" var="friend">                <li><a href="<c:url value="/profile/id${friend.id}"/>">${friend.firstName} ${friend.lastName}</a></li>            </c:forEach>        </ul>    </div>    <div style="width:250px;overflow: hidden">        Я хочу | <a href="<c:url value="/profile/add_i_want" />">Добавить</a>        <mark:list markType="want" marks="${wants}"/>    </div>    <div style="width:250px">        Я могу | <a href="<c:url value="/profile/add_i_can" />">Добавить</a>        <mark:list markType="can" marks="${cans}"/>    </div></div><div style="margin-left:260px;width:600px">    <div class="well">        <form id="wallForm">            <button type="submit" id="sendName" class="btn btn-default" style="float:right">Опубликовать            </button>            <div style="margin-right:120px">                <input type="text" id="messageForPost" class="form-control"/>            </div>        </form>        <div id="wallDiv">            <c:forEach items="${wall}" var="post">                <hr/>                <profile:wall post="${post}"/>            </c:forEach>        </div>    </div>    <script>        $("#wallForm").submit(function(){            sendName();            return false;        });    </script></div><div id="wallTemplate" style="display: none">    <hr/>    <profile:wall post="${wallTemplate}"/></div>