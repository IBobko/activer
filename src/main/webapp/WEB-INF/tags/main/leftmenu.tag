<%--suppress XmlPathReference --%>
<%--@elvariable id="friendsData" type="ru.todo100.activer.data.FriendsData"--%>
<%@ tag language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="pageType" required="false" type="java.lang.String" %>

<c:if test="${pageType == 'auth'}">
    <ul class="sidebar-nav">
        <li class="active">
            <a href="<c:url value="/auth"/>">
                <span class="fa fa-user"></span>
                Авторизация
            </a>
        </li>
        <li>
            <a href="<c:url value="/auth/signup"/>">
                <span class="fa fa-smile-o"></span>
                Регистрация
            </a>
        </li>
    </ul>
</c:if>

<c:if test="${pageType == 'dating'}">
    <ul class="sidebar-nav">
        <li class="active">
            <a href="<c:url value="/auth"/>">
                <span class="fa fa-user"></span>
                Знакомства
            </a>
        </li>
        <li>
            <a href="<c:url value="/auth/signup"/>">
                <span class="fa fa-smile-o"></span>
                История знакомств
            </a>
        </li>
    </ul>
</c:if>

<c:if test="${pageType == 'register'}">
    <ul class="sidebar-nav">
        <li>
            <a href="<c:url value="/auth/"/>">
                <span class="fa fa-user"></span>
                Авторизация
            </a>
        </li>
        <li class="active">
            <a href="<c:url value="/auth/signup"/>">
                <span class="fa fa-smile-o"></span>
                Регистрация
            </a>
        </li>
    </ul>
</c:if>

<c:if test="${pageType == 'forgot'}">
    <ul class="sidebar-nav">
        <li>
            <a href="<c:url value="/auth/"/>">
                <span class="fa fa-user"></span>
                Авторизация
            </a>
        </li>
        <li>
            <a href="<c:url value="/auth/signup"/>">
                <span class="fa fa-smile-o"></span>
                Регистрация
            </a>
        </li>
    </ul>
</c:if>

<c:if test="${pageType == null}">
    <ul class="sidebar-nav">
        <li class="active">
            <a href="<c:url value="/profile"/>">
                <span class="fa fa-user"></span>
                Моя страница
            </a>
        </li>
        <li>
            <a href="<c:url value="/friend"/>">
                <span class="fa fa-smile-o"></span>
                Друзья
                <c:if test="${friendsData.outRequest.size() > 0}">
                    <span class="badge">${friendsData.outRequest.size()}</span>
                </c:if>
            </a>
        </li>
        <li>
            <a href="<c:url value="/message"/>">
                <span class="fa fa-envelope-o"></span>
                Сообщения<span class="badge">12</span>
            </a>
        </li>
        <li>
            <a href="<c:url value="/photos/"/>">
                <span class="fa fa-photo"></span>
                Фотографии<span class="badge">3</span>
            </a>
        </li>
        <li>
            <a href="<c:url value="/videos/"/>">
                <span class="fa fa-play-circle"></span>
                Видео
            </a>
        </li>
    </ul>
</c:if>



<c:if test="${pageType == 'friends'}">
    <ul class="sidebar-nav">
        <li>
            <a href="<c:url value="/profile"/>">
                <span class="fa fa-user"></span>
                Моя страница
            </a>
        </li>
        <li class="active">
            <a href="<c:url value="/friend"/>">
                <span class="fa fa-smile-o"></span>
                Друзья

                <c:if test="${friendsData.outRequest.size() > 0}">
                    <span class="badge">${friendsData.outRequest.size()}</span>
                </c:if>

            </a>
        </li>
        <li>
            <a href="<c:url value="/message"/>">
                <span class="fa fa-envelope-o"></span>
                Сообщения<span class="badge">12</span>
            </a>
        </li>
        <li>
            <a href="<c:url value="/photos/"/>">
                <span class="fa fa-photo"></span>
                Фотографии<span class="badge">3</span>
            </a>
        </li>
        <li>
            <a href="<c:url value="/videos/"/>">
                <span class="fa fa-play-circle"></span>
                Видео
            </a>
        </li>
    </ul>
</c:if>


<c:if test="${pageType == 'message'}">
    <ul class="sidebar-nav">
        <li>
            <a href="<c:url value="/profile"/>">
                <span class="fa fa-user"></span>
                Моя страница
            </a>
        </li>
        <li>
            <a href="<c:url value="/friend"/>">
                <span class="fa fa-smile-o"></span>
                Друзья

                <c:if test="${friendsData.outRequest.size() > 0}">
                    <span class="badge">${friendsData.outRequest.size()}</span>
                </c:if>

            </a>
        </li>
        <li class="active">
            <a href="<c:url value="/message"/>">
                <span class="fa fa-envelope-o"></span>
                Сообщения<span class="badge">12</span>
            </a>
        </li>
        <li>
            <a href="<c:url value="/photos/"/>">
                <span class="fa fa-photo"></span>
                Фотографии<span class="badge">3</span>
            </a>
        </li>
        <li>
            <a href="<c:url value="/videos/"/>">
                <span class="fa fa-play-circle"></span>
                Видео
            </a>
        </li>
    </ul>
</c:if>


