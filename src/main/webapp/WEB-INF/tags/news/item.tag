<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ attribute name="news" required="false" type="ru.todo100.activer.data.NewsData" %>

<%@ taglib prefix="value" uri="/WEB-INF/tlds/DefaultValue.tld" %>

<value:default var="${news}" type="ru.todo100.activer.data.NewsData" varName="news"/>

<div style="margin: 10px 0;overflow: hidden" class="yoxview">
    <p class="status-line" style="margin-bottom:0">
        <a href="<c:url value="/profile/id${news.accountData.id}"/>">${news.accountData.firstName}&nbsp;${news.accountData.lastName}</a>
    </p>
    <img src="${staticFiles}/${news.accountData.photo60x60}." style="margin-top:10px;width:60px;float:left"/>
    <div style="margin: 10px 70px;">
        ${news.text}
    </div>
</div>
