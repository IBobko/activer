<%--suppress XmlPathReference --%>
<%--@elvariable id="friendsData" type="ru.todo100.activer.data.FriendsData"--%>
<%--@elvariable id="currentProfileData" type="ru.todo100.activer.data.ProfileData"--%>

<%@ tag language="java" trimDirectiveWhitespaces="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="pageType" required="false" type="java.lang.String" %>

<c:if test="${empty sessionScope.get('unreadMessages') ||sessionScope.get('unreadMessages') == 0 }">
    <c:set var="displayCountNotRead" value="style='display:none'"/>
</c:if>

<c:set var="countNotRead" value="<span ${displayCountNotRead} id='globalUnreadMessageBadge' class='badge'>${sessionScope.get('unreadMessages')}</span>"/>

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

<c:if test="${pageType == null or mainPage == 'profile'}">
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
                Сообщения${countNotRead}
            </a>
        </li>
        <li>
            <a href="<c:url value="/photos"/>">
                <span class="fa fa-photo"></span>
                Фотографии
            </a>
        </li>
        <li>
            <a href="<c:url value="/videos?accountId=${currentProfileData.id}"/>">
                <span class="fa fa-play-circle"></span>
                Видео
            </a>
        </li>
        <li>
            <a href="<c:url value="/premium"/>" style="color:orange">
	      <span style="color:orange" class="fa fa-diamond"></span>	
	      Купить аккаунт</a>
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
                Сообщения${countNotRead}
            </a>
        </li>
        <li>
            <a href="<c:url value="/photos"/>">
                <span class="fa fa-photo"></span>
                Фотографии
            </a>
        </li>
        <li>
            <a href="<c:url value="/videos?accountId=${currentProfileData.id}"/>">
                <span class="fa fa-play-circle"></span>
                Видео
            </a>
        </li>
        <li>
            <a href="<c:url value="/premium"/>" style="color:orange">
	      <span style="color:orange" class="fa fa-diamond"></span>	
	      Купить аккаунт</a>
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
                Сообщения${countNotRead}
            </a>
        </li>
        <li>
            <a href="<c:url value="/photos"/>">
                <span class="fa fa-photo"></span>
                Фотографии
            </a>
        </li>
        <li>
            <a href="<c:url value="/videos?accountId=${currentProfileData.id}"/>">
                <span class="fa fa-play-circle"></span>
                Видео
            </a>
        </li>
        <li>
            <a href="<c:url value="/premium"/>" style="color:orange">
	      <span style="color:orange" class="fa fa-diamond"></span>	
	      Купить аккаунт</a>
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
                Сообщения${countNotRead}
            </a>
        </li>
        <li class="active">
            <a href="<c:url value="/photos"/>">
                <span class="fa fa-photo"></span>
                Фотографии
            </a>
        </li>
        <li>
            <a href="<c:url value="/videos?accountId=${currentProfileData.id}"/>">
                <span class="fa fa-play-circle"></span>
                Видео
            </a>
        </li>
        <li>
            <a href="<c:url value="/premium"/>" style="color:orange">
	      <span style="color:orange" class="fa fa-diamond"></span>	
	      Купить аккаунт</a>
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
                Сообщения${countNotRead}
            </a>
        </li>
        <li>
            <a href="<c:url value="/photos"/>">
                <span class="fa fa-photo"></span>
                Фотографии
            </a>
        </li>
        <li>
            <a href="<c:url value="/videos?accountId=${currentProfileData.id}"/>">
                <span class="fa fa-play-circle"></span>
                Видео
            </a>
        </li>
        <li>
            <a href="<c:url value="/premium"/>" style="color:orange">
	      <span style="color:orange" class="fa fa-diamond"></span>	
	      Купить аккаунт</a>
        </li>
    </ul>
</c:if>

