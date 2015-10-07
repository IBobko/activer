<%--suppress ALL --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="mark" tagdir="/WEB-INF/tags/mark" %>

<script src="/resources/sockjs-0.3.4.js"></script>
<script src="/resources/stomp.js"></script>

<script type="text/javascript">
    var stompClient = null;
    function connect() {
        var socket = new SockJS('/wall/${personOfPage.id}');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe('/wall1/${personOfPage.id}', function (greeting) {
                var insertingText = "<hr/><div>"+JSON.parse(greeting.body).text+"</div>";
                $('#wallDiv').prepend(insertingText);
            });
        });
    }

    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
    }

    function sendName() {
        var text = document.getElementById('messageForPost').value;
        stompClient.send("/wall/" + ${personOfPage.id}, {}, JSON.stringify({'text': text}));
    }

    connect();
</script>

<div style="float:right;width:600px" class="well">

    <input type="text" id="messageForPost" value=""/>
    <input type="button" value="опубликовать" onclick="sendName()">

    <div id="wallDiv">
        <c:forEach items="${wall}" var="post">
            <hr/>
            <div>${post.text}</div>
        </c:forEach>
    </div>
</div>

<div style="overflow:hidden">
    <div style="float:left;margin-right:10px;background-color:#fff;border-radius:4px;">
        <img src="${profile.facePhotoUrl}" style="border-radius: 4px"/>

        <div style="font-weight:600;font-size:16px;text-align: center;line-height:2.2">
            ${profile.firstName} ${profile.lastName}
        </div>
    </div>
</div>

<div>
    <a href="<c:url value="/profile/change" />">Редактировать профиль</a>
</div>

<div>
    <a href="<c:url value="/message/${account.id}" />">Отправить сообщение</a>
</div>

Я хочу | <a href="<c:url value="/profile/add_i_want" />">Добавить</a>

<mark:list markType="want" marks="${wants}"/>

Я могу | <a href="<c:url value="/profile/add_i_can" />">Добавить</a>

<mark:list markType="can" marks="${cans}"/>


<div class="well" style="width:400px">
    <strong>Друзья</strong>
    <ul>
        <c:forEach items="${profile.friends}" var="friend">
            <li><a href="<c:url value="/profile/id${friend.id}"/>">${friend.firstName} ${friend.lastName}</a></li>
        </c:forEach>
    </ul>
</div>

