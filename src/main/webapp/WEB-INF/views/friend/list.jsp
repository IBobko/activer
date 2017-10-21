<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
.manBlock {
margin: 20px 0;
overflow: hidden;
float:left;
}
</style>

<c:forEach items="${friends.friends}" var="friend">
    <div class="manBlock">

        <img src="${staticImages}/${friend.photo60x60}" width="80" style="float:left">
        <div style="margin: 0 100px">
            <a href="<c:url value="/profile/id${friend.id}"/>">${friend.firstName}&nbsp;${friend.lastName}</a><br/>
            <span style="font-weight: normal">${friend.job.post} ${friend.job.work}</span><br/>
            <a class="std-button btn btn-default" style="padding:5px 10px" href="<c:url value="/message?dialog=${friend.id}"/>"><span class="fa fa-envelope"></span>Сообщение</a>
        </div>
    </div>
</c:forEach>