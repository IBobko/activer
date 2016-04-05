<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${pageType == 'auth'}">

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
                <li <c:if test="${pageType == 'settings'}">class="active"</c:if>><a href="<c:url value="/settings"/>">Настройки</a>
                </li>
                <button type="button" onclick="document.location='<c:url value="/logout"/>';"
                        class="btn btn-default navbar-btn navbar-right">Выход
                </button>
            </ul>
        </nav>
    </c:otherwise>
</c:choose>




