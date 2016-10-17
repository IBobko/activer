<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="value" uri="/WEB-INF/tlds/DefaultValue.tld" %>
<%@ attribute name="message" required="false" type="ru.todo100.activer.data.MessageData" %>

<value:default var="${message}" type="ru.todo100.activer.data.MessageData" varName="message"/>
<div style="overflow: hidden;text-align: left;margin:10px">
    <img src="${staticFiles}/${message.from.photo60x60}." style="float:left;margin-right:10px;width:50px;"/>
    <div style="margin-bottom: 5px;">
        ${message.from.firstName}&nbsp;${message.from.lastName}
        <span style="float:right">${message.date}</span>
    </div>
    <span style="font-weight: normal">${message.message}</span>
</div>
