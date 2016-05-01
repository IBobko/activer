<%--suppress XmlPathReference --%>
<%--@elvariable id="pageType" type="java.lang.String"--%>
<%--@elvariable id="mainPage" type="java.lang.String"--%>
<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<c:choose>
    <c:when test="${pageType == 'auth'}">

    </c:when>
    <c:when test="${pageType == 'register'}">
        <nav class="navbar navbar-default navbar-static-top my-navbar-top" style="height: 53px">
        </nav>
    </c:when>
    <c:when test="${pageType == 'forgot'}">
        <nav class="navbar navbar-default navbar-static-top my-navbar-top" style="height: 53px">
        </nav>
    </c:when>
    <c:otherwise>
        <!-- Navbar -->
        <nav class="navbar navbar-default navbar-static-top my-navbar-top">
            <ul class="nav navbar-nav">
                <li <c:if test="${pageType == 'news'}">class="active"</c:if>><a
                        href="<c:url value="/news/"/>">Новости</a></li>
                <li <c:if test="${pageType == 'datings'}">class="active"</c:if>><a href="<c:url value="/dating/"/>">Знакомства</a>
                </li>
                <li <c:if test="${pageType == 'gifts'}">class="active"</c:if>><a
                        href="<c:url value="/gifts/"/>">Подарки</a></li>
                <li <c:if test="${mainPage == 'settings'}">class="active"</c:if>><a href="<c:url value="/settings"/>">Настройки</a>
                </li>
                <li <c:if test="${mainPage == 'admin/partner'}">class="active"</c:if>><a
                        href="<c:url value="/admin/partner"/>">Партнер</a></li>
                <li <c:if test="${mainPage == 'admin/creator'}">class="active"</c:if>>
                    <a
                        href="<c:url value="/admin/creator"/>">Создатель</a></li>
                <button onclick="document.location='<c:url value="/logout"/>';"
                        class="btn btn-default navbar-btn navbar-right">Выход
                </button>
            </ul>
        </nav>
    </c:otherwise>
</c:choose>




