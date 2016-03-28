<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/profile" prefix="profile" %>


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
            <input type="submit" id="sendName" class="btn btn-default" style="float:right">Отправить</input>
            <div style="margin-right:96px">
                <input type="text" id="text" class="form-control"/>
            </div>
        </form>
    </div>
</div>


<script type="text/javascript">
    window.ACTIVER.Global.onPRIVATE_MESSAGE = function(data){
        var post = $('#templatePost').html();
        post = post.replace("%firstName%",data.from.firstName);
        post = post.replace("%lastName%",data.from.lastName);
        post = post.replace("%text%",data.message);
        $('#messages').append(post);
        $('#messages').scrollTop($('#messages').height());
    };
    $('#dialogForm').submit(function(){
        var data = window.ACTIVER.Global.message;
        data.to = ${friend.id};
        data.message = $('#text').val();
        data.type = "PRIVATE_MESSAGE";
        $('#text').val('');
        window.ACTIVER.Global.submit(data);
        return false;
    });
    $('#messages').scrollTop($('#messages').height());
</script>
