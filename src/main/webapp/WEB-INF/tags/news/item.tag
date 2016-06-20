<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ attribute name="news" required="true" type="ru.todo100.activer.data.NewsData" %>

<div style="margin: 10px 0;overflow: hidden" class="yoxview">
    <p class="status-line" style="margin-bottom:0">
        <a href="<c:url value="/profile/id${news.accountData.id}"/>">${news.accountData.firstName}&nbsp;${news.accountData.lastName}</a>
    </p>
    <img src="${staticFiles}/${news.accountData.photo60x60}." style="margin-top:10px;width:60px;float:left"/>
    <div style="margin: 10px 70px;">
        <c:if test="${news.type == 'WALL'}">
            <strong>написал:</strong><br/><span style="font-weight: normal;">${news.text}</span>
        </c:if>
        <c:if test="${news.type == 'AVATAR' && not empty news.photoShowing}">
            обновил аватар<br/>
            <a href="${staticFiles}/${news.photoShowing}.jpg"><img alt="First" title="First image"  style="width:200px" src="${staticFiles}/${news.photoThumbnail}."/></a>
        </c:if>
        <c:if test="${news.type == 'PHOTO'  && not empty news.photoShowing}}">
            добавил фото
            <br/>
                <a href="${staticFiles}/${news.photoShowing}.jpg"><img alt="First" title="First image"  style="width:200px" src="${staticFiles}/${news.photoThumbnail}."/></a>
        </c:if>
        <c:if test="${news.type == 'BOUGHT_PREMIUM'}">
            купил премиум аккаунт
        </c:if>
    </div>
</div>
