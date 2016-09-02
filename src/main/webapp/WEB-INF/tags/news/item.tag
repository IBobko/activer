<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ attribute name="news" required="false" type="ru.todo100.activer.data.NewsData" %>

<%@ taglib prefix="value" uri="/WEB-INF/tlds/DefaultValue.tld" %>

<value:default var="${news}" type="ru.todo100.activer.data.NewsData" varName="news"/>

<div style="overflow:hidden;margin-bottom: 10px;" wall-id="${post.id}">
    <p class="status-line" style="background-color: #f3f3f3">
        ${news.accountData.firstName} ${news.accountData.lastName}<span> - ${news.date}</span>
    </p>
    <p>
        <img style="float:left" src="${staticFiles}/${news.accountData.photo60x60}."/>

        <div style="margin-left:75px;font-weight:normal">
        <c:choose>
            <c:when test="${news.type.equalsIgnoreCase('wall')}">
                ${news.text}<br/><br/>
                <c:forEach items="${news.attachments}" var="attachment">
                    <img style="width:200px;" src="${staticFiles}/${attachment.url}."/>
                </c:forEach>
            </c:when>
            <c:otherwise>
                ${news.text}
                <%--<div style="margin-left:75px;" class="yoxview">--%>
                <%--<a href="${staticFiles}/${post.attachmentFile}.jpg">${post.attachmentHtml}</a>--%>
                <%--</div>--%>
            </c:otherwise>
        </c:choose>
        </div>

    </p>
</div>
