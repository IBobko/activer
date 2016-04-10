
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<style>
    .manBlock {
        margin: 20px 0;
        overflow: hidden;
    }

    .searchBlock{
        margin: 20px 0;
    }

    button[type="submit"] {
        font-size: 10px;
        font-weight: bold;
        color: #fff;
        background-color: #2f40a0;
        text-transform: uppercase;
        border-radius: 30px;
        border: none;
        padding: 10px 15px;
        margin: 0 10px;
    }

    .advancedSearch{
        font-weight: normal;
    }
</style>

<div>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" ${friendsActive}><a onclick="tabClick('')" href="#Friends" aria-controls="friends" role="tab" data-toggle="tab">Друзья</a></li>
        <li role="presentation" ${outActive}><a href="#outFriends" onclick="tabClick('out')"  aria-controls="outFriends" role="tab" data-toggle="tab">Входящие заявки в друзья</a></li>
        <li role="presentation" ${inActive}><a href="#inFriends" onclick="tabClick('in')" aria-controls="inFriends" role="tab" data-toggle="tab">Исходящие заявки в друзья</a></li>
        <li role="presentation" ${searchActive}><a href="#searchFriends" onclick="tabClick('search')" aria-controls="searchFriends" role="tab" data-toggle="tab">Поиск друзей</a></li>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="Friends" >
            <c:forEach items="${friendsData.friends}" var="friend">
                <div class="manBlock">
                    <img src="<c:url value="/resources/img/image-not-found.jpg"/>" width="80" style="float:left">
                    <div style="margin: 0 100px">
                        <a href="<c:url value="/profile/id${friend.id}"/>">${friend.firstName}&nbsp;${friend.lastName}</a><br/>
                        <a style="font-weight: normal" href="<c:url value="/message/${friend.id}"/>">Написать сообщение</a><br/>
                        <a style="font-weight: normal" href="<c:url value="/friend/delete/${friend.id}"/>">Убрать из друзей</a><br/>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div role="tabpanel" class="tab-pane" id="outFriends" onclick="tabClick('out')">
            <c:forEach items="${friendsData.outRequest}" var="friend">
                <div class="manBlock">
                    <img src="<c:url value="/resources/img/image-not-found.jpg"/>" width="80" style="float:left">
                    <div style="margin: 0 100px">
                        <a href="<c:url value="/profile/id${friend.id}"/>">${friend.firstName}&nbsp;${friend.lastName}</a><br/>
                        <a style="font-weight: normal" href="<c:url value="/message/${friend.id}"/>">Написать сообщение</a><br/>
                        <a style="font-weight: normal" href="<c:url value="/friend/add/${friend.id}"/>">Добавить в друзья</a><br/>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div role="tabpanel" class="tab-pane" id="inFriends" onclick="tabClick('in')">
            <c:forEach items="${friendsData.inRequest}" var="friend">
                <div class="manBlock">
                    <img src="<c:url value="/resources/img/image-not-found.jpg"/>" width="80" style="float:left">
                    <div style="margin: 0 100px">
                        <a href="<c:url value="/profile/id${friend.id}"/>">${friend.firstName}&nbsp;${friend.lastName}</a><br/>
                        <a style="font-weight: normal" href="<c:url value="/message/${friend.id}"/>">Написать сообщение</a><br/>
                        <a style="font-weight: normal" href="<c:url value="/friend/delete/${friend.id}"/>">Отменить заявку</a><br/>
                    </div>
                </div>
            </c:forEach>

        </div>

        <div role="tabpanel" class="tab-pane" id="searchFriends">
            <div class="searchBlock">
            <form method="post">
                <input type="text" class="form-control" style="width:400px;float:left" name="t"><button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span>&nbsp;Искать</button>
                <div style="clear: both"/>
                <a role="button" data-toggle="collapse" href="#collapseExample" aria-expanded="false" aria-controls="collapseExample" style="font-weight: normal">
                    Расширенный поиск
                </a>
                <div class="collapse" id="collapseExample">
                    <div class="advancedSearch">
                        ...asdfasdfasdfasdfsadfasd
                    </div>
                </div>
            </form>
            </div>

            <c:forEach items="${searchResult}" var="friend">
                <div class="manBlock">
                    <img src="<c:url value="/resources/img/image-not-found.jpg"/>" width="80" style="float:left">
                    <div style="margin: 0 100px">
                        <a href="<c:url value="/profile/id${friend.id}"/>">${friend.firstName}&nbsp;${friend.lastName}</a><br/>
                        <a style="font-weight: normal" href="<c:url value="/message/${friend.id}"/>">Написать сообщение</a><br/>
                        <a style="font-weight: normal" href="<c:url value="/friend/add/${friend.id}"/>">Добавить в друзья</a><br/>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<script type="text/javascript">

    $('.nav-tabs a[href="#${listType}Friends"]').tab('show');

    function tabClick(tab) {
        window.history.pushState('page2', 'Title', '<c:url value="/friend/"/>' + tab);
    }

</script>