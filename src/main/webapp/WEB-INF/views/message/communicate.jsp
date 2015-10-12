<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="/resources/sockjs-0.3.4.js"></script>
<script src="/resources/stomp.js"></script>
<script type="text/javascript">
    /** @TODO Пихнуть все это в какуе-нибудь красивую библиотеку **/
    var stompClient = null;

    function connect() {
        var socket = new SockJS('/message1/${myProfile.id}');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/message2/${myProfile.id}', function (greeting) {
                showGreeting(JSON.parse(greeting.body));
            });
        });
    }

    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
    }

    function sendName() {
        var text = document.getElementById('text').value;
        stompClient.send("/message1/" + ${myProfile.id}, {}, JSON.stringify({'accountTo': '${friend.id}', 'text': text}));
    }

    function showGreeting(message) {
        $('#messages').append(
                "<hr/><div>" + message.date
                + "<br/>" + message.sender.firstName + " " + message.sender.lastName + "<br/>"
                + message.text + "</div>");
        $('#messages').scrollTop($('#messages').height());
    }
    connect();



</script>

<div>
    <div id="conversationDiv">
        <form id="dialogForm">
            <button id="sendName" class="btn btn-default" style="float:right">Отправить</button>
            <div style="margin-right:96px">
                <input type="text" id="text" class="form-control"/>
            </div>
        </form>

        <div class="well" id="messages" style="margin-top:10px;overflow-y: scroll; height:600px;">
            <c:forEach items="${lastMessages}" var="message">
                <hr/>
                <div>
                        ${message.date}
                    <br/>${message.sender.firstName} ${message.sender.lastName}<br/>
                        ${message.text}</div>
            </c:forEach>
        </div>
    </div>
</div>

<script>
    $('#messages').scrollTop($('#messages').height());
    $("#dialogForm").submit(function(){
        sendName();
        return false;
    });
</script>