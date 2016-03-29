<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${dialogs}" var="dialog">
    <div>${dialog.owner.lastName} ${dialog.owner.firstName} - ${dialog.lastMessage.text} <a href="<c:url value="/message/${dialog.owner.id}"/>">Продолжить беседу</a></div>
    <br/>
</c:forEach>
