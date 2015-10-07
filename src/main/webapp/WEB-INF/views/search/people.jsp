<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>Поиск по людям</h3>

<form:form method="get" commandName="searchForm" action="${searchUrl}">
    <form:input cssClass="form-control" placeholder="Введите имя" path="s"/>
    <button type="submit" class="btn btn-default">Искать</button>
</form:form>


<c:forEach items="${searchResult}" var="account">
    <div><a href="<c:url value="/profile/id${account.id}"/>">${account.firstName} ${account.lastName}</a> - <a href="<c:url value="/profile/${account.id}/add"/>">Добавить в друзья</a></div>
</c:forEach>