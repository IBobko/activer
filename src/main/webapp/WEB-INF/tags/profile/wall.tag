<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" %>
<%@ attribute name="post" required="true" type="ru.todo100.activer.data.MessageData" %>

<div style="overflow:hidden">
    <div style="float:left;margin-right: 20px">
        <img src="${post.from.photo60x60}" style="border-radius: 4px"/>
    </div>
    <span style="font-size:10px">${post.date}</span>
    <br/>
    <strong>${post.from.firstName}&nbsp;${post.from.lastName}</strong>
    <br/>
    ${post.message}
</div>