<c:if test="${pageType == 'admin/partner'}">
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
                Сообщения${countNotRead}
            </a>
        </li>
        <li>
            <a href="<c:url value="/photos"/>">
                <span class="fa fa-photo"></span>
                Фотографии
            </a>
        </li>
        <li>
            <a href="<c:url value="/videos?accountId=${currentProfileData.id}"/>">
                <span class="fa fa-play-circle"></span>
                Видео
            </a>
        </li>
        <li>
            <a href="<c:url value="/premium"/>" style="color:orange">
	      <span style="color:orange" class="fa fa-diamond"></span>	
	      Купить аккаунт</a>
        </li>
    </ul>
</c:if>

<%--@elvariable id="SettingsInfoMenu" type="java.lang.String"--%>
<%--@elvariable id="SettingsInfoInterestMenu" type="java.lang.String"--%>
<%--@elvariable id="SettingsInfoTripMenu" type="java.lang.String"--%>
<%--@elvariable id="SettingsInfoDreamMenu" type="java.lang.String"--%>
<%--@elvariable id="SettingsSafetyMenu" type="java.lang.String"--%>
<%--@elvariable id="SettingsPrivateMenu" type="java.lang.String"--%>
<%--@elvariable id="SettingsThemeMenu" type="java.lang.String"--%>
<c:if test="${mainPage == 'settings'}">
    <ul class="sidebar-nav">
        <li>
            <div>
                <a href="<c:url value="/settings"/>">
                    <span class="fa fa-user"></span>
                    Информация
                </a>
            </div>
            <ul style="list-style:none">
                <li ${SettingsInfoMenu}><a href="<c:url value="/settings"/>">Общее</a></li>
                <li ${SettingsInfoInterestMenu}><a href="<c:url value="/settings/interests"/>">Интересы</a></li>
                <li ${SettingsInfoTripMenu}><a href="<c:url value="/settings/trips"/>">Путешествия</a></li>
                <li ${SettingsInfoDreamMenu}><a href="<c:url value="/settings/dreams"/>">Мечты</a></li>
            </ul>
        </li>
        <li ${SettingsSafetyMenu}>
            <a href="<c:url value="/safety"/>">
                <span class="fa fa-key"></span>
                Безопастность
            </a>
        </li>
        <li ${SettingsPrivateMenu}>
            <a href="<c:url value="/private"/>">
                <span class="fa fa-eye"></span>
                Приватность
            </a>
        </li>
        <li ${SettingsThemeMenu}>
            <a href="<c:url value="/themes"/>">
                <span class="fa fa-star"></span>
                Тема профиля
            </a>
        </li>
    </ul>
</c:if>

<%--@elvariable id="mainPage" type="java.lang.String"--%>
<%--@elvariable id="disputeMenu" type="java.lang.String"--%>
<%--@elvariable id="giftsMenu" type="java.lang.String"--%>
<%--@elvariable id="creatorMenu" type="java.lang.String"--%>
<%--@elvariable id="balanceMenu" type="java.lang.String"--%>
<c:if test="${pageType == 'admin/creator' || pageType == 'admin/gifts' || pageType == 'admin/dispute' || pageType == 'admin/balance'}">
    <ul class="sidebar-nav">
        <li ${creatorMenu}>
            <a href="<c:url value="/admin/creator"/>">
                <span class="fa fa-line-chart"></span>
                Статистика
            </a>
        </li>
        <li ${giftsMenu}>
            <a href="<c:url value="/admin/gifts"/>">
                <span class="fa fa-gift"></span>
                Подарки
            </a>
        </li>
        <li ${disputeMenu}>
            <a href="<c:url value="/admin/dispute"/>">
                <span class="glyphicon glyphicon-comment"></span>
                Споры
            </a>
        </li>
        <li ${balanceMenu}>
            <a href="<c:url value="/admin/balance"/>">
                <span class="glyphicon glyphicon-usd"></span>
                Баланс
            </a>
        </li>
    </ul>
</c:if>


