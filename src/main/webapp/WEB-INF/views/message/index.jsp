<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${friends}" var="friend">
    <div>${friend.firstName} ${friend.lastName} - <a href="<c:url value="/message/${friend.id}"/>">Написать сообщение</a></div>
</c:forEach>
