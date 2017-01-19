<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dating" tagdir="/WEB-INF/tags/dating" %>


<!-- Modal -->
<div class="modal fade" id="giftsPopup" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Подарок. Ваш баланс: <span id="balanceInModal">${profile.balance}</span>$</h4>
            </div>
            <div class="modal-body">
                <ul class="gifts-list">
                    <c:forEach items="${gifts}" var="gift">
                        <li>
                            <a href="#" class="giftForAdd" gift-id="${gift.id}"><img src="<c:url value="${staticFiles}/${gift.file}."/>"></a>
                            <br/>
                            Стоимость: 1$
                        </li>
                    </c:forEach>
                </ul>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $('.giftForAdd').click(function() {
        var data = ACTIVER.Global.message;
        data.to = ${flirtData.id};
        data.message = "gift:" + $(this).attr("gift-id");
        data.type = "FLIRT_MESSAGE";
        ACTIVER.Global.submit(data);
        $('#giftsPopup').modal('hide');
    });
</script>


<div style="overflow: hidden">
    <img src="<c:url value="/resources/img/flirt.jpg"/>" style="float: left;margin: 0 30px"/>
    <h4 style="color: #3F51B5;font-weight:bold;">Флирт</h4>
    Вы выбрали "Флирт". У вас есть 5 минут, чтобы познакомиться с собеседником и узнать его поближе.
</div>

<br/><br/><br/>

<div style="overflow: hidden">
    <div style="width:300px;float:left">

        <img src="${staticImages}/${flirtData.opponentAvatar}"
             style="width:117px;float:left;margin:10px"/>
        <h4 style="color: #3F51B5;font-weight:bold;">${flirtData.opponentFirstName} ${flirtData.opponentLastName}</h4>
        <div style="margin:10px 0;font-weight: normal">Ваш собеседник</div>
        ${flirtData.age} лет
        <br/>
        <br/>
        <button class="std-button btn btn-default"><span class="glyphicon glyphicon-ok"></span> Добавить в друзья
        </button>

        <h4 style="color: #3F51B5;font-weight:bold;">Интересы</h4>
        <ul>
            <c:forEach items="${F.interests}" var="interest">
                <li>${interest}</li>
            </c:forEach>
        </ul>
    </div>

    <div style="margin-left:300px;">
        <div id="timer">5:00</div>
        <div style="overflow-y: scroll;border:#b6beec 3px solid;height:400px;" id="flirtMessageDialog">

        </div>
        <div style="margin-top:20px">
            <form id="flirtForm">
                <button type="submit" style="float:right;margin-left:7px" class="std-button btn btn-default"><span class="fa fa-comment"></span>&nbsp;Отправить</button>
                <a data-toggle="modal" data-target="#giftsPopup" class="std-button btn btn-default" style="background-color:#eb1e63;margin-left:15px;font-size: 14px;width:34px;padding:7px 11px;float:right"><span class="fa fa-gift"></span></a>
                <div style="overflow: hidden">
                    <input type="text" name="flirtMessage" class="form-control"/>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript">
    var m = new window.ACTIVER.Dialog.Messages('#flirtMessageTemplate',"",function(result){});

    window.ACTIVER.Global.handlers["FLIRT_MESSAGE"] = function (data) {
        $('#flirtMessageDialog').append(m.getHtmlPost(data));
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

            if (t.getSeconds() < 1) {
                $('#myModal').modal("show");
            }

            $('#timer').html(t.getMinutes() + ":" + sec);

            timer();
        }, 1000);
    }
    timer();
</script>

<textarea style="display:none" id="flirtMessageTemplate">
    <dating:message/>
</textarea>

<div class="modal fade" tabindex="-1" id="myModal" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Время вышло </h4>
            </div>
            <div class="modal-body">
                <p>Вы можете найти нового собеседника, либо продолжить обещние в личном чате.&hellip;</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="continueButton">Продолжить</button>
                <button type="button" class="btn btn-primary" id="newInterlocutorButton">Новый собесебдник</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
    $("#continueButton").click(function(){
        var data = {
            interlocutor: ${paramValues.get("id")[0]},
            agree: "yes"
        };
        $.get("<c:url value="/dating/flirt/agree"/>",data,function(response){
            if (response.action == "wait") {
                alert("Ждем");
            }
            if (response.action == "ok") {
                document.location = window.ACTIVER.context_path + "/message?dialog=" + response.account_id;
            }
        });
    });

    $("#newInterlocutorButton").click(function(){
        var data = {
            interlocutor: ${paramValues.get("id")[0]},
            agree: "no"
        };
        $.get("<c:url value="/dating/flirt/agree"/>",data,function(response){
            if (response.action == "done") {
                $('#pleaseWaitingWindow').modal('show');
                $.get("<c:url value="/dating/search/flirt"/>", function (data) {
                    document.location = "<c:url value="/dating/flirt"/>" + "?id=" + data.trim();
                });
            }
        });
    });
</script>

<div class="modal fade" id="pleaseWaitingWindow">
    <div class="modal-dialog modal-sm">
        <div class="modal-content" style="padding: 30px">
            <img src="<c:url value="/resources/img/progress.gif"/>" style="margin: 0 30px"/>
            <div style="margin-top:30px;">Подождите, пока сервер подберет вам собеседника.</div>
        </div>
    </div>
</div>