<c:if test="${pageType == 'photos'}">
    <ul class="sidebar-nav">
        <li>
            <a href="<c:url value="/profile"/>">
                <span class="fa fa-user"></span>
                Моя страница
            </a>
        </li>
        <li>
            <a href="<c:url value="/friend"/>">
                <span class="fa fa-smile-o"></span>
                Друзья

                <c:if test="${friendsData.outRequest.size() > 0}">
                    <span class="badge">${friendsData.outRequest.size()}</span>
                </c:if>

            </a>
        </li>
        <li>
            <a href="<c:url value="/message"/>">
                <span class="fa fa-envelope-o"></span>
                Сообщения<span class="badge">12</span>
            </a>
        </li>
        <li class="active">
            <a href="<c:url value="/photos/"/>">
                <span class="fa fa-photo"></span>
                Фотографии<span class="badge">3</span>
            </a>
        </li>
        <li>
            <a href="<c:url value="/videos/"/>">
                <span class="fa fa-play-circle"></span>
                Видео
            </a>
        </li>
    </ul>
</c:if>




<c:if test="${pageType == 'settings'}">
    <ul class="sidebar-nav">
        <li>
            <div style="background-color: #14EA78;">
            <a href="<c:url value="/profile"/>" style="color:black">
                <span class="fa fa-user"></span>
                Информация</a></div>
                <ul style="list-style:none">
                    <li><a href="<c:url value="/settings"/>">Общее</a></li>
                    <li><a href="<c:url value="/settings/interests"/>">Интересы</a></li>
                    <li><a href="<c:url value="/settings/trips"/>">Путешествия</a></li>
                    <li><a href="<c:url value="/settings/dreams"/>">Мечты</a></li>
                </ul>
        </li>
        <li>
            <a href="<c:url value="/safety"/>">
                <span class="fa fa-smile-o"></span>
                Безопастность
            </a>
        </li>
        <li>
            <a href="<c:url value="/private"/>">
                <span class="fa fa-photo"></span>
                Приватность
            </a>
        </li>
    </ul>
</c:if>


<c:if test="${pageType == 'videos'}">
    <ul class="sidebar-nav">
        <li>
            <a href="<c:url value="/profile"/>">
                <span class="fa fa-user"></span>
                Моя страница
            </a>
        </li>
        <li>
            <a href="<c:url value="/friend"/>">
                <span class="fa fa-smile-o"></span>
                Друзья

                <c:if test="${friendsData.outRequest.size() > 0}">
                    <span class="badge">${friendsData.outRequest.size()}</span>
                </c:if>

            </a>
        </li>
        <li>
            <a href="<c:url value="/message"/>">
                <span class="fa fa-envelope-o"></span>
                Сообщения<span class="badge">12</span>
            </a>
        </li>
        <li>
            <a href="<c:url value="/photos/"/>">
                <span class="fa fa-photo"></span>
                Фотографии<span class="badge">3</span>
            </a>
        </li>
        <li class="active">
            <a href="<c:url value="/videos/"/>">
                <span class="fa fa-play-circle"></span>
                Видео
            </a>
        </li>
    </ul>
</c:if>


<c:if test="${pageType == 'admin/creator'}">
    <ul class="sidebar-nav">
        <li class="active">
            <a href="<c:url value="/admin/creator"/>">
                <span class="glyphicon glyphicon-stats"></span>
                Статистика
            </a>
        </li>
        <li>
            <a href="<c:url value="/admin/gifts"/>">
                <span class="fa fa-gift"></span>
                Подарки
            </a>
        </li>
        <li>
            <a href="<c:url value="/admin/dispute"/>">
                <span class="fa fa-comment"></span>
                Споры
            </a>
        </li>
    </ul>
</c:if>

<c:if test="${pageType == 'admin/gifts'}">
    <ul class="sidebar-nav">
        <li>
            <a href="<c:url value="/admin/creator"/>">
                <span class="glyphicon glyphicon-stats"></span>
                Статистика
            </a>
        </li>
        <li class="active">
            <a href="<c:url value="/admin/gifts"/>">
                <span class="fa fa-gift"></span>
                Подарки
            </a>
        </li>
        <li>
            <a href="<c:url value="/admin/dispute"/>">
                <span class="fa fa-photo"></span>
                Споры
            </a>
        </li>
    </ul>
</c:if>

<c:if test="${pageType == 'admin/gifts/add'}">
    <ul class="sidebar-nav">
        <li>
            <a href="<c:url value="/admin/creator"/>">
                <span class="glyphicon glyphicon-stats"></span>
                Статистика
            </a>
        </li>
        <li class="active">
            <a href="<c:url value="/admin/gifts"/>">
                <span class="fa fa-gift"></span>
                Подарки
            </a>
        </li>
        <li>
            <a href="<c:url value="/admin/dispute"/>">
                <span class="fa fa-photo"></span>
                Споры
            </a>
        </li>
    </ul>
</c:if>

<c:if test="${pageType == 'admin/dispute'}">
    <ul class="sidebar-nav">
        <li>
            <a href="<c:url value="/admin/creator"/>">
                <span class="glyphicon glyphicon-stats"></span>
                Статистика
            </a>
        </li>
        <li>
            <a href="<c:url value="/admin/gifts"/>">
                <span class="fa fa-gift"></span>
                Подарки
            </a>
        </li>
        <li class="active">
            <a href="<c:url value="/admin/dispute"/>">
                <span class="glyphicon glyphicon-comment"></span>
                Споры
            </a>
        </li>
    </ul>
</c:if>

<c:if test="${pageType == 'admin/dispute/add'}">
    <ul class="sidebar-nav">
        <li>
            <a href="<c:url value="/admin/creator"/>">
                <span class="glyphicon glyphicon-stats"></span>
                Статистика
            </a>
        </li>
        <li>
            <a href="<c:url value="/admin/gifts"/>">
                <span class="fa fa-gift"></span>
                Подарки
            </a>
        </li>
        <li class="active">
            <a href="<c:url value="/admin/dispute"/>">
                <span class="glyphicon glyphicon-comment"></span>
                Споры
            </a>
        </li>
    </ul>
</c:if>

