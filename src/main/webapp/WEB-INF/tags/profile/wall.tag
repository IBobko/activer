<%@ tag pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="post" required="false" type="ru.todo100.activer.data.MessageData" %>
<%@ taglib prefix="value" uri="/WEB-INF/tlds/DefaultValue.tld" %>


<value:default var="${post}" type="ru.todo100.activer.data.MessageData" varName="post"/>

<div style="overflow:hidden;margin-bottom: 10px;" wall-id="${post.id}">
    <p class="status-line" style="background-color: #f3f3f3">
        <span style="float:right" class="wallRemove"><a href="javascript:deleteWallPost(${post.id})">Удалить</a></span>

        ${post.from.firstName} ${post.from.lastName}<span> - ${post.date}</span>
    </p>
    <p>
        <img style="float:left" src="${staticFiles}/${post.from.photo60x60}.">
        <div style="margin-left:75px;">${post.message}</div>
        <div style="margin-left:75px;" class="yoxview"><li><a href="${staticFiles}/${post.attachmentFile}.jpg">${post.attachmentHtml}</a></li></div>
    </p>
</div>
