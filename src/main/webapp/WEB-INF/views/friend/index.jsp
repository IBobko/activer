<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div class="well">
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#friends" aria-controls="friends" role="tab" data-toggle="tab">Друзья</a></li>
        <li role="presentation"><a href="#outFriends" aria-controls="outFriends" role="tab" data-toggle="tab">Исходящие заявки в друзья</a></li>
        <li role="presentation"><a href="#inFriends" aria-controls="inFriends" role="tab" data-toggle="tab">Входящие заявки в друзья</a></li>
        <li role="presentation"><a href="#searchFriends" aria-controls="searchFriends" role="tab" data-toggle="tab">Поиск друзей</a></li>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="friends">

        </div>
        <div role="tabpanel" class="tab-pane" id="outFriends">
            <c:forEach items="${friends}" var="friend">
                <hr/>
                <div><img src="${friend.photo60x60}">
                    <a href="<c:url value="/profile/id${friend.id}"/>">${friend.firstName}&nbsp;${friend.lastName}</a>
                </div>
            </c:forEach>
        </div>
        <div role="tabpanel" class="tab-pane" id="inFriends">...</div>

        <div role="tabpanel" class="tab-pane" id="searchFriends">
            <form method="post">
            <input type="text" name="t"><input type="submit"></submit>
            </form>

            <c:forEach items="${searchResult}" var="item">
                <hr>
                <div>
                    <a href="<c:url value="/profile/id${item.id}"/>">${item.firstName}&nbsp;${item.lastName}</a>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
