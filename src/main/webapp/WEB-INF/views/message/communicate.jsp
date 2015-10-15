<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="profile" tagdir="/WEB-INF/tags/profile" %>

<script src="/resources/sockjs-0.3.4.js"></script>
<script src="/resources/stomp.js"></script>
<script src="/resources/js/activer.messaging.js"></script>

<script type="text/javascript">
window.ACTIVER.Data = {
    profile: {
        id: '${myProfile.id}'
    },
    browseProfile: {
        id: '${friend.id}'
    }
};
</script>

<div id="templatePost" style="display:none">
    <profile:post message="${templatePost}"/>
</div>
<div>
    <div id="conversationDiv">
        <div class="well" id="messages" style="margin-top:10px;overflow-y: scroll; height:600px;">
            <c:forEach items="${lastMessages}" var="message">
                <profile:post message="${message}"/>
            </c:forEach>
        </div>
        <form id="dialogForm">
            <button id="sendName" class="btn btn-default" style="float:right">Отправить</button>
            <div style="margin-right:96px">
                <input type="text" id="text" class="form-control"/>
            </div>
        </form>
    </div>
</div>

