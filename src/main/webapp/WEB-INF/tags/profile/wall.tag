<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ attribute name="post" required="false" type="ru.todo100.activer.data.MessageData" %>
<%@ taglib prefix="value" uri="/WEB-INF/tlds/DefaultValue.tld" %>

<value:default var="${post}" type="ru.todo100.activer.data.MessageData" varName="post"/>

<div style="overflow:hidden">
    <p class="status-line" style="background-color: #f3f3f3">
        ${post.from.firstName} ${post.from.lastName}<span> - ${post.date}</span>
    </p>
    <p>
        <img style="float:left" src="${staticFiles}/${post.from.photo60x60}.">
        <div>${post.message}</div>
    </p>
</div>
