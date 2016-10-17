<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ attribute name="message" required="true" type="ru.todo100.activer.data.MessageData" %>

<div style="margin:20px 0px">
    <span style="font-size:10px">${message.date}</span><br/><strong>${message.from.firstName}&nbsp;${message.from.lastName}</strong><br/> ${message.message}
</div>
