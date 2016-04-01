<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags/profile" prefix="profile" %>


<div id="templatePost" style="display:none">
    <profile:post message="${templatePost}"/>
</div>

<input id="search" type="button" value="Найти несчастного"/>

<script type="text/javascript">
    $('#search').click(function () {
        $.get("<c:url value="/dating/search"/>", function (data) {
            $('#meetingWindow').append("<div>Собеседник найден, пишите!</div>");
            dialog = data.trim();
        });

    });
</script>
<br/><br/><br/>

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